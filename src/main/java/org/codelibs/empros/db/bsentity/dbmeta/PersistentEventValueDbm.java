package org.codelibs.empros.db.bsentity.dbmeta;

import java.util.List;
import java.util.Map;

import org.codelibs.empros.db.allcommon.DBCurrent;
import org.codelibs.empros.db.allcommon.DBFluteConfig;
import org.codelibs.empros.db.exentity.PersistentEventValue;
import org.seasar.dbflute.DBDef;
import org.seasar.dbflute.Entity;
import org.seasar.dbflute.dbmeta.AbstractDBMeta;
import org.seasar.dbflute.dbmeta.PropertyGateway;
import org.seasar.dbflute.dbmeta.info.ColumnInfo;
import org.seasar.dbflute.dbmeta.info.ForeignInfo;
import org.seasar.dbflute.dbmeta.info.UniqueInfo;
import org.seasar.dbflute.dbmeta.name.TableSqlName;

/**
 * The DB meta of PERSISTENT_EVENT_VALUE. (Singleton)
 * @author DBFlute(AutoGenerator)
 */
public class PersistentEventValueDbm extends AbstractDBMeta {

    // ===================================================================================
    //                                                                           Singleton
    //                                                                           =========
    private static final PersistentEventValueDbm _instance = new PersistentEventValueDbm();

    private PersistentEventValueDbm() {
    }

    public static PersistentEventValueDbm getInstance() {
        return _instance;
    }

    // ===================================================================================
    //                                                                       Current DBDef
    //                                                                       =============
    @Override
    public DBDef getCurrentDBDef() {
        return DBCurrent.getInstance().currentDBDef();
    }

    // ===================================================================================
    //                                                                    Property Gateway
    //                                                                    ================
    protected final Map<String, PropertyGateway> _epgMap = newHashMap();
    {
        setupEpg(_epgMap, new EpgId(), "id");
        setupEpg(_epgMap, new EpgEventId(), "eventId");
        setupEpg(_epgMap, new EpgName(), "name");
        setupEpg(_epgMap, new EpgValue(), "value");
        setupEpg(_epgMap, new EpgClassType(), "classType");
        setupEpg(_epgMap, new EpgVersionNo(), "versionNo");
    }

    @Override
    public PropertyGateway findPropertyGateway(final String propertyName) {
        return doFindEpg(_epgMap, propertyName);
    }

    public static class EpgId implements PropertyGateway {
        @Override
        public Object read(final Entity e) {
            return ((PersistentEventValue) e).getId();
        }

        @Override
        public void write(final Entity e, final Object v) {
            ((PersistentEventValue) e).setId(ctl(v));
        }
    }

    public static class EpgEventId implements PropertyGateway {
        @Override
        public Object read(final Entity e) {
            return ((PersistentEventValue) e).getEventId();
        }

        @Override
        public void write(final Entity e, final Object v) {
            ((PersistentEventValue) e).setEventId(ctl(v));
        }
    }

    public static class EpgName implements PropertyGateway {
        @Override
        public Object read(final Entity e) {
            return ((PersistentEventValue) e).getName();
        }

        @Override
        public void write(final Entity e, final Object v) {
            ((PersistentEventValue) e).setName((String) v);
        }
    }

    public static class EpgValue implements PropertyGateway {
        @Override
        public Object read(final Entity e) {
            return ((PersistentEventValue) e).getValue();
        }

        @Override
        public void write(final Entity e, final Object v) {
            ((PersistentEventValue) e).setValue((String) v);
        }
    }

    public static class EpgClassType implements PropertyGateway {
        @Override
        public Object read(final Entity e) {
            return ((PersistentEventValue) e).getClassType();
        }

        @Override
        public void write(final Entity e, final Object v) {
            ((PersistentEventValue) e).setClassType((String) v);
        }
    }

    public static class EpgVersionNo implements PropertyGateway {
        @Override
        public Object read(final Entity e) {
            return ((PersistentEventValue) e).getVersionNo();
        }

        @Override
        public void write(final Entity e, final Object v) {
            ((PersistentEventValue) e).setVersionNo(cti(v));
        }
    }

    // ===================================================================================
    //                                                                          Table Info
    //                                                                          ==========
    protected final String _tableDbName = "PERSISTENT_EVENT_VALUE";

    protected final String _tablePropertyName = "persistentEventValue";

    protected final TableSqlName _tableSqlName = new TableSqlName(
            "PERSISTENT_EVENT_VALUE", _tableDbName);
    {
        _tableSqlName.xacceptFilter(DBFluteConfig.getInstance()
                .getTableSqlNameFilter());
    }

    @Override
    public String getTableDbName() {
        return _tableDbName;
    }

    @Override
    public String getTablePropertyName() {
        return _tablePropertyName;
    }

    @Override
    public TableSqlName getTableSqlName() {
        return _tableSqlName;
    }

    // ===================================================================================
    //                                                                         Column Info
    //                                                                         ===========
    protected final ColumnInfo _columnId = cci(
            "ID",
            "ID",
            null,
            null,
            true,
            "id",
            Long.class,
            true,
            true,
            "BIGINT",
            19,
            0,
            "NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_AAA6453B_C154_4731_83BF_DC95950A790D",
            false, null, null, null, null, null);

