package org.codelibs.empros.util;

import java.util.List;

import org.codelibs.empros.event.Event;
import org.codelibs.empros.processor.EventProcessor;
import org.codelibs.empros.processor.ProcessContext;
import org.codelibs.empros.processor.ProcessorListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessorUtil {
    private static final Logger logger = LoggerFactory
            .getLogger(ProcessorUtil.class);

    protected ProcessorUtil() {
        // nothing
    }

    public static List<Event> getCurrentEventList(final ProcessContext context) {
        final List<Event> processingEventList = context
                .getProcessingEventList();
        if (processingEventList != null) {
            return processingEventList;
        }
        return context.getIncomingEventList();
    }

    public static void finish(final ProcessContext context,
            final EventProcessor processor, final ProcessorListener listener) {
        try {
            if (listener != null) {
                listener.onSuccess(context);
            }
        } catch (final Exception e) {
            logger.error("Uncaught exception.", e);
        } finally {
            context.finish(processor);
        }
    }

    public static void fail(final ProcessContext context,
            final EventProcessor processor, final ProcessorListener listener,
            final Throwable t) {
        try {
            if (listener != null) {
                listener.onFailure(t);
            }
        } catch (final Exception e) {
            logger.error("Uncaught exception.", e);
        } finally {
            context.addFailure(t);
            if (processor != null) {
                context.finish(processor);
            }
        }
    }
}
