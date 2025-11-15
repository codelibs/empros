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
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.codelibs.empros.event.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParallelProcessorTest {

    private List<Event> eventList;
    private ProcessListener listener;
    private ParallelProcessor parallelProcessor;

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
    }

    @AfterEach
    public void tearDown() {
        if (parallelProcessor != null) {
            parallelProcessor.destroy();
        }
    }

    @Test
    public void testEmptyProcessorList() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        ProcessContext context = new ProcessContext(eventList, listener);
        parallelProcessor = new ParallelProcessor(new ArrayList<>(), 2);

        parallelProcessor.process(context, new ProcessorListener() {
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
        final CountDownLatch latch = new CountDownLatch(2);
        final AtomicInteger processCount = new AtomicInteger(0);

        ProcessListener testListener = new ProcessListener() {
            @Override
            public void onFinish(ProcessContext context) {
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
            }
        };

        ProcessContext context = new ProcessContext(eventList, testListener);

        List<EventProcessor> processors = new ArrayList<>();
        processors.add(new TestEventProcessor(processCount, 50)); // 50ms delay

        parallelProcessor = new ParallelProcessor(processors, 2);

        parallelProcessor.process(context, new ProcessorListener() {
            @Override
            public void onSuccess(ProcessContext context) {
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                fail("Should not fail: " + t.getMessage());
            }
        });

        assertTrue(latch.await(3, TimeUnit.SECONDS));
        assertEquals(1, processCount.get());
    }

    @Test
    public void testMultipleProcessorsInParallel() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);
        final AtomicInteger processCount = new AtomicInteger(0);
        final Set<Long> threadIds = ConcurrentHashMap.newKeySet();

        ProcessListener testListener = new ProcessListener() {
            @Override
            public void onFinish(ProcessContext context) {
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
            }
        };

        ProcessContext context = new ProcessContext(eventList, testListener);

        List<EventProcessor> processors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            processors.add(new TestEventProcessor(processCount, 100, threadIds));
        }

        parallelProcessor = new ParallelProcessor(processors, 5);

        long startTime = System.currentTimeMillis();

        parallelProcessor.process(context, new ProcessorListener() {
            @Override
            public void onSuccess(ProcessContext context) {
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                fail("Should not fail: " + t.getMessage());
            }
        });

        assertTrue(latch.await(5, TimeUnit.SECONDS));

        long elapsedTime = System.currentTimeMillis() - startTime;

        assertEquals(5, processCount.get());
        // Verify parallel execution: should take ~100ms not ~500ms
        assertTrue(elapsedTime < 400, "Elapsed time: " + elapsedTime + "ms (should be < 400ms for parallel execution)");
        // Verify different threads were used
        assertTrue(threadIds.size() > 1, "Should use multiple threads");
    }

    @Test
    public void testProcessedEventsAggregation() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);

        ProcessListener testListener = new ProcessListener() {
            @Override
            public void onFinish(ProcessContext context) {
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
            }
        };

        ProcessContext context = new ProcessContext(eventList, testListener);

        List<EventProcessor> processors = new ArrayList<>();
        processors.add(new ProcessedCountingProcessor(10));
        processors.add(new ProcessedCountingProcessor(20));
        processors.add(new ProcessedCountingProcessor(30));

        parallelProcessor = new ParallelProcessor(processors, 3);

        parallelProcessor.process(context, new ProcessorListener() {
            @Override
            public void onSuccess(ProcessContext context) {
                // Verify aggregated processed count
                assertEquals(60, context.getProcessed());
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                fail("Should not fail: " + t.getMessage());
            }
        });

        assertTrue(latch.await(3, TimeUnit.SECONDS));
    }

    @Test
    public void testResponsePropagation() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);
        final String testResponse = "test-response";

        ProcessListener testListener = new ProcessListener() {
            @Override
            public void onFinish(ProcessContext context) {
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
            }
        };

        ProcessContext context = new ProcessContext(eventList, testListener);

        List<EventProcessor> processors = new ArrayList<>();
        processors.add(new ResponseSettingProcessor(testResponse));
        processors.add(new TestEventProcessor(new AtomicInteger(), 10));

        parallelProcessor = new ParallelProcessor(processors, 2);

        parallelProcessor.process(context, new ProcessorListener() {
            @Override
            public void onSuccess(ProcessContext context) {
                // Verify response is propagated
                assertEquals(testResponse, context.getResponse());
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                fail("Should not fail: " + t.getMessage());
            }
        });

        assertTrue(latch.await(3, TimeUnit.SECONDS));
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

            @Override
            public void onFailure(Throwable t) {
            }
        };

        ProcessContext context = new ProcessContext(eventList, testListener);

        List<EventProcessor> processors = new ArrayList<>();
        processors.add(new FailingEventProcessor(testException));

        parallelProcessor = new ParallelProcessor(processors, 2);

        parallelProcessor.process(context, new ProcessorListener() {
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

        assertTrue(latch.await(3, TimeUnit.SECONDS));
    }

    @Test
    public void testThreadPoolSize() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);
        final Set<Long> threadIds = ConcurrentHashMap.newKeySet();
        final int threadPoolSize = 2;

        ProcessListener testListener = new ProcessListener() {
            @Override
            public void onFinish(ProcessContext context) {
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
            }
        };

        ProcessContext context = new ProcessContext(eventList, testListener);

        List<EventProcessor> processors = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            processors.add(new TestEventProcessor(new AtomicInteger(), 50, threadIds));
        }

        parallelProcessor = new ParallelProcessor(processors, threadPoolSize);

        parallelProcessor.process(context, new ProcessorListener() {
            @Override
            public void onSuccess(ProcessContext context) {
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                fail("Should not fail: " + t.getMessage());
            }
        });

        assertTrue(latch.await(5, TimeUnit.SECONDS));
        // The thread pool may create additional threads, but verify it's reasonable
        assertTrue(threadIds.size() <= threadPoolSize + 2, "Thread IDs: " + threadIds.size());
    }

    // Helper classes for testing
    private static class TestEventProcessor implements EventProcessor {
        private final AtomicInteger processCount;
        private final long delayMs;
        private final Set<Long> threadIds;

        public TestEventProcessor(AtomicInteger processCount, long delayMs) {
            this(processCount, delayMs, null);
        }

        public TestEventProcessor(AtomicInteger processCount, long delayMs, Set<Long> threadIds) {
            this.processCount = processCount;
            this.delayMs = delayMs;
            this.threadIds = threadIds;
        }

        @Override
        public void process(ProcessContext context, ProcessorListener listener) {
            if (threadIds != null) {
                threadIds.add(Thread.currentThread().getId());
            }
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            processCount.incrementAndGet();
            listener.onSuccess(context);
        }
    }

    private static class ProcessedCountingProcessor implements EventProcessor {
        private final long count;

        public ProcessedCountingProcessor(long count) {
            this.count = count;
        }

        @Override
        public void process(ProcessContext context, ProcessorListener listener) {
            context.addNumOfProcessedEvents(count);
            listener.onSuccess(context);
        }
    }

    private static class ResponseSettingProcessor implements EventProcessor {
        private final String response;

        public ResponseSettingProcessor(String response) {
            this.response = response;
        }

        @Override
        public void process(ProcessContext context, ProcessorListener listener) {
            context.setResponse(response);
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
