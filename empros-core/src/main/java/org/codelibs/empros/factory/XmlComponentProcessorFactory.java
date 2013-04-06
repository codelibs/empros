package org.codelibs.empros.factory;

import org.codelibs.empros.processor.EventProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * XmlComponentProcessorFactory loads processors from Spring's XML files.
 * 
 * @author shinsuke
 *
 */
public abstract class XmlComponentProcessorFactory implements ProcessorFactory {
    protected ApplicationContext context;

    protected String eventProcessorName = "eventProcessor";

    public XmlComponentProcessorFactory(final String[] configFiles) {
        context = new ClassPathXmlApplicationContext(configFiles);
    }

    @Override
    public EventProcessor create() {
        return context.getBean(eventProcessorName, EventProcessor.class);
    }

}
