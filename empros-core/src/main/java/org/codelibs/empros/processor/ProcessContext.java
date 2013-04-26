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
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.codelibs.empros.event.Event;

/**
 * ProcessContext contains events information and is passed to a pipeline of event processors.
 * 
 * @author shinsuke
 *
 */
public class ProcessContext implements Cloneable {
    private List<Event> incomingEventList;

    private List<Event> processingEventList;

    private AtomicLong processed = new AtomicLong(0);

    private Object response;

    protected ProcessContext() {
        // nothing
    }

    public ProcessContext(final List<Event> eventList) {
        incomingEventList = Collections.unmodifiableList(eventList);
    }

    public void addNumOfProcessedEvents(final long num) {
        processed.addAndGet(num);
    }

    public void setResponse(final Object response) {
        this.response = response;
    }

    public Object getResponse() {
        return response;
    }

    public List<Event> getIncomingEventList() {
        return incomingEventList;
    }

    public List<Event> getProcessingEventList() {
        return processingEventList;
    }

    public void setProcessingEventList(final List<Event> processingEventList) {
        this.processingEventList = processingEventList;
    }

    public long getProcessed() {
        return processed.longValue();
    }

    @Override
    protected ProcessContext clone() {
        ProcessContext context = null;
        try {
            context = (ProcessContext) super.clone();
            context.incomingEventList = incomingEventList;
            context.response = response;
            // replace with the following values.
            context.processed = new AtomicLong(0);
            if (processingEventList != null) {
                context.processingEventList = new ArrayList<Event>(
                        processingEventList.size());
                Collections.copy(context.processingEventList,
                        processingEventList);
            }
        } catch (final CloneNotSupportedException e) {
            //  Won't happen
        }
        return context;
    }
}
