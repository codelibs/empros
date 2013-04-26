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
package org.codelibs.empros.store;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.codelibs.empros.db.exbhv.PersistentEventBhv;
import org.codelibs.empros.db.exbhv.PersistentEventValueBhv;
import org.codelibs.empros.db.exentity.PersistentEvent;
import org.codelibs.empros.event.Event;
import org.springframework.transaction.annotation.Transactional;

public class DatabaseStore implements DataStore {
    @Resource
    protected PersistentEventBhv persistentEventBhv;

    @Resource
    protected PersistentEventValueBhv persistentEventValueBhv;

    public DatabaseStore() {
        // nothing
    }

    /* (non-Javadoc)
     * @see org.codelibs.empros.store.DataStore#store(java.util.List)
     */
    @Override
    @Transactional
    public void store(final List<Event> eventList,
            final DataStoreListener listener) {
        final List<PersistentEvent> pEventList = new ArrayList<PersistentEvent>(
                eventList.size());
        try {
            for (final Event event : eventList) {
                pEventList.add(new PersistentEvent(event));
            }

            for (final PersistentEvent pEvent : pEventList) {
                persistentEventBhv.insert(pEvent);
                pEvent.updateValues();
                persistentEventValueBhv.batchInsert(pEvent
                        .getPersistentEventValueList());
            }
            listener.onSuccess(this, eventList);
        } catch (final Exception e) {
            listener.onFailure(e);
        }
    }
}
