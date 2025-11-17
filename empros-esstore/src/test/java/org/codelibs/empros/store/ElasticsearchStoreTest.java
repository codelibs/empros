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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codelibs.empros.event.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ElasticsearchStoreTest {

    private ElasticsearchStore elasticsearchStore;
    private List<Event> eventList;

    @BeforeEach
    public void setUp() {
        elasticsearchStore = new ElasticsearchStore();
        eventList = new ArrayList<>();
    }

    @Test
    public void testDefaultConfiguration() {
        assertNotNull(elasticsearchStore);
        assertNotNull(elasticsearchStore.getIndexName());
        assertEquals("@timestamp", elasticsearchStore.getTimestampKey());
    }

    @Test
    public void testSetIndexName() {
        String indexName = "test-index";
        elasticsearchStore.setIndexName(indexName);
        assertEquals(indexName, elasticsearchStore.getIndexName());
    }

    @Test
    public void testSetHosts() {
        String[] hosts = {"localhost:9200", "localhost:9201"};
        elasticsearchStore.setHosts(hosts);
        assertArrayEquals(hosts, elasticsearchStore.getHosts());
    }

    @Test
    public void testSetTimestampKey() {
        String timestampKey = "timestamp";
        elasticsearchStore.setTimestampKey(timestampKey);
        assertEquals(timestampKey, elasticsearchStore.getTimestampKey());
    }

    @Test
    public void testSetIdKey() {
        String idKey = "_id";
        elasticsearchStore.setIdKey(idKey);
        assertEquals(idKey, elasticsearchStore.getIdKey());
    }

    @Test
    public void testSetMaxBulkSize() {
        int maxBulkSize = 1000;
        elasticsearchStore.setMaxBulkSize(maxBulkSize);
        assertEquals(maxBulkSize, elasticsearchStore.getMaxBulkSize());
    }

    @Test
    public void testCreateEvent() {
        Map<String, Object> data = new HashMap<>();
        data.put("field1", "value1");
        data.put("field2", 123);
        Event event = new Event(data);

        assertNotNull(event);
        assertEquals("value1", event.get("field1"));
        assertEquals(123, event.get("field2"));
    }

    @Test
    public void testMultipleEvents() {
        for (int i = 0; i < 10; i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", i);
            data.put("message", "Event " + i);
            eventList.add(new Event(data));
        }

        assertEquals(10, eventList.size());
    }

    @Test
    public void testEventWithTimestamp() {
        Map<String, Object> data = new HashMap<>();
        data.put("@timestamp", System.currentTimeMillis());
        data.put("message", "Test message");
        Event event = new Event(data);

        assertNotNull(event.get("@timestamp"));
        assertTrue(event.get("@timestamp") instanceof Long);
    }

    @Test
    public void testEventWithNestedFields() {
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> nested = new HashMap<>();
        nested.put("subfield", "subvalue");
        data.put("parent", nested);
        Event event = new Event(data);

        assertNotNull(event.get("parent"));
        assertEquals("subvalue", event.getObject("parent.subfield"));
    }
}
