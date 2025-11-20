/*
 * Copyright 2012-2020 CodeLibs Project and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.codelibs.empros.server.servlet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.net.ServerSocket;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class H2ConfigServletTest {

    @Mock
    private ServletConfig servletConfig;

    @Mock
    private ServletContext servletContext;

    private H2ConfigServlet servlet;

    @BeforeEach
    public void setUp() {
        servlet = new H2ConfigServlet();
        lenient().when(servletConfig.getServletContext()).thenReturn(servletContext);
        lenient().when(servletContext.getRealPath(anyString())).thenReturn("/tmp/db/");
    }

    /**
     * Finds an available TCP port for testing.
     * @return an available port number
     */
    private int findAvailablePort() {
        try (ServerSocket socket = new ServerSocket(0)) {
            socket.setReuseAddress(true);
            return socket.getLocalPort();
        } catch (IOException e) {
            throw new RuntimeException("Failed to find available port", e);
        }
    }

    @Test
    public void testInit_WithoutH2Class() throws ServletException {
        // Test initialization when H2 class is not available
        // This should not throw an exception but log appropriately
        when(servletConfig.getInitParameter("baseDir")).thenReturn("/tmp/h2db");

        // Should complete without throwing exception even if H2 is not available
        assertDoesNotThrow(() -> servlet.init(servletConfig));
    }

    @Test
    public void testInit_WithDefaultBaseDir() throws ServletException {
        // Test initialization with default base directory
        when(servletConfig.getInitParameter("baseDir")).thenReturn(null);
        when(servletContext.getRealPath("/WEB-INF/db/")).thenReturn("/app/WEB-INF/db/");

        assertDoesNotThrow(() -> servlet.init(servletConfig));
    }

    @Test
    public void testInit_WithCustomBaseDir() throws ServletException {
        // Test initialization with custom base directory
        when(servletConfig.getInitParameter("baseDir")).thenReturn("/custom/db/path");

        assertDoesNotThrow(() -> servlet.init(servletConfig));
    }

    @Test
    public void testInit_WithTcpAllowOthers() throws ServletException {
        // Test initialization with tcpAllowOthers enabled
        when(servletConfig.getInitParameter("tcpAllowOthers")).thenReturn("true");

        assertDoesNotThrow(() -> servlet.init(servletConfig));
    }

    @Test
    public void testInit_WithTcpPort() throws ServletException {
        // Test initialization with custom TCP port
        int port = findAvailablePort();
        when(servletConfig.getInitParameter("tcpPort")).thenReturn(String.valueOf(port));

        assertDoesNotThrow(() -> servlet.init(servletConfig));
    }

    @Test
    public void testInit_WithTcpSSL() throws ServletException {
        // Test initialization with TCP SSL enabled
        when(servletConfig.getInitParameter("tcpSSL")).thenReturn("true");

        assertDoesNotThrow(() -> servlet.init(servletConfig));
    }

    @Test
    public void testInit_WithTcpPassword() throws ServletException {
        // Test initialization with TCP password
        when(servletConfig.getInitParameter("tcpPassword")).thenReturn("testPassword");

        assertDoesNotThrow(() -> servlet.init(servletConfig));
    }

    @Test
    public void testInit_WithAllParameters() throws ServletException {
        // Test initialization with all parameters
        int port = findAvailablePort();
        when(servletConfig.getInitParameter("baseDir")).thenReturn("/custom/db");
        when(servletConfig.getInitParameter("tcpAllowOthers")).thenReturn("true");
        when(servletConfig.getInitParameter("tcpPort")).thenReturn(String.valueOf(port));
        when(servletConfig.getInitParameter("tcpSSL")).thenReturn("true");
        when(servletConfig.getInitParameter("tcpPassword")).thenReturn("password");

        assertDoesNotThrow(() -> servlet.init(servletConfig));
    }

    @Test
    public void testDestroy_WithoutServer() {
        // Test destroy when server was not initialized
        assertDoesNotThrow(() -> servlet.destroy());
    }

    @Test
    public void testDestroy_WithServer() throws ServletException {
        // Test destroy after initialization
        when(servletConfig.getInitParameter("baseDir")).thenReturn("/tmp/h2db");
        servlet.init(servletConfig);

        // Should not throw exception even if server.stop() fails
        assertDoesNotThrow(() -> servlet.destroy());
    }

    @Test
    public void testInit_ErrorHandling() throws ServletException {
        // Test that init handles errors gracefully
        when(servletContext.getRealPath(anyString())).thenReturn(null);
        when(servletConfig.getInitParameter("baseDir")).thenReturn(null);

        // Even with null paths, should handle gracefully
        assertDoesNotThrow(() -> servlet.init(servletConfig));
    }
}
