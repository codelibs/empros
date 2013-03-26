package org.codelibs.empros.db.cbean.cq.bs;

import java.util.Map;

import org.codelibs.empros.db.cbean.PersistentEventCB;
import org.codelibs.empros.db.cbean.cq.PersistentEventCQ;
import org.codelibs.empros.db.cbean.cq.PersistentEventValueCQ;
import org.codelibs.empros.db.cbean.cq.ciq.PersistentEventCIQ;
import org.seasar.dbflute.cbean.ConditionQuery;
import org.seasar.dbflute.cbean.cvalue.ConditionValue;
import org.seasar.dbflute.cbean.sqlclause.SqlClause;
import org.seasar.dbflute.exception.IllegalConditionBeanOperationException;

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
    public BsPersistentEventCQ(final ConditionQuery childQuery,
            final SqlClause sqlClause, final String aliasName,
            final int nestLevel) {
        super(childQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                 InlineView/OrClause
    //                                                                 ===================
    /**
     * Prepare InlineView query. <br />
     * {select ... from ... left outer join (select * from PERSISTENT_EVENT) where FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #FD4747">inline()</span>.setFoo...;
     * </pre>
     * @return The condition-query for InlineView query. (NotNull)
     */
    public PersistentEventCIQ inline() {
        if (_inlineQuery == null) {
            _inlineQuery = xcreateCIQ();
        }
        _inlineQuery.xsetOnClause(false);
        return _inlineQuery;
    }

    protected PersistentEventCIQ xcreateCIQ() {
        final PersistentEventCIQ ciq = xnewCIQ();
        ciq.xsetBaseCB(_baseCB);
        return ciq;
    }

    protected PersistentEventCIQ xnewCIQ() {
        return new PersistentEventCIQ(xgetReferrerQuery(), xgetSqlClause(),
                xgetAliasName(), xgetNestLevel(), this);
    }

    /**
     * Prepare OnClause query. <br />
     * {select ... from ... left outer join PERSISTENT_EVENT on ... and FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #FD4747">on()</span>.setFoo...;
     * </pre>
     * @return The condition-query for OnClause query. (NotNull)
     * @throws IllegalConditionBeanOperationException When this condition-query is base query.
     */
    public PersistentEventCIQ on() {
        if (isBaseQuery()) {
            throw new IllegalConditionBeanOperationException(
                    "OnClause for local table is unavailable!");
        }
        final PersistentEventCIQ inlineQuery = inline();
        inlineQuery.xsetOnClause(true);
        return inlineQuery;
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====

    protected ConditionValue _id;

    public ConditionValue getId() {
        if (_id == null) {
            _id = nCV();
        }
        return _id;
    }

    @Override
    protected ConditionValue getCValueId() {
        return getId();
    }

    protected Map<String, PersistentEventValueCQ> _id_ExistsReferrer_PersistentEventValueListMap;

    public Map<String, PersistentEventValueCQ> getId_ExistsReferrer_PersistentEventValueList() {
        return _id_ExistsReferrer_PersistentEventValueListMap;
    }

    @Override
    public String keepId_ExistsReferrer_PersistentEventValueList(
            final PersistentEventValueCQ subQuery) {
        if (_id_ExistsReferrer_PersistentEventValueListMap == null) {
            _id_ExistsReferrer_PersistentEventValueListMap = newLinkedHashMap();
        }
        final String key = "subQueryMapKey"
                + (_id_ExistsReferrer_PersistentEventValueListMap.size() + 1);
        _id_ExistsReferrer_PersistentEventValueListMap.put(key, subQuery);
        return "id_ExistsReferrer_PersistentEventValueList." + key;
    }

    protected Map<String, PersistentEventValueCQ> _id_NotExistsReferrer_PersistentEventValueListMap;

    public Map<String, PersistentEventValueCQ> getId_NotExistsReferrer_PersistentEventValueList() {
        return _id_NotExistsReferrer_PersistentEventValueListMap;
    }

    @Override
    public String keepId_NotExistsReferrer_PersistentEventValueList(
            final PersistentEventValueCQ subQuery) {
        if (_id_NotExistsReferrer_PersistentEventValueListMap == null) {
            _id_NotExistsReferrer_PersistentEventValueListMap = newLinkedHashMap();
        }
        final String key = "subQueryMapKey"
                + (_id_NotExistsReferrer_PersistentEventValueListMap.size() + 1);
        _id_NotExistsReferrer_PersistentEventValueListMap.put(key, subQuery);
        return "id_NotExistsReferrer_PersistentEventValueList." + key;
    }

    protected Map<String, PersistentEventValueCQ> _id_SpecifyDerivedReferrer_PersistentEventValueListMap;

    public Map<String, PersistentEventValueCQ> getId_SpecifyDerivedReferrer_PersistentEventValueList() {
        return _id_SpecifyDerivedReferrer_PersistentEventValueListMap;
    }

    @Override
    public String keepId_SpecifyDerivedReferrer_PersistentEventValueList(
            final PersistentEventValueCQ subQuery) {
        if (_id_SpecifyDerivedReferrer_PersistentEventValueListMap == null) {
            _id_SpecifyDerivedReferrer_PersistentEventValueListMap = newLinkedHashMap();
        }
        final String key = "subQueryMapKey"
                + (_id_SpecifyDerivedReferrer_PersistentEventValueListMap
                        .size() + 1);
        _id_SpecifyDerivedReferrer_PersistentEventValueListMap.put(key,
                subQuery);
        return "id_SpecifyDerivedReferrer_PersistentEventValueList." + key;
    }

    protected Map<String, PersistentEventValueCQ> _id_InScopeRelation_PersistentEventValueListMap;

    public Map<String, PersistentEventValueCQ> getId_InScopeRelation_PersistentEventValueList() {
        return _id_InScopeRelation_PersistentEventValueListMap;
    }

    @Override
    public String keepId_InScopeRelation_PersistentEventValueList(
            final PersistentEventValueCQ subQuery) {
        if (_id_InScopeRelation_PersistentEventValueListMap == null) {
            _id_InScopeRelation_PersistentEventValueListMap = newLinkedHashMap();
        }
        final String key = "subQueryMapKey"
                + (_id_InScopeRelation_PersistentEventValueListMap.size() + 1);
        _id_InScopeRelation_PersistentEventValueListMap.put(key, subQuery);
        return "id_InScopeRelation_PersistentEventValueList." + key;
    }

    protected Map<String, PersistentEventValueCQ> _id_NotInScopeRelation_PersistentEventValueListMap;

    public Map<String, PersistentEventValueCQ> getId_NotInScopeRelation_PersistentEventValueList() {
        return _id_NotInScopeRelation_PersistentEventValueListMap;
    }

    @Override
    public String keepId_NotInScopeRelation_PersistentEventValueList(
            final PersistentEventValueCQ subQuery) {
        if (_id_NotInScopeRelation_PersistentEventValueListMap == null) {
            _id_NotInScopeRelation_PersistentEventValueListMap = newLinkedHashMap();
        }
        final String key = "subQueryMapKey"
                + (_id_NotInScopeRelation_PersistentEventValueListMap.size() + 1);
        _id_NotInScopeRelation_PersistentEventValueListMap.put(key, subQuery);
        return "id_NotInScopeRelation_PersistentEventValueList." + key;
    }

    protected Map<String, PersistentEventValueCQ> _id_QueryDerivedReferrer_PersistentEventValueListMap;

    public Map<String, PersistentEventValueCQ> getId_QueryDerivedReferrer_PersistentEventValueList() {
        return _id_QueryDerivedReferrer_PersistentEventValueListMap;
    }

    @Override
    public String keepId_QueryDerivedReferrer_PersistentEventValueList(
            final PersistentEventValueCQ subQuery) {
        if (_id_QueryDerivedReferrer_PersistentEventValueListMap == null) {
            _id_QueryDerivedReferrer_PersistentEventValueListMap = newLinkedHashMap();
        }
        final String key = "subQueryMapKey"
                + (_id_QueryDerivedReferrer_PersistentEventValueListMap.size() + 1);
        _id_QueryDerivedReferrer_PersistentEventValueListMap.put(key, subQuery);
        return "id_QueryDerivedReferrer_PersistentEventValueList." + key;
    }

    protected Map<String, Object> _id_QueryDerivedReferrer_PersistentEventValueListParameterMap;

    public Map<String, Object> getId_QueryDerivedReferrer_PersistentEventValueListParameter() {
        return _id_QueryDerivedReferrer_PersistentEventValueListParameterMap;
    }

    @Override
    public String keepId_QueryDerivedReferrer_PersistentEventValueListParameter(
            final Object parameterValue) {
        if (_id_QueryDerivedReferrer_PersistentEventValueListParameterMap == null) {
            _id_QueryDerivedReferrer_PersistentEventValueListParameterMap = newLinkedHashMap();
        }
        final String key = "subQueryParameterKey"
                + (_id_QueryDerivedReferrer_PersistentEventValueListParameterMap
                        .size() + 1);
        _id_QueryDerivedReferrer_PersistentEventValueListParameterMap.put(key,
                parameterValue);
        return "id_QueryDerivedReferrer_PersistentEventValueListParameter."
                + key;
    }

    /** 
     * Add order-by as ascend. <br />
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @return this. (NotNull)
     */
    public BsPersistentEventCQ addOrderBy_Id_Asc() {
        regOBA("ID");
        return this;
    }

    /**
     * Add order-by as descend. <br />
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @return this. (NotNull)
     */
    public BsPersistentEventCQ addOrderBy_Id_Desc() {
        regOBD("ID");
        return this;
    }

    protected ConditionValue _createdBy;

    public ConditionValue getCreatedBy() {
        if (_createdBy == null) {
            _createdBy = nCV();
        }
        return _createdBy;
    }

    @Override
    protected ConditionValue getCValueCreatedBy() {
        return getCreatedBy();
    }

    /** 
     * Add order-by as ascend. <br />
     * CREATED_BY: {NotNull, VARCHAR(255)}
     * @return this. (NotNull)
     */
    public BsPersistentEventCQ addOrderBy_CreatedBy_Asc() {
        regOBA("CREATED_BY");
        return this;
    }

    /**
     * Add order-by as descend. <br />
     * CREATED_BY: {NotNull, VARCHAR(255)}
     * @return this. (NotNull)
     */
    public BsPersistentEventCQ addOrderBy_CreatedBy_Desc() {
        regOBD("CREATED_BY");
        return this;
    }

    protected ConditionValue _createdTime;

    public ConditionValue getCreatedTime() {
        if (_createdTime == null) {
            _createdTime = nCV();
        }
        return _createdTime;
    }

    @Override
    protected ConditionValue getCValueCreatedTime() {
        return getCreatedTime();
    }

    /** 
     * Add order-by as ascend. <br />
     * CREATED_TIME: {NotNull, TIMESTAMP(23, 10)}
     * @return this. (NotNull)
     */
    public BsPersistentEventCQ addOrderBy_CreatedTime_Asc() {
        regOBA("CREATED_TIME");
        return this;
    }

    /**
     * Add order-by as descend. <br />
     * CREATED_TIME: {NotNull, TIMESTAMP(23, 10)}
     * @return this. (NotNull)
     */
    public BsPersistentEventCQ addOrderBy_CreatedTime_Desc() {
        regOBD("CREATED_TIME");
        return this;
    }

    protected ConditionValue _versionNo;

    public ConditionValue getVersionNo() {
        if (_versionNo == null) {
            _versionNo = nCV();
        }
        return _versionNo;
    }

    @Override
    protected ConditionValue getCValueVersionNo() {
        return getVersionNo();
    }

    /** 
     * Add order-by as ascend. <br />
     * VERSION_NO: {NotNull, INTEGER(10)}
     * @return this. (NotNull)
     */
    public BsPersistentEventCQ addOrderBy_VersionNo_Asc() {
        regOBA("VERSION_NO");
        return this;
    }

    /**
     * Add order-by as descend. <br />
     * VERSION_NO: {NotNull, INTEGER(10)}
     * @return this. (NotNull)
     */
    public BsPersistentEventCQ addOrderBy_VersionNo_Desc() {
        regOBD("VERSION_NO");
        return this;
    }

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
     * }, <span style="color: #FD4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] asc</span>
     * cb.<span style="color: #FD4747">addSpecifiedDerivedOrderBy_Asc</span>(<span style="color: #FD4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsPersistentEventCQ addSpecifiedDerivedOrderBy_Asc(
            final String aliasName) {
        registerSpecifiedDerivedOrderBy_Asc(aliasName);
        return this;
    }

    /**
     * Add order-by for specified derived column as descend.
     * <pre>
     * cb.specify().derivedPurchaseList().max(new SubQuery&lt;PurchaseCB&gt;() {
     *     public void query(PurchaseCB subCB) {
     *         subCB.specify().columnPurchaseDatetime();
     *     }
     * }, <span style="color: #FD4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] desc</span>
     * cb.<span style="color: #FD4747">addSpecifiedDerivedOrderBy_Desc</span>(<span style="color: #FD4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsPersistentEventCQ addSpecifiedDerivedOrderBy_Desc(
            final String aliasName) {
        registerSpecifiedDerivedOrderBy_Desc(aliasName);
        return this;
    }

    // ===================================================================================
    //                                                                         Union Query
    //                                                                         ===========
    @Override
    protected void reflectRelationOnUnionQuery(
            final ConditionQuery baseQueryAsSuper,
            final ConditionQuery unionQueryAsSuper) {
    }

    // ===================================================================================
    //                                                                       Foreign Query
    //                                                                       =============
    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    protected Map<String, PersistentEventCQ> _scalarConditionMap;

    public Map<String, PersistentEventCQ> getScalarCondition() {
        return _scalarConditionMap;
    }

    @Override
    public String keepScalarCondition(final PersistentEventCQ subQuery) {
        if (_scalarConditionMap == null) {
            _scalarConditionMap = newLinkedHashMap();
        }
        final String key = "subQueryMapKey" + (_scalarConditionMap.size() + 1);
        _scalarConditionMap.put(key, subQuery);
        return "scalarCondition." + key;
    }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    protected Map<String, PersistentEventCQ> _specifyMyselfDerivedMap;

    public Map<String, PersistentEventCQ> getSpecifyMyselfDerived() {
        return _specifyMyselfDerivedMap;
    }

    @Override
    public String keepSpecifyMyselfDerived(final PersistentEventCQ subQuery) {
        if (_specifyMyselfDerivedMap == null) {
            _specifyMyselfDerivedMap = newLinkedHashMap();
        }
        final String key = "subQueryMapKey"
                + (_specifyMyselfDerivedMap.size() + 1);
        _specifyMyselfDerivedMap.put(key, subQuery);
        return "specifyMyselfDerived." + key;
    }

    protected Map<String, PersistentEventCQ> _queryMyselfDerivedMap;

    public Map<String, PersistentEventCQ> getQueryMyselfDerived() {
        return _queryMyselfDerivedMap;
    }

    @Override
    public String keepQueryMyselfDerived(final PersistentEventCQ subQuery) {
        if (_queryMyselfDerivedMap == null) {
            _queryMyselfDerivedMap = newLinkedHashMap();
        }
        final String key = "subQueryMapKey"
                + (_queryMyselfDerivedMap.size() + 1);
        _queryMyselfDerivedMap.put(key, subQuery);
        return "queryMyselfDerived." + key;
    }

    protected Map<String, Object> _qyeryMyselfDerivedParameterMap;

    public Map<String, Object> getQueryMyselfDerivedParameter() {
        return _qyeryMyselfDerivedParameterMap;
    }

    @Override
    public String keepQueryMyselfDerivedParameter(final Object parameterValue) {
        if (_qyeryMyselfDerivedParameterMap == null) {
            _qyeryMyselfDerivedParameterMap = newLinkedHashMap();
        }
        final String key = "subQueryParameterKey"
                + (_qyeryMyselfDerivedParameterMap.size() + 1);
        _qyeryMyselfDerivedParameterMap.put(key, parameterValue);
        return "queryMyselfDerivedParameter." + key;
    }

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    protected Map<String, PersistentEventCQ> _myselfExistsMap;

    public Map<String, PersistentEventCQ> getMyselfExists() {
        return _myselfExistsMap;
    }

    @Override
    public String keepMyselfExists(final PersistentEventCQ subQuery) {
        if (_myselfExistsMap == null) {
            _myselfExistsMap = newLinkedHashMap();
        }
        final String key = "subQueryMapKey" + (_myselfExistsMap.size() + 1);
        _myselfExistsMap.put(key, subQuery);
        return "myselfExists." + key;
    }

    // ===================================================================================
    //                                                                       MyselfInScope
    //                                                                       =============
    protected Map<String, PersistentEventCQ> _myselfInScopeMap;

    public Map<String, PersistentEventCQ> getMyselfInScope() {
        return _myselfInScopeMap;
    }

    @Override
    public String keepMyselfInScope(final PersistentEventCQ subQuery) {
        if (_myselfInScopeMap == null) {
            _myselfInScopeMap = newLinkedHashMap();
        }
        final String key = "subQueryMapKey" + (_myselfInScopeMap.size() + 1);
        _myselfInScopeMap.put(key, subQuery);
        return "myselfInScope." + key;
    }

    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xCB() {
        return PersistentEventCB.class.getName();
    }

    protected String xCQ() {
        return PersistentEventCQ.class.getName();
    }

    protected String xMap() {
        return Map.class.getName();
    }
}
