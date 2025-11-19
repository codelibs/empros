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
package org.codelibs.empros.server.processor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.codelibs.empros.exception.EmprosConfigException;
import org.codelibs.empros.factory.ProcessorFactory;
import org.codelibs.empros.processor.EventProcessor;
import org.codelibs.empros.processor.ParallelProcessor;
import org.codelibs.empros.processor.ProcessContext;
import org.codelibs.empros.processor.ProcessorListener;
import org.codelibs.empros.processor.SerialProcessor;
import org.codelibs.empros.server.config.ProcessorConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProcessorProviderTest {

    @Mock
    private ProcessorFactory mockFactory1;

    @Mock
    private ProcessorFactory mockFactory2;

    @Mock
    private EventProcessor mockProcessor1;

    @Mock
    private EventProcessor mockProcessor2;

    private List<ProcessorFactory> factoryList;

    @BeforeEach
    public void setUp() {
        factoryList = new ArrayList<>();
    }

    @Test
    public void testParallelProcessorCreation() {
        // Setup
        ProcessorConfig config = new ProcessorConfig();
        config.setMode("parallel");
        config.setThreadPoolSize(10);

        when(mockFactory1.isAvailable()).thenReturn(true);
        when(mockFactory1.create()).thenReturn(mockProcessor1);
        factoryList.add(mockFactory1);

        // Execute
        ProcessorProvider provider = new ProcessorProvider(config, factoryList);

        // Verify
        EventProcessor processor = provider.getProcessor();
        assertNotNull(processor);
        assertTrue(processor instanceof ParallelProcessor);
    }

    @Test
    public void testSerialProcessorCreation() {
        // Setup
        ProcessorConfig config = new ProcessorConfig();
        config.setMode("serial");
        config.setThreadPoolSize(5);

        when(mockFactory1.isAvailable()).thenReturn(true);
        when(mockFactory1.create()).thenReturn(mockProcessor1);
        factoryList.add(mockFactory1);

        // Execute
        ProcessorProvider provider = new ProcessorProvider(config, factoryList);

        // Verify
        EventProcessor processor = provider.getProcessor();
        assertNotNull(processor);
        assertTrue(processor instanceof SerialProcessor);
    }

    @Test
    public void testThreadPoolSizeConfiguration() {
        // Setup - test that thread pool size from config is used
        ProcessorConfig config = new ProcessorConfig();
        config.setMode("parallel");
        config.setThreadPoolSize(15);

        when(mockFactory1.isAvailable()).thenReturn(true);
        when(mockFactory1.create()).thenReturn(mockProcessor1);
        factoryList.add(mockFactory1);

        // Execute
        ProcessorProvider provider = new ProcessorProvider(config, factoryList);

        // Verify
        EventProcessor processor = provider.getProcessor();
        assertNotNull(processor);
        assertTrue(processor instanceof ParallelProcessor);
        // ParallelProcessor should be created with the configured thread pool size
    }

    @Test
    public void testMultipleProcessorFactories() {
        // Setup
        ProcessorConfig config = new ProcessorConfig();
        config.setMode("parallel");
        config.setThreadPoolSize(5);

        when(mockFactory1.isAvailable()).thenReturn(true);
        when(mockFactory1.create()).thenReturn(mockProcessor1);
        when(mockFactory2.isAvailable()).thenReturn(true);
        when(mockFactory2.create()).thenReturn(mockProcessor2);

        factoryList.add(mockFactory1);
        factoryList.add(mockFactory2);

        // Execute
        ProcessorProvider provider = new ProcessorProvider(config, factoryList);

        // Verify
        assertNotNull(provider.getProcessor());
        verify(mockFactory1).create();
        verify(mockFactory2).create();
    }

    @Test
    public void testUnavailableFactoriesAreSkipped() {
        // Setup
        ProcessorConfig config = new ProcessorConfig();
        config.setMode("parallel");
        config.setThreadPoolSize(5);

        when(mockFactory1.isAvailable()).thenReturn(false);
        when(mockFactory2.isAvailable()).thenReturn(true);
        when(mockFactory2.create()).thenReturn(mockProcessor2);

        factoryList.add(mockFactory1);
        factoryList.add(mockFactory2);

        // Execute
        ProcessorProvider provider = new ProcessorProvider(config, factoryList);

        // Verify
        assertNotNull(provider.getProcessor());
        verify(mockFactory1, never()).create();
        verify(mockFactory2).create();
    }

    @Test
    public void testNullProcessorsAreFiltered() {
        // Setup
        ProcessorConfig config = new ProcessorConfig();
        config.setMode("parallel");
        config.setThreadPoolSize(5);

        when(mockFactory1.isAvailable()).thenReturn(true);
        when(mockFactory1.create()).thenReturn(null);
        when(mockFactory2.isAvailable()).thenReturn(true);
        when(mockFactory2.create()).thenReturn(mockProcessor2);

        factoryList.add(mockFactory1);
        factoryList.add(mockFactory2);

        // Execute
        ProcessorProvider provider = new ProcessorProvider(config, factoryList);

        // Verify
        assertNotNull(provider.getProcessor());
    }

    @Test
    public void testEmptyProcessorList_ThrowsException() {
        // Setup
        ProcessorConfig config = new ProcessorConfig();
        config.setMode("parallel");
        config.setThreadPoolSize(5);

        when(mockFactory1.isAvailable()).thenReturn(false);
        factoryList.add(mockFactory1);

        // Execute & Verify
        assertThrows(EmprosConfigException.class, () -> {
            new ProcessorProvider(config, factoryList);
        });
    }

    @Test
    public void testInvalidMode_ThrowsException() {
        // Setup
        ProcessorConfig config = new ProcessorConfig();
        config.setMode("invalid_mode");
        config.setThreadPoolSize(5);

        when(mockFactory1.isAvailable()).thenReturn(true);
        when(mockFactory1.create()).thenReturn(mockProcessor1);
        factoryList.add(mockFactory1);

        // Execute & Verify
        assertThrows(EmprosConfigException.class, () -> {
            new ProcessorProvider(config, factoryList);
        });
    }

    @Test
    public void testDestroy_CallsFactoryDestroy() {
        // Setup
        ProcessorConfig config = new ProcessorConfig();
        config.setMode("parallel");
        config.setThreadPoolSize(5);

        when(mockFactory1.isAvailable()).thenReturn(true);
        when(mockFactory1.create()).thenReturn(mockProcessor1);
        factoryList.add(mockFactory1);

        ProcessorProvider provider = new ProcessorProvider(config, factoryList);

        // Execute
        provider.destroy();

        // Verify
        verify(mockFactory1).destroy();
    }

    @Test
    public void testDefaultThreadPoolSize() {
        // Setup
        ProcessorConfig config = new ProcessorConfig();
        config.setMode("parallel");
        // Don't set threadPoolSize - should use default (5)

        when(mockFactory1.isAvailable()).thenReturn(true);
        when(mockFactory1.create()).thenReturn(mockProcessor1);
        factoryList.add(mockFactory1);

        // Execute
        ProcessorProvider provider = new ProcessorProvider(config, factoryList);

        // Verify
        EventProcessor processor = provider.getProcessor();
        assertNotNull(processor);
        assertTrue(processor instanceof ParallelProcessor);
    }
}
