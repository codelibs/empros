/*
 * Copyright 2013 the CodeLibs Project and the Others.
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.codelibs.empros.exception.EmprosParallelProcessException;

/**
 * ParallelProcessor sends events to multiple event-processors simultaneously.
 * 
 * @author shinsuke
 *
 */
public class ParallelProcessor extends BaseProcessor {

    protected ExecutorService executorService;

    public ParallelProcessor(final List<EventProcessor> processorList,
            final int threadPoolSize) {
        super(processorList);

        executorService = Executors.newFixedThreadPool(threadPoolSize);
    }

    @Override
    public void process(final ProcessContext context) {
        invokeNext(context);
    }

    @Override
    protected void invokeNext(final ProcessContext context) {
        final List<Future<ProcessContext>> futureList = new ArrayList<Future<ProcessContext>>(
                nextProcessorList.size());
        for (final EventProcessor processor : nextProcessorList) {
            final Future<ProcessContext> future = executorService
                    .submit(new Callable<ProcessContext>() {
                        @Override
                        public ProcessContext call() {
                            final ProcessContext childContext = context.clone();
                            processor.process(childContext);
                            return childContext;
                        }
                    });
            futureList.add(future);
        }

        List<Exception> exceptionList = null;
        final int processed = 0;
        for (final Future<ProcessContext> future : futureList) {
            try {
                final ProcessContext childContext = future.get();
                if (childContext.getResponse() != null) {
                    context.setResponse(childContext.getResponse());
                }
                context.addNumOfProcessedEvents(childContext.getProcessed());
            } catch (final Exception e) {
                if (exceptionList == null) {
                    exceptionList = new ArrayList<Exception>();
                }
                exceptionList.add(e);
            }
        }

        if (exceptionList != null) {
            throw new EmprosParallelProcessException(processed, exceptionList);
        }

    }

}
