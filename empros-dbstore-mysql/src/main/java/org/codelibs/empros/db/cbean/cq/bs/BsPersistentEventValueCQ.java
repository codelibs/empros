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
package org.codelibs.empros.db.cbean.cq.bs;

import java.util.Map;

import org.codelibs.empros.db.cbean.PersistentEventValueCB;
import org.codelibs.empros.db.cbean.cq.PersistentEventCQ;
import org.codelibs.empros.db.cbean.cq.PersistentEventValueCQ;
import org.codelibs.empros.db.cbean.cq.ciq.PersistentEventValueCIQ;
import org.dbflute.cbean.ConditionQuery;
import org.dbflute.cbean.chelper.HpQDRFunction;
import org.dbflute.cbean.coption.ConditionOption;
import org.dbflute.cbean.cvalue.ConditionValue;
import org.dbflute.cbean.sqlclause.SqlClause;
import org.dbflute.exception.IllegalConditionBeanOperationException;

/**
 * The base condition-query of PERSISTENT_EVENT_VALUE.
 * @author DBFlute(AutoGenerator)
 */
public class BsPersistentEventValueCQ extends AbstractBsPersistentEventValueCQ {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected PersistentEventValueCIQ _inlineQuery;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BsPersistentEventValueCQ(ConditionQuery referrerQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
        super(referrerQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                 InlineView/OrClause
    //                                                                 ===================
    /**
     * Prepare InlineView query. <br>
     * {select ... from ... left outer join (select * from PERSISTENT_EVENT_VALUE) where FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #CC4747">inline()</span>.setFoo...;
     * </pre>
     * @return The condition-query for InlineView query. (NotNull)
     */
    public PersistentEventValueCIQ inline() {
        if (_inlineQuery == null) { _inlineQuery = xcreateCIQ(); }
        _inlineQuery.xsetOnClause(false); return _inlineQuery;
    }

    protected PersistentEventValueCIQ xcreateCIQ() {
        PersistentEventValueCIQ ciq = xnewCIQ();
        ciq.xsetBaseCB(_baseCB);
        return ciq;
    }

    protected PersistentEventValueCIQ xnewCIQ() {
        return new PersistentEventValueCIQ(xgetReferrerQuery(), xgetSqlClause(), xgetAliasName(), xgetNestLevel(), this);
    }

    /**
     * Prepare OnClause query. <br>
     * {select ... from ... left outer join PERSISTENT_EVENT_VALUE on ... and FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #CC4747">on()</span>.setFoo...;
     * </pre>
     * @return The condition-query for OnClause query. (NotNull)
     * @throws IllegalConditionBeanOperationException When this condition-query is base query.
     */
    public PersistentEventValueCIQ on() {
        if (isBaseQuery()) { throw new IllegalConditionBeanOperationException("OnClause for local table is unavailable!"); }
        PersistentEventValueCIQ inlineQuery = inline(); inlineQuery.xsetOnClause(true); return inlineQuery;
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    protected ConditionValue _id;
    public ConditionValue xdfgetId()
    { if (_id == null) { _id = nCV(); }
      return _id; }
    protected ConditionValue xgetCValueId() { return xdfgetId(); }

    /**
     * Add order-by as ascend. <br>
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_Id_Asc() { regOBA("ID"); return this; }

    /**
     * Add order-by as descend. <br>
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_Id_Desc() { regOBD("ID"); return this; }

    protected ConditionValue _eventId;
    public ConditionValue xdfgetEventId()
    { if (_eventId == null) { _eventId = nCV(); }
      return _eventId; }
    protected ConditionValue xgetCValueEventId() { return xdfgetEventId(); }

    /**
     * Add order-by as ascend. <br>
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_EventId_Asc() { regOBA("EVENT_ID"); return this; }

    /**
     * Add order-by as descend. <br>
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_EventId_Desc() { regOBD("EVENT_ID"); return this; }

    protected ConditionValue _name;
    public ConditionValue xdfgetName()
    { if (_name == null) { _name = nCV(); }
      return _name; }
    protected ConditionValue xgetCValueName() { return xdfgetName(); }

    /**
     * Add order-by as ascend. <br>
     * NAME: {NotNull, VARCHAR(4000)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_Name_Asc() { regOBA("NAME"); return this; }

    /**
     * Add order-by as descend. <br>
     * NAME: {NotNull, VARCHAR(4000)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_Name_Desc() { regOBD("NAME"); return this; }

    protected ConditionValue _value;
    public ConditionValue xdfgetValue()
    { if (_value == null) { _value = nCV(); }
      return _value; }
    protected ConditionValue xgetCValueValue() { return xdfgetValue(); }

    /**
     * Add order-by as ascend. <br>
     * VALUE: {NotNull, VARCHAR(4000)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_Value_Asc() { regOBA("VALUE"); return this; }

    /**
     * Add order-by as descend. <br>
     * VALUE: {NotNull, VARCHAR(4000)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_Value_Desc() { regOBD("VALUE"); return this; }

    protected ConditionValue _classType;
    public ConditionValue xdfgetClassType()
    { if (_classType == null) { _classType = nCV(); }
      return _classType; }
    protected ConditionValue xgetCValueClassType() { return xdfgetClassType(); }

    /**
     * Add order-by as ascend. <br>
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_ClassType_Asc() { regOBA("CLASS_TYPE"); return this; }

    /**
     * Add order-by as descend. <br>
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_ClassType_Desc() { regOBD("CLASS_TYPE"); return this; }

    protected ConditionValue _versionNo;
    public ConditionValue xdfgetVersionNo()
    { if (_versionNo == null) { _versionNo = nCV(); }
      return _versionNo; }
    protected ConditionValue xgetCValueVersionNo() { return xdfgetVersionNo(); }

    /**
     * Add order-by as ascend. <br>
     * VERSION_NO: {NotNull, INT(10)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_VersionNo_Asc() { regOBA("VERSION_NO"); return this; }

    /**
     * Add order-by as descend. <br>
     * VERSION_NO: {NotNull, INT(10)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_VersionNo_Desc() { regOBD("VERSION_NO"); return this; }

    // ===================================================================================
    //                                                             SpecifiedDerivedOrderBy
    //                                                             =======================
    /**
     * Add order-by for specified derived column as ascend.
     * <pre>
     * cb.specify().derivedPurchaseList().max(new SubQuery&lt;PurchaseCB&gt;() {
     *     public void query(PurchaseCB subCB) {
     *         subCB.specify().columnPurchaseDatetime();
     *     }
     * }, <span style="color: #CC4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] asc</span>
     * cb.<span style="color: #CC4747">addSpecifiedDerivedOrderBy_Asc</span>(<span style="color: #CC4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addSpecifiedDerivedOrderBy_Asc(String aliasName) { registerSpecifiedDerivedOrderBy_Asc(aliasName); return this; }

    /**
     * Add order-by for specified derived column as descend.
     * <pre>
     * cb.specify().derivedPurchaseList().max(new SubQuery&lt;PurchaseCB&gt;() {
     *     public void query(PurchaseCB subCB) {
     *         subCB.specify().columnPurchaseDatetime();
     *     }
     * }, <span style="color: #CC4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] desc</span>
     * cb.<span style="color: #CC4747">addSpecifiedDerivedOrderBy_Desc</span>(<span style="color: #CC4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addSpecifiedDerivedOrderBy_Desc(String aliasName) { registerSpecifiedDerivedOrderBy_Desc(aliasName); return this; }

    // ===================================================================================
    //                                                                         Union Query
    //                                                                         ===========
    public void reflectRelationOnUnionQuery(ConditionQuery bqs, ConditionQuery uqs) {
        PersistentEventValueCQ bq = (PersistentEventValueCQ)bqs;
        PersistentEventValueCQ uq = (PersistentEventValueCQ)uqs;
        if (bq.hasConditionQueryPersistentEvent()) {
            uq.queryPersistentEvent().reflectRelationOnUnionQuery(bq.queryPersistentEvent(), uq.queryPersistentEvent());
        }
    }

    // ===================================================================================
    //                                                                       Foreign Query
    //                                                                       =============
    /**
     * Get the condition-query for relation table. <br>
     * PERSISTENT_EVENT by my EVENT_ID, named 'persistentEvent'.
     * @return The instance of condition-query. (NotNull)
     */
    public PersistentEventCQ queryPersistentEvent() {
        return xdfgetConditionQueryPersistentEvent();
    }
    public PersistentEventCQ xdfgetConditionQueryPersistentEvent() {
        String prop = "persistentEvent";
        if (!xhasQueRlMap(prop)) { xregQueRl(prop, xcreateQueryPersistentEvent()); xsetupOuterJoinPersistentEvent(); }
        return xgetQueRlMap(prop);
    }
    protected PersistentEventCQ xcreateQueryPersistentEvent() {
        String nrp = xresolveNRP("PERSISTENT_EVENT_VALUE", "persistentEvent"); String jan = xresolveJAN(nrp, xgetNNLvl());
        return xinitRelCQ(new PersistentEventCQ(this, xgetSqlClause(), jan, xgetNNLvl()), _baseCB, "persistentEvent", nrp);
    }
    protected void xsetupOuterJoinPersistentEvent() { xregOutJo("persistentEvent"); }
    public boolean hasConditionQueryPersistentEvent() { return xhasQueRlMap("persistentEvent"); }

    protected Map<String, Object> xfindFixedConditionDynamicParameterMap(String property) {
        return null;
    }

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    public Map<String, PersistentEventValueCQ> xdfgetScalarCondition() { return xgetSQueMap("scalarCondition"); }
    public String keepScalarCondition(PersistentEventValueCQ sq) { return xkeepSQue("scalarCondition", sq); }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    public Map<String, PersistentEventValueCQ> xdfgetSpecifyMyselfDerived() { return xgetSQueMap("specifyMyselfDerived"); }
    public String keepSpecifyMyselfDerived(PersistentEventValueCQ sq) { return xkeepSQue("specifyMyselfDerived", sq); }

    public Map<String, PersistentEventValueCQ> xdfgetQueryMyselfDerived() { return xgetSQueMap("queryMyselfDerived"); }
    public String keepQueryMyselfDerived(PersistentEventValueCQ sq) { return xkeepSQue("queryMyselfDerived", sq); }
    public Map<String, Object> xdfgetQueryMyselfDerivedParameter() { return xgetSQuePmMap("queryMyselfDerived"); }
    public String keepQueryMyselfDerivedParameter(Object pm) { return xkeepSQuePm("queryMyselfDerived", pm); }

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    protected Map<String, PersistentEventValueCQ> _myselfExistsMap;
    public Map<String, PersistentEventValueCQ> xdfgetMyselfExists() { return xgetSQueMap("myselfExists"); }
    public String keepMyselfExists(PersistentEventValueCQ sq) { return xkeepSQue("myselfExists", sq); }

    // ===================================================================================
    //                                                                       MyselfInScope
    //                                                                       =============
    public Map<String, PersistentEventValueCQ> xdfgetMyselfInScope() { return xgetSQueMap("myselfInScope"); }
    public String keepMyselfInScope(PersistentEventValueCQ sq) { return xkeepSQue("myselfInScope", sq); }

    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xCB() { return PersistentEventValueCB.class.getName(); }
    protected String xCQ() { return PersistentEventValueCQ.class.getName(); }
    protected String xCHp() { return HpQDRFunction.class.getName(); }
    protected String xCOp() { return ConditionOption.class.getName(); }
    protected String xMap() { return Map.class.getName(); }
}
