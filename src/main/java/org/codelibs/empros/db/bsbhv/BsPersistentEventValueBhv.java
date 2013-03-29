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
package org.codelibs.empros.db.bsbhv;

import java.util.List;

import org.codelibs.empros.db.bsentity.dbmeta.PersistentEventValueDbm;
import org.codelibs.empros.db.cbean.PersistentEventValueCB;
import org.codelibs.empros.db.exbhv.PersistentEventValueBhv;
import org.codelibs.empros.db.exentity.PersistentEvent;
import org.codelibs.empros.db.exentity.PersistentEventValue;
import org.seasar.dbflute.Entity;
import org.seasar.dbflute.bhv.AbstractBehaviorWritable;
import org.seasar.dbflute.bhv.DeleteOption;
import org.seasar.dbflute.bhv.InsertOption;
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
 * The behavior of PERSISTENT_EVENT_VALUE as TABLE. <br />
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
public abstract class BsPersistentEventValueBhv extends
        AbstractBehaviorWritable {

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
        return "PERSISTENT_EVENT_VALUE";
    }

    // ===================================================================================
    //                                                                              DBMeta
    //                                                                              ======
    /** @return The instance of DBMeta. (NotNull) */
    @Override
    public DBMeta getDBMeta() {
        return PersistentEventValueDbm.getInstance();
    }

    /** @return The instance of DBMeta as my table type. (NotNull) */
    public PersistentEventValueDbm getMyDBMeta() {
        return PersistentEventValueDbm.getInstance();
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
    public PersistentEventValue newMyEntity() {
        return new PersistentEventValue();
    }

    /** @return The instance of new condition-bean as my table type. (NotNull) */
    public PersistentEventValueCB newMyConditionBean() {
        return new PersistentEventValueCB();
    }

    // ===================================================================================
    //                                                                        Count Select
    //                                                                        ============
    /**
     * Select the count of uniquely-selected records by the condition-bean. {IgnorePagingCondition, IgnoreSpecifyColumn}<br />
     * SpecifyColumn is ignored but you can use it only to remove text type column for union's distinct.
     * <pre>
     * PersistentEventValueCB cb = new PersistentEventValueCB();
     * cb.query().setFoo...(value);
     * int count = persistentEventValueBhv.<span style="color: #FD4747">selectCount</span>(cb);
     * </pre>
     * @param cb The condition-bean of PersistentEventValue. (NotNull)
     * @return The selected count.
     */
    public int selectCount(final PersistentEventValueCB cb) {
        return doSelectCountUniquely(cb);
    }

    protected int doSelectCountUniquely(final PersistentEventValueCB cb) { // called by selectCount(cb) 
        assertCBStateValid(cb);
        return delegateSelectCountUniquely(cb);
    }

    protected int doSelectCountPlainly(final PersistentEventValueCB cb) { // called by selectPage(cb)
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
     * PersistentEventValueCB cb = new PersistentEventValueCB();
     * cb.query().setFoo...(value);
     * persistentEventValueBhv.<span style="color: #FD4747">selectCursor</span>(cb, new EntityRowHandler&lt;PersistentEventValue&gt;() {
     *     public void handle(PersistentEventValue entity) {
     *         ... = entity.getFoo...();
     *     }
     * });
     * </pre>
     * @param cb The condition-bean of PersistentEventValue. (NotNull)
     * @param entityRowHandler The handler of entity row of PersistentEventValue. (NotNull)
     */
    public void selectCursor(final PersistentEventValueCB cb,
            final EntityRowHandler<PersistentEventValue> entityRowHandler) {
        doSelectCursor(cb, entityRowHandler, PersistentEventValue.class);
    }

    protected <ENTITY extends PersistentEventValue> void doSelectCursor(
            final PersistentEventValueCB cb,
            final EntityRowHandler<ENTITY> entityRowHandler,
            final Class<ENTITY> entityType) {
        assertCBStateValid(cb);
        assertObjectNotNull("entityRowHandler<PersistentEventValue>",
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
     * PersistentEventValueCB cb = new PersistentEventValueCB();
     * cb.query().setFoo...(value);
     * PersistentEventValue persistentEventValue = persistentEventValueBhv.<span style="color: #FD4747">selectEntity</span>(cb);
     * if (persistentEventValue != null) {
     *     ... = persistentEventValue.get...();
     * } else {
     *     ...
     * }
     * </pre>
     * @param cb The condition-bean of PersistentEventValue. (NotNull)
     * @return The selected entity. (NullAllowed: If the condition has no data, it returns null)
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public PersistentEventValue selectEntity(final PersistentEventValueCB cb) {
        return doSelectEntity(cb, PersistentEventValue.class);
    }

    protected <ENTITY extends PersistentEventValue> ENTITY doSelectEntity(
            final PersistentEventValueCB cb, final Class<ENTITY> entityType) {
        assertCBStateValid(cb);
        return helpSelectEntityInternally(
                cb,
                new InternalSelectEntityCallback<ENTITY, PersistentEventValueCB>() {
                    @Override
                    public List<ENTITY> callbackSelectList(
                            final PersistentEventValueCB cb) {
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
     * PersistentEventValueCB cb = new PersistentEventValueCB();
     * cb.query().setFoo...(value);
     * PersistentEventValue persistentEventValue = persistentEventValueBhv.<span style="color: #FD4747">selectEntityWithDeletedCheck</span>(cb);
     * ... = persistentEventValue.get...(); <span style="color: #3F7E5E">// the entity always be not null</span>
     * </pre>
     * @param cb The condition-bean of PersistentEventValue. (NotNull)
     * @return The selected entity. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public PersistentEventValue selectEntityWithDeletedCheck(
            final PersistentEventValueCB cb) {
        return doSelectEntityWithDeletedCheck(cb, PersistentEventValue.class);
    }

    protected <ENTITY extends PersistentEventValue> ENTITY doSelectEntityWithDeletedCheck(
            final PersistentEventValueCB cb, final Class<ENTITY> entityType) {
        assertCBStateValid(cb);
        return helpSelectEntityWithDeletedCheckInternally(
                cb,
                new InternalSelectEntityWithDeletedCheckCallback<ENTITY, PersistentEventValueCB>() {
                    @Override
                    public List<ENTITY> callbackSelectList(
                            final PersistentEventValueCB cb) {
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
    public PersistentEventValue selectByPKValue(final Long id) {
        return doSelectByPKValue(id, PersistentEventValue.class);
    }

    protected <ENTITY extends PersistentEventValue> ENTITY doSelectByPKValue(
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
    public PersistentEventValue selectByPKValueWithDeletedCheck(final Long id) {
        return doSelectByPKValueWithDeletedCheck(id, PersistentEventValue.class);
    }

    protected <ENTITY extends PersistentEventValue> ENTITY doSelectByPKValueWithDeletedCheck(
            final Long id, final Class<ENTITY> entityType) {
        return doSelectEntityWithDeletedCheck(buildPKCB(id), entityType);
    }

    private PersistentEventValueCB buildPKCB(final Long id) {
        assertObjectNotNull("id", id);
        final PersistentEventValueCB cb = newMyConditionBean();
        cb.query().setId_Equal(id);
        return cb;
    }

    // ===================================================================================
    //                                                                         List Select
    //                                                                         ===========
    /**
     * Select the list as result bean.
     * <pre>
     * PersistentEventValueCB cb = new PersistentEventValueCB();
     * cb.query().setFoo...(value);
     * cb.query().addOrderBy_Bar...();
     * ListResultBean&lt;PersistentEventValue&gt; persistentEventValueList = persistentEventValueBhv.<span style="color: #FD4747">selectList</span>(cb);
     * for (PersistentEventValue persistentEventValue : persistentEventValueList) {
     *     ... = persistentEventValue.get...();
     * }
     * </pre>
     * @param cb The condition-bean of PersistentEventValue. (NotNull)
     * @return The result bean of selected list. (NotNull)
     * @exception org.seasar.dbflute.exception.DangerousResultSizeException When the result size is over the specified safety size.
     */
    public ListResultBean<PersistentEventValue> selectList(
            final PersistentEventValueCB cb) {
        return doSelectList(cb, PersistentEventValue.class);
    }

    protected <ENTITY extends PersistentEventValue> ListResultBean<ENTITY> doSelectList(
            final PersistentEventValueCB cb, final Class<ENTITY> entityType) {
        assertCBStateValid(cb);
        assertObjectNotNull("entityType", entityType);
        assertSpecifyDerivedReferrerEntityProperty(cb, entityType);
        return helpSelectListInternally(
                cb,
                entityType,
                new InternalSelectListCallback<ENTITY, PersistentEventValueCB>() {
                    @Override
                    public List<ENTITY> callbackSelectList(
                            final PersistentEventValueCB cb,
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
     * PersistentEventValueCB cb = new PersistentEventValueCB();
     * cb.query().setFoo...(value);
     * cb.query().addOrderBy_Bar...();
     * cb.<span style="color: #FD4747">paging</span>(20, 3); <span style="color: #3F7E5E">// 20 records per a page and current page number is 3</span>
     * PagingResultBean&lt;PersistentEventValue&gt; page = persistentEventValueBhv.<span style="color: #FD4747">selectPage</span>(cb);
     * int allRecordCount = page.getAllRecordCount();
     * int allPageCount = page.getAllPageCount();
     * boolean isExistPrePage = page.isExistPrePage();
     * boolean isExistNextPage = page.isExistNextPage();
     * ...
     * for (PersistentEventValue persistentEventValue : page) {
     *     ... = persistentEventValue.get...();
     * }
     * </pre>
     * @param cb The condition-bean of PersistentEventValue. (NotNull)
     * @return The result bean of selected page. (NotNull)
     * @exception org.seasar.dbflute.exception.DangerousResultSizeException When the result size is over the specified safety size.
     */
    public PagingResultBean<PersistentEventValue> selectPage(
            final PersistentEventValueCB cb) {
        return doSelectPage(cb, PersistentEventValue.class);
    }

    protected <ENTITY extends PersistentEventValue> PagingResultBean<ENTITY> doSelectPage(
            final PersistentEventValueCB cb, final Class<ENTITY> entityType) {
        assertCBStateValid(cb);
        assertObjectNotNull("entityType", entityType);
        return helpSelectPageInternally(
                cb,
                entityType,
                new InternalSelectPageCallback<ENTITY, PersistentEventValueCB>() {
                    @Override
                    public int callbackSelectCount(
                            final PersistentEventValueCB cb) {
                        return doSelectCountPlainly(cb);
                    }

                    @Override
                    public List<ENTITY> callbackSelectList(
                            final PersistentEventValueCB cb,
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
     * persistentEventValueBhv.<span style="color: #FD4747">scalarSelect</span>(Date.class).max(new ScalarQuery() {
     *     public void query(PersistentEventValueCB cb) {
     *         cb.specify().<span style="color: #FD4747">columnFooDatetime()</span>; <span style="color: #3F7E5E">// required for a function</span>
     *         cb.query().setBarName_PrefixSearch("S");
     *     }
     * });
     * </pre>
     * @param <RESULT> The type of result.
     * @param resultType The type of result. (NotNull)
     * @return The scalar value derived by a function. (NullAllowed)
     */
    public <RESULT> SLFunction<PersistentEventValueCB, RESULT> scalarSelect(
            final Class<RESULT> resultType) {
        return doScalarSelect(resultType, newMyConditionBean());
    }

    protected <RESULT, CB extends PersistentEventValueCB> SLFunction<CB, RESULT> doScalarSelect(
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
    //                                                                    Pull out Foreign
    //                                                                    ================
    /**
     * Pull out the list of foreign table 'PersistentEvent'.
     * @param persistentEventValueList The list of persistentEventValue. (NotNull)
     * @return The list of foreign table. (NotNull)
     */
    public List<PersistentEvent> pulloutPersistentEvent(
            final List<PersistentEventValue> persistentEventValueList) {
        return helpPulloutInternally(
                persistentEventValueList,
                new InternalPulloutCallback<PersistentEventValue, PersistentEvent>() {
                    @Override
                    public PersistentEvent getFr(final PersistentEventValue e) {
                        return e.getPersistentEvent();
                    }

                    @Override
                    public boolean hasRf() {
                        return true;
                    }

                    @Override
                    public void setRfLs(final PersistentEvent e,
                            final List<PersistentEventValue> ls) {
                        e.setPersistentEventValueList(ls);
                    }
                });
    }

    // ===================================================================================
    //                                                                       Entity Update
    //                                                                       =============
    /**
     * Insert the entity.
     * <pre>
     * PersistentEventValue persistentEventValue = new PersistentEventValue();
     * <span style="color: #3F7E5E">// if auto-increment, you don't need to set the PK value</span>
     * persistentEventValue.setFoo...(value);
     * persistentEventValue.setBar...(value);
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//persistentEventValue.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//persistentEventValue.set...;</span>
     * persistentEventValueBhv.<span style="color: #FD4747">insert</span>(persistentEventValue);
     * ... = persistentEventValue.getPK...(); <span style="color: #3F7E5E">// if auto-increment, you can get the value after</span>
     * </pre>
     * @param persistentEventValue The entity of insert target. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void insert(final PersistentEventValue persistentEventValue) {
        doInsert(persistentEventValue, null);
    }

    protected void doInsert(final PersistentEventValue persistentEventValue,
            final InsertOption<PersistentEventValueCB> option) {
        assertObjectNotNull("persistentEventValue", persistentEventValue);
        prepareInsertOption(option);
        delegateInsert(persistentEventValue, option);
    }

    protected void prepareInsertOption(
            final InsertOption<PersistentEventValueCB> option) {
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
     * PersistentEventValue persistentEventValue = new PersistentEventValue();
     * persistentEventValue.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * persistentEventValue.setFoo...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//persistentEventValue.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//persistentEventValue.set...;</span>
     * <span style="color: #3F7E5E">// if exclusive control, the value of exclusive control column is required</span>
     * persistentEventValue.<span style="color: #FD4747">setVersionNo</span>(value);
     * try {
     *     persistentEventValueBhv.<span style="color: #FD4747">update</span>(persistentEventValue);
     * } catch (EntityAlreadyUpdatedException e) { <span style="color: #3F7E5E">// if concurrent update</span>
     *     ...
     * } 
     * </pre>
     * @param persistentEventValue The entity of update target. (NotNull) {PrimaryKeyRequired, ConcurrencyColumnRequired}
     * @exception org.seasar.dbflute.exception.EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void update(final PersistentEventValue persistentEventValue) {
        doUpdate(persistentEventValue, null);
    }

    protected void doUpdate(final PersistentEventValue persistentEventValue,
            final UpdateOption<PersistentEventValueCB> option) {
        assertObjectNotNull("persistentEventValue", persistentEventValue);
        prepareUpdateOption(option);
        helpUpdateInternally(persistentEventValue,
                new InternalUpdateCallback<PersistentEventValue>() {
                    @Override
                    public int callbackDelegateUpdate(
                            final PersistentEventValue entity) {
                        return delegateUpdate(entity, option);
                    }
                });
    }

    protected void prepareUpdateOption(
            final UpdateOption<PersistentEventValueCB> option) {
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

    protected PersistentEventValueCB createCBForVaryingUpdate() {
        final PersistentEventValueCB cb = newMyConditionBean();
        cb.xsetupForVaryingUpdate();
        return cb;
    }

    protected PersistentEventValueCB createCBForSpecifiedUpdate() {
        final PersistentEventValueCB cb = newMyConditionBean();
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
     * PersistentEventValue persistentEventValue = new PersistentEventValue();
     * persistentEventValue.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * persistentEventValue.setFoo...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//persistentEventValue.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//persistentEventValue.set...;</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of exclusive control column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//persistentEventValue.setVersionNo(value);</span>
     * persistentEventValueBhv.<span style="color: #FD4747">updateNonstrict</span>(persistentEventValue);
     * </pre>
     * @param persistentEventValue The entity of update target. (NotNull) {PrimaryKeyRequired}
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void updateNonstrict(final PersistentEventValue persistentEventValue) {
        doUpdateNonstrict(persistentEventValue, null);
    }

    protected void doUpdateNonstrict(
            final PersistentEventValue persistentEventValue,
            final UpdateOption<PersistentEventValueCB> option) {
        assertObjectNotNull("persistentEventValue", persistentEventValue);
        prepareUpdateOption(option);
        helpUpdateNonstrictInternally(persistentEventValue,
                new InternalUpdateNonstrictCallback<PersistentEventValue>() {
                    @Override
                    public int callbackDelegateUpdateNonstrict(
                            final PersistentEventValue entity) {
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
     * @param persistentEventValue The entity of insert or update target. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void insertOrUpdate(final PersistentEventValue persistentEventValue) {
        doInesrtOrUpdate(persistentEventValue, null, null);
    }

    protected void doInesrtOrUpdate(
            final PersistentEventValue persistentEventValue,
            final InsertOption<PersistentEventValueCB> insertOption,
            final UpdateOption<PersistentEventValueCB> updateOption) {
        helpInsertOrUpdateInternally(
                persistentEventValue,
                new InternalInsertOrUpdateCallback<PersistentEventValue, PersistentEventValueCB>() {
                    @Override
                    public void callbackInsert(final PersistentEventValue entity) {
                        doInsert(entity, insertOption);
                    }

                    @Override
                    public void callbackUpdate(final PersistentEventValue entity) {
                        doUpdate(entity, updateOption);
                    }

                    @Override
                    public PersistentEventValueCB callbackNewMyConditionBean() {
                        return newMyConditionBean();
                    }

                    @Override
                    public int callbackSelectCount(
                            final PersistentEventValueCB cb) {
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
            insertOption = insertOption == null ? new InsertOption<PersistentEventValueCB>()
                    : insertOption;
            updateOption = updateOption == null ? new UpdateOption<PersistentEventValueCB>()
                    : updateOption;
            varyingInsertOrUpdate(downcast(entity), downcast(insertOption),
                    downcast(updateOption));
        }
    }

    /**
     * Insert or update the entity non-strictly modified-only. {NonExclusiveControl(when update)}
     * @param persistentEventValue The entity of insert or update target. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void insertOrUpdateNonstrict(
            final PersistentEventValue persistentEventValue) {
        doInesrtOrUpdateNonstrict(persistentEventValue, null, null);
    }

    protected void doInesrtOrUpdateNonstrict(
            final PersistentEventValue persistentEventValue,
            final InsertOption<PersistentEventValueCB> insertOption,
            final UpdateOption<PersistentEventValueCB> updateOption) {
        helpInsertOrUpdateInternally(
                persistentEventValue,
                new InternalInsertOrUpdateNonstrictCallback<PersistentEventValue>() {
                    @Override
                    public void callbackInsert(final PersistentEventValue entity) {
                        doInsert(entity, insertOption);
                    }

                    @Override
                    public void callbackUpdateNonstrict(
                            final PersistentEventValue entity) {
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
            insertOption = insertOption == null ? new InsertOption<PersistentEventValueCB>()
                    : insertOption;
            updateOption = updateOption == null ? new UpdateOption<PersistentEventValueCB>()
                    : updateOption;
            varyingInsertOrUpdateNonstrict(downcast(entity),
                    downcast(insertOption), downcast(updateOption));
        }
    }

    /**
     * Delete the entity. {UpdateCountZeroException, ExclusiveControl}
     * <pre>
     * PersistentEventValue persistentEventValue = new PersistentEventValue();
     * persistentEventValue.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * <span style="color: #3F7E5E">// if exclusive control, the value of exclusive control column is required</span>
     * persistentEventValue.<span style="color: #FD4747">setVersionNo</span>(value);
     * try {
     *     persistentEventValueBhv.<span style="color: #FD4747">delete</span>(persistentEventValue);
     * } catch (EntityAlreadyUpdatedException e) { <span style="color: #3F7E5E">// if concurrent update</span>
     *     ...
     * } 
     * </pre>
     * @param persistentEventValue The entity of delete target. (NotNull) {PrimaryKeyRequired, ConcurrencyColumnRequired}
     * @exception org.seasar.dbflute.exception.EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     */
    public void delete(final PersistentEventValue persistentEventValue) {
        doDelete(persistentEventValue, null);
    }

    protected void doDelete(final PersistentEventValue persistentEventValue,
            final DeleteOption<PersistentEventValueCB> option) {
        assertObjectNotNull("persistentEventValue", persistentEventValue);
        prepareDeleteOption(option);
        helpDeleteInternally(persistentEventValue,
                new InternalDeleteCallback<PersistentEventValue>() {
                    @Override
                    public int callbackDelegateDelete(
                            final PersistentEventValue entity) {
                        return delegateDelete(entity, option);
                    }
                });
    }

    protected void prepareDeleteOption(
            final DeleteOption<PersistentEventValueCB> option) {
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
     * PersistentEventValue persistentEventValue = new PersistentEventValue();
     * persistentEventValue.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of exclusive control column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//persistentEventValue.setVersionNo(value);</span>
     * persistentEventValueBhv.<span style="color: #FD4747">deleteNonstrict</span>(persistentEventValue);
     * </pre>
     * @param persistentEventValue The entity of delete target. (NotNull) {PrimaryKeyRequired}
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     */
    public void deleteNonstrict(final PersistentEventValue persistentEventValue) {
        doDeleteNonstrict(persistentEventValue, null);
    }

    protected void doDeleteNonstrict(
            final PersistentEventValue persistentEventValue,
            final DeleteOption<PersistentEventValueCB> option) {
        assertObjectNotNull("persistentEventValue", persistentEventValue);
        prepareDeleteOption(option);
        helpDeleteNonstrictInternally(persistentEventValue,
                new InternalDeleteNonstrictCallback<PersistentEventValue>() {
                    @Override
                    public int callbackDelegateDeleteNonstrict(
                            final PersistentEventValue entity) {
                        return delegateDeleteNonstrict(entity, option);
                    }
                });
    }

    /**
     * Delete the entity non-strictly ignoring deleted. {UpdateCountZeroException, NonExclusiveControl}
     * <pre>
     * PersistentEventValue persistentEventValue = new PersistentEventValue();
     * persistentEventValue.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of exclusive control column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//persistentEventValue.setVersionNo(value);</span>
     * persistentEventValueBhv.<span style="color: #FD4747">deleteNonstrictIgnoreDeleted</span>(persistentEventValue);
     * <span style="color: #3F7E5E">// if the target entity doesn't exist, no exception</span>
     * </pre>
     * @param persistentEventValue The entity of delete target. (NotNull) {PrimaryKeyRequired}
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     */
    public void deleteNonstrictIgnoreDeleted(
            final PersistentEventValue persistentEventValue) {
        doDeleteNonstrictIgnoreDeleted(persistentEventValue, null);
    }

    protected void doDeleteNonstrictIgnoreDeleted(
            final PersistentEventValue persistentEventValue,
            final DeleteOption<PersistentEventValueCB> option) {
        assertObjectNotNull("persistentEventValue", persistentEventValue);
        prepareDeleteOption(option);
        helpDeleteNonstrictIgnoreDeletedInternally(
                persistentEventValue,
                new InternalDeleteNonstrictIgnoreDeletedCallback<PersistentEventValue>() {
                    @Override
                    public int callbackDelegateDeleteNonstrict(
                            final PersistentEventValue entity) {
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
     * @param persistentEventValueList The list of the entity. (NotNull)
     * @return The array of inserted count.
     */
    public int[] batchInsert(
            final List<PersistentEventValue> persistentEventValueList) {
        return doBatchInsert(persistentEventValueList, null);
    }

    protected int[] doBatchInsert(
            final List<PersistentEventValue> persistentEventValueList,
            final InsertOption<PersistentEventValueCB> option) {
        assertObjectNotNull("persistentEventValueList",
                persistentEventValueList);
        prepareInsertOption(option);
        return delegateBatchInsert(persistentEventValueList, option);
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
     * @param persistentEventValueList The list of the entity. (NotNull)
     * @return The array of updated count.
     * @exception org.seasar.dbflute.exception.BatchEntityAlreadyUpdatedException When the entity has already been updated. This exception extends EntityAlreadyUpdatedException.
     */
    public int[] batchUpdate(
            final List<PersistentEventValue> persistentEventValueList) {
        return doBatchUpdate(persistentEventValueList, null);
    }

    protected int[] doBatchUpdate(
            final List<PersistentEventValue> persistentEventValueList,
            final UpdateOption<PersistentEventValueCB> option) {
        assertObjectNotNull("persistentEventValueList",
                persistentEventValueList);
        prepareUpdateOption(option);
        return delegateBatchUpdate(persistentEventValueList, option);
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
     * @param persistentEventValueList The list of the entity. (NotNull)
     * @param updateColumnSpec The specification of update columns. (NotNull)
     * @return The array of updated count.
     * @exception org.seasar.dbflute.exception.BatchEntityAlreadyUpdatedException When the entity has already been updated. This exception extends EntityAlreadyUpdatedException.
     */
    public int[] batchUpdate(
            final List<PersistentEventValue> persistentEventValueList,
            final SpecifyQuery<PersistentEventValueCB> updateColumnSpec) {
        return doBatchUpdate(persistentEventValueList,
                createSpecifiedUpdateOption(updateColumnSpec));
    }

    /**
     * Batch-update the list non-strictly. <br />
     * This method uses 'Batch Update' of java.sql.PreparedStatement. <br />
     * All columns are update target. {NOT modified only}
     * @param persistentEventValueList The list of the entity. (NotNull)
     * @return The array of updated count.
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     */
    public int[] batchUpdateNonstrict(
            final List<PersistentEventValue> persistentEventValueList) {
        return doBatchUpdateNonstrict(persistentEventValueList, null);
    }

    protected int[] doBatchUpdateNonstrict(
            final List<PersistentEventValue> persistentEventValueList,
            final UpdateOption<PersistentEventValueCB> option) {
        assertObjectNotNull("persistentEventValueList",
                persistentEventValueList);
        prepareUpdateOption(option);
        return delegateBatchUpdateNonstrict(persistentEventValueList, option);
    }

    /**
     * Batch-update the list non-strictly. <br />
     * This method uses 'Batch Update' of java.sql.PreparedStatement. <br />
     * You can specify update columns used on set clause of update statement.
     * However you do not need to specify common columns for update
     * and an optimistick lock column because they are specified implicitly.
     * @param persistentEventValueList The list of the entity. (NotNull)
     * @param updateColumnSpec The specification of update columns. (NotNull)
     * @return The array of updated count.
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     */
    public int[] batchUpdateNonstrict(
            final List<PersistentEventValue> persistentEventValueList,
            final SpecifyQuery<PersistentEventValueCB> updateColumnSpec) {
        return doBatchUpdateNonstrict(persistentEventValueList,
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
     * @param persistentEventValueList The list of the entity. (NotNull)
     * @return The array of deleted count.
     * @exception org.seasar.dbflute.exception.BatchEntityAlreadyUpdatedException When the entity has already been updated. This exception extends EntityAlreadyUpdatedException.
     */
    public int[] batchDelete(
            final List<PersistentEventValue> persistentEventValueList) {
        return doBatchDelete(persistentEventValueList, null);
    }

    protected int[] doBatchDelete(
            final List<PersistentEventValue> persistentEventValueList,
            final DeleteOption<PersistentEventValueCB> option) {
        assertObjectNotNull("persistentEventValueList",
                persistentEventValueList);
        prepareDeleteOption(option);
        return delegateBatchDelete(persistentEventValueList, option);
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
     * @param persistentEventValueList The list of the entity. (NotNull)
     * @return The array of deleted count.
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     */
    public int[] batchDeleteNonstrict(
            final List<PersistentEventValue> persistentEventValueList) {
        return doBatchDeleteNonstrict(persistentEventValueList, null);
    }

    protected int[] doBatchDeleteNonstrict(
            final List<PersistentEventValue> persistentEventValueList,
            final DeleteOption<PersistentEventValueCB> option) {
        assertObjectNotNull("persistentEventValueList",
                persistentEventValueList);
        prepareDeleteOption(option);
        return delegateBatchDeleteNonstrict(persistentEventValueList, option);
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
     * persistentEventValueBhv.<span style="color: #FD4747">queryInsert</span>(new QueryInsertSetupper&lt;PersistentEventValue, PersistentEventValueCB&gt;() {
     *     public ConditionBean setup(persistentEventValue entity, PersistentEventValueCB intoCB) {
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
            final QueryInsertSetupper<PersistentEventValue, PersistentEventValueCB> setupper) {
        return doQueryInsert(setupper, null);
    }

    protected int doQueryInsert(
            final QueryInsertSetupper<PersistentEventValue, PersistentEventValueCB> setupper,
            final InsertOption<PersistentEventValueCB> option) {
        assertObjectNotNull("setupper", setupper);
        prepareInsertOption(option);
        final PersistentEventValue entity = new PersistentEventValue();
        final PersistentEventValueCB intoCB = createCBForQueryInsert();
        final ConditionBean resourceCB = setupper.setup(entity, intoCB);
        return delegateQueryInsert(entity, intoCB, resourceCB, option);
    }

    protected PersistentEventValueCB createCBForQueryInsert() {
        final PersistentEventValueCB cb = newMyConditionBean();
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
     * PersistentEventValue persistentEventValue = new PersistentEventValue();
     * <span style="color: #3F7E5E">// you don't need to set PK value</span>
     * <span style="color: #3F7E5E">//persistentEventValue.setPK...(value);</span>
     * persistentEventValue.setFoo...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//persistentEventValue.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//persistentEventValue.set...;</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of exclusive control column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//persistentEventValue.setVersionNo(value);</span>
     * PersistentEventValueCB cb = new PersistentEventValueCB();
     * cb.query().setFoo...(value);
     * persistentEventValueBhv.<span style="color: #FD4747">queryUpdate</span>(persistentEventValue, cb);
     * </pre>
     * @param persistentEventValue The entity that contains update values. (NotNull, PrimaryKeyNullAllowed)
     * @param cb The condition-bean of PersistentEventValue. (NotNull)
     * @return The updated count.
     * @exception org.seasar.dbflute.exception.NonQueryUpdateNotAllowedException When the query has no condition.
     */
    public int queryUpdate(final PersistentEventValue persistentEventValue,
            final PersistentEventValueCB cb) {
        return doQueryUpdate(persistentEventValue, cb, null);
    }

    protected int doQueryUpdate(
            final PersistentEventValue persistentEventValue,
            final PersistentEventValueCB cb,
            final UpdateOption<PersistentEventValueCB> option) {
        assertObjectNotNull("persistentEventValue", persistentEventValue);
        assertCBStateValid(cb);
        prepareUpdateOption(option);
        return delegateQueryUpdate(persistentEventValue, cb, option);
    }

    @Override
    protected int doRangeModify(final Entity entity, final ConditionBean cb,
            final UpdateOption<? extends ConditionBean> option) {
        if (option == null) {
            return queryUpdate(downcast(entity), (PersistentEventValueCB) cb);
        } else {
            return varyingQueryUpdate(downcast(entity),
                    (PersistentEventValueCB) cb, downcast(option));
        }
    }

    /**
     * Delete the several entities by query. {NonExclusiveControl}
     * <pre>
     * PersistentEventValueCB cb = new PersistentEventValueCB();
     * cb.query().setFoo...(value);
     * persistentEventValueBhv.<span style="color: #FD4747">queryDelete</span>(persistentEventValue, cb);
     * </pre>
     * @param cb The condition-bean of PersistentEventValue. (NotNull)
     * @return The deleted count.
     * @exception org.seasar.dbflute.exception.NonQueryDeleteNotAllowedException When the query has no condition.
     */
    public int queryDelete(final PersistentEventValueCB cb) {
        return doQueryDelete(cb, null);
    }

    protected int doQueryDelete(final PersistentEventValueCB cb,
            final DeleteOption<PersistentEventValueCB> option) {
        assertCBStateValid(cb);
        prepareDeleteOption(option);
        return delegateQueryDelete(cb, option);
    }

    @Override
    protected int doRangeRemove(final ConditionBean cb,
            final DeleteOption<? extends ConditionBean> option) {
        if (option == null) {
            return queryDelete((PersistentEventValueCB) cb);
        } else {
            return varyingQueryDelete((PersistentEventValueCB) cb,
                    downcast(option));
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
     * PersistentEventValue persistentEventValue = new PersistentEventValue();
     * <span style="color: #3F7E5E">// if auto-increment, you don't need to set the PK value</span>
     * persistentEventValue.setFoo...(value);
     * persistentEventValue.setBar...(value);
     * InsertOption<PersistentEventValueCB> option = new InsertOption<PersistentEventValueCB>();
     * <span style="color: #3F7E5E">// you can insert by your values for common columns</span>
     * option.disableCommonColumnAutoSetup();
     * persistentEventValueBhv.<span style="color: #FD4747">varyingInsert</span>(persistentEventValue, option);
     * ... = persistentEventValue.getPK...(); <span style="color: #3F7E5E">// if auto-increment, you can get the value after</span>
     * </pre>
     * @param persistentEventValue The entity of insert target. (NotNull)
     * @param option The option of insert for varying requests. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void varyingInsert(final PersistentEventValue persistentEventValue,
            final InsertOption<PersistentEventValueCB> option) {
        assertInsertOptionNotNull(option);
        doInsert(persistentEventValue, option);
    }

    /**
     * Update the entity with varying requests modified-only. {UpdateCountZeroException, ExclusiveControl} <br />
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification), disableCommonColumnAutoSetup(). <br />
     * Other specifications are same as update(entity).
     * <pre>
     * PersistentEventValue persistentEventValue = new PersistentEventValue();
     * persistentEventValue.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * persistentEventValue.setOther...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// if exclusive control, the value of exclusive control column is required</span>
     * persistentEventValue.<span style="color: #FD4747">setVersionNo</span>(value);
     * try {
     *     <span style="color: #3F7E5E">// you can update by self calculation values</span>
     *     UpdateOption&lt;PersistentEventValueCB&gt; option = new UpdateOption&lt;PersistentEventValueCB&gt;();
     *     option.self(new SpecifyQuery&lt;PersistentEventValueCB&gt;() {
     *         public void specify(PersistentEventValueCB cb) {
     *             cb.specify().<span style="color: #FD4747">columnXxxCount()</span>;
     *         }
     *     }).plus(1); <span style="color: #3F7E5E">// XXX_COUNT = XXX_COUNT + 1</span>
     *     persistentEventValueBhv.<span style="color: #FD4747">varyingUpdate</span>(persistentEventValue, option);
     * } catch (EntityAlreadyUpdatedException e) { <span style="color: #3F7E5E">// if concurrent update</span>
     *     ...
     * }
     * </pre>
     * @param persistentEventValue The entity of update target. (NotNull) {PrimaryKeyRequired, ConcurrencyColumnRequired}
     * @param option The option of update for varying requests. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void varyingUpdate(final PersistentEventValue persistentEventValue,
            final UpdateOption<PersistentEventValueCB> option) {
        assertUpdateOptionNotNull(option);
        doUpdate(persistentEventValue, option);
    }

    /**
     * Update the entity with varying requests non-strictly modified-only. {UpdateCountZeroException, NonExclusiveControl} <br />
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification), disableCommonColumnAutoSetup(). <br />
     * Other specifications are same as updateNonstrict(entity).
     * <pre>
     * <span style="color: #3F7E5E">// ex) you can update by self calculation values</span>
     * PersistentEventValue persistentEventValue = new PersistentEventValue();
     * persistentEventValue.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * persistentEventValue.setOther...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of exclusive control column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//persistentEventValue.setVersionNo(value);</span>
     * UpdateOption&lt;PersistentEventValueCB&gt; option = new UpdateOption&lt;PersistentEventValueCB&gt;();
     * option.self(new SpecifyQuery&lt;PersistentEventValueCB&gt;() {
     *     public void specify(PersistentEventValueCB cb) {
     *         cb.specify().<span style="color: #FD4747">columnFooCount()</span>;
     *     }
     * }).plus(1); <span style="color: #3F7E5E">// FOO_COUNT = FOO_COUNT + 1</span>
     * persistentEventValueBhv.<span style="color: #FD4747">varyingUpdateNonstrict</span>(persistentEventValue, option);
     * </pre>
     * @param persistentEventValue The entity of update target. (NotNull) {PrimaryKeyRequired}
     * @param option The option of update for varying requests. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void varyingUpdateNonstrict(
            final PersistentEventValue persistentEventValue,
            final UpdateOption<PersistentEventValueCB> option) {
        assertUpdateOptionNotNull(option);
        doUpdateNonstrict(persistentEventValue, option);
    }

    /**
     * Insert or update the entity with varying requests. {ExclusiveControl(when update)}<br />
     * Other specifications are same as insertOrUpdate(entity).
     * @param persistentEventValue The entity of insert or update target. (NotNull)
     * @param insertOption The option of insert for varying requests. (NotNull)
     * @param updateOption The option of update for varying requests. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void varyingInsertOrUpdate(
            final PersistentEventValue persistentEventValue,
            final InsertOption<PersistentEventValueCB> insertOption,
            final UpdateOption<PersistentEventValueCB> updateOption) {
        assertInsertOptionNotNull(insertOption);
        assertUpdateOptionNotNull(updateOption);
        doInesrtOrUpdate(persistentEventValue, insertOption, updateOption);
    }

    /**
     * Insert or update the entity with varying requests non-strictly. {NonExclusiveControl(when update)}<br />
     * Other specifications are same as insertOrUpdateNonstrict(entity).
     * @param persistentEventValue The entity of insert or update target. (NotNull)
     * @param insertOption The option of insert for varying requests. (NotNull)
     * @param updateOption The option of update for varying requests. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     * @exception org.seasar.dbflute.exception.EntityAlreadyExistsException When the entity already exists. (Unique Constraint Violation)
     */
    public void varyingInsertOrUpdateNonstrict(
            final PersistentEventValue persistentEventValue,
            final InsertOption<PersistentEventValueCB> insertOption,
            final UpdateOption<PersistentEventValueCB> updateOption) {
        assertInsertOptionNotNull(insertOption);
        assertUpdateOptionNotNull(updateOption);
        doInesrtOrUpdateNonstrict(persistentEventValue, insertOption,
                updateOption);
    }

    /**
     * Delete the entity with varying requests. {UpdateCountZeroException, ExclusiveControl} <br />
     * Now a valid option does not exist. <br />
     * Other specifications are same as delete(entity).
     * @param persistentEventValue The entity of delete target. (NotNull) {PrimaryKeyRequired, ConcurrencyColumnRequired}
     * @param option The option of update for varying requests. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     */
    public void varyingDelete(final PersistentEventValue persistentEventValue,
            final DeleteOption<PersistentEventValueCB> option) {
        assertDeleteOptionNotNull(option);
        doDelete(persistentEventValue, option);
    }

    /**
     * Delete the entity with varying requests non-strictly. {UpdateCountZeroException, NonExclusiveControl} <br />
     * Now a valid option does not exist. <br />
     * Other specifications are same as deleteNonstrict(entity).
     * @param persistentEventValue The entity of delete target. (NotNull) {PrimaryKeyRequired, ConcurrencyColumnRequired}
     * @param option The option of update for varying requests. (NotNull)
     * @exception org.seasar.dbflute.exception.EntityAlreadyDeletedException When the entity has already been deleted.
     * @exception org.seasar.dbflute.exception.EntityDuplicatedException When the entity has been duplicated.
     */
    public void varyingDeleteNonstrict(
            final PersistentEventValue persistentEventValue,
            final DeleteOption<PersistentEventValueCB> option) {
        assertDeleteOptionNotNull(option);
        doDeleteNonstrict(persistentEventValue, option);
    }

    // -----------------------------------------------------
    //                                          Batch Update
    //                                          ------------
    /**
     * Batch-insert the list with varying requests. <br />
     * For example, disableCommonColumnAutoSetup()
     * , disablePrimaryKeyIdentity(), limitBatchInsertLogging(). <br />
     * Other specifications are same as batchInsert(entityList).
     * @param persistentEventValueList The list of the entity. (NotNull)
     * @param option The option of insert for varying requests. (NotNull)
     * @return The array of inserted count.
     */
    public int[] varyingBatchInsert(
            final List<PersistentEventValue> persistentEventValueList,
            final InsertOption<PersistentEventValueCB> option) {
        assertInsertOptionNotNull(option);
        return doBatchInsert(persistentEventValueList, option);
    }

    /**
     * Batch-update the list with varying requests. <br />
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification)
     * , disableCommonColumnAutoSetup(), limitBatchUpdateLogging(). <br />
     * Other specifications are same as batchUpdate(entityList).
     * @param persistentEventValueList The list of the entity. (NotNull)
     * @param option The option of update for varying requests. (NotNull)
     * @return The array of updated count.
     */
    public int[] varyingBatchUpdate(
            final List<PersistentEventValue> persistentEventValueList,
            final UpdateOption<PersistentEventValueCB> option) {
        assertUpdateOptionNotNull(option);
        return doBatchUpdate(persistentEventValueList, option);
    }

    /**
     * Batch-update the list with varying requests non-strictly. <br />
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification)
     * , disableCommonColumnAutoSetup(), limitBatchUpdateLogging(). <br />
     * Other specifications are same as batchUpdateNonstrict(entityList).
     * @param persistentEventValueList The list of the entity. (NotNull)
     * @param option The option of update for varying requests. (NotNull)
     * @return The array of updated count.
     */
    public int[] varyingBatchUpdateNonstrict(
            final List<PersistentEventValue> persistentEventValueList,
            final UpdateOption<PersistentEventValueCB> option) {
        assertUpdateOptionNotNull(option);
        return doBatchUpdateNonstrict(persistentEventValueList, option);
    }

    /**
     * Batch-delete the list with varying requests. <br />
     * For example, limitBatchDeleteLogging(). <br />
     * Other specifications are same as batchDelete(entityList).
     * @param persistentEventValueList The list of the entity. (NotNull)
     * @param option The option of delete for varying requests. (NotNull)
     * @return The array of deleted count.
     */
    public int[] varyingBatchDelete(
            final List<PersistentEventValue> persistentEventValueList,
            final DeleteOption<PersistentEventValueCB> option) {
        assertDeleteOptionNotNull(option);
        return doBatchDelete(persistentEventValueList, option);
    }

    /**
     * Batch-delete the list with varying requests non-strictly. <br />
     * For example, limitBatchDeleteLogging(). <br />
     * Other specifications are same as batchDeleteNonstrict(entityList).
     * @param persistentEventValueList The list of the entity. (NotNull)
     * @param option The option of delete for varying requests. (NotNull)
     * @return The array of deleted count.
     */
    public int[] varyingBatchDeleteNonstrict(
            final List<PersistentEventValue> persistentEventValueList,
            final DeleteOption<PersistentEventValueCB> option) {
        assertDeleteOptionNotNull(option);
        return doBatchDeleteNonstrict(persistentEventValueList, option);
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
            final QueryInsertSetupper<PersistentEventValue, PersistentEventValueCB> setupper,
            final InsertOption<PersistentEventValueCB> option) {
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
     * PersistentEventValue persistentEventValue = new PersistentEventValue();
     * <span style="color: #3F7E5E">// you don't need to set PK value</span>
     * <span style="color: #3F7E5E">//persistentEventValue.setPK...(value);</span>
     * persistentEventValue.setOther...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of exclusive control column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//persistentEventValue.setVersionNo(value);</span>
     * PersistentEventValueCB cb = new PersistentEventValueCB();
     * cb.query().setFoo...(value);
     * UpdateOption&lt;PersistentEventValueCB&gt; option = new UpdateOption&lt;PersistentEventValueCB&gt;();
     * option.self(new SpecifyQuery&lt;PersistentEventValueCB&gt;() {
     *     public void specify(PersistentEventValueCB cb) {
     *         cb.specify().<span style="color: #FD4747">columnFooCount()</span>;
     *     }
     * }).plus(1); <span style="color: #3F7E5E">// FOO_COUNT = FOO_COUNT + 1</span>
     * persistentEventValueBhv.<span style="color: #FD4747">varyingQueryUpdate</span>(persistentEventValue, cb, option);
     * </pre>
     * @param persistentEventValue The entity that contains update values. (NotNull) {PrimaryKeyNotRequired}
     * @param cb The condition-bean of PersistentEventValue. (NotNull)
     * @param option The option of update for varying requests. (NotNull)
     * @return The updated count.
     * @exception org.seasar.dbflute.exception.NonQueryUpdateNotAllowedException When the query has no condition (if not allowed).
     */
    public int varyingQueryUpdate(
            final PersistentEventValue persistentEventValue,
            final PersistentEventValueCB cb,
            final UpdateOption<PersistentEventValueCB> option) {
        assertUpdateOptionNotNull(option);
        return doQueryUpdate(persistentEventValue, cb, option);
    }

    /**
     * Delete the several entities by query with varying requests non-strictly. <br />
     * For example, allowNonQueryDelete(). <br />
     * Other specifications are same as batchUpdateNonstrict(entityList).
     * @param cb The condition-bean of PersistentEventValue. (NotNull)
     * @param option The option of delete for varying requests. (NotNull)
     * @return The deleted count.
     * @exception org.seasar.dbflute.exception.NonQueryDeleteNotAllowedException When the query has no condition (if not allowed).
     */
    public int varyingQueryDelete(final PersistentEventValueCB cb,
            final DeleteOption<PersistentEventValueCB> option) {
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
    public OutsideSqlBasicExecutor<PersistentEventValueBhv> outsideSql() {
        return doOutsideSql();
    }

    // ===================================================================================
    //                                                                     Delegate Method
    //                                                                     ===============
    // [Behavior Command]
    // -----------------------------------------------------
    //                                                Select
    //                                                ------
    protected int delegateSelectCountUniquely(final PersistentEventValueCB cb) {
        return invoke(createSelectCountCBCommand(cb, true));
    }

    protected int delegateSelectCountPlainly(final PersistentEventValueCB cb) {
        return invoke(createSelectCountCBCommand(cb, false));
    }

    protected <ENTITY extends PersistentEventValue> void delegateSelectCursor(
            final PersistentEventValueCB cb,
            final EntityRowHandler<ENTITY> erh, final Class<ENTITY> et) {
        invoke(createSelectCursorCBCommand(cb, erh, et));
    }

    protected <ENTITY extends PersistentEventValue> List<ENTITY> delegateSelectList(
            final PersistentEventValueCB cb, final Class<ENTITY> et) {
        return invoke(createSelectListCBCommand(cb, et));
    }

    // -----------------------------------------------------
    //                                                Update
    //                                                ------
    protected int delegateInsert(final PersistentEventValue e,
            final InsertOption<PersistentEventValueCB> op) {
        if (!processBeforeInsert(e, op)) {
            return 0;
        }
        return invoke(createInsertEntityCommand(e, op));
    }

    protected int delegateUpdate(final PersistentEventValue e,
            final UpdateOption<PersistentEventValueCB> op) {
        if (!processBeforeUpdate(e, op)) {
            return 0;
        }
        return invoke(createUpdateEntityCommand(e, op));
    }

    protected int delegateUpdateNonstrict(final PersistentEventValue e,
            final UpdateOption<PersistentEventValueCB> op) {
        if (!processBeforeUpdate(e, op)) {
            return 0;
        }
        return invoke(createUpdateNonstrictEntityCommand(e, op));
    }

    protected int delegateDelete(final PersistentEventValue e,
            final DeleteOption<PersistentEventValueCB> op) {
        if (!processBeforeDelete(e, op)) {
            return 0;
        }
        return invoke(createDeleteEntityCommand(e, op));
    }

    protected int delegateDeleteNonstrict(final PersistentEventValue e,
            final DeleteOption<PersistentEventValueCB> op) {
        if (!processBeforeDelete(e, op)) {
            return 0;
        }
        return invoke(createDeleteNonstrictEntityCommand(e, op));
    }

    protected int[] delegateBatchInsert(final List<PersistentEventValue> ls,
            final InsertOption<PersistentEventValueCB> op) {
        if (ls.isEmpty()) {
            return new int[] {};
        }
        return invoke(createBatchInsertCommand(processBatchInternally(ls, op),
                op));
    }

    protected int[] delegateBatchUpdate(final List<PersistentEventValue> ls,
            final UpdateOption<PersistentEventValueCB> op) {
        if (ls.isEmpty()) {
            return new int[] {};
        }
        return invoke(createBatchUpdateCommand(
                processBatchInternally(ls, op, false), op));
    }

    protected int[] delegateBatchUpdateNonstrict(
            final List<PersistentEventValue> ls,
            final UpdateOption<PersistentEventValueCB> op) {
        if (ls.isEmpty()) {
            return new int[] {};
        }
        return invoke(createBatchUpdateNonstrictCommand(
                processBatchInternally(ls, op, true), op));
    }

    protected int[] delegateBatchDelete(final List<PersistentEventValue> ls,
            final DeleteOption<PersistentEventValueCB> op) {
        if (ls.isEmpty()) {
            return new int[] {};
        }
        return invoke(createBatchDeleteCommand(
                processBatchInternally(ls, op, false), op));
    }

    protected int[] delegateBatchDeleteNonstrict(
            final List<PersistentEventValue> ls,
            final DeleteOption<PersistentEventValueCB> op) {
        if (ls.isEmpty()) {
            return new int[] {};
        }
        return invoke(createBatchDeleteNonstrictCommand(
                processBatchInternally(ls, op, true), op));
    }

    protected int delegateQueryInsert(final PersistentEventValue e,
            final PersistentEventValueCB inCB, final ConditionBean resCB,
            final InsertOption<PersistentEventValueCB> op) {
        if (!processBeforeQueryInsert(e, inCB, resCB, op)) {
            return 0;
        }
        return invoke(createQueryInsertCBCommand(e, inCB, resCB, op));
    }

    protected int delegateQueryUpdate(final PersistentEventValue e,
            final PersistentEventValueCB cb,
            final UpdateOption<PersistentEventValueCB> op) {
        if (!processBeforeQueryUpdate(e, cb, op)) {
            return 0;
        }
        return invoke(createQueryUpdateCBCommand(e, cb, op));
    }

    protected int delegateQueryDelete(final PersistentEventValueCB cb,
            final DeleteOption<PersistentEventValueCB> op) {
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
    protected PersistentEventValue downcast(final Entity entity) {
        return helpEntityDowncastInternally(entity, PersistentEventValue.class);
    }

    protected PersistentEventValueCB downcast(final ConditionBean cb) {
        return helpConditionBeanDowncastInternally(cb,
                PersistentEventValueCB.class);
    }

    @SuppressWarnings("unchecked")
    protected List<PersistentEventValue> downcast(
            final List<? extends Entity> entityList) {
        return (List<PersistentEventValue>) entityList;
    }

    @SuppressWarnings("unchecked")
    protected InsertOption<PersistentEventValueCB> downcast(
            final InsertOption<? extends ConditionBean> option) {
        return (InsertOption<PersistentEventValueCB>) option;
    }

    @SuppressWarnings("unchecked")
    protected UpdateOption<PersistentEventValueCB> downcast(
            final UpdateOption<? extends ConditionBean> option) {
        return (UpdateOption<PersistentEventValueCB>) option;
    }

    @SuppressWarnings("unchecked")
    protected DeleteOption<PersistentEventValueCB> downcast(
            final DeleteOption<? extends ConditionBean> option) {
        return (DeleteOption<PersistentEventValueCB>) option;
    }

    @SuppressWarnings("unchecked")
    protected QueryInsertSetupper<PersistentEventValue, PersistentEventValueCB> downcast(
            final QueryInsertSetupper<? extends Entity, ? extends ConditionBean> option) {
        return (QueryInsertSetupper<PersistentEventValue, PersistentEventValueCB>) option;
    }
}
