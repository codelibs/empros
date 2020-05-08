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
import org.codelibs.empros.db.cbean.*;
import org.dbflute.bhv.*;
import org.dbflute.bhv.referrer.*;

/**
 * The referrer loader of PERSISTENT_EVENT as TABLE. <br>
 * <pre>
 * [primary key]
 *     ID
 *
 * [column]
 *     ID, CREATED_BY, CREATED_TIME, VERSION_NO
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
 *     
 *
 * [referrer table]
 *     PERSISTENT_EVENT_VALUE
 *
 * [foreign property]
 *     
 *
 * [referrer property]
 *     persistentEventValueList
 * </pre>
 * @author DBFlute(AutoGenerator)
 */
public class LoaderOfPersistentEvent {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected List<PersistentEvent> _selectedList;
    protected BehaviorSelector _selector;
    protected PersistentEventBhv _myBhv; // lazy-loaded

    // ===================================================================================
    //                                                                   Ready for Loading
    //                                                                   =================
    public LoaderOfPersistentEvent ready(List<PersistentEvent> selectedList, BehaviorSelector selector)
    { _selectedList = selectedList; _selector = selector; return this; }

    protected PersistentEventBhv myBhv()
    { if (_myBhv != null) { return _myBhv; } else { _myBhv = _selector.select(PersistentEventBhv.class); return _myBhv; } }

    // ===================================================================================
    //                                                                       Load Referrer
    //                                                                       =============
    protected List<PersistentEventValue> _referrerPersistentEventValue;

    /**
     * Load referrer of persistentEventValueList by the set-upper of referrer. <br>
     * PERSISTENT_EVENT_VALUE by EVENT_ID, named 'persistentEventValueList'.
     * <pre>
     * <span style="color: #0000C0">persistentEventBhv</span>.<span style="color: #994747">load</span>(<span style="color: #553000">persistentEventList</span>, <span style="color: #553000">eventLoader</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">eventLoader</span>.<span style="color: #CC4747">loadPersistentEventValue</span>(<span style="color: #553000">valueCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *         <span style="color: #553000">valueCB</span>.setupSelect...
     *         <span style="color: #553000">valueCB</span>.query().set...
     *         <span style="color: #553000">valueCB</span>.query().addOrderBy...
     *     }); <span style="color: #3F7E5E">// you can load nested referrer from here</span>
     *     <span style="color: #3F7E5E">//}).withNestedReferrer(<span style="color: #553000">valueLoader</span> -&gt; {</span>
     *     <span style="color: #3F7E5E">//    valueLoader.load...</span>
     *     <span style="color: #3F7E5E">//});</span>
     * });
     * for (PersistentEvent persistentEvent : <span style="color: #553000">persistentEventList</span>) {
     *     ... = persistentEvent.<span style="color: #CC4747">getPersistentEventValueList()</span>;
     * }
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has settings before callback as follows:
     * <pre>
     * cb.query().setEventId_InScope(pkList);
     * cb.query().addOrderBy_EventId_Asc();
     * </pre>
     * @param refCBLambda The callback to set up referrer condition-bean for loading referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerLoaderGateway<LoaderOfPersistentEventValue> loadPersistentEventValue(ReferrerConditionSetupper<PersistentEventValueCB> refCBLambda) {
        myBhv().loadPersistentEventValue(_selectedList, refCBLambda).withNestedReferrer(refLs -> _referrerPersistentEventValue = refLs);
        return hd -> hd.handle(new LoaderOfPersistentEventValue().ready(_referrerPersistentEventValue, _selector));
    }

    // ===================================================================================
    //                                                                    Pull out Foreign
    //                                                                    ================
    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public List<PersistentEvent> getSelectedList() { return _selectedList; }
    public BehaviorSelector getSelector() { return _selector; }
}
