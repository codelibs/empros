package org.codelibs.empros.db.cbean.nss;

import org.codelibs.empros.db.cbean.cq.PersistentEventValueCQ;
import org.seasar.dbflute.cbean.ConditionQuery;

/**
 * The nest select set-upper of PERSISTENT_EVENT_VALUE.
 * @author DBFlute(AutoGenerator)
 */
public class PersistentEventValueNss {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected PersistentEventValueCQ _query;

    public PersistentEventValueNss(final PersistentEventValueCQ query) {
        _query = query;
    }

    public boolean hasConditionQuery() {
        return _query != null;
    }

    // ===================================================================================
    //                                                                     Nested Relation
    //                                                                     ===============
    public PersistentEventNss withPersistentEvent() {
        _query.doNss(new PersistentEventValueCQ.NssCall() {
            @Override
            public ConditionQuery qf() {
                return _query.queryPersistentEvent();
            }
        });
        return new PersistentEventNss(_query.queryPersistentEvent());
    }

}
