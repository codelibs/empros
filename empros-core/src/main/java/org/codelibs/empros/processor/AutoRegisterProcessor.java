package org.codelibs.empros.processor;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

import org.codelibs.core.CoreLibConstants;
import org.codelibs.empros.exception.EmprosAutoRegisterException;
import org.codelibs.empros.factory.ProcessorFactory;
import org.seasar.util.io.InputStreamUtil;
import org.seasar.util.lang.ClassUtil;
import org.seasar.util.net.URLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AutoRegisterProcessor registers processors from META-INF/empros/ProcessorFactory automatically.
 * 
 * @author shinsuke
 *
 */
public class AutoRegisterProcessor extends ParallelProcessor {

    private static final String PROCESSOR_FACTORY_RESOURCE = "META-INF/empros/ProcessorFactory";

    private static final Logger logger = LoggerFactory
            .getLogger(AutoRegisterProcessor.class);

    public AutoRegisterProcessor(final int threadPoolSize) {
        super(new ArrayList<EventProcessor>(), threadPoolSize);

        Enumeration<URL> resources = null;
        try {
            resources = Thread.currentThread().getContextClassLoader()
                    .getResources(PROCESSOR_FACTORY_RESOURCE);
        } catch (final IOException e) {
            throw new EmprosAutoRegisterException("EEMC0005",
                    new Object[] { PROCESSOR_FACTORY_RESOURCE }, e);
        }

        while (resources.hasMoreElements()) {
            final URL url = resources.nextElement();
            try {
                final String content = new String(
                        InputStreamUtil.getBytes(URLUtil.openStream(url)),
                        CoreLibConstants.UTF_8);
                for (final String value : content.split("\n")) {
                    if (org.seasar.util.lang.StringUtil.isNotBlank(value)) {
                        final String factoryClassName = value.trim();
                        if (factoryClassName.charAt(0) == '#') {
                            continue;
                        }
                        final Class<ProcessorFactory> factoryClass = ClassUtil
                                .forName(factoryClassName);
                        final ProcessorFactory processorFactory = factoryClass
                                .newInstance();
                        nextProcessorList.add(processorFactory.create());
                        logger.info("{} loaded.", factoryClass);
                    }
                }
            } catch (final Exception e) {
                throw new EmprosAutoRegisterException("EEMC0005",
                        new Object[] { url.toExternalForm() }, e);
            }
        }

    }
}
