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
package org.codelibs.empros.processor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HttpProcessorTest {

    private HttpProcessor httpProcessor;

    @BeforeEach
    public void setUp() {
        httpProcessor = new TestHttpProcessor();
    }

    @AfterEach
    public void tearDown() {
        if (httpProcessor != null) {
            httpProcessor.destroy();
        }
    }

    @Test
    public void testDefaultTimeouts() {
        assertEquals(3000, httpProcessor.socketTimeout);
        assertEquals(3000, httpProcessor.connectTimeout);
    }

    @Test
    public void testSetSocketTimeout() {
        httpProcessor.setSocketTimeout(5000);
        assertEquals(5000, httpProcessor.socketTimeout);
        assertEquals(3000, httpProcessor.connectTimeout); // Should remain unchanged
    }

    @Test
    public void testSetConnectTimeout() {
        httpProcessor.setConnectTimeout(10000);
        assertEquals(3000, httpProcessor.socketTimeout); // Should remain unchanged
        assertEquals(10000, httpProcessor.connectTimeout);
    }

    @Test
    public void testSetBothTimeouts() {
        httpProcessor.setSocketTimeout(7000);
        httpProcessor.setConnectTimeout(8000);
        assertEquals(7000, httpProcessor.socketTimeout);
        assertEquals(8000, httpProcessor.connectTimeout);
    }

    @Test
    public void testDestroyDoesNotThrowException() {
        // Test that destroy() handles IOException gracefully
        assertDoesNotThrow(() -> httpProcessor.destroy());

        // Should be safe to call destroy multiple times
        assertDoesNotThrow(() -> httpProcessor.destroy());
    }

    // Test implementation of HttpProcessor that doesn't require actual HTTP setup
    private static class TestHttpProcessor extends HttpProcessor {
        @Override
        protected void initHttpClient() {
            // Skip actual HTTP client initialization for testing
            // Just verify that timeout fields are accessible
        }
    }
}
