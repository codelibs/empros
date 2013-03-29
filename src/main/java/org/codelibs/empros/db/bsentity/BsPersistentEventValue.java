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
package org.codelibs.empros.db.bsentity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.codelibs.empros.db.allcommon.DBMetaInstanceHandler;
import org.codelibs.empros.db.exentity.PersistentEvent;
import org.codelibs.empros.db.exentity.PersistentEventValue;
import org.seasar.dbflute.Entity;
import org.seasar.dbflute.dbmeta.DBMeta;

/**
 * The entity of PERSISTENT_EVENT_VALUE as TABLE. <br />
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
public abstract class BsPersistentEventValue implements Entity, Serializable,
        Cloneable {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** Serial version UID. (Default) */
    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // -----------------------------------------------------
    //                                                Column
    //                                                ------
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

    /** VERSION_NO: {NotNull, INTEGER(10)} */
    protected Integer _versionNo;

    // -----------------------------------------------------
    //                                              Internal
    //                                              --------
    /** The modified properties for this entity. (NotNull) */
    protected final EntityModifiedProperties __modifiedProperties = newModifiedProperties();

    // ===================================================================================
    //                                                                          Table Name
    //                                                                          ==========
    /**
     * {@inheritDoc}
     */
    @Override
    public String getTableDbName() {
        return "PERSISTENT_EVENT_VALUE";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTablePropertyName() { // according to Java Beans rule
        return "persistentEventValue";
    }

    // ===================================================================================
    //                                                                              DBMeta
    //                                                                              ======
    /**
     * {@inheritDoc}
     */
    @Override
    public DBMeta getDBMeta() {
        return DBMetaInstanceHandler.findDBMeta(getTableDbName());
    }

    // ===================================================================================
    //                                                                         Primary Key
    //                                                                         ===========
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPrimaryKeyValue() {
        if (getId() == null) {
            return false;
        }
        return true;
    }

    // ===================================================================================
    //                                                                    Foreign Property
    //                                                                    ================
    /** PERSISTENT_EVENT by my EVENT_ID, named 'persistentEvent'. */
    protected PersistentEvent _persistentEvent;

    /**
     * PERSISTENT_EVENT by my EVENT_ID, named 'persistentEvent'.
     * @return The entity of foreign property 'persistentEvent'. (NullAllowed: If the foreign key does not have 'NotNull' constraint, please check null.)
     */
    public PersistentEvent getPersistentEvent() {
        return _persistentEvent;
    }

    /**
     * PERSISTENT_EVENT by my EVENT_ID, named 'persistentEvent'.
     * @param persistentEvent The entity of foreign property 'persistentEvent'. (NullAllowed)
     */
    public void setPersistentEvent(final PersistentEvent persistentEvent) {
        _persistentEvent = persistentEvent;
    }

    // ===================================================================================
    //                                                                   Referrer Property
    //                                                                   =================
    protected <ELEMENT> List<ELEMENT> newReferrerList() {
        return new ArrayList<ELEMENT>();
    }

    // ===================================================================================
    //                                                                 Modified Properties
    //                                                                 ===================
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> modifiedProperties() {
        return __modifiedProperties.getPropertyNames();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearModifiedInfo() {
        __modifiedProperties.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasModification() {
        return !__modifiedProperties.isEmpty();
    }

    protected EntityModifiedProperties newModifiedProperties() {
        return new EntityModifiedProperties();
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    /**
     * Determine the object is equal with this. <br />
     * If primary-keys or columns of the other are same as this one, returns true.
     * @param other The other entity. (NullAllowed)
     * @return Comparing result.
     */
    @Override
    public boolean equals(final Object other) {
        if (other == null || !(other instanceof BsPersistentEventValue)) {
            return false;
        }
        final BsPersistentEventValue otherEntity = (BsPersistentEventValue) other;
        if (!xSV(getId(), otherEntity.getId())) {
            return false;
        }
        return true;
    }

    protected boolean xSV(final Object value1, final Object value2) { // isSameValue()
        return InternalUtil.isSameValue(value1, value2);
    }

    /**
     * Calculate the hash-code from primary-keys or columns.
     * @return The hash-code from primary-key or columns.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = xCH(result, getTableDbName());
        result = xCH(result, getId());
        return result;
    }

    protected int xCH(final int result, final Object value) { // calculateHashcode()
        return InternalUtil.calculateHashcode(result, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int instanceHash() {
        return super.hashCode();
    }

    /**
     * Convert to display string of entity's data. (no relation data)
     * @return The display string of all columns and relation existences. (NotNull)
     */
    @Override
    public String toString() {
        return buildDisplayString(InternalUtil.toClassTitle(this), true, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toStringWithRelation() {
        final StringBuilder sb = new StringBuilder();
        sb.append(toString());
        final String l = "\n  ";
        if (_persistentEvent != null) {
            sb.append(l).append(xbRDS(_persistentEvent, "persistentEvent"));
        }
        return sb.toString();
    }

    protected String xbRDS(final Entity e, final String name) { // buildRelationDisplayString()
        return e.buildDisplayString(name, true, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildDisplayString(final String name, final boolean column,
            final boolean relation) {
        final StringBuilder sb = new StringBuilder();
        if (name != null) {
            sb.append(name).append(column || relation ? ":" : "");
        }
        if (column) {
            sb.append(buildColumnString());
        }
        if (relation) {
            sb.append(buildRelationString());
        }
        sb.append("@").append(Integer.toHexString(hashCode()));
        return sb.toString();
    }

    protected String buildColumnString() {
        final StringBuilder sb = new StringBuilder();
        final String delimiter = ", ";
        sb.append(delimiter).append(getId());
        sb.append(delimiter).append(getEventId());
        sb.append(delimiter).append(getName());
        sb.append(delimiter).append(getValue());
        sb.append(delimiter).append(getClassType());
        sb.append(delimiter).append(getVersionNo());
        if (sb.length() > delimiter.length()) {
            sb.delete(0, delimiter.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    protected String buildRelationString() {
        final StringBuilder sb = new StringBuilder();
        final String c = ",";
        if (_persistentEvent != null) {
            sb.append(c).append("persistentEvent");
        }
        if (sb.length() > c.length()) {
            sb.delete(0, c.length()).insert(0, "(").append(")");
        }
        return sb.toString();
    }

    /**
     * Clone entity instance using super.clone(). (shallow copy) 
     * @return The cloned instance of this entity. (NotNull)
     */
    @Override
    public PersistentEventValue clone() {
        try {
            return (PersistentEventValue) super.clone();
        } catch (final CloneNotSupportedException e) {
            throw new IllegalStateException("Failed to clone the entity: "
                    + toString(), e);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * [get] ID: {PK, ID, NotNull, BIGINT(19)} <br />
     * @return The value of the column 'ID'. (NullAllowed)
     */
    public Long getId() {
        return _id;
    }

    /**
     * [set] ID: {PK, ID, NotNull, BIGINT(19)} <br />
     * @param id The value of the column 'ID'. (NullAllowed)
     */
    public void setId(final Long id) {
        __modifiedProperties.addPropertyName("id");
        _id = id;
    }

    /**
     * [get] EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT} <br />
     * @return The value of the column 'EVENT_ID'. (NullAllowed)
     */
    public Long getEventId() {
        return _eventId;
    }

    /**
     * [set] EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT} <br />
     * @param eventId The value of the column 'EVENT_ID'. (NullAllowed)
     */
    public void setEventId(final Long eventId) {
        __modifiedProperties.addPropertyName("eventId");
        _eventId = eventId;
    }

    /**
     * [get] NAME: {NotNull, VARCHAR(4000)} <br />
     * @return The value of the column 'NAME'. (NullAllowed)
     */
    public String getName() {
        return _name;
    }

    /**
     * [set] NAME: {NotNull, VARCHAR(4000)} <br />
     * @param name The value of the column 'NAME'. (NullAllowed)
     */
    public void setName(final String name) {
        __modifiedProperties.addPropertyName("name");
        _name = name;
    }

    /**
     * [get] VALUE: {NotNull, VARCHAR(4000)} <br />
     * @return The value of the column 'VALUE'. (NullAllowed)
     */
    public String getValue() {
        return _value;
    }

    /**
     * [set] VALUE: {NotNull, VARCHAR(4000)} <br />
     * @param value The value of the column 'VALUE'. (NullAllowed)
     */
    public void setValue(final String value) {
        __modifiedProperties.addPropertyName("value");
        _value = value;
    }

    /**
     * [get] CLASS_TYPE: {NotNull, VARCHAR(255)} <br />
     * @return The value of the column 'CLASS_TYPE'. (NullAllowed)
     */
    public String getClassType() {
        return _classType;
    }

    /**
     * [set] CLASS_TYPE: {NotNull, VARCHAR(255)} <br />
     * @param classType The value of the column 'CLASS_TYPE'. (NullAllowed)
     */
    public void setClassType(final String classType) {
        __modifiedProperties.addPropertyName("classType");
        _classType = classType;
    }

    /**
     * [get] VERSION_NO: {NotNull, INTEGER(10)} <br />
     * @return The value of the column 'VERSION_NO'. (NullAllowed)
     */
    public Integer getVersionNo() {
        return _versionNo;
    }

    /**
     * [set] VERSION_NO: {NotNull, INTEGER(10)} <br />
     * @param versionNo The value of the column 'VERSION_NO'. (NullAllowed)
     */
    public void setVersionNo(final Integer versionNo) {
        __modifiedProperties.addPropertyName("versionNo");
        _versionNo = versionNo;
    }
}
