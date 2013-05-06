package org.codelibs.empros.processor;

public interface ProcessorListener {

    void onSuccess(final ProcessContext context);

    void onFailure(final Throwable t);

}
