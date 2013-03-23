package org.codelibs.empros.event;

import java.util.HashMap;
import java.util.Map;

public class Event extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public Event() {
        super();
    }

    public Event(final int initialCapacity) {
        super(initialCapacity);
    }

    // TODO
    public Event(final Map<? extends String, ? extends Object> m) {
        super(m);
    }
}
