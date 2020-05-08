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

import java.util.Collection;
import java.util.Date;

import org.codelibs.empros.db.allcommon.DBMetaInstanceHandler;
import org.codelibs.empros.db.cbean.PersistentEventValueCB;
import org.codelibs.empros.db.cbean.cq.PersistentEventValueCQ;
import org.dbflute.cbean.AbstractConditionQuery;
import org.dbflute.cbean.ConditionBean;
import org.dbflute.cbean.ConditionQuery;
import org.dbflute.cbean.chelper.HpQDRFunction;
import org.dbflute.cbean.chelper.HpSLCCustomized;
import org.dbflute.cbean.chelper.HpSLCFunction;
import org.dbflute.cbean.chelper.HpSLCSetupper;
import org.dbflute.cbean.ckey.ConditionKey;
import org.dbflute.cbean.coption.*;
import org.dbflute.cbean.cvalue.ConditionValue;
import org.dbflute.cbean.ordering.ManualOrderOptionCall;
import org.dbflute.cbean.scoping.SubQuery;
import org.dbflute.cbean.sqlclause.SqlClause;
import org.dbflute.dbmeta.DBMetaProvider;

/**
 * The abstract condition-query of PERSISTENT_EVENT_VALUE.
 * @author DBFlute(AutoGenerator)
 */
