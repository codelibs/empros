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
package org.codelibs.empros.db.bsentity.dbmeta;

import java.util.List;
import java.util.Map;

import org.codelibs.empros.db.allcommon.*;
import org.codelibs.empros.db.exentity.*;
import org.dbflute.Entity;
import org.dbflute.optional.OptionalEntity;
import org.dbflute.dbmeta.AbstractDBMeta;
import org.dbflute.dbmeta.info.*;
import org.dbflute.dbmeta.name.*;
import org.dbflute.dbmeta.property.PropertyGateway;
import org.dbflute.dbway.DBDef;

/**
 * The DB meta of PERSISTENT_EVENT_VALUE. (Singleton)
 * @author DBFlute(AutoGenerator)
 */
public class PersistentEventValueDbm extends AbstractDBMeta {

    // ===================================================================================
    //                                                                           Singleton
    //                                                                           =========
    private static final PersistentEventValueDbm _instance = new PersistentEventValueDbm();
    private PersistentEventValueDbm() {}
    public static PersistentEventValueDbm getInstance() { return _instance; }

    // ===================================================================================
    //                                                                       Current DBDef
    //                                                                       =============
    public String getProjectName() { return DBCurrent.getInstance().projectName(); }
    public String getProjectPrefix() { return DBCurrent.getInstance().projectPrefix(); }
    public String getGenerationGapBasePrefix() { return DBCurrent.getInstance().generationGapBasePrefix(); }
    public DBDef getCurrentDBDef() { return DBCurrent.getInstance().currentDBDef(); }

    // ===================================================================================
    //                                                                    Property Gateway
    //                                                                    ================
    // -----------------------------------------------------
    //                                       Column Property
    //                                       ---------------
    protected final Map<String, PropertyGateway> _epgMap = newHashMap();
    { xsetupEpg(); }
    protected void xsetupEpg() {
        setupEpg(_epgMap, et -> ((PersistentEventValue)et).getId(), (et, vl) -> ((PersistentEventValue)et).setId(ctl(vl)), "id");
        setupEpg(_epgMap, et -> ((PersistentEventValue)et).getEventId(), (et, vl) -> ((PersistentEventValue)et).setEventId(ctl(vl)), "eventId");
        setupEpg(_epgMap, et -> ((PersistentEventValue)et).getName(), (et, vl) -> ((PersistentEventValue)et).setName((String)vl), "name");
        setupEpg(_epgMap, et -> ((PersistentEventValue)et).getValue(), (et, vl) -> ((PersistentEventValue)et).setValue((String)vl), "value");
        setupEpg(_epgMap, et -> ((PersistentEventValue)et).getClassType(), (et, vl) -> ((PersistentEventValue)et).setClassType((String)vl), "classType");
        setupEpg(_epgMap, et -> ((PersistentEventValue)et).getVersionNo(), (et, vl) -> ((PersistentEventValue)et).setVersionNo(cti(vl)), "versionNo");
    }
    public PropertyGateway findPropertyGateway(String prop)
    { return doFindEpg(_epgMap, prop); }

    // -----------------------------------------------------
    //                                      Foreign Property
    //                                      ----------------
    protected final Map<String, PropertyGateway> _efpgMap = newHashMap();
    { xsetupEfpg(); }
    @SuppressWarnings("unchecked")
    protected void xsetupEfpg() {
        setupEfpg(_efpgMap, et -> ((PersistentEventValue)et).getPersistentEvent(), (et, vl) -> ((PersistentEventValue)et).setPersistentEvent((OptionalEntity<PersistentEvent>)vl), "persistentEvent");
    }
    public PropertyGateway findForeignPropertyGateway(String prop)
    { return doFindEfpg(_efpgMap, prop); }

    // ===================================================================================
    //                                                                          Table Info
    //                                                                          ==========
    protected final String _tableDbName = "PERSISTENT_EVENT_VALUE";
    protected final String _tableDispName = "PERSISTENT_EVENT_VALUE";
    protected final String _tablePropertyName = "persistentEventValue";
    protected final TableSqlName _tableSqlName = new TableSqlName("PERSISTENT_EVENT_VALUE", _tableDbName);
    { _tableSqlName.xacceptFilter(DBFluteConfig.getInstance().getTableSqlNameFilter()); }
    public String getTableDbName() { return _tableDbName; }
    public String getTableDispName() { return _tableDispName; }
    public String getTablePropertyName() { return _tablePropertyName; }
    public TableSqlName getTableSqlName() { return _tableSqlName; }

