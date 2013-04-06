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
 * EmprosParallelProcessException contains multiple exceptions on a parallel processing.
 * 
 * @author shinsuke
 *
 */
public class EmprosParallelProcessException extends EmprosRuntimeException {

    private static final long serialVersionUID = 1L;

    private final List<Exception> exceptionList;

    public EmprosParallelProcessException(final int processed,
            final List<Exception> exceptionList) {
        super("EEMC0002", new Object[] { processed });
        this.exceptionList = exceptionList;
    }

    @Override
    public void printStackTrace(final PrintStream s) {
        super.printStackTrace(s);

        int count = 1;
        for (final Exception e : exceptionList) {
            s.println("Caused " + count + ":");
            e.printStackTrace(s);
            count++;
        }
    }

    @Override
    public void printStackTrace(final PrintWriter s) {
        super.printStackTrace(s);

        int count = 1;
        for (final Exception e : exceptionList) {
            s.println("Caused " + count + ":");
            e.printStackTrace(s);
            count++;
        }
    }
}
