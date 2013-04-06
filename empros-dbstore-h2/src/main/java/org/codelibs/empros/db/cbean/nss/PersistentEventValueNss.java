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
