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

    public void invoke(final List<Event> eventList) {
        final List<Event> newEventlist = process(eventList);

        invokeNextProcessors(newEventlist);
    }

    public abstract List<Event> process(final List<Event> eventList);

    protected void invokeNextProcessors(final List<Event> eventList) {
        for (final EventProcessor processor : nextProcessorList) {
            processor.process(eventList);
        }
    }
}
