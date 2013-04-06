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
package org.codelibs.empros.db.cbean.cq.bs;

import java.util.Collection;

import org.codelibs.empros.db.allcommon.DBMetaInstanceHandler;
import org.codelibs.empros.db.cbean.PersistentEventCB;
import org.codelibs.empros.db.cbean.PersistentEventValueCB;
import org.codelibs.empros.db.cbean.cq.PersistentEventCQ;
import org.codelibs.empros.db.cbean.cq.PersistentEventValueCQ;
import org.seasar.dbflute.cbean.AbstractConditionQuery;
import org.seasar.dbflute.cbean.ConditionQuery;
import org.seasar.dbflute.cbean.SubQuery;
import org.seasar.dbflute.cbean.chelper.HpQDRFunction;
import org.seasar.dbflute.cbean.chelper.HpQDRSetupper;
import org.seasar.dbflute.cbean.chelper.HpSSQFunction;
import org.seasar.dbflute.cbean.chelper.HpSSQOption;
import org.seasar.dbflute.cbean.chelper.HpSSQSetupper;
import org.seasar.dbflute.cbean.ckey.ConditionKey;
import org.seasar.dbflute.cbean.coption.DerivedReferrerOption;
import org.seasar.dbflute.cbean.coption.LikeSearchOption;
import org.seasar.dbflute.cbean.coption.RangeOfOption;
import org.seasar.dbflute.cbean.cvalue.ConditionValue;
import org.seasar.dbflute.cbean.sqlclause.SqlClause;
import org.seasar.dbflute.dbmeta.DBMetaProvider;

/**
 * The abstract condition-query of PERSISTENT_EVENT_VALUE.
 * @author DBFlute(AutoGenerator)
 */
