package org.codelibs.empros.factory;

import org.codelibs.empros.processor.EventProcessor;
import org.codelibs.empros.processor.LoggingProcessor;

public class LoggingProcessorFactory implements ProcessorFactory {

    @Override
    public EventProcessor create() {
        return new LoggingProcessor();
    }

}
