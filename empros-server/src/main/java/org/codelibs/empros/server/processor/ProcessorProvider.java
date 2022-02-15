/*
 * Copyright 2012-2020 CodeLibs Project and the Others.
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
package org.codelibs.empros.server.processor;

import org.codelibs.empros.processor.EventProcessor;
import org.codelibs.empros.processor.ParallelProcessor;
import org.codelibs.empros.processor.SerialProcessor;
import org.codelibs.empros.server.config.ProcessorConfig;
import org.codelibs.empros.exception.EmprosConfigException;
import org.codelibs.empros.factory.ProcessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ProcessorProvider {
    private static final String MODE_PARALLEL = "parallel";
    private static final String MODE_SERIAL = "serial";

    private final List<ProcessorFactory> factoryList;
    private final EventProcessor eventProcessor;

    @Autowired
    public ProcessorProvider(final ProcessorConfig config, final List<ProcessorFactory> factoryList) {
        this.factoryList = factoryList;
        final List<EventProcessor> processorList = factoryList.stream()
                .filter(ProcessorFactory::isAvailable)
                .map(ProcessorFactory::create)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (processorList.isEmpty()) {
            throw new EmprosConfigException("Processor is empty.");
        }
        if (MODE_PARALLEL.equals(config.getMode())) {
            this.eventProcessor = new ParallelProcessor(processorList, config.getThreadPoolSize());
        } else if (MODE_SERIAL.equals(config.getMode())) {
            this.eventProcessor = new SerialProcessor(processorList);
        } else {
            throw new EmprosConfigException("Unexpected processor mode {0}", config.getMode());
        }
    }

    public EventProcessor getProcessor() {
        return eventProcessor;
    }

    @PreDestroy
    public void destroy() {
        factoryList.forEach(ProcessorFactory::destroy);
    }
}
