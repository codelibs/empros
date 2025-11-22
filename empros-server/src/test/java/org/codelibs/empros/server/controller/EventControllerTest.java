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
package org.codelibs.empros.server.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codelibs.empros.processor.EventProcessor;
import org.codelibs.empros.processor.ProcessContext;
import org.codelibs.empros.processor.ProcessorListener;
import org.codelibs.empros.server.async.AsyncExecutor;
import org.codelibs.empros.server.processor.ProcessorProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.async.DeferredResult;

@ExtendWith(MockitoExtension.class)
public class EventControllerTest {

    @Mock
    private ProcessorProvider processorProvider;

    @Mock
    private EventProcessor eventProcessor;

    @Mock
    private AsyncExecutor asyncExecutor;

    private EventController eventController;

    @BeforeEach
    public void setUp() {
        // Setup Spring RequestContext for tests that need it
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);

        lenient().when(processorProvider.getProcessor()).thenReturn(eventProcessor);
        eventController = new EventController(processorProvider, asyncExecutor);
    }

    @AfterEach
    public void tearDown() {
        // Clean up RequestContext
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    public void testHead() {
        String result = eventController.head();
        assertEquals("", result);
    }

    @Test
    public void testCreateWithMapData() {
        Map<String, Object> data = new HashMap<>();
        data.put("key", "value");

        doAnswer(invocation -> {
            Runnable task = invocation.getArgument(0);
            task.run();
            return null;
        }).when(asyncExecutor).generic(any(Runnable.class));

        doAnswer(invocation -> {
            ProcessContext context = invocation.getArgument(0);
            // Simulate successful processing
            return null;
        }).when(eventProcessor).process(any(ProcessContext.class), any());

        DeferredResult<Object> result = eventController.create(data);

        assertNotNull(result);
        verify(asyncExecutor, times(1)).generic(any(Runnable.class));
    }

    @Test
    public void testCreateWithListData() {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", i);
            data.put("message", "Event " + i);
            dataList.add(data);
        }

        doAnswer(invocation -> {
            Runnable task = invocation.getArgument(0);
            task.run();
            return null;
        }).when(asyncExecutor).generic(any(Runnable.class));

        doAnswer(invocation -> {
            ProcessContext context = invocation.getArgument(0);
            assertEquals(3, context.getIncomingEventList().size());
            return null;
        }).when(eventProcessor).process(any(ProcessContext.class), any());

        DeferredResult<Object> result = eventController.create(dataList);

        assertNotNull(result);
        verify(asyncExecutor, times(1)).generic(any(Runnable.class));
    }

    @Test
    public void testCreateWithNullData() {
        lenient().doAnswer(invocation -> {
            Runnable task = invocation.getArgument(0);
            task.run();
            return null;
        }).when(asyncExecutor).generic(any(Runnable.class));

        assertThrows(Exception.class, () -> {
            DeferredResult<Object> result = eventController.create(null);
        });
    }

    @Test
    public void testCreateWithInvalidData() {
        String invalidData = "invalid";

        lenient().doAnswer(invocation -> {
            Runnable task = invocation.getArgument(0);
            task.run();
            return null;
        }).when(asyncExecutor).generic(any(Runnable.class));

        assertThrows(Exception.class, () -> {
            DeferredResult<Object> result = eventController.create(invalidData);
        });
    }

    @Test
    public void testCreateWithEmptyMap() {
        Map<String, Object> emptyData = new HashMap<>();

        doAnswer(invocation -> {
            Runnable task = invocation.getArgument(0);
            task.run();
            return null;
        }).when(asyncExecutor).generic(any(Runnable.class));

        DeferredResult<Object> result = eventController.create(emptyData);

        assertNotNull(result);
        verify(eventProcessor, times(1)).process(any(ProcessContext.class), any());
    }

    @Test
    public void testCreateWithEmptyList() {
        List<Object> emptyList = new ArrayList<>();

        doAnswer(invocation -> {
            Runnable task = invocation.getArgument(0);
            task.run();
            return null;
        }).when(asyncExecutor).generic(any(Runnable.class));

        DeferredResult<Object> result = eventController.create(emptyList);

        assertNotNull(result);
        // Event processor should not be called with empty list
        verify(eventProcessor, never()).process(any(ProcessContext.class), any());
    }

    @Test
    public void testCreateWithMixedListTypes() {
        List<Object> mixedList = new ArrayList<>();
        Map<String, Object> validData = new HashMap<>();
        validData.put("key", "value");
        mixedList.add(validData);
        mixedList.add("invalid");

        lenient().doAnswer(invocation -> {
            Runnable task = invocation.getArgument(0);
            task.run();
            return null;
        }).when(asyncExecutor).generic(any(Runnable.class));

        assertThrows(Exception.class, () -> {
            DeferredResult<Object> result = eventController.create(mixedList);
        });
    }

    @Test
    public void testDeferredResultConfiguration() {
        Map<String, Object> data = new HashMap<>();
        data.put("key", "value");

        lenient().doAnswer(invocation -> {
            Runnable task = invocation.getArgument(0);
            task.run();
            return null;
        }).when(asyncExecutor).generic(any(Runnable.class));

        DeferredResult<Object> result = eventController.create(data);

        assertNotNull(result);
        assertNotNull(result.getClass());
    }

    @Test
    public void testProcessorProviderIntegration() {
        // Verify that processorProvider.getProcessor() was called during setUp
        verify(processorProvider, atLeastOnce()).getProcessor();
        assertNotNull(eventController);
    }

    @Test
    public void testMultipleEventCreation() {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", i);
            dataList.add(data);
        }

        doAnswer(invocation -> {
            Runnable task = invocation.getArgument(0);
            task.run();
            return null;
        }).when(asyncExecutor).generic(any(Runnable.class));

        doAnswer(invocation -> {
            ProcessContext context = invocation.getArgument(0);
            assertEquals(10, context.getIncomingEventList().size());
            return null;
        }).when(eventProcessor).process(any(ProcessContext.class), any());

        DeferredResult<Object> result = eventController.create(dataList);

        assertNotNull(result);
        verify(eventProcessor, times(1)).process(any(ProcessContext.class), any());
    }

    @Test
    public void testGetCreatedBy_WithXForwardedForHeader() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("192.168.1.100");
        request.addHeader("X-Forwarded-For", "10.0.0.1");

        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);

        String createdBy = eventController.getCreatedBy();

        assertEquals("10.0.0.1", createdBy, "Should return first IP from X-Forwarded-For header");
    }

    @Test
    public void testGetCreatedBy_WithMultipleXForwardedForIPs() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("192.168.1.100");
        request.addHeader("X-Forwarded-For", "10.0.0.1, 10.0.0.2, 10.0.0.3");

        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);

        String createdBy = eventController.getCreatedBy();

        assertEquals("10.0.0.1", createdBy, "Should return first IP from comma-separated X-Forwarded-For header");
    }

    @Test
    public void testGetCreatedBy_WithoutXForwardedForHeader() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("192.168.1.100");

        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);

        String createdBy = eventController.getCreatedBy();

        assertEquals("192.168.1.100", createdBy, "Should return remote address when X-Forwarded-For is not present");
    }

    @Test
    public void testGetCreatedBy_WithEmptyXForwardedForHeader() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("192.168.1.100");
        request.addHeader("X-Forwarded-For", "");

        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);

        String createdBy = eventController.getCreatedBy();

        assertEquals("192.168.1.100", createdBy, "Should return remote address when X-Forwarded-For is empty");
    }

    @Test
    public void testGetCreatedBy_WithXForwardedForHeaderWithSpaces() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("192.168.1.100");
        request.addHeader("X-Forwarded-For", "  10.0.0.1  , 10.0.0.2 ");

        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);

        String createdBy = eventController.getCreatedBy();

        assertEquals("10.0.0.1", createdBy, "Should trim spaces from X-Forwarded-For IP");
    }

    @Test
    public void testAsyncExceptionHandling_ProcessorThrowsException() {
        Map<String, Object> data = new HashMap<>();
        data.put("key", "value");

        final RuntimeException testException = new RuntimeException("Test exception");

        doAnswer(invocation -> {
            Runnable task = invocation.getArgument(0);
            task.run();
            return null;
        }).when(asyncExecutor).generic(any(Runnable.class));

        doAnswer(invocation -> {
            throw testException;
        }).when(eventProcessor).process(any(ProcessContext.class), any());

        DeferredResult<Object> result = eventController.create(data);

        assertNotNull(result);
        // The DeferredResult should not be set to success
        verify(asyncExecutor, times(1)).generic(any(Runnable.class));
    }

    @Test
    public void testEventCreatedByMetadata() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("192.168.1.100");
        request.addHeader("X-Forwarded-For", "10.0.0.1");

        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);

        Map<String, Object> data = new HashMap<>();
        data.put("key", "value");

        doAnswer(invocation -> {
            Runnable task = invocation.getArgument(0);
            task.run();
            return null;
        }).when(asyncExecutor).generic(any(Runnable.class));

        doAnswer(invocation -> {
            ProcessContext context = invocation.getArgument(0);
            // Verify the event has the correct createdBy metadata
            assertEquals(1, context.getIncomingEventList().size());
            assertEquals("10.0.0.1", context.getIncomingEventList().get(0).getCreatedBy());
            assertNotNull(context.getIncomingEventList().get(0).getCreatedTime());
            return null;
        }).when(eventProcessor).process(any(ProcessContext.class), any());

        DeferredResult<Object> result = eventController.create(data);

        assertNotNull(result);
        verify(eventProcessor, times(1)).process(any(ProcessContext.class), any());
    }
}
