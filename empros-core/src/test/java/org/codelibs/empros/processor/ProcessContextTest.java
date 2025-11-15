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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.codelibs.empros.event.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProcessContextTest {

    private List<Event> eventList;
    private ProcessListener listener;
    private ProcessContext context;

    @BeforeEach
    public void setUp() {
        eventList = new ArrayList<>();
        Map<String, Object> data1 = new HashMap<>();
        data1.put("key1", "value1");
        eventList.add(new Event(data1));

        Map<String, Object> data2 = new HashMap<>();
        data2.put("key2", "value2");
        eventList.add(new Event(data2));

        listener = new ProcessListener() {
            @Override
            public void onFinish(ProcessContext context) {
                // Default listener
            }
            @Override
            public void onFailure(Throwable t) {
                // Default listener
            }
        };

        context = new ProcessContext(eventList, listener);
    }

    @Test
    public void testConstructor() {
        assertNotNull(context);
        assertEquals(eventList, context.getIncomingEventList());
        assertEquals(0, context.getProcessed());
        assertNull(context.getResponse());
        assertNull(context.getProcessingEventList());
    }

    @Test
    public void testAddNumOfProcessedEvents() {
        assertEquals(0, context.getProcessed());

        context.addNumOfProcessedEvents(5);
        assertEquals(5, context.getProcessed());

        context.addNumOfProcessedEvents(3);
        assertEquals(8, context.getProcessed());

        context.addNumOfProcessedEvents(0);
        assertEquals(8, context.getProcessed());
    }

    @Test
    public void testSetAndGetResponse() {
        assertNull(context.getResponse());

        String response = "test response";
        context.setResponse(response);
        assertEquals(response, context.getResponse());

        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("status", "success");
        context.setResponse(mapResponse);
        assertEquals(mapResponse, context.getResponse());
    }

    @Test
    public void testSetAndGetProcessingEventList() {
        assertNull(context.getProcessingEventList());

        List<Event> processingList = new ArrayList<>();
        processingList.add(eventList.get(0));
        context.setProcessingEventList(processingList);

        assertEquals(processingList, context.getProcessingEventList());
        assertEquals(1, context.getProcessingEventList().size());
    }

    @Test
    public void testAddFailure() {
        assertEquals(0, context.getFailures().length);

        Throwable t1 = new RuntimeException("Error 1");
        context.addFailure(t1);
        assertEquals(1, context.getFailures().length);
        assertEquals(t1, context.getFailures()[0]);

        Throwable t2 = new IllegalArgumentException("Error 2");
        context.addFailure(t2);
        assertEquals(2, context.getFailures().length);
    }

    @Test
    public void testStartAndFinish() throws InterruptedException {
        EventProcessor processor1 = new TestEventProcessor();
        EventProcessor processor2 = new TestEventProcessor();

        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicBoolean finished = new AtomicBoolean(false);

        ProcessListener testListener = new ProcessListener() {
            @Override
            public void onFinish(ProcessContext context) {
                finished.set(true);
                latch.countDown();
            }
            @Override
            public void onFailure(Throwable t) {
            }
        };

        ProcessContext testContext = new ProcessContext(eventList, testListener);

        // Start processor1
        testContext.start(processor1);
        assertFalse(finished.get());

        // Start processor2
        testContext.start(processor2);
        assertFalse(finished.get());

        // Finish processor1 - should not trigger onFinish yet
        testContext.finish(processor1);
        assertFalse(finished.get());

        // Finish processor2 - should trigger onFinish
        testContext.finish(processor2);
        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertTrue(finished.get());
    }

    @Test
    public void testGetOriginal() {
        // Test with no parent
        assertEquals(context, context.getOriginal());

        // Test with cloned contexts (parent-child relationship)
        ProcessContext child1 = context.clone();
        assertEquals(context, child1.getOriginal());

        ProcessContext child2 = child1.clone();
        assertEquals(context, child2.getOriginal());

        ProcessContext child3 = child2.clone();
        assertEquals(context, child3.getOriginal());
    }

    @Test
    public void testClone() {
        context.addNumOfProcessedEvents(10);
        context.setResponse("original response");

        List<Event> processingList = new ArrayList<>();
        processingList.add(eventList.get(0));
        context.setProcessingEventList(processingList);

        ProcessContext cloned = context.clone();

        assertNotNull(cloned);
        assertNotSame(context, cloned);

        // These should be shared
        assertEquals(context.getIncomingEventList(), cloned.getIncomingEventList());
        assertEquals(context.getResponse(), cloned.getResponse());

        // Processed counter should be reset in clone
        assertEquals(0, cloned.getProcessed());
        assertEquals(10, context.getProcessed());

        // Parent should be set
        assertEquals(context, cloned.getOriginal());

        // ProcessingEventList should be copied
        assertNotNull(cloned.getProcessingEventList());
        assertEquals(context.getProcessingEventList().size(), cloned.getProcessingEventList().size());
    }

    @Test
    public void testCloneWithNullProcessingEventList() {
        ProcessContext cloned = context.clone();

        assertNotNull(cloned);
        assertNull(cloned.getProcessingEventList());
        assertNull(context.getProcessingEventList());
    }

    @Test
    public void testFailureQueueConcurrency() throws InterruptedException {
        final int numThreads = 10;
        final int errorsPerThread = 100;
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch doneLatch = new CountDownLatch(numThreads);

        for (int i = 0; i < numThreads; i++) {
            final int threadId = i;
            new Thread(() -> {
                try {
                    startLatch.await();
                    for (int j = 0; j < errorsPerThread; j++) {
                        context.addFailure(new RuntimeException("Thread-" + threadId + " Error-" + j));
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    doneLatch.countDown();
                }
            }).start();
        }

        startLatch.countDown();
        assertTrue(doneLatch.await(5, TimeUnit.SECONDS));

        assertEquals(numThreads * errorsPerThread, context.getFailures().length);
    }

    @Test
    public void testProcessedCounterConcurrency() throws InterruptedException {
        final int numThreads = 10;
        final int incrementsPerThread = 1000;
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch doneLatch = new CountDownLatch(numThreads);

        for (int i = 0; i < numThreads; i++) {
            new Thread(() -> {
                try {
                    startLatch.await();
                    for (int j = 0; j < incrementsPerThread; j++) {
                        context.addNumOfProcessedEvents(1);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    doneLatch.countDown();
                }
            }).start();
        }

        startLatch.countDown();
        assertTrue(doneLatch.await(5, TimeUnit.SECONDS));

        assertEquals(numThreads * incrementsPerThread, context.getProcessed());
    }

    // Helper class for testing
    private static class TestEventProcessor implements EventProcessor {
        @Override
        public void process(ProcessContext context, ProcessorListener listener) {
            // Minimal implementation
        }
    }
}
