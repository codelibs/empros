package org.codelibs.empros.db.cbean.cq.bs;

import java.util.Map;

import org.codelibs.empros.db.cbean.PersistentEventValueCB;
import org.codelibs.empros.db.cbean.cq.PersistentEventCQ;
import org.codelibs.empros.db.cbean.cq.PersistentEventValueCQ;
import org.codelibs.empros.db.cbean.cq.ciq.PersistentEventValueCIQ;
import org.seasar.dbflute.cbean.ConditionQuery;
import org.seasar.dbflute.cbean.cvalue.ConditionValue;
import org.seasar.dbflute.cbean.sqlclause.SqlClause;
import org.seasar.dbflute.exception.IllegalConditionBeanOperationException;

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
    public BsPersistentEventValueCQ(final ConditionQuery childQuery,
            final SqlClause sqlClause, final String aliasName,
            final int nestLevel) {
        super(childQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                 InlineView/OrClause
    //                                                                 ===================
    /**
     * Prepare InlineView query. <br />
     * {select ... from ... left outer join (select * from PERSISTENT_EVENT_VALUE) where FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #FD4747">inline()</span>.setFoo...;
     * </pre>
     * @return The condition-query for InlineView query. (NotNull)
     */
    public PersistentEventValueCIQ inline() {
        if (_inlineQuery == null) {
            _inlineQuery = xcreateCIQ();
        }
        _inlineQuery.xsetOnClause(false);
        return _inlineQuery;
    }

    protected PersistentEventValueCIQ xcreateCIQ() {
        final PersistentEventValueCIQ ciq = xnewCIQ();
        ciq.xsetBaseCB(_baseCB);
        return ciq;
    }

    protected PersistentEventValueCIQ xnewCIQ() {
        return new PersistentEventValueCIQ(xgetReferrerQuery(),
                xgetSqlClause(), xgetAliasName(), xgetNestLevel(), this);
    }

    /**
     * Prepare OnClause query. <br />
     * {select ... from ... left outer join PERSISTENT_EVENT_VALUE on ... and FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #FD4747">on()</span>.setFoo...;
     * </pre>
     * @return The condition-query for OnClause query. (NotNull)
     * @throws IllegalConditionBeanOperationException When this condition-query is base query.
     */
    public PersistentEventValueCIQ on() {
        if (isBaseQuery()) {
            throw new IllegalConditionBeanOperationException(
                    "OnClause for local table is unavailable!");
        }
        final PersistentEventValueCIQ inlineQuery = inline();
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

    /** 
     * Add order-by as ascend. <br />
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_Id_Asc() {
        regOBA("ID");
        return this;
    }

    /**
     * Add order-by as descend. <br />
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_Id_Desc() {
        regOBD("ID");
        return this;
    }

    protected ConditionValue _eventId;

    public ConditionValue getEventId() {
        if (_eventId == null) {
            _eventId = nCV();
        }
        return _eventId;
    }

    @Override
    protected ConditionValue getCValueEventId() {
        return getEventId();
    }

    protected Map<String, PersistentEventCQ> _eventId_InScopeRelation_PersistentEventMap;

    public Map<String, PersistentEventCQ> getEventId_InScopeRelation_PersistentEvent() {
        return _eventId_InScopeRelation_PersistentEventMap;
    }

    @Override
    public String keepEventId_InScopeRelation_PersistentEvent(
            final PersistentEventCQ subQuery) {
        if (_eventId_InScopeRelation_PersistentEventMap == null) {
            _eventId_InScopeRelation_PersistentEventMap = newLinkedHashMap();
        }
        final String key = "subQueryMapKey"
                + (_eventId_InScopeRelation_PersistentEventMap.size() + 1);
        _eventId_InScopeRelation_PersistentEventMap.put(key, subQuery);
        return "eventId_InScopeRelation_PersistentEvent." + key;
    }

    protected Map<String, PersistentEventCQ> _eventId_NotInScopeRelation_PersistentEventMap;

    public Map<String, PersistentEventCQ> getEventId_NotInScopeRelation_PersistentEvent() {
        return _eventId_NotInScopeRelation_PersistentEventMap;
    }

    @Override
    public String keepEventId_NotInScopeRelation_PersistentEvent(
            final PersistentEventCQ subQuery) {
        if (_eventId_NotInScopeRelation_PersistentEventMap == null) {
            _eventId_NotInScopeRelation_PersistentEventMap = newLinkedHashMap();
        }
        final String key = "subQueryMapKey"
                + (_eventId_NotInScopeRelation_PersistentEventMap.size() + 1);
        _eventId_NotInScopeRelation_PersistentEventMap.put(key, subQuery);
        return "eventId_NotInScopeRelation_PersistentEvent." + key;
    }

    /** 
     * Add order-by as ascend. <br />
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_EventId_Asc() {
        regOBA("EVENT_ID");
        return this;
    }

    /**
     * Add order-by as descend. <br />
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_EventId_Desc() {
        regOBD("EVENT_ID");
        return this;
    }

    protected ConditionValue _name;

    public ConditionValue getName() {
        if (_name == null) {
            _name = nCV();
        }
        return _name;
    }

    @Override
    protected ConditionValue getCValueName() {
        return getName();
    }

    /** 
     * Add order-by as ascend. <br />
     * NAME: {NotNull, VARCHAR(4000)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_Name_Asc() {
        regOBA("NAME");
        return this;
    }

    /**
     * Add order-by as descend. <br />
     * NAME: {NotNull, VARCHAR(4000)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_Name_Desc() {
        regOBD("NAME");
        return this;
    }

    protected ConditionValue _value;

    public ConditionValue getValue() {
        if (_value == null) {
            _value = nCV();
        }
        return _value;
    }

    @Override
    protected ConditionValue getCValueValue() {
        return getValue();
    }

    /** 
     * Add order-by as ascend. <br />
     * VALUE: {NotNull, VARCHAR(4000)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_Value_Asc() {
        regOBA("VALUE");
        return this;
    }

    /**
     * Add order-by as descend. <br />
     * VALUE: {NotNull, VARCHAR(4000)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_Value_Desc() {
        regOBD("VALUE");
        return this;
    }

    protected ConditionValue _classType;

    public ConditionValue getClassType() {
        if (_classType == null) {
            _classType = nCV();
        }
        return _classType;
    }

    @Override
    protected ConditionValue getCValueClassType() {
        return getClassType();
    }

    /** 
     * Add order-by as ascend. <br />
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_ClassType_Asc() {
        regOBA("CLASS_TYPE");
        return this;
    }

    /**
     * Add order-by as descend. <br />
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_ClassType_Desc() {
        regOBD("CLASS_TYPE");
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
    public BsPersistentEventValueCQ addOrderBy_VersionNo_Asc() {
        regOBA("VERSION_NO");
        return this;
    }

    /**
     * Add order-by as descend. <br />
     * VERSION_NO: {NotNull, INTEGER(10)}
     * @return this. (NotNull)
     */
    public BsPersistentEventValueCQ addOrderBy_VersionNo_Desc() {
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
    public BsPersistentEventValueCQ addSpecifiedDerivedOrderBy_Asc(
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
    public BsPersistentEventValueCQ addSpecifiedDerivedOrderBy_Desc(
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
        final PersistentEventValueCQ baseQuery = (PersistentEventValueCQ) baseQueryAsSuper;
        final PersistentEventValueCQ unionQuery = (PersistentEventValueCQ) unionQueryAsSuper;
        if (baseQuery.hasConditionQueryPersistentEvent()) {
            unionQuery.queryPersistentEvent().reflectRelationOnUnionQuery(
                    baseQuery.queryPersistentEvent(),
                    unionQuery.queryPersistentEvent());
        }
    }

    // ===================================================================================
    //                                                                       Foreign Query
    //                                                                       =============
    /**
     * Get the condition-query for relation table. <br />
     * PERSISTENT_EVENT by my EVENT_ID, named 'persistentEvent'.
     * @return The instance of condition-query. (NotNull)
     */
    public PersistentEventCQ queryPersistentEvent() {
        return getConditionQueryPersistentEvent();
    }

    protected PersistentEventCQ _conditionQueryPersistentEvent;

    public PersistentEventCQ getConditionQueryPersistentEvent() {
        if (_conditionQueryPersistentEvent == null) {
            _conditionQueryPersistentEvent = xcreateQueryPersistentEvent();
            xsetupOuterJoinPersistentEvent();
        }
        return _conditionQueryPersistentEvent;
    }

    protected PersistentEventCQ xcreateQueryPersistentEvent() {
        final String nrp = resolveNextRelationPath("PERSISTENT_EVENT_VALUE",
                "persistentEvent");
        final String jan = resolveJoinAliasName(nrp, xgetNextNestLevel());
        final PersistentEventCQ cq = new PersistentEventCQ(this,
                xgetSqlClause(), jan, xgetNextNestLevel());
        cq.xsetBaseCB(_baseCB);
        cq.xsetForeignPropertyName("persistentEvent");
        cq.xsetRelationPath(nrp);
        return cq;
    }

    protected void xsetupOuterJoinPersistentEvent() {
        final PersistentEventCQ cq = getConditionQueryPersistentEvent();
        final Map<String, String> joinOnMap = newLinkedHashMap();
        joinOnMap.put("EVENT_ID", "ID");
        registerOuterJoin(cq, joinOnMap, "persistentEvent");
    }

    public boolean hasConditionQueryPersistentEvent() {
        return _conditionQueryPersistentEvent != null;
    }

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    protected Map<String, PersistentEventValueCQ> _scalarConditionMap;

    public Map<String, PersistentEventValueCQ> getScalarCondition() {
        return _scalarConditionMap;
    }

    @Override
    public String keepScalarCondition(final PersistentEventValueCQ subQuery) {
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
    protected Map<String, PersistentEventValueCQ> _specifyMyselfDerivedMap;

    public Map<String, PersistentEventValueCQ> getSpecifyMyselfDerived() {
        return _specifyMyselfDerivedMap;
    }

    @Override
    public String keepSpecifyMyselfDerived(final PersistentEventValueCQ subQuery) {
        if (_specifyMyselfDerivedMap == null) {
            _specifyMyselfDerivedMap = newLinkedHashMap();
        }
        final String key = "subQueryMapKey"
                + (_specifyMyselfDerivedMap.size() + 1);
        _specifyMyselfDerivedMap.put(key, subQuery);
        return "specifyMyselfDerived." + key;
    }

    protected Map<String, PersistentEventValueCQ> _queryMyselfDerivedMap;

    public Map<String, PersistentEventValueCQ> getQueryMyselfDerived() {
        return _queryMyselfDerivedMap;
    }

    @Override
    public String keepQueryMyselfDerived(final PersistentEventValueCQ subQuery) {
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
    protected Map<String, PersistentEventValueCQ> _myselfExistsMap;

    public Map<String, PersistentEventValueCQ> getMyselfExists() {
        return _myselfExistsMap;
    }

    @Override
    public String keepMyselfExists(final PersistentEventValueCQ subQuery) {
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
    protected Map<String, PersistentEventValueCQ> _myselfInScopeMap;

    public Map<String, PersistentEventValueCQ> getMyselfInScope() {
        return _myselfInScopeMap;
    }

    @Override
    public String keepMyselfInScope(final PersistentEventValueCQ subQuery) {
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
        return PersistentEventValueCB.class.getName();
    }

    protected String xCQ() {
        return PersistentEventValueCQ.class.getName();
    }

    protected String xMap() {
        return Map.class.getName();
    }
}
