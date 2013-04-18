package org.codelibs.empros.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ProcessCallback {
    protected List<Throwable> failureList = Collections
            .synchronizedList(new ArrayList<Throwable>());

    protected AtomicBoolean processed = new AtomicBoolean(false);

    public abstract void onSuccess();

    public void onFailure(final Throwable t) {
        failureList.add(t);
    }

}
