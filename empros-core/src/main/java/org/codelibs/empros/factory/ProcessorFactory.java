package org.codelibs.empros.factory;

import org.codelibs.empros.processor.EventProcessor;

/**
 * ProcessorFactory is a factory to create a Processor instance.
 * 
 * @author shinsuke
 *
 */
public interface ProcessorFactory {
    EventProcessor create();
}