public abstract class AbstractBsPersistentEventValueCQ extends AbstractConditionQuery {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public AbstractBsPersistentEventValueCQ(ConditionQuery referrerQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
        super(referrerQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    @Override
    protected DBMetaProvider xgetDBMetaProvider() {
        return DBMetaInstanceHandler.getProvider();
    }

    public String asTableDbName() {
        return "PERSISTENT_EVENT_VALUE";
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param id The value of id as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setId_Equal(Long id) {
        doSetId_Equal(id);
    }

    protected void doSetId_Equal(Long id) {
        regId(CK_EQ, id);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param id The value of id as notEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setId_NotEqual(Long id) {
        doSetId_NotEqual(id);
    }

    protected void doSetId_NotEqual(Long id) {
        regId(CK_NES, id);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param id The value of id as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setId_GreaterThan(Long id) {
        regId(CK_GT, id);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param id The value of id as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setId_LessThan(Long id) {
        regId(CK_LT, id);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param id The value of id as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setId_GreaterEqual(Long id) {
        regId(CK_GE, id);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param id The value of id as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setId_LessEqual(Long id) {
        regId(CK_LE, id);
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param minNumber The min number of id. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of id. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of range-of. (NotNull)
     */
    public void setId_RangeOf(Long minNumber, Long maxNumber, ConditionOptionCall<RangeOfOption> opLambda) {
        setId_RangeOf(minNumber, maxNumber, xcROOP(opLambda));
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param minNumber The min number of id. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of id. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    protected void setId_RangeOf(Long minNumber, Long maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, xgetCValueId(), "ID", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param idList The collection of id as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setId_InScope(Collection<Long> idList) {
        doSetId_InScope(idList);
    }

    protected void doSetId_InScope(Collection<Long> idList) {
        regINS(CK_INS, cTL(idList), xgetCValueId(), "ID");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @param idList The collection of id as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setId_NotInScope(Collection<Long> idList) {
        doSetId_NotInScope(idList);
    }

    protected void doSetId_NotInScope(Collection<Long> idList) {
        regINS(CK_NINS, cTL(idList), xgetCValueId(), "ID");
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * ID: {PK, ID, NotNull, BIGINT(19)}
     */
    public void setId_IsNull() { regId(CK_ISN, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * ID: {PK, ID, NotNull, BIGINT(19)}
     */
    public void setId_IsNotNull() { regId(CK_ISNN, DOBJ); }

    protected void regId(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueId(), "ID"); }
    protected abstract ConditionValue xgetCValueId();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param eventId The value of eventId as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setEventId_Equal(Long eventId) {
        doSetEventId_Equal(eventId);
    }

    protected void doSetEventId_Equal(Long eventId) {
        regEventId(CK_EQ, eventId);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param eventId The value of eventId as notEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setEventId_NotEqual(Long eventId) {
        doSetEventId_NotEqual(eventId);
    }

    protected void doSetEventId_NotEqual(Long eventId) {
        regEventId(CK_NES, eventId);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param eventId The value of eventId as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setEventId_GreaterThan(Long eventId) {
        regEventId(CK_GT, eventId);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param eventId The value of eventId as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setEventId_LessThan(Long eventId) {
        regEventId(CK_LT, eventId);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param eventId The value of eventId as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setEventId_GreaterEqual(Long eventId) {
        regEventId(CK_GE, eventId);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param eventId The value of eventId as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setEventId_LessEqual(Long eventId) {
        regEventId(CK_LE, eventId);
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param minNumber The min number of eventId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of eventId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of range-of. (NotNull)
     */
    public void setEventId_RangeOf(Long minNumber, Long maxNumber, ConditionOptionCall<RangeOfOption> opLambda) {
        setEventId_RangeOf(minNumber, maxNumber, xcROOP(opLambda));
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param minNumber The min number of eventId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of eventId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    protected void setEventId_RangeOf(Long minNumber, Long maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, xgetCValueEventId(), "EVENT_ID", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param eventIdList The collection of eventId as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setEventId_InScope(Collection<Long> eventIdList) {
        doSetEventId_InScope(eventIdList);
    }

    protected void doSetEventId_InScope(Collection<Long> eventIdList) {
        regINS(CK_INS, cTL(eventIdList), xgetCValueEventId(), "EVENT_ID");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @param eventIdList The collection of eventId as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setEventId_NotInScope(Collection<Long> eventIdList) {
        doSetEventId_NotInScope(eventIdList);
    }

    protected void doSetEventId_NotInScope(Collection<Long> eventIdList) {
        regINS(CK_NINS, cTL(eventIdList), xgetCValueEventId(), "EVENT_ID");
    }

    protected void regEventId(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueEventId(), "EVENT_ID"); }
    protected abstract ConditionValue xgetCValueEventId();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * NAME: {NotNull, VARCHAR(4000)}
     * @param name The value of name as equal. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setName_Equal(String name) {
        doSetName_Equal(fRES(name));
    }

    protected void doSetName_Equal(String name) {
        regName(CK_EQ, name);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * NAME: {NotNull, VARCHAR(4000)}
     * @param name The value of name as notEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setName_NotEqual(String name) {
        doSetName_NotEqual(fRES(name));
    }

    protected void doSetName_NotEqual(String name) {
        regName(CK_NES, name);
    }

    /**
     * GreaterThan(&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * NAME: {NotNull, VARCHAR(4000)}
     * @param name The value of name as greaterThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setName_GreaterThan(String name) {
        regName(CK_GT, fRES(name));
    }

    /**
     * LessThan(&lt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * NAME: {NotNull, VARCHAR(4000)}
     * @param name The value of name as lessThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setName_LessThan(String name) {
        regName(CK_LT, fRES(name));
    }

    /**
     * GreaterEqual(&gt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * NAME: {NotNull, VARCHAR(4000)}
     * @param name The value of name as greaterEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setName_GreaterEqual(String name) {
        regName(CK_GE, fRES(name));
    }

    /**
     * LessEqual(&lt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * NAME: {NotNull, VARCHAR(4000)}
     * @param name The value of name as lessEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setName_LessEqual(String name) {
        regName(CK_LE, fRES(name));
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * NAME: {NotNull, VARCHAR(4000)}
     * @param nameList The collection of name as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setName_InScope(Collection<String> nameList) {
        doSetName_InScope(nameList);
    }

    protected void doSetName_InScope(Collection<String> nameList) {
        regINS(CK_INS, cTL(nameList), xgetCValueName(), "NAME");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * NAME: {NotNull, VARCHAR(4000)}
     * @param nameList The collection of name as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setName_NotInScope(Collection<String> nameList) {
        doSetName_NotInScope(nameList);
    }

    protected void doSetName_NotInScope(Collection<String> nameList) {
        regINS(CK_NINS, cTL(nameList), xgetCValueName(), "NAME");
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * NAME: {NotNull, VARCHAR(4000)} <br>
     * <pre>e.g. setName_LikeSearch("xxx", op <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> op.<span style="color: #CC4747">likeContain()</span>);</pre>
     * @param name The value of name as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setName_LikeSearch(String name, ConditionOptionCall<LikeSearchOption> opLambda) {
        setName_LikeSearch(name, xcLSOP(opLambda));
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * NAME: {NotNull, VARCHAR(4000)} <br>
     * <pre>e.g. setName_LikeSearch("xxx", new <span style="color: #CC4747">LikeSearchOption</span>().likeContain());</pre>
     * @param name The value of name as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of like-search. (NotNull)
     */
    protected void setName_LikeSearch(String name, LikeSearchOption likeSearchOption) {
        regLSQ(CK_LS, fRES(name), xgetCValueName(), "NAME", likeSearchOption);
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * NAME: {NotNull, VARCHAR(4000)}
     * @param name The value of name as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setName_NotLikeSearch(String name, ConditionOptionCall<LikeSearchOption> opLambda) {
        setName_NotLikeSearch(name, xcLSOP(opLambda));
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * NAME: {NotNull, VARCHAR(4000)}
     * @param name The value of name as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of not-like-search. (NotNull)
     */
    protected void setName_NotLikeSearch(String name, LikeSearchOption likeSearchOption) {
        regLSQ(CK_NLS, fRES(name), xgetCValueName(), "NAME", likeSearchOption);
    }

    protected void regName(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueName(), "NAME"); }
    protected abstract ConditionValue xgetCValueName();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param value The value of value as equal. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setValue_Equal(String value) {
        doSetValue_Equal(fRES(value));
    }

    protected void doSetValue_Equal(String value) {
        regValue(CK_EQ, value);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param value The value of value as notEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setValue_NotEqual(String value) {
        doSetValue_NotEqual(fRES(value));
    }

    protected void doSetValue_NotEqual(String value) {
        regValue(CK_NES, value);
    }

    /**
     * GreaterThan(&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param value The value of value as greaterThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setValue_GreaterThan(String value) {
        regValue(CK_GT, fRES(value));
    }

    /**
     * LessThan(&lt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param value The value of value as lessThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setValue_LessThan(String value) {
        regValue(CK_LT, fRES(value));
    }

    /**
     * GreaterEqual(&gt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param value The value of value as greaterEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setValue_GreaterEqual(String value) {
        regValue(CK_GE, fRES(value));
    }

    /**
     * LessEqual(&lt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param value The value of value as lessEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setValue_LessEqual(String value) {
        regValue(CK_LE, fRES(value));
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param valueList The collection of value as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setValue_InScope(Collection<String> valueList) {
        doSetValue_InScope(valueList);
    }

    protected void doSetValue_InScope(Collection<String> valueList) {
        regINS(CK_INS, cTL(valueList), xgetCValueValue(), "VALUE");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param valueList The collection of value as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setValue_NotInScope(Collection<String> valueList) {
        doSetValue_NotInScope(valueList);
    }

    protected void doSetValue_NotInScope(Collection<String> valueList) {
        regINS(CK_NINS, cTL(valueList), xgetCValueValue(), "VALUE");
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * VALUE: {NotNull, VARCHAR(4000)} <br>
     * <pre>e.g. setValue_LikeSearch("xxx", op <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> op.<span style="color: #CC4747">likeContain()</span>);</pre>
     * @param value The value of value as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setValue_LikeSearch(String value, ConditionOptionCall<LikeSearchOption> opLambda) {
        setValue_LikeSearch(value, xcLSOP(opLambda));
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * VALUE: {NotNull, VARCHAR(4000)} <br>
     * <pre>e.g. setValue_LikeSearch("xxx", new <span style="color: #CC4747">LikeSearchOption</span>().likeContain());</pre>
     * @param value The value of value as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of like-search. (NotNull)
     */
    protected void setValue_LikeSearch(String value, LikeSearchOption likeSearchOption) {
        regLSQ(CK_LS, fRES(value), xgetCValueValue(), "VALUE", likeSearchOption);
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param value The value of value as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setValue_NotLikeSearch(String value, ConditionOptionCall<LikeSearchOption> opLambda) {
        setValue_NotLikeSearch(value, xcLSOP(opLambda));
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * VALUE: {NotNull, VARCHAR(4000)}
     * @param value The value of value as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of not-like-search. (NotNull)
     */
    protected void setValue_NotLikeSearch(String value, LikeSearchOption likeSearchOption) {
        regLSQ(CK_NLS, fRES(value), xgetCValueValue(), "VALUE", likeSearchOption);
    }

    protected void regValue(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueValue(), "VALUE"); }
    protected abstract ConditionValue xgetCValueValue();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classType The value of classType as equal. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setClassType_Equal(String classType) {
        doSetClassType_Equal(fRES(classType));
    }

    protected void doSetClassType_Equal(String classType) {
        regClassType(CK_EQ, classType);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classType The value of classType as notEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setClassType_NotEqual(String classType) {
        doSetClassType_NotEqual(fRES(classType));
    }

    protected void doSetClassType_NotEqual(String classType) {
        regClassType(CK_NES, classType);
    }

    /**
     * GreaterThan(&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classType The value of classType as greaterThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setClassType_GreaterThan(String classType) {
        regClassType(CK_GT, fRES(classType));
    }

    /**
     * LessThan(&lt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classType The value of classType as lessThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setClassType_LessThan(String classType) {
        regClassType(CK_LT, fRES(classType));
    }

    /**
     * GreaterEqual(&gt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classType The value of classType as greaterEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setClassType_GreaterEqual(String classType) {
        regClassType(CK_GE, fRES(classType));
    }

    /**
     * LessEqual(&lt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classType The value of classType as lessEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setClassType_LessEqual(String classType) {
        regClassType(CK_LE, fRES(classType));
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classTypeList The collection of classType as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setClassType_InScope(Collection<String> classTypeList) {
        doSetClassType_InScope(classTypeList);
    }

    protected void doSetClassType_InScope(Collection<String> classTypeList) {
        regINS(CK_INS, cTL(classTypeList), xgetCValueClassType(), "CLASS_TYPE");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classTypeList The collection of classType as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setClassType_NotInScope(Collection<String> classTypeList) {
        doSetClassType_NotInScope(classTypeList);
    }

    protected void doSetClassType_NotInScope(Collection<String> classTypeList) {
        regINS(CK_NINS, cTL(classTypeList), xgetCValueClassType(), "CLASS_TYPE");
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * CLASS_TYPE: {NotNull, VARCHAR(255)} <br>
     * <pre>e.g. setClassType_LikeSearch("xxx", op <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> op.<span style="color: #CC4747">likeContain()</span>);</pre>
     * @param classType The value of classType as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setClassType_LikeSearch(String classType, ConditionOptionCall<LikeSearchOption> opLambda) {
        setClassType_LikeSearch(classType, xcLSOP(opLambda));
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * CLASS_TYPE: {NotNull, VARCHAR(255)} <br>
     * <pre>e.g. setClassType_LikeSearch("xxx", new <span style="color: #CC4747">LikeSearchOption</span>().likeContain());</pre>
     * @param classType The value of classType as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of like-search. (NotNull)
     */
    protected void setClassType_LikeSearch(String classType, LikeSearchOption likeSearchOption) {
        regLSQ(CK_LS, fRES(classType), xgetCValueClassType(), "CLASS_TYPE", likeSearchOption);
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classType The value of classType as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setClassType_NotLikeSearch(String classType, ConditionOptionCall<LikeSearchOption> opLambda) {
        setClassType_NotLikeSearch(classType, xcLSOP(opLambda));
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @param classType The value of classType as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of not-like-search. (NotNull)
     */
    protected void setClassType_NotLikeSearch(String classType, LikeSearchOption likeSearchOption) {
        regLSQ(CK_NLS, fRES(classType), xgetCValueClassType(), "CLASS_TYPE", likeSearchOption);
    }

    protected void regClassType(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueClassType(), "CLASS_TYPE"); }
    protected abstract ConditionValue xgetCValueClassType();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * VERSION_NO: {NotNull, INT(10)}
     * @param versionNo The value of versionNo as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setVersionNo_Equal(Integer versionNo) {
        doSetVersionNo_Equal(versionNo);
    }

    protected void doSetVersionNo_Equal(Integer versionNo) {
        regVersionNo(CK_EQ, versionNo);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * VERSION_NO: {NotNull, INT(10)}
     * @param versionNo The value of versionNo as notEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setVersionNo_NotEqual(Integer versionNo) {
        doSetVersionNo_NotEqual(versionNo);
    }

    protected void doSetVersionNo_NotEqual(Integer versionNo) {
        regVersionNo(CK_NES, versionNo);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * VERSION_NO: {NotNull, INT(10)}
     * @param versionNo The value of versionNo as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setVersionNo_GreaterThan(Integer versionNo) {
        regVersionNo(CK_GT, versionNo);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * VERSION_NO: {NotNull, INT(10)}
     * @param versionNo The value of versionNo as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setVersionNo_LessThan(Integer versionNo) {
        regVersionNo(CK_LT, versionNo);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * VERSION_NO: {NotNull, INT(10)}
     * @param versionNo The value of versionNo as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setVersionNo_GreaterEqual(Integer versionNo) {
        regVersionNo(CK_GE, versionNo);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * VERSION_NO: {NotNull, INT(10)}
     * @param versionNo The value of versionNo as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setVersionNo_LessEqual(Integer versionNo) {
        regVersionNo(CK_LE, versionNo);
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * VERSION_NO: {NotNull, INT(10)}
     * @param minNumber The min number of versionNo. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of versionNo. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of range-of. (NotNull)
     */
    public void setVersionNo_RangeOf(Integer minNumber, Integer maxNumber, ConditionOptionCall<RangeOfOption> opLambda) {
        setVersionNo_RangeOf(minNumber, maxNumber, xcROOP(opLambda));
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * VERSION_NO: {NotNull, INT(10)}
     * @param minNumber The min number of versionNo. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of versionNo. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    protected void setVersionNo_RangeOf(Integer minNumber, Integer maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, xgetCValueVersionNo(), "VERSION_NO", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * VERSION_NO: {NotNull, INT(10)}
     * @param versionNoList The collection of versionNo as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setVersionNo_InScope(Collection<Integer> versionNoList) {
        doSetVersionNo_InScope(versionNoList);
    }

    protected void doSetVersionNo_InScope(Collection<Integer> versionNoList) {
        regINS(CK_INS, cTL(versionNoList), xgetCValueVersionNo(), "VERSION_NO");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * VERSION_NO: {NotNull, INT(10)}
     * @param versionNoList The collection of versionNo as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setVersionNo_NotInScope(Collection<Integer> versionNoList) {
        doSetVersionNo_NotInScope(versionNoList);
    }

    protected void doSetVersionNo_NotInScope(Collection<Integer> versionNoList) {
        regINS(CK_NINS, cTL(versionNoList), xgetCValueVersionNo(), "VERSION_NO");
    }

    protected void regVersionNo(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueVersionNo(), "VERSION_NO"); }
    protected abstract ConditionValue xgetCValueVersionNo();

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    /**
     * Prepare ScalarCondition as equal. <br>
     * {where FOO = (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<PersistentEventValueCB> scalar_Equal() {
        return xcreateSLCFunction(CK_EQ, PersistentEventValueCB.class);
    }

    /**
     * Prepare ScalarCondition as equal. <br>
     * {where FOO &lt;&gt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<PersistentEventValueCB> scalar_NotEqual() {
        return xcreateSLCFunction(CK_NES, PersistentEventValueCB.class);
    }

    /**
     * Prepare ScalarCondition as greaterThan. <br>
     * {where FOO &gt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<PersistentEventValueCB> scalar_GreaterThan() {
        return xcreateSLCFunction(CK_GT, PersistentEventValueCB.class);
    }

    /**
     * Prepare ScalarCondition as lessThan. <br>
     * {where FOO &lt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<PersistentEventValueCB> scalar_LessThan() {
        return xcreateSLCFunction(CK_LT, PersistentEventValueCB.class);
    }

    /**
     * Prepare ScalarCondition as greaterEqual. <br>
     * {where FOO &gt;= (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<PersistentEventValueCB> scalar_GreaterEqual() {
        return xcreateSLCFunction(CK_GE, PersistentEventValueCB.class);
    }

    /**
     * Prepare ScalarCondition as lessEqual. <br>
     * {where FOO &lt;= (select max(BAR) from ...)}
     * <pre>
     * cb.query().<span style="color: #CC4747">scalar_LessEqual()</span>.max(new SubQuery&lt;PersistentEventValueCB&gt;() {
     *     public void query(PersistentEventValueCB subCB) {
     *         subCB.specify().setFoo... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setBar...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<PersistentEventValueCB> scalar_LessEqual() {
        return xcreateSLCFunction(CK_LE, PersistentEventValueCB.class);
    }

    @SuppressWarnings("unchecked")
    protected <CB extends ConditionBean> void xscalarCondition(String fn, SubQuery<CB> sq, String rd, HpSLCCustomized<CB> cs, ScalarConditionOption op) {
        assertObjectNotNull("subQuery", sq);
        PersistentEventValueCB cb = xcreateScalarConditionCB(); sq.query((CB)cb);
        String pp = keepScalarCondition(cb.query()); // for saving query-value
        cs.setPartitionByCBean((CB)xcreateScalarConditionPartitionByCB()); // for using partition-by
        registerScalarCondition(fn, cb.query(), pp, rd, cs, op);
    }
    public abstract String keepScalarCondition(PersistentEventValueCQ sq);

    protected PersistentEventValueCB xcreateScalarConditionCB() {
        PersistentEventValueCB cb = newMyCB(); cb.xsetupForScalarCondition(this); return cb;
    }

    protected PersistentEventValueCB xcreateScalarConditionPartitionByCB() {
        PersistentEventValueCB cb = newMyCB(); cb.xsetupForScalarConditionPartitionBy(this); return cb;
    }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    public void xsmyselfDerive(String fn, SubQuery<PersistentEventValueCB> sq, String al, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        PersistentEventValueCB cb = new PersistentEventValueCB(); cb.xsetupForDerivedReferrer(this);
        lockCall(() -> sq.query(cb)); String pp = keepSpecifyMyselfDerived(cb.query()); String pk = "ID";
        registerSpecifyMyselfDerived(fn, cb.query(), pk, pk, pp, "myselfDerived", al, op);
    }
    public abstract String keepSpecifyMyselfDerived(PersistentEventValueCQ sq);

    /**
     * Prepare for (Query)MyselfDerived (correlated sub-query).
     * @return The object to set up a function for myself table. (NotNull)
     */
    public HpQDRFunction<PersistentEventValueCB> myselfDerived() {
        return xcreateQDRFunctionMyselfDerived(PersistentEventValueCB.class);
    }
    @SuppressWarnings("unchecked")
    protected <CB extends ConditionBean> void xqderiveMyselfDerived(String fn, SubQuery<CB> sq, String rd, Object vl, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        PersistentEventValueCB cb = new PersistentEventValueCB(); cb.xsetupForDerivedReferrer(this); sq.query((CB)cb);
        String pk = "ID";
        String sqpp = keepQueryMyselfDerived(cb.query()); // for saving query-value.
        String prpp = keepQueryMyselfDerivedParameter(vl);
        registerQueryMyselfDerived(fn, cb.query(), pk, pk, sqpp, "myselfDerived", rd, vl, prpp, op);
    }
    public abstract String keepQueryMyselfDerived(PersistentEventValueCQ sq);
    public abstract String keepQueryMyselfDerivedParameter(Object vl);

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    /**
     * Prepare for MyselfExists (correlated sub-query).
     * @param subCBLambda The implementation of sub-query. (NotNull)
     */
    public void myselfExists(SubQuery<PersistentEventValueCB> subCBLambda) {
        assertObjectNotNull("subCBLambda", subCBLambda);
        PersistentEventValueCB cb = new PersistentEventValueCB(); cb.xsetupForMyselfExists(this);
        lockCall(() -> subCBLambda.query(cb)); String pp = keepMyselfExists(cb.query());
        registerMyselfExists(cb.query(), pp);
    }
    public abstract String keepMyselfExists(PersistentEventValueCQ sq);

    // ===================================================================================
    //                                                                        Manual Order
    //                                                                        ============
    /**
     * Order along manual ordering information.
     * <pre>
     * cb.query().addOrderBy_Birthdate_Asc().<span style="color: #CC4747">withManualOrder</span>(<span style="color: #553000">op</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_GreaterEqual</span>(priorityDate); <span style="color: #3F7E5E">// e.g. 2000/01/01</span>
     * });
     * <span style="color: #3F7E5E">// order by </span>
     * <span style="color: #3F7E5E">//   case</span>
     * <span style="color: #3F7E5E">//     when BIRTHDATE &gt;= '2000/01/01' then 0</span>
     * <span style="color: #3F7E5E">//     else 1</span>
     * <span style="color: #3F7E5E">//   end asc, ...</span>
     *
     * cb.query().addOrderBy_MemberStatusCode_Asc().<span style="color: #CC4747">withManualOrder</span>(<span style="color: #553000">op</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Withdrawal);
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Formalized);
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Provisional);
     * });
     * <span style="color: #3F7E5E">// order by </span>
     * <span style="color: #3F7E5E">//   case</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'WDL' then 0</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'FML' then 1</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'PRV' then 2</span>
     * <span style="color: #3F7E5E">//     else 3</span>
     * <span style="color: #3F7E5E">//   end asc, ...</span>
     * </pre>
     * <p>This function with Union is unsupported!</p>
     * <p>The order values are bound (treated as bind parameter).</p>
     * @param opLambda The callback for option of manual-order containing order values. (NotNull)
     */
    public void withManualOrder(ManualOrderOptionCall opLambda) { // is user public!
        xdoWithManualOrder(cMOO(opLambda));
    }

    // ===================================================================================
    //                                                                    Small Adjustment
    //                                                                    ================
    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    protected PersistentEventValueCB newMyCB() {
        return new PersistentEventValueCB();
    }
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xabUDT() { return Date.class.getName(); }
    protected String xabCQ() { return PersistentEventValueCQ.class.getName(); }
    protected String xabLSO() { return LikeSearchOption.class.getName(); }
    protected String xabSLCS() { return HpSLCSetupper.class.getName(); }
    protected String xabSCP() { return SubQuery.class.getName(); }
}
