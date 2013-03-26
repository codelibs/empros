package org.codelibs.empros.event;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Event extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    protected Date createdTime;

    protected String createdBy;

    public Event(final Map<?, ?> m) {
        super(m.size());

        for (final Map.Entry<?, ?> entry : m.entrySet()) {
            final Object key = entry.getKey();
            if (key != null) {
                put(key.toString(), entry.getValue());
            }
        }
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(final Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

}
