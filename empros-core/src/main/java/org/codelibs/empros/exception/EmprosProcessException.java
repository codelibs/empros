/*
 * Copyright 2013 the CodeLibs Project and the Others.
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
package org.codelibs.empros.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * EmprosProcessException contains multiple exceptions on a event processing.
 * 
 * @author shinsuke
 *
 */
public class EmprosProcessException extends EmprosRuntimeException {

    private static final long serialVersionUID = 1L;

    private final Throwable[] throwables;

    public EmprosProcessException(final Throwable[] throwables) {
        super("EEMC0002", new Object[0]);
        this.throwables = throwables;
    }

    public EmprosProcessException(final List<Throwable> throwableList) {
        super("EEMC0002", new Object[0]);
        throwables = throwableList.toArray(new Throwable[throwableList.size()]);
    }

    @Override
    public void printStackTrace(final PrintStream s) {
        super.printStackTrace(s);

        int count = 1;
        for (final Throwable t : throwables) {
            s.println("Caused " + count + ":");
            t.printStackTrace(s);
            count++;
        }
    }

    @Override
    public void printStackTrace(final PrintWriter s) {
        super.printStackTrace(s);

        int count = 1;
        for (final Throwable t : throwables) {
            s.println("Caused " + count + ":");
            t.printStackTrace(s);
            count++;
        }
    }
}
