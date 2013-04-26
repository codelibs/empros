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

import org.codelibs.empros.event.Event;

/**
 * BaseProcessor is a base event processor class.
 * 
 * @author shinsuke
 *
 */
public abstract class BaseProcessor implements EventProcessor {

    protected List<EventProcessor> nextProcessorList;

    public BaseProcessor() {
        nextProcessorList = new ArrayList<EventProcessor>();
    }

    public BaseProcessor(final List<EventProcessor> processorList) {
        nextProcessorList = processorList;
    }

    /* (non-Javadoc)
     * @see org.codelibs.empros.processor.EventProcessor#process(org.codelibs.empros.processor.ProcessContext)
     */
    @Override
    public abstract void process(final ProcessContext context,
            ProcessorListener listener);

    protected void invokeNext(final ProcessContext context,
            final ProcessorListener listener) {
        ProcessorListener nextCallback = listener;
        final int size = nextProcessorList.size();
        try {
            if (size == 0) {
                listener.onSuccess(context);
            } else {
                final ProcessContext currentContext = context;
                for (int i = size - 1; i >= 0; i--) {
                    final EventProcessor currentProcessor = nextProcessorList
                            .get(i);
                    final ProcessorListener currentCallback = nextCallback;
                    if (i == 0) {
                        currentProcessor.process(context, currentCallback);
                    } else {
                        nextCallback = new ProcessorListener() {
                            @Override
                            public void onSuccess(final ProcessContext context) {
                                currentProcessor.process(currentContext,
                                        currentCallback);
                            }
                        };
                    }
                }
            }
        } catch (final Throwable t) {
            listener.onFailure(t);
        }
    }

    protected List<Event> getCurrentEventList(final ProcessContext context) {
        final List<Event> processingEventList = context
                .getProcessingEventList();
        if (processingEventList != null) {
            return processingEventList;
        }
        return context.getIncomingEventList();
    }
}
