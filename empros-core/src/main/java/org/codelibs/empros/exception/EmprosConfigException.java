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
package org.codelibs.empros.exception;

/**
 * EmprosClientException is thrown when an client request is invalid.
 * 
 * @author shinsuke
 *
 */
public class EmprosConfigException extends EmprosRuntimeException {

    private static final long serialVersionUID = 1L;

    public EmprosConfigException(final String messageCode, final Object[] args,
            final Throwable cause) {
        super(messageCode, args, cause);
    }

    public EmprosConfigException(final String messageCode, final Object... args) {
        super(messageCode, args);
    }

    public EmprosConfigException(final String messageCode, final Throwable cause) {
        super(messageCode, cause);
    }

}
