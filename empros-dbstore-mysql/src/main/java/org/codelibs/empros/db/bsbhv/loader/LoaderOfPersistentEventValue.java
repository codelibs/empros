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
package org.codelibs.empros.db.bsbhv.loader;

import java.util.List;

import org.codelibs.empros.db.exbhv.*;
import org.codelibs.empros.db.exentity.*;
import org.dbflute.bhv.*;

/**
 * The referrer loader of PERSISTENT_EVENT_VALUE as TABLE. <br>
 * <pre>
 * [primary key]
 *     ID
 *
 * [column]
 *     ID, EVENT_ID, NAME, VALUE, CLASS_TYPE, VERSION_NO
 *
 * [sequence]
 *     
 *
 * [identity]
 *     ID
 *
 * [version-no]
 *     VERSION_NO
 *
 * [foreign table]
 *     PERSISTENT_EVENT
 *
 * [referrer table]
 *     
 *
 * [foreign property]
 *     persistentEvent
 *
 * [referrer property]
 *     
 * </pre>
 * @author DBFlute(AutoGenerator)
 */
public class LoaderOfPersistentEventValue {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected List<PersistentEventValue> _selectedList;
    protected BehaviorSelector _selector;
    protected PersistentEventValueBhv _myBhv; // lazy-loaded

    // ===================================================================================
    //                                                                   Ready for Loading
    //                                                                   =================
    public LoaderOfPersistentEventValue ready(List<PersistentEventValue> selectedList, BehaviorSelector selector)
    { _selectedList = selectedList; _selector = selector; return this; }

    protected PersistentEventValueBhv myBhv()
    { if (_myBhv != null) { return _myBhv; } else { _myBhv = _selector.select(PersistentEventValueBhv.class); return _myBhv; } }

    // ===================================================================================
    //                                                                    Pull out Foreign
    //                                                                    ================
    protected LoaderOfPersistentEvent _foreignPersistentEventLoader;
    public LoaderOfPersistentEvent pulloutPersistentEvent() {
        if (_foreignPersistentEventLoader == null)
        { _foreignPersistentEventLoader = new LoaderOfPersistentEvent().ready(myBhv().pulloutPersistentEvent(_selectedList), _selector); }
        return _foreignPersistentEventLoader;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public List<PersistentEventValue> getSelectedList() { return _selectedList; }
    public BehaviorSelector getSelector() { return _selector; }
}
