package org.codelibs.empros.db.exentity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.codelibs.empros.db.bsentity.BsPersistentEvent;
import org.codelibs.empros.event.Event;

/**
 * The entity of PERSISTENT_EVENT.
 * <p>
 * You can implement your original methods here.
 * This class remains when re-generating.
 * </p>
 * @author DBFlute(AutoGenerator)
 */
public class PersistentEvent extends BsPersistentEvent {

    /** Serial version UID. (Default) */
    private static final long serialVersionUID = 1L;

    public PersistentEvent() {
        super();
    }

    public PersistentEvent(final Event event) {
        final List<PersistentEventValue> pEventValueList = getPersistentEventValueList();
        for (final Map.Entry<String, Object> entry : event.entrySet()) {
            final PersistentEventValue pEventValue = new PersistentEventValue();
            pEventValue.setName(entry.getKey());
            final Object value = entry.getValue();
            if (value != null) {
                pEventValue.setValue(value.toString());
                pEventValue.setClassType(value.getClass().getCanonicalName());
            } else {
                pEventValue.setValue("null");
                pEventValue.setClassType("null");
            }
            pEventValueList.add(pEventValue);
        }
        setCreatedBy(event.getCreatedBy());
        setCreatedTime(new Timestamp(event.getCreatedTime().getTime()));
    }

    public void updateValues() {
        final List<PersistentEventValue> pEventValueList = getPersistentEventValueList();
        for (final PersistentEventValue pEventValue : pEventValueList) {
            pEventValue.setEventId(getId());
        }
    }
}
