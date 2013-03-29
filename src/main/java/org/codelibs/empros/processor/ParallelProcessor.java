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

import org.codelibs.empros.event.Event;
import org.codelibs.empros.exception.EmprosParallelProcessException;

public class ParallelProcessor extends EventProcessor {

    protected ExecutorService executorService;

    public ParallelProcessor() {
    }

    public ParallelProcessor(final List<EventProcessor> processorList,
            final int threadPoolSize) {
        super(processorList);

        executorService = Executors.newFixedThreadPool(threadPoolSize);
    }

    @Override
    public int invoke(final List<Event> eventList) {
        return invokeNextProcessors(eventList);
    }

    @Override
    protected int invokeNextProcessors(final List<Event> eventList) {
        final List<Future<Integer>> futureList = new ArrayList<>(
                nextProcessorList.size());
        for (final EventProcessor processor : nextProcessorList) {
            final Future<Integer> future = executorService
                    .submit(new Callable<Integer>() {
                        @Override
                        public Integer call() throws Exception {
                            return processor.invoke(eventList);
                        }
                    });
            futureList.add(future);
        }

        List<Exception> exceptionList = null;
        int processed = 0;
        for (final Future<Integer> future : futureList) {
            try {
                processed += future.get().intValue();
            } catch (final Exception e) {
                if (exceptionList == null) {
                    exceptionList = new ArrayList<>();
                }
                exceptionList.add(e);
            }
        }

        if (exceptionList != null) {
            throw new EmprosParallelProcessException(processed, exceptionList);
        }

        return processed;
    }

    @Override
    public List<Event> process(final List<Event> eventList) {
        // nothing
        return null;
    }

}
