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

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.codelibs.empros.util.ProcessorUtil;

/**
 * ParallelProcessor sends events to multiple event-processors simultaneously.
 *
 * @author shinsuke
 *
 */
public class ParallelProcessor extends DispatchProcessor {

    private static final AtomicInteger threadCounter = new AtomicInteger(0);

    protected ExecutorService executorService;

    protected int queueCapacity = 1000;

    public ParallelProcessor(final List<EventProcessor> processorList,
            final int threadPoolSize) {
        super(processorList);

        executorService = new ThreadPoolExecutor(
                threadPoolSize,
                threadPoolSize,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(queueCapacity),
                r -> {
                    final Thread thread = new Thread(r,
                            "ParallelProcessor-" + threadCounter.incrementAndGet());
                    thread.setDaemon(true);
                    return thread;
                },
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void destroy() {
        executorService.shutdown();
    }

    public void setQueueCapacity(final int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    @Override
    protected void invokeNext(final ProcessContext context,
            final ProcessorListener listener) {
        final int size = nextProcessorList.size();
        try {
            if (size == 0) {
                listener.onSuccess(context);
            } else {
                invokeProcessorsByParallel(context, listener, size);
            }
        } catch (final Exception e) {
            listener.onFailure(e);
        }
    }

    protected void invokeProcessorsByParallel(final ProcessContext context,
            final ProcessorListener listener, final int size) {
        final AtomicInteger counter = new AtomicInteger(size);
        for (final EventProcessor processor : nextProcessorList) {
            executorService.execute( () -> {
                    final ProcessContext childContext = context.clone();
                    processor.process(childContext, new ProcessorListener() {
                        @Override
                        public void onSuccess(
                                final ProcessContext currentContext) {
                            if (childContext.getResponse() != null) {
                                context.setResponse(childContext.getResponse());
                            }
                            context.addNumOfProcessedEvents(childContext
                                    .getProcessed());
                            final int count = counter.decrementAndGet();
                            if (count == 0) {
                                ProcessorUtil.finish(context,
                                        ParallelProcessor.this, listener);
                            }
                        }

                        @Override
                        public void onFailure(final Throwable t) {
                            ProcessorUtil.fail(context, ParallelProcessor.this,
                                    listener, t);
                        }
                    });
                }
            );
        }
    }

}
