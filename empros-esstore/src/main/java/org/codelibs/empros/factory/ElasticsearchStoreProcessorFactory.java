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

import org.codelibs.core.lang.StringUtil;
import org.codelibs.empros.config.ElasticsearchStoreConfig;
import org.codelibs.empros.processor.DataStoreProcessor;
import org.codelibs.empros.processor.EventProcessor;
import org.codelibs.empros.store.ElasticsearchStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchStoreProcessorFactory implements ProcessorFactory {
    private final ElasticsearchStoreConfig config;

    private final ElasticsearchStore elasticsearchStore;

    @Autowired
    public ElasticsearchStoreProcessorFactory(ElasticsearchStoreConfig config) {
        this.config = config;
        if (StringUtil.isNotBlank(config.getIndex())) {
            elasticsearchStore = new ElasticsearchStore();
            if (StringUtil.isBlank(elasticsearchStore.getAddress())) {
                elasticsearchStore.setAddress(config.getAddress());
            }
            elasticsearchStore.setIndex(config.getIndex());
            elasticsearchStore.setRequestEventSize(config.getRequestEventSize());
            elasticsearchStore.setIdKey(config.getIdKey());
            elasticsearchStore.init();
        } else {
            elasticsearchStore = null;
        }
    }

    @Override
    public EventProcessor create() {
        DataStoreProcessor dataStoreProcessor = new DataStoreProcessor();
        dataStoreProcessor.setDataStore(elasticsearchStore);
        return dataStoreProcessor;
    }

    @Override
    public void destroy() {
        elasticsearchStore.destroy();
    }

    @Override
    public boolean isAvailable() {
        return elasticsearchStore != null;
    }
}
