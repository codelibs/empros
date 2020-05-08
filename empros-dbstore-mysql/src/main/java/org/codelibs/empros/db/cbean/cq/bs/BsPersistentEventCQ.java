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

import org.codelibs.empros.db.cbean.PersistentEventCB;
import org.codelibs.empros.db.cbean.cq.PersistentEventCQ;
import org.codelibs.empros.db.cbean.cq.PersistentEventValueCQ;
import org.codelibs.empros.db.cbean.cq.ciq.PersistentEventCIQ;
import org.dbflute.cbean.ConditionQuery;
import org.dbflute.cbean.chelper.HpQDRFunction;
import org.dbflute.cbean.coption.ConditionOption;
import org.dbflute.cbean.cvalue.ConditionValue;
import org.dbflute.cbean.sqlclause.SqlClause;
import org.dbflute.exception.IllegalConditionBeanOperationException;

/**
 * The base condition-query of PERSISTENT_EVENT.
 * @author DBFlute(AutoGenerator)
 */
public class BsPersistentEventCQ extends AbstractBsPersistentEventCQ {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected PersistentEventCIQ _inlineQuery;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BsPersistentEventCQ(ConditionQuery referrerQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
        super(referrerQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                 InlineView/OrClause
    //                                                                 ===================
    /**
     * Prepare InlineView query. <br>
     * {select ... from ... left outer join (select * from PERSISTENT_EVENT) where FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #CC4747">inline()</span>.setFoo...;
     * </pre>
     * @return The condition-query for InlineView query. (NotNull)
     */
    public PersistentEventCIQ inline() {
        if (_inlineQuery == null) { _inlineQuery = xcreateCIQ(); }
        _inlineQuery.xsetOnClause(false); return _inlineQuery;
    }

    protected PersistentEventCIQ xcreateCIQ() {
        PersistentEventCIQ ciq = xnewCIQ();
        ciq.xsetBaseCB(_baseCB);
        return ciq;
    }

    protected PersistentEventCIQ xnewCIQ() {
        return new PersistentEventCIQ(xgetReferrerQuery(), xgetSqlClause(), xgetAliasName(), xgetNestLevel(), this);
    }

    /**
     * Prepare OnClause query. <br>
     * {select ... from ... left outer join PERSISTENT_EVENT on ... and FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #CC4747">on()</span>.setFoo...;
     * </pre>
     * @return The condition-query for OnClause query. (NotNull)
     * @throws IllegalConditionBeanOperationException When this condition-query is base query.
     */
    public PersistentEventCIQ on() {
        if (isBaseQuery()) { throw new IllegalConditionBeanOperationException("OnClause for local table is unavailable!"); }
        PersistentEventCIQ inlineQuery = inline(); inlineQuery.xsetOnClause(true); return inlineQuery;
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    protected ConditionValue _id;
    public ConditionValue xdfgetId()
    { if (_id == null) { _id = nCV(); }
      return _id; }
    protected ConditionValue xgetCValueId() { return xdfgetId(); }

    public Map<String, PersistentEventValueCQ> xdfgetId_ExistsReferrer_PersistentEventValueList() { return xgetSQueMap("id_ExistsReferrer_PersistentEventValueList"); }
    public String keepId_ExistsReferrer_PersistentEventValueList(PersistentEventValueCQ sq) { return xkeepSQue("id_ExistsReferrer_PersistentEventValueList", sq); }

    public Map<String, PersistentEventValueCQ> xdfgetId_NotExistsReferrer_PersistentEventValueList() { return xgetSQueMap("id_NotExistsReferrer_PersistentEventValueList"); }
    public String keepId_NotExistsReferrer_PersistentEventValueList(PersistentEventValueCQ sq) { return xkeepSQue("id_NotExistsReferrer_PersistentEventValueList", sq); }

    public Map<String, PersistentEventValueCQ> xdfgetId_SpecifyDerivedReferrer_PersistentEventValueList() { return xgetSQueMap("id_SpecifyDerivedReferrer_PersistentEventValueList"); }
    public String keepId_SpecifyDerivedReferrer_PersistentEventValueList(PersistentEventValueCQ sq) { return xkeepSQue("id_SpecifyDerivedReferrer_PersistentEventValueList", sq); }

    public Map<String, PersistentEventValueCQ> xdfgetId_QueryDerivedReferrer_PersistentEventValueList() { return xgetSQueMap("id_QueryDerivedReferrer_PersistentEventValueList"); }
    public String keepId_QueryDerivedReferrer_PersistentEventValueList(PersistentEventValueCQ sq) { return xkeepSQue("id_QueryDerivedReferrer_PersistentEventValueList", sq); }
    public Map<String, Object> xdfgetId_QueryDerivedReferrer_PersistentEventValueListParameter() { return xgetSQuePmMap("id_QueryDerivedReferrer_PersistentEventValueList"); }
    public String keepId_QueryDerivedReferrer_PersistentEventValueListParameter(Object pm) { return xkeepSQuePm("id_QueryDerivedReferrer_PersistentEventValueList", pm); }

    /**
     * Add order-by as ascend. <br>
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @return this. (NotNull)
     */
    public BsPersistentEventCQ addOrderBy_Id_Asc() { regOBA("ID"); return this; }

    /**
     * Add order-by as descend. <br>
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @return this. (NotNull)
     */
    public BsPersistentEventCQ addOrderBy_Id_Desc() { regOBD("ID"); return this; }

    protected ConditionValue _createdBy;
    public ConditionValue xdfgetCreatedBy()
    { if (_createdBy == null) { _createdBy = nCV(); }
      return _createdBy; }
    protected ConditionValue xgetCValueCreatedBy() { return xdfgetCreatedBy(); }

    /**
     * Add order-by as ascend. <br>
     * CREATED_BY: {NotNull, VARCHAR(255)}
     * @return this. (NotNull)
     */
    public BsPersistentEventCQ addOrderBy_CreatedBy_Asc() { regOBA("CREATED_BY"); return this; }

    /**
     * Add order-by as descend. <br>
     * CREATED_BY: {NotNull, VARCHAR(255)}
     * @return this. (NotNull)
     */
    public BsPersistentEventCQ addOrderBy_CreatedBy_Desc() { regOBD("CREATED_BY"); return this; }

    protected ConditionValue _createdTime;
    public ConditionValue xdfgetCreatedTime()
    { if (_createdTime == null) { _createdTime = nCV(); }
      return _createdTime; }
    protected ConditionValue xgetCValueCreatedTime() { return xdfgetCreatedTime(); }

    /**
     * Add order-by as ascend. <br>
     * CREATED_TIME: {NotNull, DATETIME(19)}
     * @return this. (NotNull)
     */
    public BsPersistentEventCQ addOrderBy_CreatedTime_Asc() { regOBA("CREATED_TIME"); return this; }

    /**
     * Add order-by as descend. <br>
     * CREATED_TIME: {NotNull, DATETIME(19)}
     * @return this. (NotNull)
     */
    public BsPersistentEventCQ addOrderBy_CreatedTime_Desc() { regOBD("CREATED_TIME"); return this; }

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
    public BsPersistentEventCQ addOrderBy_VersionNo_Asc() { regOBA("VERSION_NO"); return this; }

    /**
     * Add order-by as descend. <br>
     * VERSION_NO: {NotNull, INT(10)}
     * @return this. (NotNull)
     */
    public BsPersistentEventCQ addOrderBy_VersionNo_Desc() { regOBD("VERSION_NO"); return this; }

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
    public BsPersistentEventCQ addSpecifiedDerivedOrderBy_Asc(String aliasName) { registerSpecifiedDerivedOrderBy_Asc(aliasName); return this; }

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
    public BsPersistentEventCQ addSpecifiedDerivedOrderBy_Desc(String aliasName) { registerSpecifiedDerivedOrderBy_Desc(aliasName); return this; }

    // ===================================================================================
    //                                                                         Union Query
    //                                                                         ===========
    public void reflectRelationOnUnionQuery(ConditionQuery bqs, ConditionQuery uqs) {
    }

    // ===================================================================================
    //                                                                       Foreign Query
    //                                                                       =============
    protected Map<String, Object> xfindFixedConditionDynamicParameterMap(String property) {
        return null;
    }

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    public Map<String, PersistentEventCQ> xdfgetScalarCondition() { return xgetSQueMap("scalarCondition"); }
    public String keepScalarCondition(PersistentEventCQ sq) { return xkeepSQue("scalarCondition", sq); }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    public Map<String, PersistentEventCQ> xdfgetSpecifyMyselfDerived() { return xgetSQueMap("specifyMyselfDerived"); }
    public String keepSpecifyMyselfDerived(PersistentEventCQ sq) { return xkeepSQue("specifyMyselfDerived", sq); }

    public Map<String, PersistentEventCQ> xdfgetQueryMyselfDerived() { return xgetSQueMap("queryMyselfDerived"); }
    public String keepQueryMyselfDerived(PersistentEventCQ sq) { return xkeepSQue("queryMyselfDerived", sq); }
    public Map<String, Object> xdfgetQueryMyselfDerivedParameter() { return xgetSQuePmMap("queryMyselfDerived"); }
    public String keepQueryMyselfDerivedParameter(Object pm) { return xkeepSQuePm("queryMyselfDerived", pm); }

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    protected Map<String, PersistentEventCQ> _myselfExistsMap;
    public Map<String, PersistentEventCQ> xdfgetMyselfExists() { return xgetSQueMap("myselfExists"); }
    public String keepMyselfExists(PersistentEventCQ sq) { return xkeepSQue("myselfExists", sq); }

    // ===================================================================================
    //                                                                       MyselfInScope
    //                                                                       =============
    public Map<String, PersistentEventCQ> xdfgetMyselfInScope() { return xgetSQueMap("myselfInScope"); }
    public String keepMyselfInScope(PersistentEventCQ sq) { return xkeepSQue("myselfInScope", sq); }

    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xCB() { return PersistentEventCB.class.getName(); }
    protected String xCQ() { return PersistentEventCQ.class.getName(); }
    protected String xCHp() { return HpQDRFunction.class.getName(); }
    protected String xCOp() { return ConditionOption.class.getName(); }
    protected String xMap() { return Map.class.getName(); }
}
