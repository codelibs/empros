package org.codelibs.empros.processor;

import java.util.List;

import org.codelibs.empros.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingProcessor extends EventProcessor {
    private static final Logger logger = LoggerFactory
            .getLogger(LoggingProcessor.class);

    public LoggingProcessor() {
        super();
    }

    public LoggingProcessor(final List<EventProcessor> processorList) {
        super(processorList);
    }

    @Override
    public List<Event> process(final List<Event> eventList) {
        if (logger.isInfoEnabled()) {
            logger.info("incoming event: {}", eventList.toString());
        }
        return eventList;
    }

}
