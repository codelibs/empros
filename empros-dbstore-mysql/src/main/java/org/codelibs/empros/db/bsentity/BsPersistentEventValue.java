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

import org.codelibs.empros.db.allcommon.DBMetaInstanceHandler;
import org.codelibs.empros.db.exentity.*;
import org.dbflute.Entity;
import org.dbflute.dbmeta.DBMeta;
import org.dbflute.dbmeta.AbstractEntity;
import org.dbflute.dbmeta.accessory.DomainEntity;
import org.dbflute.optional.OptionalEntity;

/**
 * The entity of PERSISTENT_EVENT_VALUE as TABLE. <br>
 * <pre>
 * [primary-key]
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
 *
 * [get/set template]
 * /= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
 * Long id = entity.getId();
 * Long eventId = entity.getEventId();
 * String name = entity.getName();
 * String value = entity.getValue();
 * String classType = entity.getClassType();
 * Integer versionNo = entity.getVersionNo();
 * entity.setId(id);
 * entity.setEventId(eventId);
 * entity.setName(name);
 * entity.setValue(value);
 * entity.setClassType(classType);
 * entity.setVersionNo(versionNo);
 * = = = = = = = = = =/
 * </pre>
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsPersistentEventValue extends AbstractEntity implements DomainEntity {

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

    /** EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT} */
    protected Long _eventId;

    /** NAME: {NotNull, VARCHAR(4000)} */
    protected String _name;

    /** VALUE: {NotNull, VARCHAR(4000)} */
    protected String _value;

    /** CLASS_TYPE: {NotNull, VARCHAR(255)} */
    protected String _classType;

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
        return "PERSISTENT_EVENT_VALUE";
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
    /** PERSISTENT_EVENT by my EVENT_ID, named 'persistentEvent'. */
    protected OptionalEntity<PersistentEvent> _persistentEvent;

    /**
     * [get] PERSISTENT_EVENT by my EVENT_ID, named 'persistentEvent'. <br>
     * Optional: alwaysPresent(), ifPresent().orElse(), get(), ...
     * @return The entity of foreign property 'persistentEvent'. (NotNull, EmptyAllowed: when e.g. null FK column, no setupSelect)
     */
    public OptionalEntity<PersistentEvent> getPersistentEvent() {
        if (_persistentEvent == null) { _persistentEvent = OptionalEntity.relationEmpty(this, "persistentEvent"); }
        return _persistentEvent;
    }

    /**
     * [set] PERSISTENT_EVENT by my EVENT_ID, named 'persistentEvent'.
     * @param persistentEvent The entity of foreign property 'persistentEvent'. (NullAllowed)
     */
    public void setPersistentEvent(OptionalEntity<PersistentEvent> persistentEvent) {
        _persistentEvent = persistentEvent;
    }

    // ===================================================================================
    //                                                                   Referrer Property
    //                                                                   =================
    protected <ELEMENT> List<ELEMENT> newReferrerList() { // overriding to import
        return new ArrayList<ELEMENT>();
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected boolean doEquals(Object obj) {
        if (obj instanceof BsPersistentEventValue) {
            BsPersistentEventValue other = (BsPersistentEventValue)obj;
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
        if (_persistentEvent != null && _persistentEvent.isPresent())
        { sb.append(li).append(xbRDS(_persistentEvent, "persistentEvent")); }
        return sb.toString();
    }
    protected <ET extends Entity> String xbRDS(org.dbflute.optional.OptionalEntity<ET> et, String name) { // buildRelationDisplayString()
        return et.get().buildDisplayString(name, true, true);
    }

    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(xfND(_id));
        sb.append(dm).append(xfND(_eventId));
        sb.append(dm).append(xfND(_name));
        sb.append(dm).append(xfND(_value));
        sb.append(dm).append(xfND(_classType));
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
        if (_persistentEvent != null && _persistentEvent.isPresent())
        { sb.append(dm).append("persistentEvent"); }
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length()).insert(0, "(").append(")");
        }
        return sb.toString();
    }

    @Override
    public PersistentEventValue clone() {
        return (PersistentEventValue)super.clone();
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
     * [get] EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT} <br>
     * @return The value of the column 'EVENT_ID'. (basically NotNull if selected: for the constraint)
     */
    public Long getEventId() {
        checkSpecifiedProperty("eventId");
        return _eventId;
    }

    /**
     * [set] EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT} <br>
     * @param eventId The value of the column 'EVENT_ID'. (basically NotNull if update: for the constraint)
     */
    public void setEventId(Long eventId) {
        registerModifiedProperty("eventId");
        _eventId = eventId;
    }

    /**
     * [get] NAME: {NotNull, VARCHAR(4000)} <br>
     * @return The value of the column 'NAME'. (basically NotNull if selected: for the constraint)
     */
    public String getName() {
        checkSpecifiedProperty("name");
        return _name;
    }

    /**
     * [set] NAME: {NotNull, VARCHAR(4000)} <br>
     * @param name The value of the column 'NAME'. (basically NotNull if update: for the constraint)
     */
    public void setName(String name) {
        registerModifiedProperty("name");
        _name = name;
    }

    /**
     * [get] VALUE: {NotNull, VARCHAR(4000)} <br>
     * @return The value of the column 'VALUE'. (basically NotNull if selected: for the constraint)
     */
    public String getValue() {
        checkSpecifiedProperty("value");
        return _value;
    }

    /**
     * [set] VALUE: {NotNull, VARCHAR(4000)} <br>
     * @param value The value of the column 'VALUE'. (basically NotNull if update: for the constraint)
     */
    public void setValue(String value) {
        registerModifiedProperty("value");
        _value = value;
    }

    /**
     * [get] CLASS_TYPE: {NotNull, VARCHAR(255)} <br>
     * @return The value of the column 'CLASS_TYPE'. (basically NotNull if selected: for the constraint)
     */
    public String getClassType() {
        checkSpecifiedProperty("classType");
        return _classType;
    }

    /**
     * [set] CLASS_TYPE: {NotNull, VARCHAR(255)} <br>
     * @param classType The value of the column 'CLASS_TYPE'. (basically NotNull if update: for the constraint)
     */
    public void setClassType(String classType) {
        registerModifiedProperty("classType");
        _classType = classType;
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
