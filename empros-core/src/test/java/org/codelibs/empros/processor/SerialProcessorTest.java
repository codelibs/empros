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
import java.util.concurrent.atomic.AtomicInteger;

import org.codelibs.empros.event.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SerialProcessorTest {

    private List<Event> eventList;
    private ProcessListener listener;

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
            public void onFailure(Throwable t) {
            }
        };
    }

    @Test
    public void testEmptyProcessorList() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);

        ProcessListener testListener = new ProcessListener() {
            @Override
            public void onFinish(ProcessContext context) {
                latch.countDown();
            }
            public void onFailure(Throwable t) {
            }
        };

        ProcessContext context = new ProcessContext(eventList, testListener);
        SerialProcessor processor = new SerialProcessor(new ArrayList<>());

        processor.process(context, new ProcessorListener() {
            @Override
            public void onSuccess(ProcessContext context) {
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                fail("Should not fail");
            }
        });

        assertTrue(latch.await(2, TimeUnit.SECONDS));
    }

    @Test
    public void testSingleProcessor() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(3);
        final AtomicInteger processOrder = new AtomicInteger(0);

        ProcessListener testListener = new ProcessListener() {
            @Override
            public void onFinish(ProcessContext context) {
                latch.countDown();
            }
            public void onFailure(Throwable t) {
            }
        };

        ProcessContext context = new ProcessContext(eventList, testListener);

        List<EventProcessor> processors = new ArrayList<>();
        processors.add(new TestEventProcessor(processOrder, 1));

        SerialProcessor serialProcessor = new SerialProcessor(processors);

        serialProcessor.process(context, new ProcessorListener() {
            @Override
            public void onSuccess(ProcessContext context) {
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                fail("Should not fail");
            }
        });

        assertTrue(latch.await(2, TimeUnit.SECONDS));
        assertEquals(1, processOrder.get());
    }

    @Test
    public void testMultipleProcessorsInOrder() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(4);
        final AtomicInteger processOrder = new AtomicInteger(0);
        final List<Integer> executionOrder = new ArrayList<>();

        ProcessListener testListener = new ProcessListener() {
            @Override
            public void onFinish(ProcessContext context) {
                latch.countDown();
            }
            public void onFailure(Throwable t) {
            }
        };

        ProcessContext context = new ProcessContext(eventList, testListener);

        List<EventProcessor> processors = new ArrayList<>();
        processors.add(new TestEventProcessor(processOrder, 1, executionOrder));
        processors.add(new TestEventProcessor(processOrder, 2, executionOrder));
        processors.add(new TestEventProcessor(processOrder, 3, executionOrder));

        SerialProcessor serialProcessor = new SerialProcessor(processors);

        serialProcessor.process(context, new ProcessorListener() {
            @Override
            public void onSuccess(ProcessContext context) {
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                fail("Should not fail");
            }
        });

        assertTrue(latch.await(2, TimeUnit.SECONDS));
        assertEquals(3, processOrder.get());
        assertEquals(3, executionOrder.size());
        // Verify execution order: should be 1, 2, 3
        assertEquals(1, executionOrder.get(0));
        assertEquals(2, executionOrder.get(1));
        assertEquals(3, executionOrder.get(2));
    }

    @Test
    public void testProcessorFailure() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);
        final RuntimeException testException = new RuntimeException("Test failure");

        ProcessListener testListener = new ProcessListener() {
            @Override
            public void onFinish(ProcessContext context) {
                latch.countDown();
            }
            public void onFailure(Throwable t) {
            }
        };

        ProcessContext context = new ProcessContext(eventList, testListener);

        List<EventProcessor> processors = new ArrayList<>();
        processors.add(new FailingEventProcessor(testException));

        SerialProcessor serialProcessor = new SerialProcessor(processors);

        serialProcessor.process(context, new ProcessorListener() {
            @Override
            public void onSuccess(ProcessContext context) {
                fail("Should not succeed");
            }

            @Override
            public void onFailure(Throwable t) {
                assertEquals(testException, t);
                latch.countDown();
            }
        });

        assertTrue(latch.await(2, TimeUnit.SECONDS));
    }

    @Test
    public void testFirstProcessorFailsStopsChain() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);
        final RuntimeException testException = new RuntimeException("First processor failed");
        final AtomicInteger processOrder = new AtomicInteger(0);

        ProcessListener testListener = new ProcessListener() {
            @Override
            public void onFinish(ProcessContext context) {
                latch.countDown();
            }
            public void onFailure(Throwable t) {
            }
        };

        ProcessContext context = new ProcessContext(eventList, testListener);

        List<EventProcessor> processors = new ArrayList<>();
        processors.add(new FailingEventProcessor(testException));
        processors.add(new TestEventProcessor(processOrder, 2)); // Should not execute

        SerialProcessor serialProcessor = new SerialProcessor(processors);

        serialProcessor.process(context, new ProcessorListener() {
            @Override
            public void onSuccess(ProcessContext context) {
                fail("Should not succeed");
            }

            @Override
            public void onFailure(Throwable t) {
                assertEquals(testException, t);
                latch.countDown();
            }
        });

        assertTrue(latch.await(2, TimeUnit.SECONDS));
        assertEquals(0, processOrder.get()); // Second processor should not execute
    }

    // Helper classes for testing
    private static class TestEventProcessor implements EventProcessor {
        private final AtomicInteger processOrder;
        private final int expectedOrder;
        private final List<Integer> executionOrder;

        public TestEventProcessor(AtomicInteger processOrder, int expectedOrder) {
            this(processOrder, expectedOrder, null);
        }

        public TestEventProcessor(AtomicInteger processOrder, int expectedOrder, List<Integer> executionOrder) {
            this.processOrder = processOrder;
            this.expectedOrder = expectedOrder;
            this.executionOrder = executionOrder;
        }

        @Override
        public void process(ProcessContext context, ProcessorListener listener) {
            int order = processOrder.incrementAndGet();
            if (executionOrder != null) {
                synchronized (executionOrder) {
                    executionOrder.add(expectedOrder);
                }
            }
            listener.onSuccess(context);
        }
    }

    private static class FailingEventProcessor implements EventProcessor {
        private final RuntimeException exception;

        public FailingEventProcessor(RuntimeException exception) {
            this.exception = exception;
        }

        @Override
        public void process(ProcessContext context, ProcessorListener listener) {
            listener.onFailure(exception);
        }
    }
}
