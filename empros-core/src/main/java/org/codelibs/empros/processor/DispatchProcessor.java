package org.codelibs.empros.processor;

import java.util.List;

public abstract class DispatchProcessor implements EventProcessor {

    protected List<EventProcessor> nextProcessorList;

    public DispatchProcessor(final List<EventProcessor> processorList) {
        nextProcessorList = processorList;
    }

    @Override
    public void process(final ProcessContext context,
            final ProcessorListener listener) {
        context.start(this);
        invokeNext(context, listener);
    }

    protected abstract void invokeNext(final ProcessContext context,
            final ProcessorListener listener);
}