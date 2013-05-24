package org.codelibs.empros.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

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
        assertEquals("e76371e237693885ec70f9dccc118e66", event.getID("id"));
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