    // ===================================================================================
    //                                                                         Column Info
    //                                                                         ===========
    protected final ColumnInfo _columnId = cci("ID", "ID", null, null, Long.class, "id", null, true, true, true, "BIGINT", 19, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnEventId = cci("EVENT_ID", "EVENT_ID", null, null, Long.class, "eventId", null, false, false, true, "BIGINT", 19, 0, null, null, false, null, null, "persistentEvent", null, null, false);
    protected final ColumnInfo _columnName = cci("NAME", "NAME", null, null, String.class, "name", null, false, false, true, "VARCHAR", 4000, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnValue = cci("VALUE", "VALUE", null, null, String.class, "value", null, false, false, true, "VARCHAR", 4000, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnClassType = cci("CLASS_TYPE", "CLASS_TYPE", null, null, String.class, "classType", null, false, false, true, "VARCHAR", 255, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnVersionNo = cci("VERSION_NO", "VERSION_NO", null, null, Integer.class, "versionNo", null, false, false, true, "INT", 10, 0, null, null, false, OptimisticLockType.VERSION_NO, null, null, null, null, false);

    /**
     * ID: {PK, ID, NotNull, BIGINT(19)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnId() { return _columnId; }
    /**
     * EVENT_ID: {IX, NotNull, BIGINT(19), FK to PERSISTENT_EVENT}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnEventId() { return _columnEventId; }
    /**
     * NAME: {NotNull, VARCHAR(4000)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnName() { return _columnName; }
    /**
     * VALUE: {NotNull, VARCHAR(4000)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnValue() { return _columnValue; }
    /**
     * CLASS_TYPE: {NotNull, VARCHAR(255)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnClassType() { return _columnClassType; }
    /**
     * VERSION_NO: {NotNull, INT(10)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnVersionNo() { return _columnVersionNo; }

    protected List<ColumnInfo> ccil() {
        List<ColumnInfo> ls = newArrayList();
        ls.add(columnId());
        ls.add(columnEventId());
        ls.add(columnName());
        ls.add(columnValue());
        ls.add(columnClassType());
        ls.add(columnVersionNo());
        return ls;
    }

    { initializeInformationResource(); }

    // ===================================================================================
    //                                                                         Unique Info
    //                                                                         ===========
    // -----------------------------------------------------
    //                                       Primary Element
    //                                       ---------------
    protected UniqueInfo cpui() { return hpcpui(columnId()); }
    public boolean hasPrimaryKey() { return true; }
    public boolean hasCompoundPrimaryKey() { return false; }

    // ===================================================================================
    //                                                                       Relation Info
    //                                                                       =============
    // cannot cache because it uses related DB meta instance while booting
    // (instead, cached by super's collection)
    // -----------------------------------------------------
    //                                      Foreign Property
    //                                      ----------------
    /**
     * PERSISTENT_EVENT by my EVENT_ID, named 'persistentEvent'.
     * @return The information object of foreign property. (NotNull)
     */
    public ForeignInfo foreignPersistentEvent() {
        Map<ColumnInfo, ColumnInfo> mp = newLinkedHashMap(columnEventId(), PersistentEventDbm.getInstance().columnId());
        return cfi("FK_PERSISTENT_EVENT_VALUE_PERSISTENT_EVENT", "persistentEvent", this, PersistentEventDbm.getInstance(), mp, 0, org.dbflute.optional.OptionalEntity.class, false, false, false, false, null, null, false, "persistentEventValueList", false);
    }

    // -----------------------------------------------------
    //                                     Referrer Property
    //                                     -----------------

    // ===================================================================================
    //                                                                        Various Info
    //                                                                        ============
    public boolean hasIdentity() { return true; }
    public boolean hasVersionNo() { return true; }
    public ColumnInfo getVersionNoColumnInfo() { return _columnVersionNo; }

    // ===================================================================================
    //                                                                           Type Name
    //                                                                           =========
    public String getEntityTypeName() { return "org.codelibs.empros.db.exentity.PersistentEventValue"; }
    public String getConditionBeanTypeName() { return "org.codelibs.empros.db.cbean.PersistentEventValueCB"; }
    public String getBehaviorTypeName() { return "org.codelibs.empros.db.exbhv.PersistentEventValueBhv"; }

    // ===================================================================================
    //                                                                         Object Type
    //                                                                         ===========
    public Class<PersistentEventValue> getEntityType() { return PersistentEventValue.class; }

    // ===================================================================================
    //                                                                     Object Instance
    //                                                                     ===============
    public PersistentEventValue newEntity() { return new PersistentEventValue(); }

    // ===================================================================================
    //                                                                   Map Communication
    //                                                                   =================
    public void acceptPrimaryKeyMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptPrimaryKeyMap((PersistentEventValue)et, mp); }
    public void acceptAllColumnMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptAllColumnMap((PersistentEventValue)et, mp); }
    public Map<String, Object> extractPrimaryKeyMap(Entity et) { return doExtractPrimaryKeyMap(et); }
    public Map<String, Object> extractAllColumnMap(Entity et) { return doExtractAllColumnMap(et); }
}
