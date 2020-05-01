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
package org.codelibs.empros.processor;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.codelibs.core.CoreLibConstants;
import org.codelibs.core.io.InputStreamUtil;
import org.codelibs.core.lang.ClassUtil;
import org.codelibs.core.net.URLUtil;
import org.codelibs.empros.exception.EmprosAutoRegisterException;
import org.codelibs.empros.factory.ProcessorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AutoRegisterProcessor registers processors from META-INF/empros/ProcessorFactory automatically.
 * 
 * @author shinsuke
 *
 */
public class AutoRegisterProcessor extends ParallelProcessor {

    protected static final String PROCESSOR_FACTORY_RESOURCE = "META-INF/empros/ProcessorFactory";

    private static final Logger logger = LoggerFactory
            .getLogger(AutoRegisterProcessor.class);

    protected List<ProcessorFactory> processorFactoryList = new ArrayList<ProcessorFactory>();

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
                    if (org.codelibs.core.lang.StringUtil.isNotBlank(value)) {
                        final String factoryClassName = value.trim();
                        if (factoryClassName.charAt(0) == '#') {
                            continue;
                        }
                        final Class<ProcessorFactory> factoryClass = ClassUtil
                                .forName(factoryClassName);
                        final ProcessorFactory processorFactory = factoryClass
                                .newInstance();
                        processorFactoryList.add(processorFactory);
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

    @Override
    public void destroy() {
        super.destroy();
        for (final ProcessorFactory factory : processorFactoryList) {
            try {
                factory.destroy();
            } catch (final Exception e) {
                logger.error("Failed to destroy " + factory, e);
            }
        }
    }
}