public abstract class AbstractBsPersistentEventValueCQ extends
        AbstractConditionQuery {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public AbstractBsPersistentEventValueCQ(final ConditionQuery childQuery,
            final SqlClause sqlClause, final String aliasName,
            final int nestLevel) {
        super(childQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                     DBMeta Provider
    //                                                                     ===============
    @Override
    protected DBMetaProvider xgetDBMetaProvider() {
        return DBMetaInstanceHandler.getProvider();
    }

    // ===================================================================================
    //                                                                          Table Name
    //                                                                          ==========
    @Override
    public String getTableDbName() {
        return "PERSISTENT_EVENT_VALUE";
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br />
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param id The value of id as equal.
     */
    public void setId_Equal(final Long id) {
        doSetId_Equal(id);
    }

    protected void doSetId_Equal(final Long id) {
        regId(CK_EQ, id);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br />
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param id The value of id as notEqual.
     */
    public void setId_NotEqual(final Long id) {
        doSetId_NotEqual(id);
    }

    protected void doSetId_NotEqual(final Long id) {
        regId(CK_NES, id);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br />
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param id The value of id as greaterThan.
     */
    public void setId_GreaterThan(final Long id) {
        regId(CK_GT, id);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br />
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param id The value of id as lessThan.
     */
    public void setId_LessThan(final Long id) {
        regId(CK_LT, id);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br />
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param id The value of id as greaterEqual.
     */
    public void setId_GreaterEqual(final Long id) {
        regId(CK_GE, id);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br />
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param id The value of id as lessEqual.
     */
    public void setId_LessEqual(final Long id) {
        regId(CK_LE, id);
    }

    /**
     * RangeOf with various options. (versatile) <br />
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br />
     * And NullIgnored, OnlyOnceRegistered. <br />
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param minNumber The min number of id. (NullAllowed)
     * @param maxNumber The max number of id. (NullAllowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    public void setId_RangeOf(final Long minNumber, final Long maxNumber,
            final RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, getCValueId(), "ID", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br />
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param idList The collection of id as inScope.
     */
    public void setId_InScope(final Collection<Long> idList) {
        doSetId_InScope(idList);
    }

    protected void doSetId_InScope(final Collection<Long> idList) {
        regINS(CK_INS, cTL(idList), getCValueId(), "ID");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br />
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param idList The collection of id as notInScope.
     */
    public void setId_NotInScope(final Collection<Long> idList) {
        doSetId_NotInScope(idList);
    }

    protected void doSetId_NotInScope(final Collection<Long> idList) {
        regINS(CK_NINS, cTL(idList), getCValueId(), "ID");
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br />
     * ID: {PK, ID, NotNull, BIGINT(19)}
     */
    public void setId_IsNull() {
        regId(CK_ISN, DOBJ);
    }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br />
     * ID: {PK, ID, NotNull, BIGINT(19)}
     */
    public void setId_IsNotNull() {
        regId(CK_ISNN, DOBJ);
    }

    protected void regId(final ConditionKey k, final Object v) {
        regQ(k, v, getCValueId(), "ID");
    }

    abstract protected ConditionValue getCValueId();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br />
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param eventId The value of eventId as equal.
     */
    public void setEventId_Equal(final Long eventId) {
        doSetEventId_Equal(eventId);
    }

    protected void doSetEventId_Equal(final Long eventId) {
        regEventId(CK_EQ, eventId);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br />
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param eventId The value of eventId as notEqual.
     */
    public void setEventId_NotEqual(final Long eventId) {
        doSetEventId_NotEqual(eventId);
    }

    protected void doSetEventId_NotEqual(final Long eventId) {
        regEventId(CK_NES, eventId);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br />
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param eventId The value of eventId as greaterThan.
     */
    public void setEventId_GreaterThan(final Long eventId) {
        regEventId(CK_GT, eventId);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br />
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param eventId The value of eventId as lessThan.
     */
    public void setEventId_LessThan(final Long eventId) {
        regEventId(CK_LT, eventId);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br />
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param eventId The value of eventId as greaterEqual.
     */
    public void setEventId_GreaterEqual(final Long eventId) {
        regEventId(CK_GE, eventId);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br />
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param eventId The value of eventId as lessEqual.
     */
    public void setEventId_LessEqual(final Long eventId) {
        regEventId(CK_LE, eventId);
    }

    /**
     * RangeOf with various options. (versatile) <br />
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br />
     * And NullIgnored, OnlyOnceRegistered. <br />
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param minNumber The min number of eventId. (NullAllowed)
     * @param maxNumber The max number of eventId. (NullAllowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    public void setEventId_RangeOf(final Long minNumber, final Long maxNumber,
            final RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, getCValueEventId(), "EVENT_ID",
                rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br />
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param eventIdList The collection of eventId as inScope.
     */
    public void setEventId_InScope(final Collection<Long> eventIdList) {
        doSetEventId_InScope(eventIdList);
    }

    protected void doSetEventId_InScope(final Collection<Long> eventIdList) {
        regINS(CK_INS, cTL(eventIdList), getCValueEventId(), "EVENT_ID");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br />
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param eventIdList The collection of eventId as notInScope.
     */
    public void setEventId_NotInScope(final Collection<Long> eventIdList) {
        doSetEventId_NotInScope(eventIdList);
    }

    protected void doSetEventId_NotInScope(final Collection<Long> eventIdList) {
        regINS(CK_NINS, cTL(eventIdList), getCValueEventId(), "EVENT_ID");
    }

    /**
     * Set up InScopeRelation (sub-query). <br />
     * {in (select EVENT_ID from PERSISTENT_EVENT where ...)} <br />
     * PERSISTENT_EVENT by my EVENT_ID, named 'persistentEvent'.
     * @param subQuery The sub-query of PersistentEvent for 'in-scope'. (NotNull)
     */
    public void inScopePersistentEvent(
            final SubQuery<PersistentEventCB> subQuery) {
        assertObjectNotNull("subQuery<PersistentEventCB>", subQuery);
        final PersistentEventCB cb = new PersistentEventCB();
        cb.xsetupForInScopeRelation(this);
        subQuery.query(cb);
        final String subQueryPropertyName = keepEventId_InScopeRelation_PersistentEvent(cb
                .query()); // for saving query-value.
        registerInScopeRelation(cb.query(), "EVENT_ID", "ID",
                subQueryPropertyName, "persistentEvent");
    }

    public abstract String keepEventId_InScopeRelation_PersistentEvent(
            PersistentEventCQ subQuery);

    /**
     * Set up NotInScopeRelation (sub-query). <br />
     * {not in (select EVENT_ID from PERSISTENT_EVENT where ...)} <br />
     * PERSISTENT_EVENT by my EVENT_ID, named 'persistentEvent'.
     * @param subQuery The sub-query of PersistentEvent for 'not in-scope'. (NotNull)
     */
    public void notInScopePersistentEvent(
            final SubQuery<PersistentEventCB> subQuery) {
        assertObjectNotNull("subQuery<PersistentEventCB>", subQuery);
        final PersistentEventCB cb = new PersistentEventCB();
        cb.xsetupForInScopeRelation(this);
        subQuery.query(cb);
        final String subQueryPropertyName = keepEventId_NotInScopeRelation_PersistentEvent(cb
                .query()); // for saving query-value.
        registerNotInScopeRelation(cb.query(), "EVENT_ID", "ID",
                subQueryPropertyName, "persistentEvent");
    }

    public abstract String keepEventId_NotInScopeRelation_PersistentEvent(
            PersistentEventCQ subQuery);

    protected void regEventId(final ConditionKey k, final Object v) {
        regQ(k, v, getCValueEventId(), "EVENT_ID");
    }

    abstract protected ConditionValue getCValueEventId();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * NAME: {NotNull, VARCHAR(4000)}
     * @param name The value of name as equal.
     */
    public void setName_Equal(final String name) {
        doSetName_Equal(fRES(name));
    }

    protected void doSetName_Equal(final String name) {
        regName(CK_EQ, name);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * NAME: {NotNull, VARCHAR(4000)}
     * @param name The value of name as notEqual.
     */
    public void setName_NotEqual(final String name) {
        doSetName_NotEqual(fRES(name));
    }

    protected void doSetName_NotEqual(final String name) {
        regName(CK_NES, name);
    }

    /**
     * GreaterThan(&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * NAME: {NotNull, VARCHAR(4000)}
     * @param name The value of name as greaterThan.
     */
    public void setName_GreaterThan(final String name) {
        regName(CK_GT, fRES(name));
    }

    /**
     * LessThan(&lt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * NAME: {NotNull, VARCHAR(4000)}
     * @param name The value of name as lessThan.
     */
    public void setName_LessThan(final String name) {
        regName(CK_LT, fRES(name));
    }

    /**
     * GreaterEqual(&gt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * NAME: {NotNull, VARCHAR(4000)}
     * @param name The value of name as greaterEqual.
     */
    public void setName_GreaterEqual(final String name) {
        regName(CK_GE, fRES(name));
    }

    /**
     * LessEqual(&lt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * NAME: {NotNull, VARCHAR(4000)}
     * @param name The value of name as lessEqual.
     */
    public void setName_LessEqual(final String name) {
        regName(CK_LE, fRES(name));
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br />
     * NAME: {NotNull, VARCHAR(4000)}
     * @param nameList The collection of name as inScope.
     */
    public void setName_InScope(final Collection<String> nameList) {
        doSetName_InScope(nameList);
    }

    public void doSetName_InScope(final Collection<String> nameList) {
        regINS(CK_INS, cTL(nameList), getCValueName(), "NAME");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br />
     * NAME: {NotNull, VARCHAR(4000)}
     * @param nameList The collection of name as notInScope.
     */
    public void setName_NotInScope(final Collection<String> nameList) {
        doSetName_NotInScope(nameList);
    }

    public void doSetName_NotInScope(final Collection<String> nameList) {
        regINS(CK_NINS, cTL(nameList), getCValueName(), "NAME");
    }

    /**
     * PrefixSearch {like 'xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br />
     * NAME: {NotNull, VARCHAR(4000)}
     * @param name The value of name as prefixSearch.
     */
    public void setName_PrefixSearch(final String name) {
        setName_LikeSearch(name, cLSOP());
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...} <br />
     * And NullOrEmptyIgnored, SeveralRegistered. <br />
     * NAME: {NotNull, VARCHAR(4000)}
     * @param name The value of name as likeSearch.
     * @param likeSearchOption The option of like-search. (NotNull)
     */
    public void setName_LikeSearch(final String name,
            final LikeSearchOption likeSearchOption) {
        regLSQ(CK_LS, fRES(name), getCValueName(), "NAME", likeSearchOption);
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br />
     * And NullOrEmptyIgnored, SeveralRegistered. <br />
     * NAME: {NotNull, VARCHAR(4000)}
     * @param name The value of name as notLikeSearch.
     * @param likeSearchOption The option of not-like-search. (NotNull)
     */
    public void setName_NotLikeSearch(final String name,
            final LikeSearchOption likeSearchOption) {
        regLSQ(CK_NLS, fRES(name), getCValueName(), "NAME", likeSearchOption);
    }

    protected void regName(final ConditionKey k, final Object v) {
        regQ(k, v, getCValueName(), "NAME");
    }

    abstract protected ConditionValue getCValueName();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param value The value of value as equal.
     */
    public void setValue_Equal(final String value) {
        doSetValue_Equal(fRES(value));
    }

    protected void doSetValue_Equal(final String value) {
        regValue(CK_EQ, value);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param value The value of value as notEqual.
     */
    public void setValue_NotEqual(final String value) {
        doSetValue_NotEqual(fRES(value));
    }

    protected void doSetValue_NotEqual(final String value) {
        regValue(CK_NES, value);
    }

    /**
     * GreaterThan(&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param value The value of value as greaterThan.
     */
    public void setValue_GreaterThan(final String value) {
        regValue(CK_GT, fRES(value));
    }

    /**
     * LessThan(&lt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param value The value of value as lessThan.
     */
    public void setValue_LessThan(final String value) {
        regValue(CK_LT, fRES(value));
    }

    /**
     * GreaterEqual(&gt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param value The value of value as greaterEqual.
     */
    public void setValue_GreaterEqual(final String value) {
        regValue(CK_GE, fRES(value));
    }

    /**
     * LessEqual(&lt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param value The value of value as lessEqual.
     */
    public void setValue_LessEqual(final String value) {
        regValue(CK_LE, fRES(value));
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br />
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param valueList The collection of value as inScope.
     */
    public void setValue_InScope(final Collection<String> valueList) {
        doSetValue_InScope(valueList);
    }

    public void doSetValue_InScope(final Collection<String> valueList) {
        regINS(CK_INS, cTL(valueList), getCValueValue(), "VALUE");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br />
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param valueList The collection of value as notInScope.
     */
    public void setValue_NotInScope(final Collection<String> valueList) {
        doSetValue_NotInScope(valueList);
    }

    public void doSetValue_NotInScope(final Collection<String> valueList) {
        regINS(CK_NINS, cTL(valueList), getCValueValue(), "VALUE");
    }

    /**
     * PrefixSearch {like 'xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br />
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param value The value of value as prefixSearch.
     */
    public void setValue_PrefixSearch(final String value) {
        setValue_LikeSearch(value, cLSOP());
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...} <br />
     * And NullOrEmptyIgnored, SeveralRegistered. <br />
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param value The value of value as likeSearch.
     * @param likeSearchOption The option of like-search. (NotNull)
     */
    public void setValue_LikeSearch(final String value,
            final LikeSearchOption likeSearchOption) {
        regLSQ(CK_LS, fRES(value), getCValueValue(), "VALUE", likeSearchOption);
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br />
     * And NullOrEmptyIgnored, SeveralRegistered. <br />
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param value The value of value as notLikeSearch.
     * @param likeSearchOption The option of not-like-search. (NotNull)
     */
    public void setValue_NotLikeSearch(final String value,
            final LikeSearchOption likeSearchOption) {
        regLSQ(CK_NLS, fRES(value), getCValueValue(), "VALUE", likeSearchOption);
    }

    protected void regValue(final ConditionKey k, final Object v) {
        regQ(k, v, getCValueValue(), "VALUE");
    }

    abstract protected ConditionValue getCValueValue();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classType The value of classType as equal.
     */
    public void setClassType_Equal(final String classType) {
        doSetClassType_Equal(fRES(classType));
    }

    protected void doSetClassType_Equal(final String classType) {
        regClassType(CK_EQ, classType);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classType The value of classType as notEqual.
     */
    public void setClassType_NotEqual(final String classType) {
        doSetClassType_NotEqual(fRES(classType));
    }

    protected void doSetClassType_NotEqual(final String classType) {
        regClassType(CK_NES, classType);
    }

    /**
     * GreaterThan(&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classType The value of classType as greaterThan.
     */
    public void setClassType_GreaterThan(final String classType) {
        regClassType(CK_GT, fRES(classType));
    }

    /**
     * LessThan(&lt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classType The value of classType as lessThan.
     */
    public void setClassType_LessThan(final String classType) {
        regClassType(CK_LT, fRES(classType));
    }

    /**
     * GreaterEqual(&gt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classType The value of classType as greaterEqual.
     */
    public void setClassType_GreaterEqual(final String classType) {
        regClassType(CK_GE, fRES(classType));
    }

    /**
     * LessEqual(&lt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classType The value of classType as lessEqual.
     */
    public void setClassType_LessEqual(final String classType) {
        regClassType(CK_LE, fRES(classType));
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br />
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classTypeList The collection of classType as inScope.
     */
    public void setClassType_InScope(final Collection<String> classTypeList) {
        doSetClassType_InScope(classTypeList);
    }

    public void doSetClassType_InScope(final Collection<String> classTypeList) {
        regINS(CK_INS, cTL(classTypeList), getCValueClassType(), "CLASS_TYPE");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br />
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classTypeList The collection of classType as notInScope.
     */
    public void setClassType_NotInScope(final Collection<String> classTypeList) {
        doSetClassType_NotInScope(classTypeList);
    }

    public void doSetClassType_NotInScope(final Collection<String> classTypeList) {
        regINS(CK_NINS, cTL(classTypeList), getCValueClassType(), "CLASS_TYPE");
    }

    /**
     * PrefixSearch {like 'xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br />
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classType The value of classType as prefixSearch.
     */
    public void setClassType_PrefixSearch(final String classType) {
        setClassType_LikeSearch(classType, cLSOP());
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...} <br />
     * And NullOrEmptyIgnored, SeveralRegistered. <br />
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classType The value of classType as likeSearch.
     * @param likeSearchOption The option of like-search. (NotNull)
     */
    public void setClassType_LikeSearch(final String classType,
            final LikeSearchOption likeSearchOption) {
        regLSQ(CK_LS, fRES(classType), getCValueClassType(), "CLASS_TYPE",
                likeSearchOption);
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br />
     * And NullOrEmptyIgnored, SeveralRegistered. <br />
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classType The value of classType as notLikeSearch.
     * @param likeSearchOption The option of not-like-search. (NotNull)
     */
    public void setClassType_NotLikeSearch(final String classType,
            final LikeSearchOption likeSearchOption) {
        regLSQ(CK_NLS, fRES(classType), getCValueClassType(), "CLASS_TYPE",
                likeSearchOption);
    }

    protected void regClassType(final ConditionKey k, final Object v) {
        regQ(k, v, getCValueClassType(), "CLASS_TYPE");
    }

    abstract protected ConditionValue getCValueClassType();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br />
     * VERSION_NO: {NotNull, INTEGER(10)}
     * @param versionNo The value of versionNo as equal.
     */
    public void setVersionNo_Equal(final Integer versionNo) {
        doSetVersionNo_Equal(versionNo);
    }

    protected void doSetVersionNo_Equal(final Integer versionNo) {
        regVersionNo(CK_EQ, versionNo);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br />
     * VERSION_NO: {NotNull, INTEGER(10)}
     * @param versionNo The value of versionNo as notEqual.
     */
    public void setVersionNo_NotEqual(final Integer versionNo) {
        doSetVersionNo_NotEqual(versionNo);
    }

    protected void doSetVersionNo_NotEqual(final Integer versionNo) {
        regVersionNo(CK_NES, versionNo);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br />
     * VERSION_NO: {NotNull, INTEGER(10)}
     * @param versionNo The value of versionNo as greaterThan.
     */
    public void setVersionNo_GreaterThan(final Integer versionNo) {
        regVersionNo(CK_GT, versionNo);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br />
     * VERSION_NO: {NotNull, INTEGER(10)}
     * @param versionNo The value of versionNo as lessThan.
     */
    public void setVersionNo_LessThan(final Integer versionNo) {
        regVersionNo(CK_LT, versionNo);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br />
     * VERSION_NO: {NotNull, INTEGER(10)}
     * @param versionNo The value of versionNo as greaterEqual.
     */
    public void setVersionNo_GreaterEqual(final Integer versionNo) {
        regVersionNo(CK_GE, versionNo);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br />
     * VERSION_NO: {NotNull, INTEGER(10)}
     * @param versionNo The value of versionNo as lessEqual.
     */
    public void setVersionNo_LessEqual(final Integer versionNo) {
        regVersionNo(CK_LE, versionNo);
    }

    /**
     * RangeOf with various options. (versatile) <br />
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br />
     * And NullIgnored, OnlyOnceRegistered. <br />
     * VERSION_NO: {NotNull, INTEGER(10)}
     * @param minNumber The min number of versionNo. (NullAllowed)
     * @param maxNumber The max number of versionNo. (NullAllowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    public void setVersionNo_RangeOf(final Integer minNumber,
            final Integer maxNumber, final RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, getCValueVersionNo(), "VERSION_NO",
                rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br />
     * VERSION_NO: {NotNull, INTEGER(10)}
     * @param versionNoList The collection of versionNo as inScope.
     */
    public void setVersionNo_InScope(final Collection<Integer> versionNoList) {
        doSetVersionNo_InScope(versionNoList);
    }

    protected void doSetVersionNo_InScope(
            final Collection<Integer> versionNoList) {
        regINS(CK_INS, cTL(versionNoList), getCValueVersionNo(), "VERSION_NO");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br />
     * VERSION_NO: {NotNull, INTEGER(10)}
     * @param versionNoList The collection of versionNo as notInScope.
     */
    public void setVersionNo_NotInScope(final Collection<Integer> versionNoList) {
        doSetVersionNo_NotInScope(versionNoList);
    }

    protected void doSetVersionNo_NotInScope(
            final Collection<Integer> versionNoList) {
        regINS(CK_NINS, cTL(versionNoList), getCValueVersionNo(), "VERSION_NO");
    }

    protected void regVersionNo(final ConditionKey k, final Object v) {
        regQ(k, v, getCValueVersionNo(), "VERSION_NO");
    }

    abstract protected ConditionValue getCValueVersionNo();

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    /**
     * Prepare ScalarCondition as equal. <br />
     * {where FOO = (select max(BAR) from ...)
     * <pre>
     * cb.query().<span style="color: #FD4747">scalar_Equal()</span>.max(new SubQuery&lt;PersistentEventValueCB&gt;() {
     *     public void query(PersistentEventValueCB subCB) {
     *         subCB.specify().setXxx... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setYyy...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSSQFunction<PersistentEventValueCB> scalar_Equal() {
        return xcreateSSQFunction(CK_EQ.getOperand());
    }

    /**
     * Prepare ScalarCondition as equal. <br />
     * {where FOO &lt;&gt; (select max(BAR) from ...)
     * <pre>
     * cb.query().<span style="color: #FD4747">scalar_NotEqual()</span>.max(new SubQuery&lt;PersistentEventValueCB&gt;() {
     *     public void query(PersistentEventValueCB subCB) {
     *         subCB.specify().setXxx... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setYyy...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSSQFunction<PersistentEventValueCB> scalar_NotEqual() {
        return xcreateSSQFunction(CK_NES.getOperand());
    }

    /**
     * Prepare ScalarCondition as greaterThan. <br />
     * {where FOO &gt; (select max(BAR) from ...)
     * <pre>
     * cb.query().<span style="color: #FD4747">scalar_GreaterThan()</span>.max(new SubQuery&lt;PersistentEventValueCB&gt;() {
     *     public void query(PersistentEventValueCB subCB) {
     *         subCB.specify().setFoo... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setBar...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSSQFunction<PersistentEventValueCB> scalar_GreaterThan() {
        return xcreateSSQFunction(CK_GT.getOperand());
    }

    /**
     * Prepare ScalarCondition as lessThan. <br />
     * {where FOO &lt; (select max(BAR) from ...)
     * <pre>
     * cb.query().<span style="color: #FD4747">scalar_LessThan()</span>.max(new SubQuery&lt;PersistentEventValueCB&gt;() {
     *     public void query(PersistentEventValueCB subCB) {
     *         subCB.specify().setFoo... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setBar...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSSQFunction<PersistentEventValueCB> scalar_LessThan() {
        return xcreateSSQFunction(CK_LT.getOperand());
    }

    /**
     * Prepare ScalarCondition as greaterEqual. <br />
     * {where FOO &gt;= (select max(BAR) from ...)
     * <pre>
     * cb.query().<span style="color: #FD4747">scalar_GreaterEqual()</span>.max(new SubQuery&lt;PersistentEventValueCB&gt;() {
     *     public void query(PersistentEventValueCB subCB) {
     *         subCB.specify().setFoo... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setBar...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSSQFunction<PersistentEventValueCB> scalar_GreaterEqual() {
        return xcreateSSQFunction(CK_GE.getOperand());
    }

    /**
     * Prepare ScalarCondition as lessEqual. <br />
     * {where FOO &lt;= (select max(BAR) from ...)
     * <pre>
     * cb.query().<span style="color: #FD4747">scalar_LessEqual()</span>.max(new SubQuery&lt;PersistentEventValueCB&gt;() {
     *     public void query(PersistentEventValueCB subCB) {
     *         subCB.specify().setFoo... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setBar...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSSQFunction<PersistentEventValueCB> scalar_LessEqual() {
        return xcreateSSQFunction(CK_LE.getOperand());
    }

    protected HpSSQFunction<PersistentEventValueCB> xcreateSSQFunction(
            final String operand) {
        return new HpSSQFunction<PersistentEventValueCB>(
                new HpSSQSetupper<PersistentEventValueCB>() {
                    @Override
                    public void setup(final String function,
                            final SubQuery<PersistentEventValueCB> subQuery,
                            final HpSSQOption<PersistentEventValueCB> option) {
                        xscalarCondition(function, subQuery, operand, option);
                    }
                });
    }

    protected void xscalarCondition(final String function,
            final SubQuery<PersistentEventValueCB> subQuery,
            final String operand,
            final HpSSQOption<PersistentEventValueCB> option) {
        assertObjectNotNull("subQuery<PersistentEventValueCB>", subQuery);
        final PersistentEventValueCB cb = xcreateScalarConditionCB();
        subQuery.query(cb);
        final String subQueryPropertyName = keepScalarCondition(cb.query()); // for saving query-value
        option.setPartitionByCBean(xcreateScalarConditionPartitionByCB()); // for using partition-by
        registerScalarCondition(function, cb.query(), subQueryPropertyName,
                operand, option);
    }

    public abstract String keepScalarCondition(PersistentEventValueCQ subQuery);

    protected PersistentEventValueCB xcreateScalarConditionCB() {
        final PersistentEventValueCB cb = new PersistentEventValueCB();
        cb.xsetupForScalarCondition(this);
        return cb;
    }

    protected PersistentEventValueCB xcreateScalarConditionPartitionByCB() {
        final PersistentEventValueCB cb = new PersistentEventValueCB();
        cb.xsetupForScalarConditionPartitionBy(this);
        return cb;
    }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    public void xsmyselfDerive(final String function,
            final SubQuery<PersistentEventValueCB> subQuery,
            final String aliasName, final DerivedReferrerOption option) {
        assertObjectNotNull("subQuery<PersistentEventValueCB>", subQuery);
        final PersistentEventValueCB cb = new PersistentEventValueCB();
        cb.xsetupForDerivedReferrer(this);
        subQuery.query(cb);
        final String subQueryPropertyName = keepSpecifyMyselfDerived(cb.query()); // for saving query-value.
        registerSpecifyMyselfDerived(function, cb.query(), "ID", "ID",
                subQueryPropertyName, "myselfDerived", aliasName, option);
    }

    public abstract String keepSpecifyMyselfDerived(
            PersistentEventValueCQ subQuery);

    /**
     * Prepare for (Query)MyselfDerived (SubQuery).
     * @return The object to set up a function for myself table. (NotNull)
     */
    public HpQDRFunction<PersistentEventValueCB> myselfDerived() {
        return xcreateQDRFunctionMyselfDerived();
    }

    protected HpQDRFunction<PersistentEventValueCB> xcreateQDRFunctionMyselfDerived() {
        return new HpQDRFunction<PersistentEventValueCB>(
                new HpQDRSetupper<PersistentEventValueCB>() {
                    @Override
                    public void setup(final String function,
                            final SubQuery<PersistentEventValueCB> subQuery,
                            final String operand, final Object value,
                            final DerivedReferrerOption option) {
                        xqderiveMyselfDerived(function, subQuery, operand,
                                value, option);
                    }
                });
    }

    public void xqderiveMyselfDerived(final String function,
            final SubQuery<PersistentEventValueCB> subQuery,
            final String operand, final Object value,
            final DerivedReferrerOption option) {
        assertObjectNotNull("subQuery<PersistentEventValueCB>", subQuery);
        final PersistentEventValueCB cb = new PersistentEventValueCB();
        cb.xsetupForDerivedReferrer(this);
        subQuery.query(cb);
        final String subQueryPropertyName = keepQueryMyselfDerived(cb.query()); // for saving query-value.
        final String parameterPropertyName = keepQueryMyselfDerivedParameter(value);
        registerQueryMyselfDerived(function, cb.query(), "ID", "ID",
                subQueryPropertyName, "myselfDerived", operand, value,
                parameterPropertyName, option);
    }

    public abstract String keepQueryMyselfDerived(
            PersistentEventValueCQ subQuery);

    public abstract String keepQueryMyselfDerivedParameter(Object parameterValue);

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    /**
     * Prepare for MyselfExists (SubQuery).
     * @param subQuery The implementation of sub query. (NotNull)
     */
    public void myselfExists(final SubQuery<PersistentEventValueCB> subQuery) {
        assertObjectNotNull("subQuery<PersistentEventValueCB>", subQuery);
        final PersistentEventValueCB cb = new PersistentEventValueCB();
        cb.xsetupForMyselfExists(this);
        subQuery.query(cb);
        final String subQueryPropertyName = keepMyselfExists(cb.query()); // for saving query-value.
        registerMyselfExists(cb.query(), subQueryPropertyName);
    }

    public abstract String keepMyselfExists(PersistentEventValueCQ subQuery);

    // ===================================================================================
    //                                                                       MyselfInScope
    //                                                                       =============
    /**
     * Prepare for MyselfInScope (SubQuery).
     * @param subQuery The implementation of sub query. (NotNull)
     */
    public void myselfInScope(final SubQuery<PersistentEventValueCB> subQuery) {
        assertObjectNotNull("subQuery<PersistentEventValueCB>", subQuery);
        final PersistentEventValueCB cb = new PersistentEventValueCB();
        cb.xsetupForMyselfInScope(this);
        subQuery.query(cb);
        final String subQueryPropertyName = keepMyselfInScope(cb.query()); // for saving query-value.
        registerMyselfInScope(cb.query(), subQueryPropertyName);
    }

    public abstract String keepMyselfInScope(PersistentEventValueCQ subQuery);

    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xabCB() {
        return PersistentEventValueCB.class.getName();
    }

    protected String xabCQ() {
        return PersistentEventValueCQ.class.getName();
    }

    protected String xabLSO() {
        return LikeSearchOption.class.getName();
    }

    protected String xabSSQS() {
        return HpSSQSetupper.class.getName();
    }
}
