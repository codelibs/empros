package org.codelibs.empros.exception;

import org.seasar.util.exception.SRuntimeException;

public class EmprosRuntimeException extends SRuntimeException {

    private static final long serialVersionUID = 1L;

    public EmprosRuntimeException(final String messageCode,
            final Object[] args, final Throwable cause) {
        super(messageCode, args, cause);
    }

    public EmprosRuntimeException(final String messageCode, final Object[] args) {
        super(messageCode, args);
    }

    public EmprosRuntimeException(final String messageCode,
            final Throwable cause) {
        super(messageCode, new Object[] {}, cause);
    }

    public EmprosRuntimeException(final String messageCode) {
        super(messageCode, new Object[0]);
    }

}
