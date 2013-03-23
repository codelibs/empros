package org.codelibs.empros.processor;

import java.util.ArrayList;
import java.util.List;

import org.codelibs.empros.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventProcessor {
    private static final Logger logger = LoggerFactory
            .getLogger(EventProcessor.class);

    protected List<EventProcessor> nextProcessorList = new ArrayList<>();

    public void process(final List<Event> eventList) {
        if (logger.isInfoEnabled()) {
            logger.info("incoming event: {}", eventList.toString());
        }

        invokeNextProcessors(eventList);
    }

    public void addNext(final EventProcessor processor) {
        nextProcessorList.add(processor);
    }

    protected void invokeNextProcessors(final List<Event> eventList) {
        for (final EventProcessor processor : nextProcessorList) {
            processor.process(eventList);
        }
    }
}
