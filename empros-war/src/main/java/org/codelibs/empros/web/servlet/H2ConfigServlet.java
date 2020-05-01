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
package org.codelibs.empros.web.servlet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.codelibs.core.misc.Disposable;
import org.codelibs.core.misc.DisposableUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class H2ConfigServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory
            .getLogger(H2ConfigServlet.class);

    private static final long serialVersionUID = 1L;

    protected Class<?> serverClass;

    protected Object server = null;

    @Override
    public void init() throws ServletException {
        final List<String> argList = new ArrayList<String>();
        String value;

        try {
            argList.add("-baseDir");
            value = getServletConfig().getInitParameter("baseDir");
            if (value != null) {
                argList.add(value);
            } else {
                argList.add(getServletContext().getRealPath("/WEB-INF/db/"));
            }

            argList.add("-tcp");

            value = getServletConfig().getInitParameter("tcpAllowOthers");
            if (value != null && "true".equalsIgnoreCase(value)) {
                argList.add("-tcpAllowOthers");
            }

            value = getServletConfig().getInitParameter("tcpPort");
            if (value != null) {
                argList.add("-tcpPort");
                argList.add(value);
            }

            value = getServletConfig().getInitParameter("tcpSSL");
            if (value != null && "true".equalsIgnoreCase(value)) {
                argList.add("-tcpSSL");
            }

            value = getServletConfig().getInitParameter("tcpPassword");
            if (value != null) {
                argList.add("-tcpPassword");
                argList.add(value);
            }

            if (logger.isInfoEnabled()) {
                logger.info("Starting H2 server...");
            }
            server = getH2Server(argList.toArray(new String[argList.size()]));
            if (logger.isInfoEnabled()) {
                if (server == null) {
                    logger.info("H2 DataStore Server is not supported.");
                } else {
                    logger.info("H2 DataStore Server is running.");
                }
            }
        } catch (final Exception e) {
            throw new ServletException("Could not start H2 DataStore..", e);
        }
    }

    @Override
    public void destroy() {
        DisposableUtil.add(new Disposable() {
            @Override
            public void dispose() {
                if (server != null) {
                    try {
                        final Method stopMethod = serverClass.getMethod("stop",
                                (Class[]) null);
                        stopMethod.invoke(server, (Object[]) null);
                        logger.info("H2 DataStore Server is stopped.");
                    } catch (final Exception e) {
                        logger.warn("Could not stop H2 DataStore.", e);
                    }
                }
            }
        });
    }

    private Object getH2Server(final String[] args)
            throws IllegalAccessException, InvocationTargetException,
            SecurityException, NoSuchMethodException {
        try {
            serverClass = Class.forName("org.h2.tools.Server");
        } catch (final ClassNotFoundException e) {
            return null;
        }

        final Method createTcpServerMethod = serverClass.getMethod(
                "createTcpServer", new Class<?>[] { String[].class });
        final Object h2Server = createTcpServerMethod.invoke(null,
                new Object[] { args });

        final Method startMethod = serverClass.getMethod("start",
                (Class[]) null);
        return startMethod.invoke(h2Server, (Object[]) null);
    }

}
