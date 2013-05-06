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
import org.codelibs.empros.util.ProcessorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LoggingProcessor sends events to a logger.
 * 
 * @author shinsuke
 *
 */
public class LoggingProcessor implements EventProcessor {
    private static final Logger logger = LoggerFactory
            .getLogger(LoggingProcessor.class);

    public LoggingProcessor() {
        super();
    }

    @Override
    public void process(final ProcessContext context,
            final ProcessorListener listener) {
        context.start(this);
        if (logger.isInfoEnabled()) {
            final List<Event> eventList = ProcessorUtil
                    .getCurrentEventList(context);
            logger.info("incoming event: {}", eventList.toString());
            context.addNumOfProcessedEvents(eventList.size());
        }

        ProcessorUtil.finish(context, this, listener);
    }

}
