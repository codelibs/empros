package org.codelibs.empros.exception;

/**
 * EmprosProcessTimeoutException is thrown when processors exceeded the timeout.
 * 
 * @author shinsuke
 *
 */
public class EmprosProcessTimeoutException extends EmprosRuntimeException {

    private static final long serialVersionUID = 1L;

    public EmprosProcessTimeoutException(final String messageCode,
            final Object[] args) {
        super(messageCode, args);
    }

}
