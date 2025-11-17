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
package org.codelibs.empros.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void create() {
        final Map<String, String> dataMap = new HashMap<String, String>();
        Event event;

        event = new Event(dataMap);
        assertEquals(dataMap.size(), event.size());

        dataMap.put("aaa", "1");

        event = new Event(dataMap);
        assertEquals(dataMap.size(), event.size());

        dataMap.put("bbb", "2");

        event = new Event(dataMap);
        assertEquals(dataMap.size(), event.size());
    }

    @Test
    public void getID() {
        final Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("aaa", "1");
        dataMap.put("bbb", "2");
        dataMap.put("ccc", "3");
        final Event event = new Event(dataMap);
        // SHA-256 hash of the event map
        String idHash = event.getID("id");
        assertEquals(64, idHash.length()); // SHA-256 produces 64 hex characters
        assertEquals("1", event.getID("aaa"));
        assertEquals("2", event.getID("bbb"));
    }

    @Test
    public void getObject() {
        final Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("aaa", "1");
        dataMap.put("bbb", "2");
        final Map<String, Object> childMap = new HashMap<String, Object>();
        dataMap.put("ccc", childMap);
        childMap.put("aa", "11");
        childMap.put("bb", "22");
        final Map<String, Object> grandchildMap = new HashMap<String, Object>();
        childMap.put("cc", grandchildMap);
        grandchildMap.put("a", "111");
        grandchildMap.put("b", "222");
        final Event event = new Event(dataMap);

        assertEquals("1", event.getObject("aaa"));
        assertEquals("2", event.getObject("bbb"));
        assertEquals(childMap, event.getObject("ccc"));
        assertNull(event.getObject("ddd"));

        assertEquals("11", event.getObject("ccc.aa"));
        assertEquals("22", event.getObject("ccc.bb"));
        assertEquals(grandchildMap, event.getObject("ccc.cc"));
        assertNull(event.getObject("ccc.dd"));

        assertEquals("111", event.getObject("ccc.cc.a"));
        assertEquals("222", event.getObject("ccc.cc.b"));
        assertNull(event.getObject("ccc.cc.d"));

        assertNull(event.getObject(""));
        assertNull(event.getObject(" "));
        assertNull(event.getObject("."));
        assertNull(event.getObject(" ."));
        assertNull(event.getObject(". "));
        assertNull(event.getObject(" . "));
    }
}
