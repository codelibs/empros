package org.codelibs.empros.exception;

public class EmprosDataStoreException extends EmprosRuntimeException {

    private static final long serialVersionUID = 1L;

    public EmprosDataStoreException(final String messageCode,
            final Object[] args, final Throwable cause) {
        super(messageCode, args, cause);
    }

    public EmprosDataStoreException(final String messageCode,
            final Object[] args) {
        super(messageCode, args);
    }

    public EmprosDataStoreException(final String messageCode,
            final Throwable cause) {
        super(messageCode, cause);
    }

    public EmprosDataStoreException(final String messageCode) {
        super(messageCode);
    }

}
