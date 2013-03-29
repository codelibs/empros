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

public abstract class EventProcessor {

    protected List<EventProcessor> nextProcessorList;

    public EventProcessor() {
        nextProcessorList = new ArrayList<>();
    }

    public EventProcessor(final List<EventProcessor> processorList) {
        nextProcessorList = processorList;
    }

    public int invoke(final List<Event> eventList) {
        final List<Event> newEventlist = process(eventList);

        return invokeNextProcessors(newEventlist) + eventList.size();
    }

    abstract List<Event> process(final List<Event> eventList);

    protected int invokeNextProcessors(final List<Event> eventList) {
        int processed = 0;
        for (final EventProcessor processor : nextProcessorList) {
            processed += processor.invoke(eventList);
        }
        return processed;
    }
}
