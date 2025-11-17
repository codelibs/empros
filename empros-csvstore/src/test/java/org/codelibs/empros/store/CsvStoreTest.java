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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.codelibs.empros.event.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class CsvStoreTest {

    @TempDir
    File tempDir;

    private CsvStore csvStore;
    private List<Event> eventList;

    @BeforeEach
    public void setUp() {
        csvStore = new CsvStore();
        csvStore.setCsvDirectoryPath(tempDir.getAbsolutePath());

        eventList = new ArrayList<>();
    }

    @AfterEach
    public void tearDown() {
        if (csvStore != null) {
            csvStore.destroy();
        }
    }

    @Test
    public void testConfiguration() {
        assertEquals(tempDir.getAbsolutePath(), csvStore.getCsvDirectoryPath());

        csvStore.setCsvNamePrefix("test_");
        assertEquals("test_", csvStore.getCsvNamePrefix());

        csvStore.setCsvNameSuffix(".txt");
        assertEquals(".txt", csvStore.getCsvNameSuffix());

        csvStore.setOutputEncoding("UTF-8");
        assertEquals("UTF-8", csvStore.getOutputEncoding());
    }

    @Test
    public void testStoreWithoutColumns() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<Throwable> errorRef = new AtomicReference<>();

        Map<String, Object> data = new HashMap<>();
        data.put("key", "value");
        eventList.add(new Event(data));

        csvStore.store(eventList, new DataStoreListener() {
            @Override
            public void onSuccess(DataStore dataStore, List<Event> events) {
                fail("Should not succeed without columns configured");
            }

            @Override
            public void onFailure(Throwable t) {
                errorRef.set(t);
                latch.countDown();
            }
        });

        assertTrue(latch.await(2, TimeUnit.SECONDS));
        assertNotNull(errorRef.get());
    }

    @Test
    public void testStoreWithColumns() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        List<String> columns = new ArrayList<>();
        columns.add("key1");
        columns.add("key2");
        csvStore.setColumnList(columns);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        eventList.add(new Event(data));

        csvStore.store(eventList, new DataStoreListener() {
            @Override
            public void onSuccess(DataStore dataStore, List<Event> events) {
                assertEquals(eventList, events);
                assertEquals(csvStore, dataStore);
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                fail("Should not fail: " + t.getMessage());
            }
        });

        assertTrue(latch.await(2, TimeUnit.SECONDS));
    }

    @Test
    public void testStoreMultipleEvents() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        List<String> columns = new ArrayList<>();
        columns.add("name");
        columns.add("age");
        csvStore.setColumnList(columns);

        for (int i = 0; i < 10; i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", "User" + i);
            data.put("age", 20 + i);
            eventList.add(new Event(data));
        }

        csvStore.store(eventList, new DataStoreListener() {
            @Override
            public void onSuccess(DataStore dataStore, List<Event> events) {
                assertEquals(10, events.size());
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                fail("Should not fail: " + t.getMessage());
            }
        });

        assertTrue(latch.await(2, TimeUnit.SECONDS));
    }

    @Test
    public void testStoreWithMissingValues() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        List<String> columns = new ArrayList<>();
        columns.add("key1");
        columns.add("key2");
        columns.add("key3");
        csvStore.setColumnList(columns);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        // key2 is missing
        data.put("key3", "value3");
        eventList.add(new Event(data));

        csvStore.store(eventList, new DataStoreListener() {
            @Override
            public void onSuccess(DataStore dataStore, List<Event> events) {
                assertEquals(1, events.size());
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                fail("Should not fail: " + t.getMessage());
            }
        });

        assertTrue(latch.await(2, TimeUnit.SECONDS));
    }

    @Test
    public void testStoreWithNestedValues() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        List<String> columns = new ArrayList<>();
        columns.add("user.name");
        columns.add("user.age");
        csvStore.setColumnList(columns);

        Map<String, Object> data = new HashMap<>();
        Map<String, Object> user = new HashMap<>();
        user.put("name", "John");
        user.put("age", 30);
        data.put("user", user);
        eventList.add(new Event(data));

        csvStore.store(eventList, new DataStoreListener() {
            @Override
            public void onSuccess(DataStore dataStore, List<Event> events) {
                assertEquals(1, events.size());
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                fail("Should not fail: " + t.getMessage());
            }
        });

        assertTrue(latch.await(2, TimeUnit.SECONDS));
    }

    @Test
    public void testColumnListGetterSetter() {
        List<String> columns = new ArrayList<>();
        columns.add("col1");
        columns.add("col2");

        csvStore.setColumnList(columns);
        assertEquals(columns, csvStore.getColumnList());
        assertEquals(2, csvStore.getColumnList().size());
    }

    @Test
    public void testCsvDirectoryPath() {
        String newPath = "/tmp/test";
        csvStore.setCsvDirectoryPath(newPath);
        assertEquals(newPath, csvStore.getCsvDirectoryPath());
    }

    @Test
    public void testTimestampKey() {
        String timestampKey = "@timestamp";
        csvStore.setTimestampKey(timestampKey);
        assertEquals(timestampKey, csvStore.getTimestampKey());
    }

    @Test
    public void testCsvNamePrefix() {
        String prefix = "prefix_";
        csvStore.setCsvNamePrefix(prefix);
        assertEquals(prefix, csvStore.getCsvNamePrefix());
    }

    @Test
    public void testCsvNameSuffix() {
        String suffix = ".log";
        csvStore.setCsvNameSuffix(suffix);
        assertEquals(suffix, csvStore.getCsvNameSuffix());
    }

    @Test
    public void testOutputEncoding() {
        String encoding = "ISO-8859-1";
        csvStore.setOutputEncoding(encoding);
        assertEquals(encoding, csvStore.getOutputEncoding());
    }

    @Test
    public void testRotationDatePattern() {
        String pattern = "yyyy-MM-dd";
        csvStore.setRotationDatePattern(pattern);
        assertEquals(pattern, csvStore.getRotationDatePattern());
    }
}
