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
package org.codelibs.empros.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.codelibs.empros.event.Event;
import org.codelibs.empros.processor.EventProcessor;
import org.codelibs.empros.processor.ProcessContext;
import org.codelibs.empros.processor.ProcessListener;
import org.codelibs.empros.processor.ProcessorListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProcessorUtilTest {

    private List<Event> eventList;
    private ProcessListener listener;
    private ProcessContext context;
    private EventProcessor processor;

    @BeforeEach
    public void setUp() {
        eventList = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        data.put("key", "value");
        eventList.add(new Event(data));

        listener = new ProcessListener() {
            @Override
            public void onFinish(ProcessContext context) {
            }
            @Override
            public void onFailure(Throwable t) {
            }
        };

        context = new ProcessContext(eventList, listener);
        processor = new TestEventProcessor();
    }

    @Test
    public void testGetCurrentEventList_WithProcessingEventList() {
        List<Event> processingList = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        data.put("processing", "event");
        processingList.add(new Event(data));

        context.setProcessingEventList(processingList);

        List<Event> result = ProcessorUtil.getCurrentEventList(context);
        assertEquals(processingList, result);
        assertNotEquals(eventList, result);
    }

    @Test
    public void testGetCurrentEventList_WithoutProcessingEventList() {
        List<Event> result = ProcessorUtil.getCurrentEventList(context);
        assertEquals(eventList, result);
    }

    @Test
    public void testFinish_WithListener() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicBoolean success = new AtomicBoolean(false);

        ProcessorListener processorListener = new ProcessorListener() {
            @Override
            public void onSuccess(ProcessContext context) {
                success.set(true);
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                latch.countDown();
            }
        };

        context.start(processor);
        ProcessorUtil.finish(context, processor, processorListener);

        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertTrue(success.get());
    }

    @Test
    public void testFinish_WithNullListener() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        ProcessListener testListener = new ProcessListener() {
            @Override
            public void onFinish(ProcessContext context) {
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
            }
        };

        ProcessContext testContext = new ProcessContext(eventList, testListener);
        testContext.start(processor);

        // Should not throw exception even with null listener
        assertDoesNotThrow(() -> ProcessorUtil.finish(testContext, processor, null));

        assertTrue(latch.await(1, TimeUnit.SECONDS));
    }

    @Test
    public void testFinish_ListenerThrowsException() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        ProcessListener testListener = new ProcessListener() {
            @Override
            public void onFinish(ProcessContext context) {
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
            }
        };

        ProcessContext testContext = new ProcessContext(eventList, testListener);
        testContext.start(processor);

        ProcessorListener failingListener = new ProcessorListener() {
            @Override
            public void onSuccess(ProcessContext context) {
                throw new RuntimeException("Test exception");
            }

            @Override
            public void onFailure(Throwable t) {
            }
        };

        // Should handle exception and still finish
        assertDoesNotThrow(() -> ProcessorUtil.finish(testContext, processor, failingListener));

        assertTrue(latch.await(1, TimeUnit.SECONDS));
    }

    @Test
    public void testFail_WithListener() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<Throwable> failureRef = new AtomicReference<>();

        ProcessorListener processorListener = new ProcessorListener() {
            @Override
            public void onSuccess(ProcessContext context) {
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                failureRef.set(t);
                latch.countDown();
            }
        };

        context.start(processor);
        RuntimeException testException = new RuntimeException("Test failure");
        ProcessorUtil.fail(context, processor, processorListener, testException);

        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertEquals(testException, failureRef.get());
        assertEquals(1, context.getFailures().length);
        assertEquals(testException, context.getFailures()[0]);
    }

    @Test
    public void testFail_WithNullListener() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        ProcessListener testListener = new ProcessListener() {
            @Override
            public void onFinish(ProcessContext context) {
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
            }
        };

        ProcessContext testContext = new ProcessContext(eventList, testListener);
        testContext.start(processor);

        RuntimeException testException = new RuntimeException("Test failure");

        // Should not throw exception even with null listener
        assertDoesNotThrow(() -> ProcessorUtil.fail(testContext, processor, null, testException));

        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertEquals(1, testContext.getFailures().length);
    }

    @Test
    public void testFail_WithNullProcessor() {
        RuntimeException testException = new RuntimeException("Test failure");

        // Should handle null processor gracefully
        assertDoesNotThrow(() -> ProcessorUtil.fail(context, null, null, testException));

        assertEquals(1, context.getFailures().length);
        assertEquals(testException, context.getFailures()[0]);
    }

    @Test
    public void testFail_ListenerThrowsException() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        ProcessListener testListener = new ProcessListener() {
            @Override
            public void onFinish(ProcessContext context) {
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
            }
        };

        ProcessContext testContext = new ProcessContext(eventList, testListener);
        testContext.start(processor);

        ProcessorListener failingListener = new ProcessorListener() {
            @Override
            public void onSuccess(ProcessContext context) {
            }

            @Override
            public void onFailure(Throwable t) {
                throw new RuntimeException("Listener exception");
            }
        };

        RuntimeException testException = new RuntimeException("Test failure");

        // Should handle exception and still finish
        assertDoesNotThrow(() -> ProcessorUtil.fail(testContext, processor, failingListener, testException));

        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertEquals(1, testContext.getFailures().length);
    }

    // Helper class for testing
    private static class TestEventProcessor implements EventProcessor {
        @Override
        public void process(ProcessContext context, ProcessorListener listener) {
            // Minimal implementation
        }
    }
}
