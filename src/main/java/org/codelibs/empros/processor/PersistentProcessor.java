package org.codelibs.empros.processor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.codelibs.empros.db.exbhv.PersistentEventBhv;
import org.codelibs.empros.db.exbhv.PersistentEventValueBhv;
import org.codelibs.empros.db.exentity.PersistentEvent;
import org.codelibs.empros.event.Event;

public class PersistentProcessor extends EventProcessor {
    @Resource
    protected PersistentEventBhv persistentEventBhv;

    @Resource
    protected PersistentEventValueBhv persistentEventValueBhv;

    public PersistentProcessor() {
        super();
    }

    public PersistentProcessor(final List<EventProcessor> processorList) {
        super(processorList);
    }

    @Override
    public List<Event> process(final List<Event> eventList) {

        final List<PersistentEvent> pEventList = new ArrayList<>(
                eventList.size());
        for (final Event event : eventList) {
            pEventList.add(new PersistentEvent(event));
        }

        store(pEventList);

        return eventList;
    }

    protected void store(final List<PersistentEvent> pEventList) {
        for (final PersistentEvent pEvent : pEventList) {
            persistentEventBhv.insert(pEvent);
            pEvent.updateValues();
            persistentEventValueBhv.batchInsert(pEvent
                    .getPersistentEventValueList());
        }
    }

}
