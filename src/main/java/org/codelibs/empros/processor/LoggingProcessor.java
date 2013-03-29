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
    protected List<Event> process(final List<Event> eventList) {
        if (logger.isInfoEnabled()) {
            logger.info("incoming event: {}", eventList.toString());
        }
        return eventList;
    }

}
