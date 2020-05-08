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
package org.codelibs.empros.db.exentity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        setCreatedTime(LocalDateTime.parse(event.getCreatedTime().toString()));
    }

    public void updateValues() {
        final List<PersistentEventValue> pEventValueList = getPersistentEventValueList();
        for (final PersistentEventValue pEventValue : pEventValueList) {
            pEventValue.setEventId(getId());
        }
    }
}
