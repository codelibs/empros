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
            ProcessCallback callback);

    protected void invokeNext(final ProcessContext context,
            final ProcessCallback callback) {
        ProcessCallback nextCallback = callback;
        final int size = nextProcessorList.size();
        try {
            if (size == 0) {
                callback.onSuccess();
            } else {
                for (int i = size - 1; i >= 0; i--) {
                    final EventProcessor currentProcessor = nextProcessorList
                            .get(i);
                    final ProcessCallback currentCallback = nextCallback;
                    if (i == 0) {
                        currentProcessor.process(context, currentCallback);
                    } else {
                        nextCallback = new ProcessCallback() {
                            @Override
                            public void onSuccess() {
                                currentProcessor.process(context,
                                        currentCallback);
                            }
                        };
                    }
                }
            }
        } catch (final Throwable t) {
            callback.onFailure(t);
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