    protected final ColumnInfo _columnEventId = cci("EVENT_ID", "EVENT_ID",
            null, null, true, "eventId", Long.class, false, false, "BIGINT",
            19, 0, null, false, null, null, "persistentEvent", null, null);

    protected final ColumnInfo _columnName = cci("NAME", "NAME", null, null,
            true, "name", String.class, false, false, "VARCHAR", 4000, 0, null,
            false, null, null, null, null, null);

    protected final ColumnInfo _columnValue = cci("VALUE", "VALUE", null, null,
            true, "value", String.class, false, false, "VARCHAR", 4000, 0,
            null, false, null, null, null, null, null);

    protected final ColumnInfo _columnClassType = cci("CLASS_TYPE",
            "CLASS_TYPE", null, null, true, "classType", String.class, false,
            false, "VARCHAR", 255, 0, null, false, null, null, null, null, null);

    protected final ColumnInfo _columnVersionNo = cci("VERSION_NO",
            "VERSION_NO", null, null, true, "versionNo", Integer.class, false,
            false, "INTEGER", 10, 0, null, false,
            OptimisticLockType.VERSION_NO, null, null, null, null);

    public ColumnInfo columnId() {
        return _columnId;
    }

    public ColumnInfo columnEventId() {
        return _columnEventId;
    }

    public ColumnInfo columnName() {
        return _columnName;
    }

    public ColumnInfo columnValue() {
        return _columnValue;
    }

    public ColumnInfo columnClassType() {
        return _columnClassType;
    }

    public ColumnInfo columnVersionNo() {
        return _columnVersionNo;
    }

    @Override
    protected List<ColumnInfo> ccil() {
        final List<ColumnInfo> ls = newArrayList();
        ls.add(columnId());
        ls.add(columnEventId());
        ls.add(columnName());
        ls.add(columnValue());
        ls.add(columnClassType());
        ls.add(columnVersionNo());
        return ls;
    }

    {
        initializeInformationResource();
    }

    // ===================================================================================
    //                                                                         Unique Info
    //                                                                         ===========
    // -----------------------------------------------------
    //                                       Primary Element
    //                                       ---------------
    @Override
    public UniqueInfo getPrimaryUniqueInfo() {
        return cpui(columnId());
    }

    @Override
    public boolean hasPrimaryKey() {
        return true;
    }

    @Override
    public boolean hasCompoundPrimaryKey() {
        return false;
    }

    // ===================================================================================
    //                                                                       Relation Info
    //                                                                       =============
    // -----------------------------------------------------
    //                                      Foreign Property
    //                                      ----------------
    public ForeignInfo foreignPersistentEvent() {
        final Map<ColumnInfo, ColumnInfo> map = newLinkedHashMap(
                columnEventId(), PersistentEventDbm.getInstance().columnId());
        return cfi("CONSTRAINT_81", "persistentEvent", this,
                PersistentEventDbm.getInstance(), map, 0, false, false, false,
                false, null, false, "persistentEventValueList");
    }

    // -----------------------------------------------------
    //                                     Referrer Property
    //                                     -----------------

    // ===================================================================================
    //                                                                        Various Info
    //                                                                        ============
    @Override
    public boolean hasIdentity() {
        return true;
    }

    @Override
    public boolean hasVersionNo() {
        return true;
    }

    @Override
    public ColumnInfo getVersionNoColumnInfo() {
        return _columnVersionNo;
    }

    // ===================================================================================
    //                                                                           Type Name
    //                                                                           =========
    @Override
    public String getEntityTypeName() {
        return "org.codelibs.empros.db.exentity.PersistentEventValue";
    }

    @Override
    public String getConditionBeanTypeName() {
        return "org.codelibs.empros.db.cbean.PersistentEventValueCB";
    }

    @Override
    public String getDaoTypeName() {
        return "${glPackageExtendedDao}.PersistentEventValueDao";
    }

    @Override
    public String getBehaviorTypeName() {
        return "org.codelibs.empros.db.exbhv.PersistentEventValueBhv";
    }

    // ===================================================================================
    //                                                                         Object Type
    //                                                                         ===========
    @Override
    public Class<PersistentEventValue> getEntityType() {
        return PersistentEventValue.class;
    }

    // ===================================================================================
    //                                                                     Object Instance
    //                                                                     ===============
    @Override
    public Entity newEntity() {
        return newMyEntity();
    }

    public PersistentEventValue newMyEntity() {
        return new PersistentEventValue();
    }

    // ===================================================================================
    //                                                                   Map Communication
    //                                                                   =================
    @Override
    public void acceptPrimaryKeyMap(final Entity e,
            final Map<String, ? extends Object> m) {
        doAcceptPrimaryKeyMap((PersistentEventValue) e, m);
    }

    @Override
    public void acceptAllColumnMap(final Entity e,
            final Map<String, ? extends Object> m) {
        doAcceptAllColumnMap((PersistentEventValue) e, m);
    }

    @Override
    public Map<String, Object> extractPrimaryKeyMap(final Entity e) {
        return doExtractPrimaryKeyMap(e);
    }

    @Override
    public Map<String, Object> extractAllColumnMap(final Entity e) {
        return doExtractAllColumnMap(e);
    }
}
