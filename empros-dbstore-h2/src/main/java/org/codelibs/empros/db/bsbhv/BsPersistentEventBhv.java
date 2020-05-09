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
package org.codelibs.empros.db.bsbhv;

import java.util.List;

import org.codelibs.empros.db.bsentity.dbmeta.PersistentEventDbm;
import org.codelibs.empros.db.cbean.PersistentEventCB;
import org.codelibs.empros.db.cbean.PersistentEventValueCB;
import org.codelibs.empros.db.exbhv.PersistentEventBhv;
import org.codelibs.empros.db.exbhv.PersistentEventValueBhv;
import org.codelibs.empros.db.exentity.PersistentEvent;
import org.codelibs.empros.db.exentity.PersistentEventValue;
import org.seasar.dbflute.Entity;
import org.seasar.dbflute.bhv.AbstractBehaviorWritable;
import org.seasar.dbflute.bhv.ConditionBeanSetupper;
import org.seasar.dbflute.bhv.DeleteOption;
import org.seasar.dbflute.bhv.InsertOption;
import org.seasar.dbflute.bhv.LoadReferrerOption;
import org.seasar.dbflute.bhv.QueryInsertSetupper;
import org.seasar.dbflute.bhv.UpdateOption;
import org.seasar.dbflute.cbean.ConditionBean;
import org.seasar.dbflute.cbean.EntityRowHandler;
import org.seasar.dbflute.cbean.ListResultBean;
import org.seasar.dbflute.cbean.PagingResultBean;
import org.seasar.dbflute.cbean.SpecifyQuery;
import org.seasar.dbflute.dbmeta.DBMeta;
import org.seasar.dbflute.outsidesql.executor.OutsideSqlBasicExecutor;

