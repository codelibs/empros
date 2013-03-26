package org.codelibs.empros.db.cbean.nss;

import org.codelibs.empros.db.cbean.cq.PersistentEventCQ;

/**
 * The nest select set-upper of PERSISTENT_EVENT.
 * @author DBFlute(AutoGenerator)
 */
public class PersistentEventNss {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected PersistentEventCQ _query;

    public PersistentEventNss(final PersistentEventCQ query) {
        _query = query;
    }

    public boolean hasConditionQuery() {
        return _query != null;
    }

    // ===================================================================================
    //                                                                     Nested Relation
    //                                                                     ===============

}
