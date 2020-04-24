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
package org.codelibs.empros.processor;

import java.util.List;

import org.codelibs.empros.event.Event;
import org.codelibs.empros.store.DataStore;
import org.codelibs.empros.store.DataStoreListener;
import org.codelibs.empros.util.ProcessorUtil;

/**
 * DataStoreProcessor sends events to a specified data store.
 * 
 * @author shinsuke
 *
 */
public class DataStoreProcessor implements EventProcessor {
    protected DataStore dataStore;

    @Override
    public void process(final ProcessContext context,
            final ProcessorListener listener) {
        context.start(this);

        final List<Event> eventList = ProcessorUtil
                .getCurrentEventList(context);
        try {
            dataStore.store(eventList, new DataStoreListener() {
                @Override
                public void onSuccess(final DataStore dataStore,
                        final List<Event> eventList) {
                    context.addNumOfProcessedEvents(eventList.size());
                    ProcessorUtil.finish(context, DataStoreProcessor.this,
                            listener);
                }

                @Override
                public void onFailure(final Throwable t) {
                    ProcessorUtil.fail(context, DataStoreProcessor.this,
                            listener, t);
                }
            });
        } catch (final Throwable t) {
            ProcessorUtil.fail(context, this, listener, t);
        }
    }

    public DataStore getDataStore() {
        return dataStore;
    }

    public void setDataStore(final DataStore dataStore) {
        this.dataStore = dataStore;
    }

}
