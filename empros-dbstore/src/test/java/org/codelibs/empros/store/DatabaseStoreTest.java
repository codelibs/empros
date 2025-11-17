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
package org.codelibs.empros.store;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codelibs.empros.db.exbhv.PersistentEventBhv;
import org.codelibs.empros.db.exbhv.PersistentEventValueBhv;
import org.codelibs.empros.db.exentity.PersistentEvent;
import org.codelibs.empros.event.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DatabaseStoreTest {

    @Mock
    private PersistentEventBhv persistentEventBhv;

    @Mock
    private PersistentEventValueBhv persistentEventValueBhv;

    @Mock
    private DataStoreListener listener;

    private DatabaseStore databaseStore;
    private List<Event> eventList;

    @BeforeEach
    public void setUp() {
        databaseStore = new DatabaseStore(persistentEventBhv, persistentEventValueBhv);
        eventList = new ArrayList<>();
    }

    @Test
    public void testConstructor() {
        assertNotNull(databaseStore);
    }

    @Test
    public void testStoreSuccessfully() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        eventList.add(new Event(data));

        databaseStore.store(eventList, listener);

        verify(persistentEventBhv, times(1)).insert(any(PersistentEvent.class));
        verify(persistentEventValueBhv, times(1)).batchInsert(any());
        verify(listener, times(1)).onSuccess(databaseStore, eventList);
        verify(listener, never()).onFailure(any());
    }

    @Test
    public void testStoreMultipleEvents() {
        for (int i = 0; i < 5; i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", i);
            data.put("name", "Event" + i);
            eventList.add(new Event(data));
        }

        databaseStore.store(eventList, listener);

        verify(persistentEventBhv, times(5)).insert(any(PersistentEvent.class));
        verify(persistentEventValueBhv, times(5)).batchInsert(any());
        verify(listener, times(1)).onSuccess(databaseStore, eventList);
        verify(listener, never()).onFailure(any());
    }

    @Test
    public void testStoreWithException() {
        Map<String, Object> data = new HashMap<>();
        data.put("key", "value");
        eventList.add(new Event(data));

        RuntimeException testException = new RuntimeException("Database error");
        doThrow(testException).when(persistentEventBhv).insert(any(PersistentEvent.class));

        databaseStore.store(eventList, listener);

        verify(listener, never()).onSuccess(any(), any());
        verify(listener, times(1)).onFailure(testException);
    }

    @Test
    public void testStoreWithEmptyEventList() {
        databaseStore.store(eventList, listener);

        verify(persistentEventBhv, never()).insert(any());
        verify(persistentEventValueBhv, never()).batchInsert(any());
        verify(listener, times(1)).onSuccess(databaseStore, eventList);
        verify(listener, never()).onFailure(any());
    }

    @Test
    public void testStoreWithNullValues() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", null);
        eventList.add(new Event(data));

        databaseStore.store(eventList, listener);

        verify(persistentEventBhv, times(1)).insert(any(PersistentEvent.class));
        verify(listener, times(1)).onSuccess(databaseStore, eventList);
    }
}
