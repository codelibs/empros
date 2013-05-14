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
package org.codelibs.empros.factory;

import org.codelibs.empros.processor.EventProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * XmlComponentProcessorFactory loads processors from Spring's XML files.
 * 
 * @author shinsuke
 *
 */
public abstract class XmlComponentProcessorFactory implements ProcessorFactory {
    protected ApplicationContext context;

    protected String eventProcessorName = "eventProcessor";

    public XmlComponentProcessorFactory(final String[] configFiles) {
        context = new ClassPathXmlApplicationContext(configFiles);
    }

    @Override
    public EventProcessor create() {
        return context.getBean(eventProcessorName, EventProcessor.class);
    }

    @Override
    public void destroy() {
        if (context instanceof ConfigurableApplicationContext) {
            ((ConfigurableApplicationContext) context).close();
        }
    }
}
