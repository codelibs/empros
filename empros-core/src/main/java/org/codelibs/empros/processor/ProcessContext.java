package org.codelibs.empros.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.codelibs.empros.event.Event;

/**
 * ProcessContext contains events information and is passed to a pipeline of event processors.
 * 
 * @author shinsuke
 *
 */
public class ProcessContext {
    private List<Event> incomingEventList;

    private List<Event> processingEventList;

    private final AtomicLong processed = new AtomicLong(0);

    private Object response;

    protected ProcessContext() {
    }

    public ProcessContext(final List<Event> eventList) {
        incomingEventList = Collections.unmodifiableList(eventList);
    }

    public void addNumOfProcessedEvents(final long num) {
        processed.addAndGet(num);
    }

    public void setResponse(final Object response) {
        this.response = response;
    }

    public Object getResponse() {
        return response;
    }

    public List<Event> getIncomingEventList() {
        return incomingEventList;
    }

    public List<Event> getProcessingEventList() {
        return processingEventList;
    }

    public void setProcessingEventList(final List<Event> processingEventList) {
        this.processingEventList = processingEventList;
    }

    public long getProcessed() {
        return processed.longValue();
    }

    @Override
    protected ProcessContext clone() {
        final ProcessContext context = new ProcessContext();
        context.incomingEventList = incomingEventList;
        if (processingEventList != null) {
            context.processingEventList = new ArrayList<Event>(
                    processingEventList.size());
            Collections.copy(context.processingEventList, processingEventList);
        }
        context.response = response;
        return context;
    }
}
