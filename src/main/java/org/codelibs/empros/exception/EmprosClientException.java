package org.codelibs.empros.exception;

public class EmprosClientException extends EmprosRuntimeException {

    private static final long serialVersionUID = 1L;

    public EmprosClientException(final String messageCode, final Object[] args,
            final Throwable cause) {
        super(messageCode, args, cause);
    }

    public EmprosClientException(final String messageCode, final Object[] args) {
        super(messageCode, args);
    }

    public EmprosClientException(final String messageCode, final Throwable cause) {
        super(messageCode, cause);
    }

}
