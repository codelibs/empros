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
package org.codelibs.empros.db.bsentity;

import java.util.List;
import java.util.ArrayList;

import org.dbflute.dbmeta.DBMeta;
import org.dbflute.dbmeta.AbstractEntity;
import org.dbflute.dbmeta.accessory.DomainEntity;
import org.codelibs.empros.db.allcommon.DBMetaInstanceHandler;
import org.codelibs.empros.db.exentity.*;

/**
 * The entity of PERSISTENT_EVENT as TABLE. <br>
 * <pre>
 * [primary-key]
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
 *
 * [get/set template]
 * /= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
 * Long id = entity.getId();
 * String createdBy = entity.getCreatedBy();
 * java.time.LocalDateTime createdTime = entity.getCreatedTime();
 * Integer versionNo = entity.getVersionNo();
 * entity.setId(id);
 * entity.setCreatedBy(createdBy);
 * entity.setCreatedTime(createdTime);
 * entity.setVersionNo(versionNo);
 * = = = = = = = = = =/
 * </pre>
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsPersistentEvent extends AbstractEntity implements DomainEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** The serial version UID for object serialization. (Default) */
    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** ID: {PK, ID, NotNull, BIGINT(19)} */
    protected Long _id;

    /** CREATED_BY: {NotNull, VARCHAR(255)} */
    protected String _createdBy;

    /** CREATED_TIME: {NotNull, DATETIME(19)} */
    protected java.time.LocalDateTime _createdTime;

    /** VERSION_NO: {NotNull, INT(10)} */
    protected Integer _versionNo;

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    /** {@inheritDoc} */
    public DBMeta asDBMeta() {
        return DBMetaInstanceHandler.findDBMeta(asTableDbName());
    }

    /** {@inheritDoc} */
    public String asTableDbName() {
        return "PERSISTENT_EVENT";
    }

    // ===================================================================================
    //                                                                        Key Handling
    //                                                                        ============
    /** {@inheritDoc} */
    public boolean hasPrimaryKeyValue() {
        if (_id == null) { return false; }
        return true;
    }

    // ===================================================================================
    //                                                                    Foreign Property
    //                                                                    ================
    // ===================================================================================
    //                                                                   Referrer Property
    //                                                                   =================
    /** PERSISTENT_EVENT_VALUE by EVENT_ID, named 'persistentEventValueList'. */
    protected List<PersistentEventValue> _persistentEventValueList;

    /**
     * [get] PERSISTENT_EVENT_VALUE by EVENT_ID, named 'persistentEventValueList'.
     * @return The entity list of referrer property 'persistentEventValueList'. (NotNull: even if no loading, returns empty list)
     */
    public List<PersistentEventValue> getPersistentEventValueList() {
        if (_persistentEventValueList == null) { _persistentEventValueList = newReferrerList(); }
        return _persistentEventValueList;
    }

    /**
     * [set] PERSISTENT_EVENT_VALUE by EVENT_ID, named 'persistentEventValueList'.
     * @param persistentEventValueList The entity list of referrer property 'persistentEventValueList'. (NullAllowed)
     */
    public void setPersistentEventValueList(List<PersistentEventValue> persistentEventValueList) {
        _persistentEventValueList = persistentEventValueList;
    }

    protected <ELEMENT> List<ELEMENT> newReferrerList() { // overriding to import
        return new ArrayList<ELEMENT>();
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected boolean doEquals(Object obj) {
        if (obj instanceof BsPersistentEvent) {
            BsPersistentEvent other = (BsPersistentEvent)obj;
            if (!xSV(_id, other._id)) { return false; }
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected int doHashCode(int initial) {
        int hs = initial;
        hs = xCH(hs, asTableDbName());
        hs = xCH(hs, _id);
        return hs;
    }

    @Override
    protected String doBuildStringWithRelation(String li) {
        StringBuilder sb = new StringBuilder();
        if (_persistentEventValueList != null) { for (PersistentEventValue et : _persistentEventValueList)
        { if (et != null) { sb.append(li).append(xbRDS(et, "persistentEventValueList")); } } }
        return sb.toString();
    }

    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(xfND(_id));
        sb.append(dm).append(xfND(_createdBy));
        sb.append(dm).append(xfND(_createdTime));
        sb.append(dm).append(xfND(_versionNo));
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    @Override
    protected String doBuildRelationString(String dm) {
        StringBuilder sb = new StringBuilder();
        if (_persistentEventValueList != null && !_persistentEventValueList.isEmpty())
        { sb.append(dm).append("persistentEventValueList"); }
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length()).insert(0, "(").append(")");
        }
        return sb.toString();
    }

    @Override
    public PersistentEvent clone() {
        return (PersistentEvent)super.clone();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * [get] ID: {PK, ID, NotNull, BIGINT(19)} <br>
     * @return The value of the column 'ID'. (basically NotNull if selected: for the constraint)
     */
    public Long getId() {
        checkSpecifiedProperty("id");
        return _id;
    }

    /**
     * [set] ID: {PK, ID, NotNull, BIGINT(19)} <br>
     * @param id The value of the column 'ID'. (basically NotNull if update: for the constraint)
     */
    public void setId(Long id) {
        registerModifiedProperty("id");
        _id = id;
    }

    /**
     * [get] CREATED_BY: {NotNull, VARCHAR(255)} <br>
     * @return The value of the column 'CREATED_BY'. (basically NotNull if selected: for the constraint)
     */
    public String getCreatedBy() {
        checkSpecifiedProperty("createdBy");
        return _createdBy;
    }

    /**
     * [set] CREATED_BY: {NotNull, VARCHAR(255)} <br>
     * @param createdBy The value of the column 'CREATED_BY'. (basically NotNull if update: for the constraint)
     */
    public void setCreatedBy(String createdBy) {
        registerModifiedProperty("createdBy");
        _createdBy = createdBy;
    }

    /**
     * [get] CREATED_TIME: {NotNull, DATETIME(19)} <br>
     * @return The value of the column 'CREATED_TIME'. (basically NotNull if selected: for the constraint)
     */
    public java.time.LocalDateTime getCreatedTime() {
        checkSpecifiedProperty("createdTime");
        return _createdTime;
    }

    /**
     * [set] CREATED_TIME: {NotNull, DATETIME(19)} <br>
     * @param createdTime The value of the column 'CREATED_TIME'. (basically NotNull if update: for the constraint)
     */
    public void setCreatedTime(java.time.LocalDateTime createdTime) {
        registerModifiedProperty("createdTime");
        _createdTime = createdTime;
    }

    /**
     * [get] VERSION_NO: {NotNull, INT(10)} <br>
     * @return The value of the column 'VERSION_NO'. (basically NotNull if selected: for the constraint)
     */
    public Integer getVersionNo() {
        checkSpecifiedProperty("versionNo");
        return _versionNo;
    }

    /**
     * [set] VERSION_NO: {NotNull, INT(10)} <br>
     * @param versionNo The value of the column 'VERSION_NO'. (basically NotNull if update: for the constraint)
     */
    public void setVersionNo(Integer versionNo) {
        registerModifiedProperty("versionNo");
        _versionNo = versionNo;
    }
}