/**
 * The behavior of PERSISTENT_EVENT as TABLE. <br />
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
public abstract class BsPersistentEventBhv extends AbstractBehaviorWritable {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /*df:beginQueryPath*/
    /*df:endQueryPath*/

    // ===================================================================================
    //                                                                          Table name
    //                                                                          ==========
    /** @return The name on database of table. (NotNull) */
    @Override
    public String getTableDbName() {
        return "PERSISTENT_EVENT";
    }

    // ===================================================================================
    //                                                                              DBMeta
    //                                                                              ======
    /** @return The instance of DBMeta. (NotNull) */
    @Override
    public DBMeta getDBMeta() {
        return PersistentEventDbm.getInstance();
    }

    /** @return The instance of DBMeta as my table type. (NotNull) */
    public PersistentEventDbm getMyDBMeta() {
        return PersistentEventDbm.getInstance();
    }

    // ===================================================================================
    //                                                                        New Instance
    //                                                                        ============
    /** {@inheritDoc} */
    @Override
    public Entity newEntity() {
        return newMyEntity();
    }

    /** {@inheritDoc} */
    @Override
    public ConditionBean newConditionBean() {
        return newMyConditionBean();
    }

    /** @return The instance of new entity as my table type. (NotNull) */
    public PersistentEvent newMyEntity() {
        return new PersistentEvent();
    }

    /** @return The instance of new condition-bean as my table type. (NotNull) */
    public PersistentEventCB newMyConditionBean() {
        return new PersistentEventCB();
    }

    // ===================================================================================
    //                                                                        Count Select
    //                                                                        ============
    /**
     * Select the count of uniquely-selected records by the condition-bean. {IgnorePagingCondition, IgnoreSpecifyColumn}<br />
     * SpecifyColumn is ignored but you can use it only to remove text type column for union's distinct.
     * <pre>
     * PersistentEventCB cb = new PersistentEventCB();
     * cb.query().setFoo...(value);
     * int count = persistentEventBhv.<span style="color: #FD4747">selectCount</span>(cb);
     * </pre>
     * @param cb The condition-bean of PersistentEvent. (NotNull)
     * @return The selected count.
     */
    public int selectCount(final PersistentEventCB cb) {
        return doSelectCountUniquely(cb);
    }

    protected int doSelectCountUniquely(final PersistentEventCB cb) { // called by selectCount(cb) 
        assertCBStateValid(cb);
        return delegateSelectCountUniquely(cb);
    }

    protected int doSelectCountPlainly(final PersistentEventCB cb) { // called by selectPage(cb)
        assertCBStateValid(cb);
        return delegateSelectCountPlainly(cb);
    }

    @Override
    protected int doReadCount(final ConditionBean cb) {
        return selectCount(downcast(cb));
    }

    // ===================================================================================
    //                                                                       Cursor Select
    //                                                                       =============
    /**
     * Select the cursor by the condition-bean.
     * <pre>
     * PersistentEventCB cb = new PersistentEventCB();
     * cb.query().setFoo...(value);
     * persistentEventBhv.<span style="color: #FD4747">selectCursor</span>(cb, new EntityRowHandler&lt;PersistentEvent&gt;() {
     *     public void handle(PersistentEvent entity) {
     *         ... = entity.getFoo...();
     *     }
     * });
     * </pre>
     * @param cb The condition-bean of PersistentEvent. (NotNull)
     * @param entityRowHandler The handler of entity row of PersistentEvent. (NotNull)
     */
    public void selectCursor(final PersistentEventCB cb,
            final EntityRowHandler<PersistentEvent> entityRowHandler) {
        doSelectCursor(cb, entityRowHandler, PersistentEvent.class);
    }

    protected <ENTITY extends PersistentEvent> void doSelectCursor(
            final PersistentEventCB cb,
            final EntityRowHandler<ENTITY> entityRowHandler,
            final Class<ENTITY> entityType) {
        assertCBStateValid(cb);
        assertObjectNotNull("entityRowHandler<PersistentEvent>",
                entityRowHandler);
        assertObjectNotNull("entityType", entityType);
        assertSpecifyDerivedReferrerEntityProperty(cb, entityType);
        delegateSelectCursor(cb, entityRowHandler, entityType);
    }

    // ===================================================================================
    //                                                                       Entity Select
    //                                                                       =============
    /**
     * Select the entity by the condition-bean.
     * <pre>
     * PersistentEventCB cb = new PersistentEventCB();
     * cb.query().setFoo...(value);
     * PersistentEvent persistentEvent = persistentEventBhv.<span style="color: #FD4747">selectEntity</span>(cb);
     * if (persistentEvent != null) {
     *     ... = persistentEvent.get...();
     * } else {
     *     ...
     * }
     * </pre>
     * @param cb The condition-bean of PersistentEvent. (NotNull)
     * @return The selected entity. (NullAllowed: If the condition has no data, it returns null)
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public PersistentEvent selectEntity(final PersistentEventCB cb) {
        return doSelectEntity(cb, PersistentEvent.class);
    }

    protected <ENTITY extends PersistentEvent> ENTITY doSelectEntity(
            final PersistentEventCB cb, final Class<ENTITY> entityType) {
        assertCBStateValid(cb);
        return helpSelectEntityInternally(cb,
                new InternalSelectEntityCallback<ENTITY, PersistentEventCB>() {
                    @Override
                    public List<ENTITY> callbackSelectList(
                            final PersistentEventCB cb) {
                        return doSelectList(cb, entityType);
                    }
                });
    }

    @Override
    protected Entity doReadEntity(final ConditionBean cb) {
        return selectEntity(downcast(cb));
    }

    /**
     * Select the entity by the condition-bean with deleted check.
     * <pre>
     * PersistentEventCB cb = new PersistentEventCB();
     * cb.query().setFoo...(value);
     * PersistentEvent persistentEvent = persistentEventBhv.<span style="color: #FD4747">selectEntityWithDeletedCheck</span>(cb);
     * ... = persistentEvent.get...(); <span style="color: #3F7E5E">// the entity always be not null</span>
     * </pre>
     * @param cb The condition-bean of PersistentEvent. (NotNull)
     * @return The selected entity. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public PersistentEvent selectEntityWithDeletedCheck(
            final PersistentEventCB cb) {
        return doSelectEntityWithDeletedCheck(cb, PersistentEvent.class);
    }

    protected <ENTITY extends PersistentEvent> ENTITY doSelectEntityWithDeletedCheck(
            final PersistentEventCB cb, final Class<ENTITY> entityType) {
        assertCBStateValid(cb);
        return helpSelectEntityWithDeletedCheckInternally(
                cb,
                new InternalSelectEntityWithDeletedCheckCallback<ENTITY, PersistentEventCB>() {
                    @Override
                    public List<ENTITY> callbackSelectList(
                            final PersistentEventCB cb) {
                        return doSelectList(cb, entityType);
                    }
                });
    }

    @Override
    protected Entity doReadEntityWithDeletedCheck(final ConditionBean cb) {
        return selectEntityWithDeletedCheck(downcast(cb));
    }

    /**
     * Select the entity by the primary-key value.
     * @param id The one of primary key. (NotNull)
     * @return The selected entity. (NullAllowed: If the primary-key value has no data, it returns null)
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public PersistentEvent selectByPKValue(final Long id) {
        return doSelectByPKValue(id, PersistentEvent.class);
    }

    protected <ENTITY extends PersistentEvent> ENTITY doSelectByPKValue(
            final Long id, final Class<ENTITY> entityType) {
        return doSelectEntity(buildPKCB(id), entityType);
    }

    /**
     * Select the entity by the primary-key value with deleted check.
     * @param id The one of primary key. (NotNull)
     * @return The selected entity. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public PersistentEvent selectByPKValueWithDeletedCheck(final Long id) {
        return doSelectByPKValueWithDeletedCheck(id, PersistentEvent.class);
    }

    protected <ENTITY extends PersistentEvent> ENTITY doSelectByPKValueWithDeletedCheck(
            final Long id, final Class<ENTITY> entityType) {
        return doSelectEntityWithDeletedCheck(buildPKCB(id), entityType);
    }

    private PersistentEventCB buildPKCB(final Long id) {
        assertObjectNotNull("id", id);
        final PersistentEventCB cb = newMyConditionBean();
        cb.query().setId_Equal(id);
        return cb;
    }

    // ===================================================================================
    //                                                                         List Select
    //                                                                         ===========
    /**
     * Select the list as result bean.
     * <pre>
     * PersistentEventCB cb = new PersistentEventCB();
     * cb.query().setFoo...(value);
     * cb.query().addOrderBy_Bar...();
     * ListResultBean&lt;PersistentEvent&gt; persistentEventList = persistentEventBhv.<span style="color: #FD4747">selectList</span>(cb);
     * for (PersistentEvent persistentEvent : persistentEventList) {
     *     ... = persistentEvent.get...();
     * }
     * </pre>
     * @param cb The condition-bean of PersistentEvent. (NotNull)
     * @return The result bean of selected list. (NotNull)
     * @exception org.seasar.dbflute.exception.DangerousResultSizeException When the result size is over the specified safety size.
     */
    public ListResultBean<PersistentEvent> selectList(final PersistentEventCB cb) {
        return doSelectList(cb, PersistentEvent.class);
    }

    protected <ENTITY extends PersistentEvent> ListResultBean<ENTITY> doSelectList(
            final PersistentEventCB cb, final Class<ENTITY> entityType) {
        assertCBStateValid(cb);
        assertObjectNotNull("entityType", entityType);
        assertSpecifyDerivedReferrerEntityProperty(cb, entityType);
        return helpSelectListInternally(cb, entityType,
                new InternalSelectListCallback<ENTITY, PersistentEventCB>() {
                    @Override
                    public List<ENTITY> callbackSelectList(
                            final PersistentEventCB cb,
                            final Class<ENTITY> entityType) {
                        return delegateSelectList(cb, entityType);
                    }
                });
    }

    @Override
    protected ListResultBean<? extends Entity> doReadList(final ConditionBean cb) {
        return selectList(downcast(cb));
    }

    // ===================================================================================
    //                                                                         Page Select
    //                                                                         ===========
    /**
     * Select the page as result bean. <br />
     * (both count-select and paging-select are executed)
     * <pre>
     * PersistentEventCB cb = new PersistentEventCB();
     * cb.query().setFoo...(value);
     * cb.query().addOrderBy_Bar...();
     * cb.<span style="color: #FD4747">paging</span>(20, 3); <span style="color: #3F7E5E">// 20 records per a page and current page number is 3</span>
     * PagingResultBean&lt;PersistentEvent&gt; page = persistentEventBhv.<span style="color: #FD4747">selectPage</span>(cb);
     * int allRecordCount = page.getAllRecordCount();
     * int allPageCount = page.getAllPageCount();
     * boolean isExistPrePage = page.isExistPrePage();
     * boolean isExistNextPage = page.isExistNextPage();
     * ...
     * for (PersistentEvent persistentEvent : page) {
     *     ... = persistentEvent.get...();
     * }
     * </pre>
     * @param cb The condition-bean of PersistentEvent. (NotNull)
     * @return The result bean of selected page. (NotNull)
     * @exception org.seasar.dbflute.exception.DangerousResultSizeException When the result size is over the specified safety size.
     */
    public PagingResultBean<PersistentEvent> selectPage(
            final PersistentEventCB cb) {
        return doSelectPage(cb, PersistentEvent.class);
    }

    protected <ENTITY extends PersistentEvent> PagingResultBean<ENTITY> doSelectPage(
            final PersistentEventCB cb, final Class<ENTITY> entityType) {
        assertCBStateValid(cb);
        assertObjectNotNull("entityType", entityType);
        return helpSelectPageInternally(cb, entityType,
                new InternalSelectPageCallback<ENTITY, PersistentEventCB>() {
                    @Override
                    public int callbackSelectCount(final PersistentEventCB cb) {
                        return doSelectCountPlainly(cb);
                    }

                    @Override
                    public List<ENTITY> callbackSelectList(
                            final PersistentEventCB cb,
                            final Class<ENTITY> entityType) {
                        return doSelectList(cb, entityType);
                    }
                });
    }

    @Override
    protected PagingResultBean<? extends Entity> doReadPage(
            final ConditionBean cb) {
        return selectPage(downcast(cb));
    }

    // ===================================================================================
    //                                                                       Scalar Select
    //                                                                       =============
    /**
     * Select the scalar value derived by a function from uniquely-selected records. <br />
     * You should call a function method after this method called like as follows:
     * <pre>
     * persistentEventBhv.<span style="color: #FD4747">scalarSelect</span>(Date.class).max(new ScalarQuery() {
     *     public void query(PersistentEventCB cb) {
     *         cb.specify().<span style="color: #FD4747">columnFooDatetime()</span>; <span style="color: #3F7E5E">// required for a function</span>
     *         cb.query().setBarName_PrefixSearch("S");
     *     }
     * });
     * </pre>
     * @param <RESULT> The type of result.
     * @param resultType The type of result. (NotNull)
     * @return The scalar value derived by a function. (NullAllowed)
     */
    public <RESULT> SLFunction<PersistentEventCB, RESULT> scalarSelect(
            final Class<RESULT> resultType) {
        return doScalarSelect(resultType, newMyConditionBean());
    }

    protected <RESULT, CB extends PersistentEventCB> SLFunction<CB, RESULT> doScalarSelect(
            final Class<RESULT> resultType, final CB cb) {
        assertObjectNotNull("resultType", resultType);
        assertCBStateValid(cb);
        cb.xsetupForScalarSelect();
        cb.getSqlClause().disableSelectIndex(); // for when you use union
        return new SLFunction<CB, RESULT>(cb, resultType);
    }

    // ===================================================================================
    //                                                                            Sequence
    //                                                                            ========
    @Override
    protected Number doReadNextVal() {
        final String msg = "This table is NOT related to sequence: "
                + getTableDbName();
        throw new UnsupportedOperationException(msg);
    }

    // ===================================================================================
    //                                                                       Load Referrer
    //                                                                       =============
    /**
     * {Refer to overload method that has an argument of the list of entity.}
     * @param persistentEvent The entity of persistentEvent. (NotNull)
     * @param conditionBeanSetupper The instance of referrer condition-bean set-upper for registering referrer condition. (NotNull)
     */
    public void loadPersistentEventValueList(
            final PersistentEvent persistentEvent,
            final ConditionBeanSetupper<PersistentEventValueCB> conditionBeanSetupper) {
        xassLRArg(persistentEvent, conditionBeanSetupper);
        loadPersistentEventValueList(xnewLRLs(persistentEvent),
                conditionBeanSetupper);
    }

    /**
     * Load referrer of persistentEventValueList with the set-upper for condition-bean of referrer. <br />
     * PERSISTENT_EVENT_VALUE by your EVENT_ID, named 'persistentEventValueList'.
     * <pre>
     * persistentEventBhv.<span style="color: #FD4747">loadPersistentEventValueList</span>(persistentEventList, new ConditionBeanSetupper&lt;PersistentEventValueCB&gt;() {
     *     public void setup(PersistentEventValueCB cb) {
     *         cb.setupSelect...();
     *         cb.query().setFoo...(value);
     *         cb.query().addOrderBy_Bar...(); <span style="color: #3F7E5E">// basically you should order referrer list</span>
     *     }
     * });
     * for (PersistentEvent persistentEvent : persistentEventList) {
     *     ... = persistentEvent.<span style="color: #FD4747">getPersistentEventValueList()</span>;
     * }
     * </pre>
     * About internal policy, the value of primary key(and others too) is treated as case-insensitive. <br />
     * The condition-bean that the set-upper provides have settings before you touch it. It is as follows:
     * <pre>
     * cb.query().setEventId_InScope(pkList);
     * cb.query().addOrderBy_EventId_Asc();
     * </pre>
     * @param persistentEventList The entity list of persistentEvent. (NotNull)
     * @param conditionBeanSetupper The instance of referrer condition-bean set-upper for registering referrer condition. (NotNull)
     */
    public void loadPersistentEventValueList(
            final List<PersistentEvent> persistentEventList,
            final ConditionBeanSetupper<PersistentEventValueCB> conditionBeanSetupper) {
        xassLRArg(persistentEventList, conditionBeanSetupper);
        loadPersistentEventValueList(
                persistentEventList,
                new LoadReferrerOption<PersistentEventValueCB, PersistentEventValue>()
                        .xinit(conditionBeanSetupper));
    }

    /**
     * {Refer to overload method that has an argument of the list of entity.}
     * @param persistentEvent The entity of persistentEvent. (NotNull)
     * @param loadReferrerOption The option of load-referrer. (NotNull)
     */
    public void loadPersistentEventValueList(
            final PersistentEvent persistentEvent,
            final LoadReferrerOption<PersistentEventValueCB, PersistentEventValue> loadReferrerOption) {
        xassLRArg(persistentEvent, loadReferrerOption);
        loadPersistentEventValueList(xnewLRLs(persistentEvent),
                loadReferrerOption);
    }

    /**
     * {Refer to overload method that has an argument of condition-bean setupper.}
     * @param persistentEventList The entity list of persistentEvent. (NotNull)
     * @param loadReferrerOption The option of load-referrer. (NotNull)
     */
    public void loadPersistentEventValueList(
            final List<PersistentEvent> persistentEventList,
            final LoadReferrerOption<PersistentEventValueCB, PersistentEventValue> loadReferrerOption) {
        xassLRArg(persistentEventList, loadReferrerOption);
        if (persistentEventList.isEmpty()) {
            return;
        }
        final PersistentEventValueBhv referrerBhv = xgetBSFLR().select(
                PersistentEventValueBhv.class);
        helpLoadReferrerInternally(
                persistentEventList,
                loadReferrerOption,
                new InternalLoadReferrerCallback<PersistentEvent, Long, PersistentEventValueCB, PersistentEventValue>() {
                    @Override
                    public Long getPKVal(final PersistentEvent e) {
                        return e.getId();
                    }

                    @Override
                    public void setRfLs(final PersistentEvent e,
                            final List<PersistentEventValue> ls) {
                        e.setPersistentEventValueList(ls);
                    }

                    @Override
                    public PersistentEventValueCB newMyCB() {
                        return referrerBhv.newMyConditionBean();
                    }

                    @Override
                    public void qyFKIn(final PersistentEventValueCB cb,
                            final List<Long> ls) {
                        cb.query().setEventId_InScope(ls);
                    }

                    @Override
                    public void qyOdFKAsc(final PersistentEventValueCB cb) {
                        cb.query().addOrderBy_EventId_Asc();
                    }

                    @Override
                    public void spFKCol(final PersistentEventValueCB cb) {
                        cb.specify().columnEventId();
                    }

                    @Override
                    public List<PersistentEventValue> selRfLs(
                            final PersistentEventValueCB cb) {
                        return referrerBhv.selectList(cb);
                    }

                    @Override
                    public Long getFKVal(final PersistentEventValue e) {
                        return e.getEventId();
                    }

                    @Override
                    public void setlcEt(final PersistentEventValue re,
                            final PersistentEvent le) {
                        re.setPersistentEvent(le);
                    }

                    @Override
                    public String getRfPrNm() {
                        return "persistentEventValueList";
                    }
                });
    }

    // ===================================================================================
    //                                                                    Pull out Foreign
    //                                                                    ================

    // ===================================================================================
    //                                                                       Entity Update
    //                                                                       =============
    /**
     * Insert the entity.
     * <pre>
     * PersistentEvent persistentEvent = new PersistentEvent();
     * <span style="color: #3F7E5E">// if auto-increment, you don't need to set the PK value</span>
     * persistentEvent.setFoo...(value);
     * persistentEvent.setBar...(value);
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//persistentEvent.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//persistentEvent.set...;</span>
     * persistentEventBhv.<span style="color: #FD4747">insert</span>(persistentEvent);
     * ... = persistentEvent.getPK...(); <span style="color: #3F7E5E">// if auto-increment, you can get the value after</span>
     * </pre>
     * @param persistentEvent The entity of insert target. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void insert(final PersistentEvent persistentEvent) {
        doInsert(persistentEvent, null);
    }

    protected void doInsert(final PersistentEvent persistentEvent,
            final InsertOption<PersistentEventCB> option) {
        assertObjectNotNull("persistentEvent", persistentEvent);
        prepareInsertOption(option);
        delegateInsert(persistentEvent, option);
    }

    protected void prepareInsertOption(
            final InsertOption<PersistentEventCB> option) {
        if (option == null) {
            return;
        }
        assertInsertOptionStatus(option);
    }

    @Override
    protected void doCreate(final Entity entity,
            final InsertOption<? extends ConditionBean> option) {
        if (option == null) {
            insert(downcast(entity));
        } else {
            varyingInsert(downcast(entity), downcast(option));
        }
    }

    /**
     * Update the entity modified-only. {UpdateCountZeroException, ExclusiveControl}
     * <pre>
     * PersistentEvent persistentEvent = new PersistentEvent();
     * persistentEvent.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * persistentEvent.setFoo...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//persistentEvent.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//persistentEvent.set...;</span>
     * <span style="color: #3F7E5E">// if exclusive control, the value of exclusive control column is required</span>
     * persistentEvent.<span style="color: #FD4747">setVersionNo</span>(value);
     * try {
     *     persistentEventBhv.<span style="color: #FD4747">update</span>(persistentEvent);
     * } catch (EntityAlreadyUpdatedException e) { <span style="color: #3F7E5E">// if concurrent update</span>
     *     ...
     * } 
     * </pre>
     * @param persistentEvent The entity of update target. (NotNull) {PrimaryKeyRequired, ConcurrencyColumnRequired}
     * @exception org.seasar.dbflute.exception.EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void update(final PersistentEvent persistentEvent) {
        doUpdate(persistentEvent, null);
    }

    protected void doUpdate(final PersistentEvent persistentEvent,
            final UpdateOption<PersistentEventCB> option) {
        assertObjectNotNull("persistentEvent", persistentEvent);
        prepareUpdateOption(option);
        helpUpdateInternally(persistentEvent,
                new InternalUpdateCallback<PersistentEvent>() {
                    @Override
                    public int callbackDelegateUpdate(
                            final PersistentEvent entity) {
                        return delegateUpdate(entity, option);
                    }
                });
    }

    protected void prepareUpdateOption(
            final UpdateOption<PersistentEventCB> option) {
        if (option == null) {
            return;
        }
        assertUpdateOptionStatus(option);
        if (option.hasSelfSpecification()) {
            option.resolveSelfSpecification(createCBForVaryingUpdate());
        }
        if (option.hasSpecifiedUpdateColumn()) {
            option.resolveUpdateColumnSpecification(createCBForSpecifiedUpdate());
        }
    }

    protected PersistentEventCB createCBForVaryingUpdate() {
        final PersistentEventCB cb = newMyConditionBean();
        cb.xsetupForVaryingUpdate();
        return cb;
    }

    protected PersistentEventCB createCBForSpecifiedUpdate() {
        final PersistentEventCB cb = newMyConditionBean();
        cb.xsetupForSpecifiedUpdate();
        return cb;
    }

    @Override
    protected void doModify(final Entity entity,
            final UpdateOption<? extends ConditionBean> option) {
        if (option == null) {
            update(downcast(entity));
        } else {
            varyingUpdate(downcast(entity), downcast(option));
        }
    }

    /**
     * Update the entity non-strictly modified-only. {UpdateCountZeroException, NonExclusiveControl}
     * <pre>
     * PersistentEvent persistentEvent = new PersistentEvent();
     * persistentEvent.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * persistentEvent.setFoo...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//persistentEvent.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//persistentEvent.set...;</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of exclusive control column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//persistentEvent.setVersionNo(value);</span>
     * persistentEventBhv.<span style="color: #FD4747">updateNonstrict</span>(persistentEvent);
     * </pre>
     * @param persistentEvent The entity of update target. (NotNull) {PrimaryKeyRequired}
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void updateNonstrict(final PersistentEvent persistentEvent) {
        doUpdateNonstrict(persistentEvent, null);
    }

    protected void doUpdateNonstrict(final PersistentEvent persistentEvent,
            final UpdateOption<PersistentEventCB> option) {
        assertObjectNotNull("persistentEvent", persistentEvent);
        prepareUpdateOption(option);
        helpUpdateNonstrictInternally(persistentEvent,
                new InternalUpdateNonstrictCallback<PersistentEvent>() {
                    @Override
                    public int callbackDelegateUpdateNonstrict(
                            final PersistentEvent entity) {
                        return delegateUpdateNonstrict(entity, option);
                    }
                });
    }

    @Override
    protected void doModifyNonstrict(final Entity entity,
            final UpdateOption<? extends ConditionBean> option) {
        if (option == null) {
            updateNonstrict(downcast(entity));
        } else {
            varyingUpdateNonstrict(downcast(entity), downcast(option));
        }
    }

    /**
     * Insert or update the entity modified-only. {ExclusiveControl(when update)}
     * @param persistentEvent The entity of insert or update target. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void insertOrUpdate(final PersistentEvent persistentEvent) {
        doInesrtOrUpdate(persistentEvent, null, null);
    }

    protected void doInesrtOrUpdate(final PersistentEvent persistentEvent,
            final InsertOption<PersistentEventCB> insertOption,
            final UpdateOption<PersistentEventCB> updateOption) {
        helpInsertOrUpdateInternally(
                persistentEvent,
                new InternalInsertOrUpdateCallback<PersistentEvent, PersistentEventCB>() {
                    @Override
                    public void callbackInsert(final PersistentEvent entity) {
                        doInsert(entity, insertOption);
                    }

                    @Override
                    public void callbackUpdate(final PersistentEvent entity) {
                        doUpdate(entity, updateOption);
                    }

                    @Override
                    public PersistentEventCB callbackNewMyConditionBean() {
                        return newMyConditionBean();
                    }

                    @Override
                    public int callbackSelectCount(final PersistentEventCB cb) {
                        return selectCount(cb);
                    }
                });
    }

    @Override
    protected void doCreateOrModify(final Entity entity,
            InsertOption<? extends ConditionBean> insertOption,
            UpdateOption<? extends ConditionBean> updateOption) {
        if (insertOption == null && updateOption == null) {
            insertOrUpdate(downcast(entity));
        } else {
            insertOption = insertOption == null ? new InsertOption<PersistentEventCB>()
                    : insertOption;
            updateOption = updateOption == null ? new UpdateOption<PersistentEventCB>()
                    : updateOption;
            varyingInsertOrUpdate(downcast(entity), downcast(insertOption),
                    downcast(updateOption));
        }
    }

    /**
     * Insert or update the entity non-strictly modified-only. {NonExclusiveControl(when update)}
     * @param persistentEvent The entity of insert or update target. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void insertOrUpdateNonstrict(final PersistentEvent persistentEvent) {
        doInesrtOrUpdateNonstrict(persistentEvent, null, null);
    }

    protected void doInesrtOrUpdateNonstrict(
            final PersistentEvent persistentEvent,
            final InsertOption<PersistentEventCB> insertOption,
            final UpdateOption<PersistentEventCB> updateOption) {
        helpInsertOrUpdateInternally(persistentEvent,
                new InternalInsertOrUpdateNonstrictCallback<PersistentEvent>() {
                    @Override
                    public void callbackInsert(final PersistentEvent entity) {
                        doInsert(entity, insertOption);
                    }

                    @Override
                    public void callbackUpdateNonstrict(
                            final PersistentEvent entity) {
                        doUpdateNonstrict(entity, updateOption);
                    }
                });
    }

    @Override
    protected void doCreateOrModifyNonstrict(final Entity entity,
            InsertOption<? extends ConditionBean> insertOption,
            UpdateOption<? extends ConditionBean> updateOption) {
        if (insertOption == null && updateOption == null) {
            insertOrUpdateNonstrict(downcast(entity));
        } else {
            insertOption = insertOption == null ? new InsertOption<PersistentEventCB>()
                    : insertOption;
            updateOption = updateOption == null ? new UpdateOption<PersistentEventCB>()
                    : updateOption;
            varyingInsertOrUpdateNonstrict(downcast(entity),
                    downcast(insertOption), downcast(updateOption));
        }
    }

    /**
     * Delete the entity. {UpdateCountZeroException, ExclusiveControl}
     * <pre>
     * PersistentEvent persistentEvent = new PersistentEvent();
     * persistentEvent.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * <span style="color: #3F7E5E">// if exclusive control, the value of exclusive control column is required</span>
     * persistentEvent.<span style="color: #FD4747">setVersionNo</span>(value);
     * try {
     *     persistentEventBhv.<span style="color: #FD4747">delete</span>(persistentEvent);
     * } catch (EntityAlreadyUpdatedException e) { <span style="color: #3F7E5E">// if concurrent update</span>
     *     ...
     * } 
     * </pre>
     * @param persistentEvent The entity of delete target. (NotNull) {PrimaryKeyRequired, ConcurrencyColumnRequired}
     * @exception org.seasar.dbflute.exception.EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     */
    public void delete(final PersistentEvent persistentEvent) {
        doDelete(persistentEvent, null);
    }

    protected void doDelete(final PersistentEvent persistentEvent,
            final DeleteOption<PersistentEventCB> option) {
        assertObjectNotNull("persistentEvent", persistentEvent);
        prepareDeleteOption(option);
        helpDeleteInternally(persistentEvent,
                new InternalDeleteCallback<PersistentEvent>() {
                    @Override
                    public int callbackDelegateDelete(
                            final PersistentEvent entity) {
                        return delegateDelete(entity, option);
                    }
                });
    }

    protected void prepareDeleteOption(
            final DeleteOption<PersistentEventCB> option) {
        if (option == null) {
            return;
        }
        assertDeleteOptionStatus(option);
    }

    @Override
    protected void doRemove(final Entity entity,
            final DeleteOption<? extends ConditionBean> option) {
        if (option == null) {
            delete(downcast(entity));
        } else {
            varyingDelete(downcast(entity), downcast(option));
        }
    }

    /**
     * Delete the entity non-strictly. {UpdateCountZeroException, NonExclusiveControl}
     * <pre>
     * PersistentEvent persistentEvent = new PersistentEvent();
     * persistentEvent.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of exclusive control column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//persistentEvent.setVersionNo(value);</span>
     * persistentEventBhv.<span style="color: #FD4747">deleteNonstrict</span>(persistentEvent);
     * </pre>
     * @param persistentEvent The entity of delete target. (NotNull) {PrimaryKeyRequired}
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     */
    public void deleteNonstrict(final PersistentEvent persistentEvent) {
        doDeleteNonstrict(persistentEvent, null);
    }

    protected void doDeleteNonstrict(final PersistentEvent persistentEvent,
            final DeleteOption<PersistentEventCB> option) {
        assertObjectNotNull("persistentEvent", persistentEvent);
        prepareDeleteOption(option);
        helpDeleteNonstrictInternally(persistentEvent,
                new InternalDeleteNonstrictCallback<PersistentEvent>() {
                    @Override
                    public int callbackDelegateDeleteNonstrict(
                            final PersistentEvent entity) {
                        return delegateDeleteNonstrict(entity, option);
                    }
                });
    }

    /**
     * Delete the entity non-strictly ignoring deleted. {UpdateCountZeroException, NonExclusiveControl}
     * <pre>
     * PersistentEvent persistentEvent = new PersistentEvent();
     * persistentEvent.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of exclusive control column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//persistentEvent.setVersionNo(value);</span>
     * persistentEventBhv.<span style="color: #FD4747">deleteNonstrictIgnoreDeleted</span>(persistentEvent);
     * <span style="color: #3F7E5E">// if the target entity doesn't exist, no exception</span>
     * </pre>
     * @param persistentEvent The entity of delete target. (NotNull) {PrimaryKeyRequired}
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     */
    public void deleteNonstrictIgnoreDeleted(
            final PersistentEvent persistentEvent) {
        doDeleteNonstrictIgnoreDeleted(persistentEvent, null);
    }

    protected void doDeleteNonstrictIgnoreDeleted(
            final PersistentEvent persistentEvent,
            final DeleteOption<PersistentEventCB> option) {
        assertObjectNotNull("persistentEvent", persistentEvent);
        prepareDeleteOption(option);
        helpDeleteNonstrictIgnoreDeletedInternally(
                persistentEvent,
                new InternalDeleteNonstrictIgnoreDeletedCallback<PersistentEvent>() {
                    @Override
                    public int callbackDelegateDeleteNonstrict(
                            final PersistentEvent entity) {
                        return delegateDeleteNonstrict(entity, option);
                    }
                });
    }

    @Override
    protected void doRemoveNonstrict(final Entity entity,
            final DeleteOption<? extends ConditionBean> option) {
        if (option == null) {
            deleteNonstrict(downcast(entity));
        } else {
            varyingDeleteNonstrict(downcast(entity), downcast(option));
        }
    }

    // ===================================================================================
    //                                                                        Batch Update
    //                                                                        ============
    /**
     * Batch-insert the list. <br />
     * This method uses 'Batch Update' of java.sql.PreparedStatement. <br />
     * All columns are insert target. (so default constraints are not available) <br />
     * And if the table has an identity, entities after the process do not have incremented values.
     * (When you use the (normal) insert(), an entity after the process has an incremented value)
     * @param persistentEventList The list of the entity. (NotNull)
     * @return The array of inserted count.
     */
    public int[] batchInsert(final List<PersistentEvent> persistentEventList) {
        return doBatchInsert(persistentEventList, null);
    }

    protected int[] doBatchInsert(
            final List<PersistentEvent> persistentEventList,
            final InsertOption<PersistentEventCB> option) {
        assertObjectNotNull("persistentEventList", persistentEventList);
        prepareInsertOption(option);
        return delegateBatchInsert(persistentEventList, option);
    }

    @Override
    protected int[] doLumpCreate(final List<Entity> ls,
            final InsertOption<? extends ConditionBean> option) {
        if (option == null) {
            return batchInsert(downcast(ls));
        } else {
            return varyingBatchInsert(downcast(ls), downcast(option));
        }
    }

    /**
     * Batch-update the list. <br />
     * This method uses 'Batch Update' of java.sql.PreparedStatement. <br />
     * All columns are update target. {NOT modified only}
     * @param persistentEventList The list of the entity. (NotNull)
     * @return The array of updated count.
     * @exception org.seasar.dbflute.exception.BatchEntityAlreadyUpdatedException When the entity has already been updated. This exception extends EntityAlreadyUpdatedException.
     */
    public int[] batchUpdate(final List<PersistentEvent> persistentEventList) {
        return doBatchUpdate(persistentEventList, null);
    }

    protected int[] doBatchUpdate(
            final List<PersistentEvent> persistentEventList,
            final UpdateOption<PersistentEventCB> option) {
        assertObjectNotNull("persistentEventList", persistentEventList);
        prepareUpdateOption(option);
        return delegateBatchUpdate(persistentEventList, option);
    }

    @Override
    protected int[] doLumpModify(final List<Entity> ls,
            final UpdateOption<? extends ConditionBean> option) {
        if (option == null) {
            return batchUpdate(downcast(ls));
        } else {
            return varyingBatchUpdate(downcast(ls), downcast(option));
        }
    }

    /**
     * Batch-update the list. <br />
     * This method uses 'Batch Update' of java.sql.PreparedStatement. <br />
     * You can specify update columns used on set clause of update statement.
     * However you do not need to specify common columns for update
     * and an optimistick lock column because they are specified implicitly.
     * @param persistentEventList The list of the entity. (NotNull)
     * @param updateColumnSpec The specification of update columns. (NotNull)
     * @return The array of updated count.
     * @exception org.seasar.dbflute.exception.BatchEntityAlreadyUpdatedException When the entity has already been updated. This exception extends EntityAlreadyUpdatedException.
     */
    public int[] batchUpdate(final List<PersistentEvent> persistentEventList,
            final SpecifyQuery<PersistentEventCB> updateColumnSpec) {
        return doBatchUpdate(persistentEventList,
                createSpecifiedUpdateOption(updateColumnSpec));
    }

    /**
     * Batch-update the list non-strictly. <br />
     * This method uses 'Batch Update' of java.sql.PreparedStatement. <br />
     * All columns are update target. {NOT modified only}
     * @param persistentEventList The list of the entity. (NotNull)
     * @return The array of updated count.
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     */
    public int[] batchUpdateNonstrict(
            final List<PersistentEvent> persistentEventList) {
        return doBatchUpdateNonstrict(persistentEventList, null);
    }

    protected int[] doBatchUpdateNonstrict(
            final List<PersistentEvent> persistentEventList,
            final UpdateOption<PersistentEventCB> option) {
        assertObjectNotNull("persistentEventList", persistentEventList);
        prepareUpdateOption(option);
        return delegateBatchUpdateNonstrict(persistentEventList, option);
    }

    /**
     * Batch-update the list non-strictly. <br />
     * This method uses 'Batch Update' of java.sql.PreparedStatement. <br />
     * You can specify update columns used on set clause of update statement.
     * However you do not need to specify common columns for update
     * and an optimistick lock column because they are specified implicitly.
     * @param persistentEventList The list of the entity. (NotNull)
     * @param updateColumnSpec The specification of update columns. (NotNull)
     * @return The array of updated count.
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     */
    public int[] batchUpdateNonstrict(
            final List<PersistentEvent> persistentEventList,
            final SpecifyQuery<PersistentEventCB> updateColumnSpec) {
        return doBatchUpdateNonstrict(persistentEventList,
                createSpecifiedUpdateOption(updateColumnSpec));
    }

    @Override
    protected int[] doLumpModifyNonstrict(final List<Entity> ls,
            final UpdateOption<? extends ConditionBean> option) {
        if (option == null) {
            return batchUpdateNonstrict(downcast(ls));
        } else {
            return varyingBatchUpdateNonstrict(downcast(ls), downcast(option));
        }
    }

    /**
     * Batch-delete the list. <br />
     * This method uses 'Batch Update' of java.sql.PreparedStatement.
     * @param persistentEventList The list of the entity. (NotNull)
     * @return The array of deleted count.
     * @exception org.seasar.dbflute.exception.BatchEntityAlreadyUpdatedException When the entity has already been updated. This exception extends EntityAlreadyUpdatedException.
     */
    public int[] batchDelete(final List<PersistentEvent> persistentEventList) {
        return doBatchDelete(persistentEventList, null);
    }

    protected int[] doBatchDelete(
            final List<PersistentEvent> persistentEventList,
            final DeleteOption<PersistentEventCB> option) {
        assertObjectNotNull("persistentEventList", persistentEventList);
        prepareDeleteOption(option);
        return delegateBatchDelete(persistentEventList, option);
    }

    @Override
    protected int[] doLumpRemove(final List<Entity> ls,
            final DeleteOption<? extends ConditionBean> option) {
        if (option == null) {
            return batchDelete(downcast(ls));
        } else {
            return varyingBatchDelete(downcast(ls), downcast(option));
        }
    }

    /**
     * Batch-delete the list non-strictly. <br />
     * This method uses 'Batch Update' of java.sql.PreparedStatement.
     * @param persistentEventList The list of the entity. (NotNull)
     * @return The array of deleted count.
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     */
    public int[] batchDeleteNonstrict(
            final List<PersistentEvent> persistentEventList) {
        return doBatchDeleteNonstrict(persistentEventList, null);
    }

    protected int[] doBatchDeleteNonstrict(
            final List<PersistentEvent> persistentEventList,
            final DeleteOption<PersistentEventCB> option) {
        assertObjectNotNull("persistentEventList", persistentEventList);
        prepareDeleteOption(option);
        return delegateBatchDeleteNonstrict(persistentEventList, option);
    }

    @Override
    protected int[] doLumpRemoveNonstrict(final List<Entity> ls,
            final DeleteOption<? extends ConditionBean> option) {
        if (option == null) {
            return batchDeleteNonstrict(downcast(ls));
        } else {
            return varyingBatchDeleteNonstrict(downcast(ls), downcast(option));
        }
    }

    // ===================================================================================
    //                                                                        Query Update
    //                                                                        ============
    /**
     * Insert the several entities by query (modified-only for fixed value).
     * <pre>
     * persistentEventBhv.<span style="color: #FD4747">queryInsert</span>(new QueryInsertSetupper&lt;PersistentEvent, PersistentEventCB&gt;() {
     *     public ConditionBean setup(persistentEvent entity, PersistentEventCB intoCB) {
     *         FooCB cb = FooCB();
     *         cb.setupSelect_Bar();
     * 
     *         <span style="color: #3F7E5E">// mapping</span>
     *         intoCB.specify().columnMyName().mappedFrom(cb.specify().columnFooName());
     *         intoCB.specify().columnMyCount().mappedFrom(cb.specify().columnFooCount());
     *         intoCB.specify().columnMyDate().mappedFrom(cb.specify().specifyBar().columnBarDate());
     *         entity.setMyFixedValue("foo"); <span style="color: #3F7E5E">// fixed value</span>
     *         <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     *         <span style="color: #3F7E5E">//entity.setRegisterUser(value);</span>
     *         <span style="color: #3F7E5E">//entity.set...;</span>
     *         <span style="color: #3F7E5E">// you don't need to set a value of exclusive control column</span>
     *         <span style="color: #3F7E5E">//entity.setVersionNo(value);</span>
     * 
     *         return cb;
     *     }
     * });
     * </pre>
     * @param setupper The setup-per of query-insert. (NotNull)
     * @return The inserted count.
     */
    public int queryInsert(
            final QueryInsertSetupper<PersistentEvent, PersistentEventCB> setupper) {
        return doQueryInsert(setupper, null);
    }

    protected int doQueryInsert(
            final QueryInsertSetupper<PersistentEvent, PersistentEventCB> setupper,
            final InsertOption<PersistentEventCB> option) {
        assertObjectNotNull("setupper", setupper);
        prepareInsertOption(option);
        final PersistentEvent entity = new PersistentEvent();
        final PersistentEventCB intoCB = createCBForQueryInsert();
        final ConditionBean resourceCB = setupper.setup(entity, intoCB);
        return delegateQueryInsert(entity, intoCB, resourceCB, option);
    }

    protected PersistentEventCB createCBForQueryInsert() {
        final PersistentEventCB cb = newMyConditionBean();
        cb.xsetupForQueryInsert();
        return cb;
    }

    @Override
    protected int doRangeCreate(
            final QueryInsertSetupper<? extends Entity, ? extends ConditionBean> setupper,
            final InsertOption<? extends ConditionBean> option) {
        if (option == null) {
            return queryInsert(downcast(setupper));
        } else {
            return varyingQueryInsert(downcast(setupper), downcast(option));
        }
    }

    /**
     * Update the several entities by query non-strictly modified-only. {NonExclusiveControl}
     * <pre>
     * PersistentEvent persistentEvent = new PersistentEvent();
     * <span style="color: #3F7E5E">// you don't need to set PK value</span>
     * <span style="color: #3F7E5E">//persistentEvent.setPK...(value);</span>
     * persistentEvent.setFoo...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//persistentEvent.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//persistentEvent.set...;</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of exclusive control column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//persistentEvent.setVersionNo(value);</span>
     * PersistentEventCB cb = new PersistentEventCB();
     * cb.query().setFoo...(value);
     * persistentEventBhv.<span style="color: #FD4747">queryUpdate</span>(persistentEvent, cb);
     * </pre>
     * @param persistentEvent The entity that contains update values. (NotNull, PrimaryKeyNullAllowed)
     * @param cb The condition-bean of PersistentEvent. (NotNull)
     * @return The updated count.
     * @exception org.seasar.dbflute.exception.NonQueryUpdateNotAllowedException When the query has no condition.
     */
    public int queryUpdate(final PersistentEvent persistentEvent,
            final PersistentEventCB cb) {
        return doQueryUpdate(persistentEvent, cb, null);
    }

    protected int doQueryUpdate(final PersistentEvent persistentEvent,
            final PersistentEventCB cb,
            final UpdateOption<PersistentEventCB> option) {
        assertObjectNotNull("persistentEvent", persistentEvent);
        assertCBStateValid(cb);
        prepareUpdateOption(option);
        return delegateQueryUpdate(persistentEvent, cb, option);
    }

    @Override
    protected int doRangeModify(final Entity entity, final ConditionBean cb,
            final UpdateOption<? extends ConditionBean> option) {
        if (option == null) {
            return queryUpdate(downcast(entity), (PersistentEventCB) cb);
        } else {
            return varyingQueryUpdate(downcast(entity), (PersistentEventCB) cb,
                    downcast(option));
        }
    }

    /**
     * Delete the several entities by query. {NonExclusiveControl}
     * <pre>
     * PersistentEventCB cb = new PersistentEventCB();
     * cb.query().setFoo...(value);
     * persistentEventBhv.<span style="color: #FD4747">queryDelete</span>(persistentEvent, cb);
     * </pre>
     * @param cb The condition-bean of PersistentEvent. (NotNull)
     * @return The deleted count.
     * @exception org.seasar.dbflute.exception.NonQueryDeleteNotAllowedException When the query has no condition.
     */
    public int queryDelete(final PersistentEventCB cb) {
        return doQueryDelete(cb, null);
    }

    protected int doQueryDelete(final PersistentEventCB cb,
            final DeleteOption<PersistentEventCB> option) {
        assertCBStateValid(cb);
        prepareDeleteOption(option);
        return delegateQueryDelete(cb, option);
    }

    @Override
    protected int doRangeRemove(final ConditionBean cb,
            final DeleteOption<? extends ConditionBean> option) {
        if (option == null) {
            return queryDelete((PersistentEventCB) cb);
        } else {
            return varyingQueryDelete((PersistentEventCB) cb, downcast(option));
        }
    }

    // ===================================================================================
    //                                                                      Varying Update
    //                                                                      ==============
    // -----------------------------------------------------
    //                                         Entity Update
    //                                         -------------
    /**
     * Insert the entity with varying requests. <br />
     * For example, disableCommonColumnAutoSetup(), disablePrimaryKeyIdentity(). <br />
     * Other specifications are same as insert(entity).
     * <pre>
     * PersistentEvent persistentEvent = new PersistentEvent();
     * <span style="color: #3F7E5E">// if auto-increment, you don't need to set the PK value</span>
     * persistentEvent.setFoo...(value);
     * persistentEvent.setBar...(value);
     * InsertOption<PersistentEventCB> option = new InsertOption<PersistentEventCB>();
     * <span style="color: #3F7E5E">// you can insert by your values for common columns</span>
     * option.disableCommonColumnAutoSetup();
     * persistentEventBhv.<span style="color: #FD4747">varyingInsert</span>(persistentEvent, option);
     * ... = persistentEvent.getPK...(); <span style="color: #3F7E5E">// if auto-increment, you can get the value after</span>
     * </pre>
     * @param persistentEvent The entity of insert target. (NotNull)
     * @param option The option of insert for varying requests. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void varyingInsert(final PersistentEvent persistentEvent,
            final InsertOption<PersistentEventCB> option) {
        assertInsertOptionNotNull(option);
        doInsert(persistentEvent, option);
    }

    /**
     * Update the entity with varying requests modified-only. {UpdateCountZeroException, ExclusiveControl} <br />
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification), disableCommonColumnAutoSetup(). <br />
     * Other specifications are same as update(entity).
     * <pre>
     * PersistentEvent persistentEvent = new PersistentEvent();
     * persistentEvent.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * persistentEvent.setOther...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// if exclusive control, the value of exclusive control column is required</span>
     * persistentEvent.<span style="color: #FD4747">setVersionNo</span>(value);
     * try {
     *     <span style="color: #3F7E5E">// you can update by self calculation values</span>
     *     UpdateOption&lt;PersistentEventCB&gt; option = new UpdateOption&lt;PersistentEventCB&gt;();
     *     option.self(new SpecifyQuery&lt;PersistentEventCB&gt;() {
     *         public void specify(PersistentEventCB cb) {
     *             cb.specify().<span style="color: #FD4747">columnXxxCount()</span>;
     *         }
     *     }).plus(1); <span style="color: #3F7E5E">// XXX_COUNT = XXX_COUNT + 1</span>
     *     persistentEventBhv.<span style="color: #FD4747">varyingUpdate</span>(persistentEvent, option);
     * } catch (EntityAlreadyUpdatedException e) { <span style="color: #3F7E5E">// if concurrent update</span>
     *     ...
     * }
     * </pre>
     * @param persistentEvent The entity of update target. (NotNull) {PrimaryKeyRequired, ConcurrencyColumnRequired}
     * @param option The option of update for varying requests. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void varyingUpdate(final PersistentEvent persistentEvent,
            final UpdateOption<PersistentEventCB> option) {
        assertUpdateOptionNotNull(option);
        doUpdate(persistentEvent, option);
    }

    /**
     * Update the entity with varying requests non-strictly modified-only. {UpdateCountZeroException, NonExclusiveControl} <br />
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification), disableCommonColumnAutoSetup(). <br />
     * Other specifications are same as updateNonstrict(entity).
     * <pre>
     * <span style="color: #3F7E5E">// ex) you can update by self calculation values</span>
     * PersistentEvent persistentEvent = new PersistentEvent();
     * persistentEvent.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * persistentEvent.setOther...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of exclusive control column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//persistentEvent.setVersionNo(value);</span>
     * UpdateOption&lt;PersistentEventCB&gt; option = new UpdateOption&lt;PersistentEventCB&gt;();
     * option.self(new SpecifyQuery&lt;PersistentEventCB&gt;() {
     *     public void specify(PersistentEventCB cb) {
     *         cb.specify().<span style="color: #FD4747">columnFooCount()</span>;
     *     }
     * }).plus(1); <span style="color: #3F7E5E">// FOO_COUNT = FOO_COUNT + 1</span>
     * persistentEventBhv.<span style="color: #FD4747">varyingUpdateNonstrict</span>(persistentEvent, option);
     * </pre>
     * @param persistentEvent The entity of update target. (NotNull) {PrimaryKeyRequired}
     * @param option The option of update for varying requests. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void varyingUpdateNonstrict(final PersistentEvent persistentEvent,
            final UpdateOption<PersistentEventCB> option) {
        assertUpdateOptionNotNull(option);
        doUpdateNonstrict(persistentEvent, option);
    }

    /**
     * Insert or update the entity with varying requests. {ExclusiveControl(when update)}<br />
     * Other specifications are same as insertOrUpdate(entity).
     * @param persistentEvent The entity of insert or update target. (NotNull)
     * @param insertOption The option of insert for varying requests. (NotNull)
     * @param updateOption The option of update for varying requests. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void varyingInsertOrUpdate(final PersistentEvent persistentEvent,
            final InsertOption<PersistentEventCB> insertOption,
            final UpdateOption<PersistentEventCB> updateOption) {
        assertInsertOptionNotNull(insertOption);
        assertUpdateOptionNotNull(updateOption);
        doInesrtOrUpdate(persistentEvent, insertOption, updateOption);
    }

    /**
     * Insert or update the entity with varying requests non-strictly. {NonExclusiveControl(when update)}<br />
     * Other specifications are same as insertOrUpdateNonstrict(entity).
     * @param persistentEvent The entity of insert or update target. (NotNull)
     * @param insertOption The option of insert for varying requests. (NotNull)
     * @param updateOption The option of update for varying requests. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void varyingInsertOrUpdateNonstrict(
            final PersistentEvent persistentEvent,
            final InsertOption<PersistentEventCB> insertOption,
            final UpdateOption<PersistentEventCB> updateOption) {
        assertInsertOptionNotNull(insertOption);
        assertUpdateOptionNotNull(updateOption);
        doInesrtOrUpdateNonstrict(persistentEvent, insertOption, updateOption);
    }

    /**
     * Delete the entity with varying requests. {UpdateCountZeroException, ExclusiveControl} <br />
     * Now a valid option does not exist. <br />
     * Other specifications are same as delete(entity).
     * @param persistentEvent The entity of delete target. (NotNull) {PrimaryKeyRequired, ConcurrencyColumnRequired}
     * @param option The option of update for varying requests. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     */
    public void varyingDelete(final PersistentEvent persistentEvent,
            final DeleteOption<PersistentEventCB> option) {
        assertDeleteOptionNotNull(option);
        doDelete(persistentEvent, option);
    }

    /**
     * Delete the entity with varying requests non-strictly. {UpdateCountZeroException, NonExclusiveControl} <br />
     * Now a valid option does not exist. <br />
     * Other specifications are same as deleteNonstrict(entity).
     * @param persistentEvent The entity of delete target. (NotNull) {PrimaryKeyRequired, ConcurrencyColumnRequired}
     * @param option The option of update for varying requests. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     */
    public void varyingDeleteNonstrict(final PersistentEvent persistentEvent,
            final DeleteOption<PersistentEventCB> option) {
        assertDeleteOptionNotNull(option);
        doDeleteNonstrict(persistentEvent, option);
    }

    // -----------------------------------------------------
    //                                          Batch Update
    //                                          ------------
    /**
     * Batch-insert the list with varying requests. <br />
     * For example, disableCommonColumnAutoSetup()
     * , disablePrimaryKeyIdentity(), limitBatchInsertLogging(). <br />
     * Other specifications are same as batchInsert(entityList).
     * @param persistentEventList The list of the entity. (NotNull)
     * @param option The option of insert for varying requests. (NotNull)
     * @return The array of inserted count.
     */
    public int[] varyingBatchInsert(
            final List<PersistentEvent> persistentEventList,
            final InsertOption<PersistentEventCB> option) {
        assertInsertOptionNotNull(option);
        return doBatchInsert(persistentEventList, option);
    }

    /**
     * Batch-update the list with varying requests. <br />
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification)
     * , disableCommonColumnAutoSetup(), limitBatchUpdateLogging(). <br />
     * Other specifications are same as batchUpdate(entityList).
     * @param persistentEventList The list of the entity. (NotNull)
     * @param option The option of update for varying requests. (NotNull)
     * @return The array of updated count.
     */
    public int[] varyingBatchUpdate(
            final List<PersistentEvent> persistentEventList,
            final UpdateOption<PersistentEventCB> option) {
        assertUpdateOptionNotNull(option);
        return doBatchUpdate(persistentEventList, option);
    }

    /**
     * Batch-update the list with varying requests non-strictly. <br />
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification)
     * , disableCommonColumnAutoSetup(), limitBatchUpdateLogging(). <br />
     * Other specifications are same as batchUpdateNonstrict(entityList).
     * @param persistentEventList The list of the entity. (NotNull)
     * @param option The option of update for varying requests. (NotNull)
     * @return The array of updated count.
     */
    public int[] varyingBatchUpdateNonstrict(
            final List<PersistentEvent> persistentEventList,
            final UpdateOption<PersistentEventCB> option) {
        assertUpdateOptionNotNull(option);
        return doBatchUpdateNonstrict(persistentEventList, option);
    }

    /**
     * Batch-delete the list with varying requests. <br />
     * For example, limitBatchDeleteLogging(). <br />
     * Other specifications are same as batchDelete(entityList).
     * @param persistentEventList The list of the entity. (NotNull)
     * @param option The option of delete for varying requests. (NotNull)
     * @return The array of deleted count.
     */
    public int[] varyingBatchDelete(
            final List<PersistentEvent> persistentEventList,
            final DeleteOption<PersistentEventCB> option) {
        assertDeleteOptionNotNull(option);
        return doBatchDelete(persistentEventList, option);
    }

    /**
     * Batch-delete the list with varying requests non-strictly. <br />
     * For example, limitBatchDeleteLogging(). <br />
     * Other specifications are same as batchDeleteNonstrict(entityList).
     * @param persistentEventList The list of the entity. (NotNull)
     * @param option The option of delete for varying requests. (NotNull)
     * @return The array of deleted count.
     */
    public int[] varyingBatchDeleteNonstrict(
            final List<PersistentEvent> persistentEventList,
            final DeleteOption<PersistentEventCB> option) {
        assertDeleteOptionNotNull(option);
        return doBatchDeleteNonstrict(persistentEventList, option);
    }

    // -----------------------------------------------------
    //                                          Query Update
    //                                          ------------
    /**
     * Insert the several entities by query with varying requests (modified-only for fixed value). <br />
     * For example, disableCommonColumnAutoSetup(), disablePrimaryKeyIdentity(). <br />
     * Other specifications are same as queryInsert(entity, setupper). 
     * @param setupper The setup-per of query-insert. (NotNull)
     * @param option The option of insert for varying requests. (NotNull)
     * @return The inserted count.
     */
    public int varyingQueryInsert(
            final QueryInsertSetupper<PersistentEvent, PersistentEventCB> setupper,
            final InsertOption<PersistentEventCB> option) {
        assertInsertOptionNotNull(option);
        return doQueryInsert(setupper, option);
    }

    /**
     * Update the several entities by query with varying requests non-strictly modified-only. {NonExclusiveControl} <br />
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification)
     * , disableCommonColumnAutoSetup(), allowNonQueryUpdate(). <br />
     * Other specifications are same as queryUpdate(entity, cb). 
     * <pre>
     * <span style="color: #3F7E5E">// ex) you can update by self calculation values</span>
     * PersistentEvent persistentEvent = new PersistentEvent();
     * <span style="color: #3F7E5E">// you don't need to set PK value</span>
     * <span style="color: #3F7E5E">//persistentEvent.setPK...(value);</span>
     * persistentEvent.setOther...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of exclusive control column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//persistentEvent.setVersionNo(value);</span>
     * PersistentEventCB cb = new PersistentEventCB();
     * cb.query().setFoo...(value);
     * UpdateOption&lt;PersistentEventCB&gt; option = new UpdateOption&lt;PersistentEventCB&gt;();
     * option.self(new SpecifyQuery&lt;PersistentEventCB&gt;() {
     *     public void specify(PersistentEventCB cb) {
     *         cb.specify().<span style="color: #FD4747">columnFooCount()</span>;
     *     }
     * }).plus(1); <span style="color: #3F7E5E">// FOO_COUNT = FOO_COUNT + 1</span>
     * persistentEventBhv.<span style="color: #FD4747">varyingQueryUpdate</span>(persistentEvent, cb, option);
     * </pre>
     * @param persistentEvent The entity that contains update values. (NotNull) {PrimaryKeyNotRequired}
     * @param cb The condition-bean of PersistentEvent. (NotNull)
     * @param option The option of update for varying requests. (NotNull)
     * @return The updated count.
     * @exception org.seasar.dbflute.exception.NonQueryUpdateNotAllowedException When the query has no condition (if not allowed).
     */
    public int varyingQueryUpdate(final PersistentEvent persistentEvent,
            final PersistentEventCB cb,
            final UpdateOption<PersistentEventCB> option) {
        assertUpdateOptionNotNull(option);
        return doQueryUpdate(persistentEvent, cb, option);
    }

    /**
     * Delete the several entities by query with varying requests non-strictly. <br />
     * For example, allowNonQueryDelete(). <br />
     * Other specifications are same as batchUpdateNonstrict(entityList).
     * @param cb The condition-bean of PersistentEvent. (NotNull)
     * @param option The option of delete for varying requests. (NotNull)
     * @return The deleted count.
     * @exception org.seasar.dbflute.exception.NonQueryDeleteNotAllowedException When the query has no condition (if not allowed).
     */
    public int varyingQueryDelete(final PersistentEventCB cb,
            final DeleteOption<PersistentEventCB> option) {
        assertDeleteOptionNotNull(option);
        return doQueryDelete(cb, option);
    }

    // ===================================================================================
    //                                                                          OutsideSql
    //                                                                          ==========
    /**
     * Prepare the basic executor of outside-SQL to execute it. <br />
     * The invoker of behavior command should be not null when you call this method.
     * <pre>
     * You can use the methods for outside-SQL are as follows:
     * {Basic}
     *   o selectList()
     *   o execute()
     *   o call()
     * 
     * {Entity}
     *   o entityHandling().selectEntity()
     *   o entityHandling().selectEntityWithDeletedCheck()
     * 
     * {Paging}
     *   o autoPaging().selectList()
     *   o autoPaging().selectPage()
     *   o manualPaging().selectList()
     *   o manualPaging().selectPage()
     * 
     * {Cursor}
     *   o cursorHandling().selectCursor()
     * 
     * {Option}
     *   o dynamicBinding().selectList()
     *   o removeBlockComment().selectList()
     *   o removeLineComment().selectList()
     *   o formatSql().selectList()
     * </pre>
     * @return The basic executor of outside-SQL. (NotNull) 
     */
    public OutsideSqlBasicExecutor<PersistentEventBhv> outsideSql() {
        return doOutsideSql();
    }

    // ===================================================================================
    //                                                                     Delegate Method
    //                                                                     ===============
    // [Behavior Command]
    // -----------------------------------------------------
    //                                                Select
    //                                                ------
    protected int delegateSelectCountUniquely(final PersistentEventCB cb) {
        return invoke(createSelectCountCBCommand(cb, true));
    }

    protected int delegateSelectCountPlainly(final PersistentEventCB cb) {
        return invoke(createSelectCountCBCommand(cb, false));
    }

    protected <ENTITY extends PersistentEvent> void delegateSelectCursor(
            final PersistentEventCB cb, final EntityRowHandler<ENTITY> erh,
            final Class<ENTITY> et) {
        invoke(createSelectCursorCBCommand(cb, erh, et));
    }

    protected <ENTITY extends PersistentEvent> List<ENTITY> delegateSelectList(
            final PersistentEventCB cb, final Class<ENTITY> et) {
        return invoke(createSelectListCBCommand(cb, et));
    }

    // -----------------------------------------------------
    //                                                Update
    //                                                ------
    protected int delegateInsert(final PersistentEvent e,
            final InsertOption<PersistentEventCB> op) {
        if (!processBeforeInsert(e, op)) {
            return 0;
        }
        return invoke(createInsertEntityCommand(e, op));
    }

    protected int delegateUpdate(final PersistentEvent e,
            final UpdateOption<PersistentEventCB> op) {
        if (!processBeforeUpdate(e, op)) {
            return 0;
        }
        return invoke(createUpdateEntityCommand(e, op));
    }

    protected int delegateUpdateNonstrict(final PersistentEvent e,
            final UpdateOption<PersistentEventCB> op) {
        if (!processBeforeUpdate(e, op)) {
            return 0;
        }
        return invoke(createUpdateNonstrictEntityCommand(e, op));
    }

    protected int delegateDelete(final PersistentEvent e,
            final DeleteOption<PersistentEventCB> op) {
        if (!processBeforeDelete(e, op)) {
            return 0;
        }
        return invoke(createDeleteEntityCommand(e, op));
    }

    protected int delegateDeleteNonstrict(final PersistentEvent e,
            final DeleteOption<PersistentEventCB> op) {
        if (!processBeforeDelete(e, op)) {
            return 0;
        }
        return invoke(createDeleteNonstrictEntityCommand(e, op));
    }

    protected int[] delegateBatchInsert(final List<PersistentEvent> ls,
            final InsertOption<PersistentEventCB> op) {
        if (ls.isEmpty()) {
            return new int[] {};
        }
        return invoke(createBatchInsertCommand(processBatchInternally(ls, op),
                op));
    }

    protected int[] delegateBatchUpdate(final List<PersistentEvent> ls,
            final UpdateOption<PersistentEventCB> op) {
        if (ls.isEmpty()) {
            return new int[] {};
        }
        return invoke(createBatchUpdateCommand(
                processBatchInternally(ls, op, false), op));
    }

    protected int[] delegateBatchUpdateNonstrict(
            final List<PersistentEvent> ls,
            final UpdateOption<PersistentEventCB> op) {
        if (ls.isEmpty()) {
            return new int[] {};
        }
        return invoke(createBatchUpdateNonstrictCommand(
                processBatchInternally(ls, op, true), op));
    }

    protected int[] delegateBatchDelete(final List<PersistentEvent> ls,
            final DeleteOption<PersistentEventCB> op) {
        if (ls.isEmpty()) {
            return new int[] {};
        }
        return invoke(createBatchDeleteCommand(
                processBatchInternally(ls, op, false), op));
    }

    protected int[] delegateBatchDeleteNonstrict(
            final List<PersistentEvent> ls,
            final DeleteOption<PersistentEventCB> op) {
        if (ls.isEmpty()) {
            return new int[] {};
        }
        return invoke(createBatchDeleteNonstrictCommand(
                processBatchInternally(ls, op, true), op));
    }

    protected int delegateQueryInsert(final PersistentEvent e,
            final PersistentEventCB inCB, final ConditionBean resCB,
            final InsertOption<PersistentEventCB> op) {
        if (!processBeforeQueryInsert(e, inCB, resCB, op)) {
            return 0;
        }
        return invoke(createQueryInsertCBCommand(e, inCB, resCB, op));
    }

    protected int delegateQueryUpdate(final PersistentEvent e,
            final PersistentEventCB cb, final UpdateOption<PersistentEventCB> op) {
        if (!processBeforeQueryUpdate(e, cb, op)) {
            return 0;
        }
        return invoke(createQueryUpdateCBCommand(e, cb, op));
    }

    protected int delegateQueryDelete(final PersistentEventCB cb,
            final DeleteOption<PersistentEventCB> op) {
        if (!processBeforeQueryDelete(cb, op)) {
            return 0;
        }
        return invoke(createQueryDeleteCBCommand(cb, op));
    }

    // ===================================================================================
    //                                                                Optimistic Lock Info
    //                                                                ====================
    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean hasVersionNoValue(final Entity entity) {
        return !(downcast(entity).getVersionNo() + "").equals("null");// For primitive type
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean hasUpdateDateValue(final Entity entity) {
        return false;
    }

    // ===================================================================================
    //                                                                     Downcast Helper
    //                                                                     ===============
    protected PersistentEvent downcast(final Entity entity) {
        return helpEntityDowncastInternally(entity, PersistentEvent.class);
    }

    protected PersistentEventCB downcast(final ConditionBean cb) {
        return helpConditionBeanDowncastInternally(cb, PersistentEventCB.class);
    }

    @SuppressWarnings("unchecked")
    protected List<PersistentEvent> downcast(
            final List<? extends Entity> entityList) {
        return (List<PersistentEvent>) entityList;
    }

    @SuppressWarnings("unchecked")
    protected InsertOption<PersistentEventCB> downcast(
            final InsertOption<? extends ConditionBean> option) {
        return (InsertOption<PersistentEventCB>) option;
    }

    @SuppressWarnings("unchecked")
    protected UpdateOption<PersistentEventCB> downcast(
            final UpdateOption<? extends ConditionBean> option) {
        return (UpdateOption<PersistentEventCB>) option;
    }

    @SuppressWarnings("unchecked")
    protected DeleteOption<PersistentEventCB> downcast(
            final DeleteOption<? extends ConditionBean> option) {
        return (DeleteOption<PersistentEventCB>) option;
    }

    @SuppressWarnings("unchecked")
    protected QueryInsertSetupper<PersistentEvent, PersistentEventCB> downcast(
            final QueryInsertSetupper<? extends Entity, ? extends ConditionBean> option) {
        return (QueryInsertSetupper<PersistentEvent, PersistentEventCB>) option;
    }
}
