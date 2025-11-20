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
        lenient().doReturn(servletContext).when(servletConfig).getServletContext();
        lenient().doReturn("/tmp/db/").when(servletContext).getRealPath(anyString());
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
        doReturn("/tmp/h2db").when(servletConfig).getInitParameter("baseDir");

        // Should complete without throwing exception even if H2 is not available
        assertDoesNotThrow(() -> servlet.init(servletConfig));
    }

    @Test
    public void testInit_WithDefaultBaseDir() throws ServletException {
        // Test initialization with default base directory
        doReturn(null).when(servletConfig).getInitParameter("baseDir");
        doReturn("/app/WEB-INF/db/").when(servletContext).getRealPath("/WEB-INF/db/");

        assertDoesNotThrow(() -> servlet.init(servletConfig));
    }

    @Test
    public void testInit_WithCustomBaseDir() throws ServletException {
        // Test initialization with custom base directory
        doReturn("/custom/db/path").when(servletConfig).getInitParameter("baseDir");

        assertDoesNotThrow(() -> servlet.init(servletConfig));
    }

    @Test
    public void testInit_WithTcpAllowOthers() throws ServletException {
        // Test initialization with tcpAllowOthers enabled
        doReturn("true").when(servletConfig).getInitParameter("tcpAllowOthers");

        assertDoesNotThrow(() -> servlet.init(servletConfig));
    }

    @Test
    public void testInit_WithTcpPort() throws ServletException {
        // Test initialization with custom TCP port
        int port = findAvailablePort();
        doReturn(String.valueOf(port)).when(servletConfig).getInitParameter("tcpPort");

        assertDoesNotThrow(() -> servlet.init(servletConfig));
    }

    @Test
    public void testInit_WithTcpSSL() throws ServletException {
        // Test initialization with TCP SSL enabled
        doReturn("true").when(servletConfig).getInitParameter("tcpSSL");

        assertDoesNotThrow(() -> servlet.init(servletConfig));
    }

    @Test
    public void testInit_WithTcpPassword() throws ServletException {
        // Test initialization with TCP password
        doReturn("testPassword").when(servletConfig).getInitParameter("tcpPassword");

        assertDoesNotThrow(() -> servlet.init(servletConfig));
    }

    @Test
    public void testInit_WithAllParameters() throws ServletException {
        // Test initialization with all parameters
        int port = findAvailablePort();
        doReturn("/custom/db").when(servletConfig).getInitParameter("baseDir");
        doReturn("true").when(servletConfig).getInitParameter("tcpAllowOthers");
        doReturn(String.valueOf(port)).when(servletConfig).getInitParameter("tcpPort");
        doReturn("true").when(servletConfig).getInitParameter("tcpSSL");
        doReturn("password").when(servletConfig).getInitParameter("tcpPassword");

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
        doReturn("/tmp/h2db").when(servletConfig).getInitParameter("baseDir");
        servlet.init(servletConfig);

        // Should not throw exception even if server.stop() fails
        assertDoesNotThrow(() -> servlet.destroy());
    }

    @Test
    public void testInit_ErrorHandling() throws ServletException {
        // Test that init handles errors gracefully
        doReturn(null).when(servletContext).getRealPath(anyString());
        doReturn(null).when(servletConfig).getInitParameter("baseDir");

        // Even with null paths, should handle gracefully
        assertDoesNotThrow(() -> servlet.init(servletConfig));
    }
}
