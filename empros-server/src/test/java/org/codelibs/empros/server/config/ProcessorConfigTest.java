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
package org.codelibs.empros.server.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProcessorConfigTest {

    private ProcessorConfig config;

    @BeforeEach
    public void setUp() {
        config = new ProcessorConfig();
    }

    @Test
    public void testDefaultValues() {
        // Test default values
        assertNull(config.getMode());
        assertEquals(5, config.getThreadPoolSize());
    }

    @Test
    public void testSetMode() {
        config.setMode("parallel");
        assertEquals("parallel", config.getMode());

        config.setMode("serial");
        assertEquals("serial", config.getMode());
    }

    @Test
    public void testSetThreadPoolSize() {
        config.setThreadPoolSize(10);
        assertEquals(10, config.getThreadPoolSize());

        config.setThreadPoolSize(100);
        assertEquals(100, config.getThreadPoolSize());

        config.setThreadPoolSize(1);
        assertEquals(1, config.getThreadPoolSize());
    }

    @Test
    public void testThreadPoolSizeWithZero() {
        // Test that zero can be set (though it may not be practical)
        config.setThreadPoolSize(0);
        assertEquals(0, config.getThreadPoolSize());
    }

    @Test
    public void testThreadPoolSizeWithNegative() {
        // Test that negative values can be set (though they should be validated elsewhere)
        config.setThreadPoolSize(-1);
        assertEquals(-1, config.getThreadPoolSize());
    }

    @Test
    public void testConfigurationPropertyBinding() {
        // This test verifies that the config can be used with Spring Boot's
        // @ConfigurationProperties annotation

        // Set values as Spring Boot would do
        config.setMode("parallel");
        config.setThreadPoolSize(15);

        // Verify values are correctly stored
        assertEquals("parallel", config.getMode());
        assertEquals(15, config.getThreadPoolSize());
    }

    @Test
    public void testThreadPoolSizeTypoFix() {
        // This test documents that the property name should be "thread_pool_size"
        // not "thead_pool_size" (which was a typo in the original configuration)

        // When Spring Boot reads from YAML with key "thread_pool_size",
        // it should call setThreadPoolSize()
        config.setThreadPoolSize(20);
        assertEquals(20, config.getThreadPoolSize());
    }

    @Test
    public void testModeValues() {
        // Test common mode values
        String[] validModes = {"parallel", "serial"};

        for (String mode : validModes) {
            config.setMode(mode);
            assertEquals(mode, config.getMode());
        }
    }

    @Test
    public void testNullMode() {
        // Test that null mode can be set
        config.setMode(null);
        assertNull(config.getMode());
    }

    @Test
    public void testEmptyMode() {
        // Test that empty string mode can be set
        config.setMode("");
        assertEquals("", config.getMode());
    }

    @Test
    public void testLargeThreadPoolSize() {
        // Test with large thread pool size
        config.setThreadPoolSize(1000);
        assertEquals(1000, config.getThreadPoolSize());
    }
}
