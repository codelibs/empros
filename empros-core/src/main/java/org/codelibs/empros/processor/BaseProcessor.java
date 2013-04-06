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
    public abstract void process(final ProcessContext context);

    protected void invokeNext(final ProcessContext context) {
        for (final EventProcessor processor : nextProcessorList) {
            processor.process(context);
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
