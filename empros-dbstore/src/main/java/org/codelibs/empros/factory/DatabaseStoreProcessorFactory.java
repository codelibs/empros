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
package org.codelibs.empros.factory;

import org.codelibs.empros.config.DatabaseStoreConfig;
import org.codelibs.empros.db.exbhv.PersistentEventBhv;
import org.codelibs.empros.db.exbhv.PersistentEventValueBhv;
import org.codelibs.empros.processor.DataStoreProcessor;
import org.codelibs.empros.processor.EventProcessor;
import org.codelibs.empros.store.DatabaseStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DatabaseStoreProcessorFactory implements ProcessorFactory {
    protected ApplicationContext context;
    private final boolean available;

    private final PersistentEventBhv persistentEventBhv;
    private final PersistentEventValueBhv persistentEventValueBhv;

    @Autowired
    public DatabaseStoreProcessorFactory(final DatabaseStoreConfig config) {
        if (config.getConfigFiles() != null && config.getConfigFiles().length > 0) {
            this.context = new ClassPathXmlApplicationContext(config.getConfigFiles());
            this.persistentEventBhv = context.getBean("persistentEventBhv", PersistentEventBhv.class);
            this.persistentEventValueBhv = context.getBean("persistentEventValueBhv", PersistentEventValueBhv.class);
            available = true;
        } else {
            this.persistentEventBhv = null;
            this.persistentEventValueBhv = null;
            available = false;
        }
    }

    @Override
    public EventProcessor create() {
        final DataStoreProcessor processor = new DataStoreProcessor();
        processor.setDataStore(new DatabaseStore(persistentEventBhv, persistentEventValueBhv));
        return processor;
    }

    @Override
    public void destroy() {
        if (context instanceof ConfigurableApplicationContext) {
            ((ConfigurableApplicationContext) context).close();
        }
    }

    @Override
    public boolean isAvailable() {
        return available;
    }
}
