package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmFieldType;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetTradeListRealmProxy extends com.xstock.models.GetTradeList
    implements RealmObjectProxy, GetTradeListRealmProxyInterface {

    static final class GetTradeListColumnInfo extends ColumnInfo {

        public final long idIndex;
        public final long nameIndex;
        public final long company_nameIndex;
        public final long parent_icbIndex;
        public final long parent_ttIndex;
        public final long countryIndex;
        public final long marketIndex;
        public final long typeIndex;
        public final long group1Index;
        public final long level1Index;
        public final long level2Index;
        public final long level3Index;
        public final long level4Index;
        public final long activeIndex;
        public final long pic_name1Index;
        public final long pic_name2Index;
        public final long createdIndex;
        public final long modifiedIndex;

        GetTradeListColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(18);
            this.idIndex = getValidColumnIndex(path, table, "GetTradeList", "id");
            indicesMap.put("id", this.idIndex);

            this.nameIndex = getValidColumnIndex(path, table, "GetTradeList", "name");
            indicesMap.put("name", this.nameIndex);

            this.company_nameIndex = getValidColumnIndex(path, table, "GetTradeList", "company_name");
            indicesMap.put("company_name", this.company_nameIndex);

            this.parent_icbIndex = getValidColumnIndex(path, table, "GetTradeList", "parent_icb");
            indicesMap.put("parent_icb", this.parent_icbIndex);

            this.parent_ttIndex = getValidColumnIndex(path, table, "GetTradeList", "parent_tt");
            indicesMap.put("parent_tt", this.parent_ttIndex);

            this.countryIndex = getValidColumnIndex(path, table, "GetTradeList", "country");
            indicesMap.put("country", this.countryIndex);

            this.marketIndex = getValidColumnIndex(path, table, "GetTradeList", "market");
            indicesMap.put("market", this.marketIndex);

            this.typeIndex = getValidColumnIndex(path, table, "GetTradeList", "type");
            indicesMap.put("type", this.typeIndex);

            this.group1Index = getValidColumnIndex(path, table, "GetTradeList", "group1");
            indicesMap.put("group1", this.group1Index);

            this.level1Index = getValidColumnIndex(path, table, "GetTradeList", "level1");
            indicesMap.put("level1", this.level1Index);

            this.level2Index = getValidColumnIndex(path, table, "GetTradeList", "level2");
            indicesMap.put("level2", this.level2Index);

            this.level3Index = getValidColumnIndex(path, table, "GetTradeList", "level3");
            indicesMap.put("level3", this.level3Index);

            this.level4Index = getValidColumnIndex(path, table, "GetTradeList", "level4");
            indicesMap.put("level4", this.level4Index);

            this.activeIndex = getValidColumnIndex(path, table, "GetTradeList", "active");
            indicesMap.put("active", this.activeIndex);

            this.pic_name1Index = getValidColumnIndex(path, table, "GetTradeList", "pic_name1");
            indicesMap.put("pic_name1", this.pic_name1Index);

            this.pic_name2Index = getValidColumnIndex(path, table, "GetTradeList", "pic_name2");
            indicesMap.put("pic_name2", this.pic_name2Index);

            this.createdIndex = getValidColumnIndex(path, table, "GetTradeList", "created");
            indicesMap.put("created", this.createdIndex);

            this.modifiedIndex = getValidColumnIndex(path, table, "GetTradeList", "modified");
            indicesMap.put("modified", this.modifiedIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final GetTradeListColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("name");
        fieldNames.add("company_name");
        fieldNames.add("parent_icb");
        fieldNames.add("parent_tt");
        fieldNames.add("country");
        fieldNames.add("market");
        fieldNames.add("type");
        fieldNames.add("group1");
        fieldNames.add("level1");
        fieldNames.add("level2");
        fieldNames.add("level3");
        fieldNames.add("level4");
        fieldNames.add("active");
        fieldNames.add("pic_name1");
        fieldNames.add("pic_name2");
        fieldNames.add("created");
        fieldNames.add("modified");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    GetTradeListRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (GetTradeListColumnInfo) columnInfo;
        this.proxyState = new ProxyState(com.xstock.models.GetTradeList.class, this);
    }

    @SuppressWarnings("cast")
    public String realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.idIndex);
    }

    public void realmSet$id(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.idIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.idIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameIndex);
    }

    public void realmSet$name(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$company_name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.company_nameIndex);
    }

    public void realmSet$company_name(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.company_nameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.company_nameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$parent_icb() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.parent_icbIndex);
    }

    public void realmSet$parent_icb(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.parent_icbIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.parent_icbIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$parent_tt() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.parent_ttIndex);
    }

    public void realmSet$parent_tt(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.parent_ttIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.parent_ttIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$country() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.countryIndex);
    }

    public void realmSet$country(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.countryIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.countryIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$market() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.marketIndex);
    }

    public void realmSet$market(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.marketIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.marketIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$type() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.typeIndex);
    }

    public void realmSet$type(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.typeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.typeIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$group1() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.group1Index);
    }

    public void realmSet$group1(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.group1Index);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.group1Index, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$level1() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.level1Index);
    }

    public void realmSet$level1(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.level1Index);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.level1Index, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$level2() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.level2Index);
    }

    public void realmSet$level2(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.level2Index);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.level2Index, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$level3() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.level3Index);
    }

    public void realmSet$level3(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.level3Index);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.level3Index, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$level4() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.level4Index);
    }

    public void realmSet$level4(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.level4Index);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.level4Index, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$active() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.activeIndex);
    }

    public void realmSet$active(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.activeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.activeIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$pic_name1() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.pic_name1Index);
    }

    public void realmSet$pic_name1(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.pic_name1Index);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.pic_name1Index, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$pic_name2() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.pic_name2Index);
    }

    public void realmSet$pic_name2(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.pic_name2Index);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.pic_name2Index, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$created() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.createdIndex);
    }

    public void realmSet$created(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.createdIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.createdIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$modified() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.modifiedIndex);
    }

    public void realmSet$modified(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.modifiedIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.modifiedIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_GetTradeList")) {
            Table table = transaction.getTable("class_GetTradeList");
            table.addColumn(RealmFieldType.STRING, "id", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "name", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "company_name", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "parent_icb", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "parent_tt", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "country", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "market", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "type", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "group1", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "level1", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "level2", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "level3", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "level4", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "active", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "pic_name1", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "pic_name2", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "created", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "modified", Table.NULLABLE);
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return transaction.getTable("class_GetTradeList");
    }

    public static GetTradeListColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_GetTradeList")) {
            Table table = transaction.getTable("class_GetTradeList");
            if (table.getColumnCount() != 18) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 18 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 18; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final GetTradeListColumnInfo columnInfo = new GetTradeListColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("id") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'id' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.idIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(),"@PrimaryKey field 'id' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Primary key not defined for field 'id' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("id"))) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Index not defined for field 'id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("name")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("name") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'name' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.nameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'name' is required. Either set @Required to field 'name' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("company_name")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'company_name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("company_name") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'company_name' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.company_nameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'company_name' is required. Either set @Required to field 'company_name' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("parent_icb")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'parent_icb' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("parent_icb") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'parent_icb' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.parent_icbIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'parent_icb' is required. Either set @Required to field 'parent_icb' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("parent_tt")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'parent_tt' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("parent_tt") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'parent_tt' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.parent_ttIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'parent_tt' is required. Either set @Required to field 'parent_tt' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("country")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'country' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("country") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'country' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.countryIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'country' is required. Either set @Required to field 'country' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("market")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'market' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("market") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'market' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.marketIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'market' is required. Either set @Required to field 'market' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("type")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'type' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("type") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'type' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.typeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'type' is required. Either set @Required to field 'type' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("group1")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'group1' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("group1") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'group1' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.group1Index)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'group1' is required. Either set @Required to field 'group1' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("level1")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'level1' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("level1") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'level1' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.level1Index)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'level1' is required. Either set @Required to field 'level1' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("level2")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'level2' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("level2") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'level2' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.level2Index)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'level2' is required. Either set @Required to field 'level2' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("level3")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'level3' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("level3") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'level3' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.level3Index)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'level3' is required. Either set @Required to field 'level3' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("level4")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'level4' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("level4") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'level4' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.level4Index)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'level4' is required. Either set @Required to field 'level4' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("active")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'active' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("active") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'active' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.activeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'active' is required. Either set @Required to field 'active' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("pic_name1")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'pic_name1' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("pic_name1") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'pic_name1' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.pic_name1Index)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'pic_name1' is required. Either set @Required to field 'pic_name1' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("pic_name2")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'pic_name2' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("pic_name2") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'pic_name2' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.pic_name2Index)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'pic_name2' is required. Either set @Required to field 'pic_name2' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("created")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'created' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("created") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'created' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.createdIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'created' is required. Either set @Required to field 'created' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("modified")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'modified' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("modified") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'modified' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.modifiedIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'modified' is required. Either set @Required to field 'modified' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The GetTradeList class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_GetTradeList";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.xstock.models.GetTradeList createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        com.xstock.models.GetTradeList obj = null;
        if (update) {
            Table table = realm.getTable(com.xstock.models.GetTradeList.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = TableOrView.NO_MATCH;
            if (json.isNull("id")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("id"));
            }
            if (rowIndex != TableOrView.NO_MATCH) {
                obj = new io.realm.GetTradeListRealmProxy(realm.schema.getColumnInfo(com.xstock.models.GetTradeList.class));
                ((RealmObjectProxy)obj).realmGet$proxyState().setRealm$realm(realm);
                ((RealmObjectProxy)obj).realmGet$proxyState().setRow$realm(table.getUncheckedRow(rowIndex));
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.GetTradeListRealmProxy) realm.createObject(com.xstock.models.GetTradeList.class, null);
                } else {
                    obj = (io.realm.GetTradeListRealmProxy) realm.createObject(com.xstock.models.GetTradeList.class, json.getString("id"));
                }
            } else {
                obj = (io.realm.GetTradeListRealmProxy) realm.createObject(com.xstock.models.GetTradeList.class);
            }
        }
        if (json.has("id")) {
            if (json.isNull("id")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$id(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$id((String) json.getString("id"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$name(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("company_name")) {
            if (json.isNull("company_name")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$company_name(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$company_name((String) json.getString("company_name"));
            }
        }
        if (json.has("parent_icb")) {
            if (json.isNull("parent_icb")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$parent_icb(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$parent_icb((String) json.getString("parent_icb"));
            }
        }
        if (json.has("parent_tt")) {
            if (json.isNull("parent_tt")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$parent_tt(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$parent_tt((String) json.getString("parent_tt"));
            }
        }
        if (json.has("country")) {
            if (json.isNull("country")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$country(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$country((String) json.getString("country"));
            }
        }
        if (json.has("market")) {
            if (json.isNull("market")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$market(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$market((String) json.getString("market"));
            }
        }
        if (json.has("type")) {
            if (json.isNull("type")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$type(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$type((String) json.getString("type"));
            }
        }
        if (json.has("group1")) {
            if (json.isNull("group1")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$group1(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$group1((String) json.getString("group1"));
            }
        }
        if (json.has("level1")) {
            if (json.isNull("level1")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$level1(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$level1((String) json.getString("level1"));
            }
        }
        if (json.has("level2")) {
            if (json.isNull("level2")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$level2(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$level2((String) json.getString("level2"));
            }
        }
        if (json.has("level3")) {
            if (json.isNull("level3")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$level3(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$level3((String) json.getString("level3"));
            }
        }
        if (json.has("level4")) {
            if (json.isNull("level4")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$level4(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$level4((String) json.getString("level4"));
            }
        }
        if (json.has("active")) {
            if (json.isNull("active")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$active(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$active((String) json.getString("active"));
            }
        }
        if (json.has("pic_name1")) {
            if (json.isNull("pic_name1")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$pic_name1(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$pic_name1((String) json.getString("pic_name1"));
            }
        }
        if (json.has("pic_name2")) {
            if (json.isNull("pic_name2")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$pic_name2(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$pic_name2((String) json.getString("pic_name2"));
            }
        }
        if (json.has("created")) {
            if (json.isNull("created")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$created(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$created((String) json.getString("created"));
            }
        }
        if (json.has("modified")) {
            if (json.isNull("modified")) {
                ((GetTradeListRealmProxyInterface) obj).realmSet$modified(null);
            } else {
                ((GetTradeListRealmProxyInterface) obj).realmSet$modified((String) json.getString("modified"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static com.xstock.models.GetTradeList createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.xstock.models.GetTradeList obj = realm.createObject(com.xstock.models.GetTradeList.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$id(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$id((String) reader.nextString());
                }
            } else if (name.equals("name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$name(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$name((String) reader.nextString());
                }
            } else if (name.equals("company_name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$company_name(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$company_name((String) reader.nextString());
                }
            } else if (name.equals("parent_icb")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$parent_icb(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$parent_icb((String) reader.nextString());
                }
            } else if (name.equals("parent_tt")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$parent_tt(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$parent_tt((String) reader.nextString());
                }
            } else if (name.equals("country")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$country(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$country((String) reader.nextString());
                }
            } else if (name.equals("market")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$market(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$market((String) reader.nextString());
                }
            } else if (name.equals("type")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$type(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$type((String) reader.nextString());
                }
            } else if (name.equals("group1")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$group1(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$group1((String) reader.nextString());
                }
            } else if (name.equals("level1")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$level1(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$level1((String) reader.nextString());
                }
            } else if (name.equals("level2")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$level2(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$level2((String) reader.nextString());
                }
            } else if (name.equals("level3")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$level3(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$level3((String) reader.nextString());
                }
            } else if (name.equals("level4")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$level4(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$level4((String) reader.nextString());
                }
            } else if (name.equals("active")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$active(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$active((String) reader.nextString());
                }
            } else if (name.equals("pic_name1")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$pic_name1(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$pic_name1((String) reader.nextString());
                }
            } else if (name.equals("pic_name2")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$pic_name2(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$pic_name2((String) reader.nextString());
                }
            } else if (name.equals("created")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$created(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$created((String) reader.nextString());
                }
            } else if (name.equals("modified")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetTradeListRealmProxyInterface) obj).realmSet$modified(null);
                } else {
                    ((GetTradeListRealmProxyInterface) obj).realmSet$modified((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static com.xstock.models.GetTradeList copyOrUpdate(Realm realm, com.xstock.models.GetTradeList object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.xstock.models.GetTradeList) cachedRealmObject;
        } else {
            com.xstock.models.GetTradeList realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.xstock.models.GetTradeList.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((GetTradeListRealmProxyInterface) object).realmGet$id();
                long rowIndex = TableOrView.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != TableOrView.NO_MATCH) {
                    realmObject = new io.realm.GetTradeListRealmProxy(realm.schema.getColumnInfo(com.xstock.models.GetTradeList.class));
                    ((RealmObjectProxy)realmObject).realmGet$proxyState().setRealm$realm(realm);
                    ((RealmObjectProxy)realmObject).realmGet$proxyState().setRow$realm(table.getUncheckedRow(rowIndex));
                    cache.put(object, (RealmObjectProxy) realmObject);
                } else {
                    canUpdate = false;
                }
            }

            if (canUpdate) {
                return update(realm, realmObject, object, cache);
            } else {
                return copy(realm, object, update, cache);
            }
        }
    }

    public static com.xstock.models.GetTradeList copy(Realm realm, com.xstock.models.GetTradeList newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.xstock.models.GetTradeList) cachedRealmObject;
        } else {
            com.xstock.models.GetTradeList realmObject = realm.createObject(com.xstock.models.GetTradeList.class, ((GetTradeListRealmProxyInterface) newObject).realmGet$id());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$id(((GetTradeListRealmProxyInterface) newObject).realmGet$id());
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$name(((GetTradeListRealmProxyInterface) newObject).realmGet$name());
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$company_name(((GetTradeListRealmProxyInterface) newObject).realmGet$company_name());
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$parent_icb(((GetTradeListRealmProxyInterface) newObject).realmGet$parent_icb());
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$parent_tt(((GetTradeListRealmProxyInterface) newObject).realmGet$parent_tt());
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$country(((GetTradeListRealmProxyInterface) newObject).realmGet$country());
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$market(((GetTradeListRealmProxyInterface) newObject).realmGet$market());
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$type(((GetTradeListRealmProxyInterface) newObject).realmGet$type());
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$group1(((GetTradeListRealmProxyInterface) newObject).realmGet$group1());
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$level1(((GetTradeListRealmProxyInterface) newObject).realmGet$level1());
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$level2(((GetTradeListRealmProxyInterface) newObject).realmGet$level2());
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$level3(((GetTradeListRealmProxyInterface) newObject).realmGet$level3());
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$level4(((GetTradeListRealmProxyInterface) newObject).realmGet$level4());
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$active(((GetTradeListRealmProxyInterface) newObject).realmGet$active());
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$pic_name1(((GetTradeListRealmProxyInterface) newObject).realmGet$pic_name1());
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$pic_name2(((GetTradeListRealmProxyInterface) newObject).realmGet$pic_name2());
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$created(((GetTradeListRealmProxyInterface) newObject).realmGet$created());
            ((GetTradeListRealmProxyInterface) realmObject).realmSet$modified(((GetTradeListRealmProxyInterface) newObject).realmGet$modified());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.xstock.models.GetTradeList object, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.xstock.models.GetTradeList.class);
        long tableNativePtr = table.getNativeTablePointer();
        GetTradeListColumnInfo columnInfo = (GetTradeListColumnInfo) realm.schema.getColumnInfo(com.xstock.models.GetTradeList.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$id = ((GetTradeListRealmProxyInterface)object).realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id);
        }
        String realmGet$name = ((GetTradeListRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name);
        }
        String realmGet$company_name = ((GetTradeListRealmProxyInterface)object).realmGet$company_name();
        if (realmGet$company_name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.company_nameIndex, rowIndex, realmGet$company_name);
        }
        String realmGet$parent_icb = ((GetTradeListRealmProxyInterface)object).realmGet$parent_icb();
        if (realmGet$parent_icb != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parent_icbIndex, rowIndex, realmGet$parent_icb);
        }
        String realmGet$parent_tt = ((GetTradeListRealmProxyInterface)object).realmGet$parent_tt();
        if (realmGet$parent_tt != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parent_ttIndex, rowIndex, realmGet$parent_tt);
        }
        String realmGet$country = ((GetTradeListRealmProxyInterface)object).realmGet$country();
        if (realmGet$country != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.countryIndex, rowIndex, realmGet$country);
        }
        String realmGet$market = ((GetTradeListRealmProxyInterface)object).realmGet$market();
        if (realmGet$market != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.marketIndex, rowIndex, realmGet$market);
        }
        String realmGet$type = ((GetTradeListRealmProxyInterface)object).realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type);
        }
        String realmGet$group1 = ((GetTradeListRealmProxyInterface)object).realmGet$group1();
        if (realmGet$group1 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.group1Index, rowIndex, realmGet$group1);
        }
        String realmGet$level1 = ((GetTradeListRealmProxyInterface)object).realmGet$level1();
        if (realmGet$level1 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.level1Index, rowIndex, realmGet$level1);
        }
        String realmGet$level2 = ((GetTradeListRealmProxyInterface)object).realmGet$level2();
        if (realmGet$level2 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.level2Index, rowIndex, realmGet$level2);
        }
        String realmGet$level3 = ((GetTradeListRealmProxyInterface)object).realmGet$level3();
        if (realmGet$level3 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.level3Index, rowIndex, realmGet$level3);
        }
        String realmGet$level4 = ((GetTradeListRealmProxyInterface)object).realmGet$level4();
        if (realmGet$level4 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.level4Index, rowIndex, realmGet$level4);
        }
        String realmGet$active = ((GetTradeListRealmProxyInterface)object).realmGet$active();
        if (realmGet$active != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.activeIndex, rowIndex, realmGet$active);
        }
        String realmGet$pic_name1 = ((GetTradeListRealmProxyInterface)object).realmGet$pic_name1();
        if (realmGet$pic_name1 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.pic_name1Index, rowIndex, realmGet$pic_name1);
        }
        String realmGet$pic_name2 = ((GetTradeListRealmProxyInterface)object).realmGet$pic_name2();
        if (realmGet$pic_name2 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.pic_name2Index, rowIndex, realmGet$pic_name2);
        }
        String realmGet$created = ((GetTradeListRealmProxyInterface)object).realmGet$created();
        if (realmGet$created != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.createdIndex, rowIndex, realmGet$created);
        }
        String realmGet$modified = ((GetTradeListRealmProxyInterface)object).realmGet$modified();
        if (realmGet$modified != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.modifiedIndex, rowIndex, realmGet$modified);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.xstock.models.GetTradeList.class);
        long tableNativePtr = table.getNativeTablePointer();
        GetTradeListColumnInfo columnInfo = (GetTradeListColumnInfo) realm.schema.getColumnInfo(com.xstock.models.GetTradeList.class);
        com.xstock.models.GetTradeList object = null;
        while (objects.hasNext()) {
            object = (com.xstock.models.GetTradeList) objects.next();
            if(!cache.containsKey(object)) {
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$id = ((GetTradeListRealmProxyInterface)object).realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id);
                }
                String realmGet$name = ((GetTradeListRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name);
                }
                String realmGet$company_name = ((GetTradeListRealmProxyInterface)object).realmGet$company_name();
                if (realmGet$company_name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.company_nameIndex, rowIndex, realmGet$company_name);
                }
                String realmGet$parent_icb = ((GetTradeListRealmProxyInterface)object).realmGet$parent_icb();
                if (realmGet$parent_icb != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.parent_icbIndex, rowIndex, realmGet$parent_icb);
                }
                String realmGet$parent_tt = ((GetTradeListRealmProxyInterface)object).realmGet$parent_tt();
                if (realmGet$parent_tt != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.parent_ttIndex, rowIndex, realmGet$parent_tt);
                }
                String realmGet$country = ((GetTradeListRealmProxyInterface)object).realmGet$country();
                if (realmGet$country != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.countryIndex, rowIndex, realmGet$country);
                }
                String realmGet$market = ((GetTradeListRealmProxyInterface)object).realmGet$market();
                if (realmGet$market != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.marketIndex, rowIndex, realmGet$market);
                }
                String realmGet$type = ((GetTradeListRealmProxyInterface)object).realmGet$type();
                if (realmGet$type != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type);
                }
                String realmGet$group1 = ((GetTradeListRealmProxyInterface)object).realmGet$group1();
                if (realmGet$group1 != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.group1Index, rowIndex, realmGet$group1);
                }
                String realmGet$level1 = ((GetTradeListRealmProxyInterface)object).realmGet$level1();
                if (realmGet$level1 != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.level1Index, rowIndex, realmGet$level1);
                }
                String realmGet$level2 = ((GetTradeListRealmProxyInterface)object).realmGet$level2();
                if (realmGet$level2 != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.level2Index, rowIndex, realmGet$level2);
                }
                String realmGet$level3 = ((GetTradeListRealmProxyInterface)object).realmGet$level3();
                if (realmGet$level3 != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.level3Index, rowIndex, realmGet$level3);
                }
                String realmGet$level4 = ((GetTradeListRealmProxyInterface)object).realmGet$level4();
                if (realmGet$level4 != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.level4Index, rowIndex, realmGet$level4);
                }
                String realmGet$active = ((GetTradeListRealmProxyInterface)object).realmGet$active();
                if (realmGet$active != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.activeIndex, rowIndex, realmGet$active);
                }
                String realmGet$pic_name1 = ((GetTradeListRealmProxyInterface)object).realmGet$pic_name1();
                if (realmGet$pic_name1 != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.pic_name1Index, rowIndex, realmGet$pic_name1);
                }
                String realmGet$pic_name2 = ((GetTradeListRealmProxyInterface)object).realmGet$pic_name2();
                if (realmGet$pic_name2 != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.pic_name2Index, rowIndex, realmGet$pic_name2);
                }
                String realmGet$created = ((GetTradeListRealmProxyInterface)object).realmGet$created();
                if (realmGet$created != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.createdIndex, rowIndex, realmGet$created);
                }
                String realmGet$modified = ((GetTradeListRealmProxyInterface)object).realmGet$modified();
                if (realmGet$modified != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.modifiedIndex, rowIndex, realmGet$modified);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.xstock.models.GetTradeList object, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.xstock.models.GetTradeList.class);
        long tableNativePtr = table.getNativeTablePointer();
        GetTradeListColumnInfo columnInfo = (GetTradeListColumnInfo) realm.schema.getColumnInfo(com.xstock.models.GetTradeList.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((GetTradeListRealmProxyInterface) object).realmGet$id();
        long rowIndex = TableOrView.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = table.findFirstNull(pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
            if (primaryKeyValue != null) {
                Table.nativeSetString(tableNativePtr, pkColumnIndex, rowIndex, (String)primaryKeyValue);
            }
        }
        cache.put(object, rowIndex);
        String realmGet$id = ((GetTradeListRealmProxyInterface)object).realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex);
        }
        String realmGet$name = ((GetTradeListRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex);
        }
        String realmGet$company_name = ((GetTradeListRealmProxyInterface)object).realmGet$company_name();
        if (realmGet$company_name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.company_nameIndex, rowIndex, realmGet$company_name);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.company_nameIndex, rowIndex);
        }
        String realmGet$parent_icb = ((GetTradeListRealmProxyInterface)object).realmGet$parent_icb();
        if (realmGet$parent_icb != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parent_icbIndex, rowIndex, realmGet$parent_icb);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.parent_icbIndex, rowIndex);
        }
        String realmGet$parent_tt = ((GetTradeListRealmProxyInterface)object).realmGet$parent_tt();
        if (realmGet$parent_tt != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parent_ttIndex, rowIndex, realmGet$parent_tt);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.parent_ttIndex, rowIndex);
        }
        String realmGet$country = ((GetTradeListRealmProxyInterface)object).realmGet$country();
        if (realmGet$country != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.countryIndex, rowIndex, realmGet$country);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.countryIndex, rowIndex);
        }
        String realmGet$market = ((GetTradeListRealmProxyInterface)object).realmGet$market();
        if (realmGet$market != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.marketIndex, rowIndex, realmGet$market);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.marketIndex, rowIndex);
        }
        String realmGet$type = ((GetTradeListRealmProxyInterface)object).realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex);
        }
        String realmGet$group1 = ((GetTradeListRealmProxyInterface)object).realmGet$group1();
        if (realmGet$group1 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.group1Index, rowIndex, realmGet$group1);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.group1Index, rowIndex);
        }
        String realmGet$level1 = ((GetTradeListRealmProxyInterface)object).realmGet$level1();
        if (realmGet$level1 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.level1Index, rowIndex, realmGet$level1);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.level1Index, rowIndex);
        }
        String realmGet$level2 = ((GetTradeListRealmProxyInterface)object).realmGet$level2();
        if (realmGet$level2 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.level2Index, rowIndex, realmGet$level2);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.level2Index, rowIndex);
        }
        String realmGet$level3 = ((GetTradeListRealmProxyInterface)object).realmGet$level3();
        if (realmGet$level3 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.level3Index, rowIndex, realmGet$level3);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.level3Index, rowIndex);
        }
        String realmGet$level4 = ((GetTradeListRealmProxyInterface)object).realmGet$level4();
        if (realmGet$level4 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.level4Index, rowIndex, realmGet$level4);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.level4Index, rowIndex);
        }
        String realmGet$active = ((GetTradeListRealmProxyInterface)object).realmGet$active();
        if (realmGet$active != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.activeIndex, rowIndex, realmGet$active);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.activeIndex, rowIndex);
        }
        String realmGet$pic_name1 = ((GetTradeListRealmProxyInterface)object).realmGet$pic_name1();
        if (realmGet$pic_name1 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.pic_name1Index, rowIndex, realmGet$pic_name1);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.pic_name1Index, rowIndex);
        }
        String realmGet$pic_name2 = ((GetTradeListRealmProxyInterface)object).realmGet$pic_name2();
        if (realmGet$pic_name2 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.pic_name2Index, rowIndex, realmGet$pic_name2);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.pic_name2Index, rowIndex);
        }
        String realmGet$created = ((GetTradeListRealmProxyInterface)object).realmGet$created();
        if (realmGet$created != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.createdIndex, rowIndex, realmGet$created);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.createdIndex, rowIndex);
        }
        String realmGet$modified = ((GetTradeListRealmProxyInterface)object).realmGet$modified();
        if (realmGet$modified != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.modifiedIndex, rowIndex, realmGet$modified);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.modifiedIndex, rowIndex);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.xstock.models.GetTradeList.class);
        long tableNativePtr = table.getNativeTablePointer();
        GetTradeListColumnInfo columnInfo = (GetTradeListColumnInfo) realm.schema.getColumnInfo(com.xstock.models.GetTradeList.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.xstock.models.GetTradeList object = null;
        while (objects.hasNext()) {
            object = (com.xstock.models.GetTradeList) objects.next();
            if(!cache.containsKey(object)) {
                String primaryKeyValue = ((GetTradeListRealmProxyInterface) object).realmGet$id();
                long rowIndex = TableOrView.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                    if (primaryKeyValue != null) {
                        Table.nativeSetString(tableNativePtr, pkColumnIndex, rowIndex, ((GetTradeListRealmProxyInterface) object).realmGet$id());
                    }
                }
                cache.put(object, rowIndex);
                String realmGet$id = ((GetTradeListRealmProxyInterface)object).realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex);
                }
                String realmGet$name = ((GetTradeListRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex);
                }
                String realmGet$company_name = ((GetTradeListRealmProxyInterface)object).realmGet$company_name();
                if (realmGet$company_name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.company_nameIndex, rowIndex, realmGet$company_name);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.company_nameIndex, rowIndex);
                }
                String realmGet$parent_icb = ((GetTradeListRealmProxyInterface)object).realmGet$parent_icb();
                if (realmGet$parent_icb != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.parent_icbIndex, rowIndex, realmGet$parent_icb);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.parent_icbIndex, rowIndex);
                }
                String realmGet$parent_tt = ((GetTradeListRealmProxyInterface)object).realmGet$parent_tt();
                if (realmGet$parent_tt != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.parent_ttIndex, rowIndex, realmGet$parent_tt);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.parent_ttIndex, rowIndex);
                }
                String realmGet$country = ((GetTradeListRealmProxyInterface)object).realmGet$country();
                if (realmGet$country != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.countryIndex, rowIndex, realmGet$country);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.countryIndex, rowIndex);
                }
                String realmGet$market = ((GetTradeListRealmProxyInterface)object).realmGet$market();
                if (realmGet$market != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.marketIndex, rowIndex, realmGet$market);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.marketIndex, rowIndex);
                }
                String realmGet$type = ((GetTradeListRealmProxyInterface)object).realmGet$type();
                if (realmGet$type != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex);
                }
                String realmGet$group1 = ((GetTradeListRealmProxyInterface)object).realmGet$group1();
                if (realmGet$group1 != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.group1Index, rowIndex, realmGet$group1);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.group1Index, rowIndex);
                }
                String realmGet$level1 = ((GetTradeListRealmProxyInterface)object).realmGet$level1();
                if (realmGet$level1 != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.level1Index, rowIndex, realmGet$level1);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.level1Index, rowIndex);
                }
                String realmGet$level2 = ((GetTradeListRealmProxyInterface)object).realmGet$level2();
                if (realmGet$level2 != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.level2Index, rowIndex, realmGet$level2);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.level2Index, rowIndex);
                }
                String realmGet$level3 = ((GetTradeListRealmProxyInterface)object).realmGet$level3();
                if (realmGet$level3 != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.level3Index, rowIndex, realmGet$level3);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.level3Index, rowIndex);
                }
                String realmGet$level4 = ((GetTradeListRealmProxyInterface)object).realmGet$level4();
                if (realmGet$level4 != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.level4Index, rowIndex, realmGet$level4);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.level4Index, rowIndex);
                }
                String realmGet$active = ((GetTradeListRealmProxyInterface)object).realmGet$active();
                if (realmGet$active != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.activeIndex, rowIndex, realmGet$active);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.activeIndex, rowIndex);
                }
                String realmGet$pic_name1 = ((GetTradeListRealmProxyInterface)object).realmGet$pic_name1();
                if (realmGet$pic_name1 != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.pic_name1Index, rowIndex, realmGet$pic_name1);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.pic_name1Index, rowIndex);
                }
                String realmGet$pic_name2 = ((GetTradeListRealmProxyInterface)object).realmGet$pic_name2();
                if (realmGet$pic_name2 != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.pic_name2Index, rowIndex, realmGet$pic_name2);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.pic_name2Index, rowIndex);
                }
                String realmGet$created = ((GetTradeListRealmProxyInterface)object).realmGet$created();
                if (realmGet$created != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.createdIndex, rowIndex, realmGet$created);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.createdIndex, rowIndex);
                }
                String realmGet$modified = ((GetTradeListRealmProxyInterface)object).realmGet$modified();
                if (realmGet$modified != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.modifiedIndex, rowIndex, realmGet$modified);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.modifiedIndex, rowIndex);
                }
            }
        }
    }

    public static com.xstock.models.GetTradeList createDetachedCopy(com.xstock.models.GetTradeList realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.xstock.models.GetTradeList unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.xstock.models.GetTradeList)cachedObject.object;
            } else {
                unmanagedObject = (com.xstock.models.GetTradeList)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.xstock.models.GetTradeList();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$id(((GetTradeListRealmProxyInterface) realmObject).realmGet$id());
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$name(((GetTradeListRealmProxyInterface) realmObject).realmGet$name());
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$company_name(((GetTradeListRealmProxyInterface) realmObject).realmGet$company_name());
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$parent_icb(((GetTradeListRealmProxyInterface) realmObject).realmGet$parent_icb());
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$parent_tt(((GetTradeListRealmProxyInterface) realmObject).realmGet$parent_tt());
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$country(((GetTradeListRealmProxyInterface) realmObject).realmGet$country());
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$market(((GetTradeListRealmProxyInterface) realmObject).realmGet$market());
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$type(((GetTradeListRealmProxyInterface) realmObject).realmGet$type());
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$group1(((GetTradeListRealmProxyInterface) realmObject).realmGet$group1());
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$level1(((GetTradeListRealmProxyInterface) realmObject).realmGet$level1());
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$level2(((GetTradeListRealmProxyInterface) realmObject).realmGet$level2());
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$level3(((GetTradeListRealmProxyInterface) realmObject).realmGet$level3());
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$level4(((GetTradeListRealmProxyInterface) realmObject).realmGet$level4());
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$active(((GetTradeListRealmProxyInterface) realmObject).realmGet$active());
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$pic_name1(((GetTradeListRealmProxyInterface) realmObject).realmGet$pic_name1());
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$pic_name2(((GetTradeListRealmProxyInterface) realmObject).realmGet$pic_name2());
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$created(((GetTradeListRealmProxyInterface) realmObject).realmGet$created());
        ((GetTradeListRealmProxyInterface) unmanagedObject).realmSet$modified(((GetTradeListRealmProxyInterface) realmObject).realmGet$modified());
        return unmanagedObject;
    }

    static com.xstock.models.GetTradeList update(Realm realm, com.xstock.models.GetTradeList realmObject, com.xstock.models.GetTradeList newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((GetTradeListRealmProxyInterface) realmObject).realmSet$name(((GetTradeListRealmProxyInterface) newObject).realmGet$name());
        ((GetTradeListRealmProxyInterface) realmObject).realmSet$company_name(((GetTradeListRealmProxyInterface) newObject).realmGet$company_name());
        ((GetTradeListRealmProxyInterface) realmObject).realmSet$parent_icb(((GetTradeListRealmProxyInterface) newObject).realmGet$parent_icb());
        ((GetTradeListRealmProxyInterface) realmObject).realmSet$parent_tt(((GetTradeListRealmProxyInterface) newObject).realmGet$parent_tt());
        ((GetTradeListRealmProxyInterface) realmObject).realmSet$country(((GetTradeListRealmProxyInterface) newObject).realmGet$country());
        ((GetTradeListRealmProxyInterface) realmObject).realmSet$market(((GetTradeListRealmProxyInterface) newObject).realmGet$market());
        ((GetTradeListRealmProxyInterface) realmObject).realmSet$type(((GetTradeListRealmProxyInterface) newObject).realmGet$type());
        ((GetTradeListRealmProxyInterface) realmObject).realmSet$group1(((GetTradeListRealmProxyInterface) newObject).realmGet$group1());
        ((GetTradeListRealmProxyInterface) realmObject).realmSet$level1(((GetTradeListRealmProxyInterface) newObject).realmGet$level1());
        ((GetTradeListRealmProxyInterface) realmObject).realmSet$level2(((GetTradeListRealmProxyInterface) newObject).realmGet$level2());
        ((GetTradeListRealmProxyInterface) realmObject).realmSet$level3(((GetTradeListRealmProxyInterface) newObject).realmGet$level3());
        ((GetTradeListRealmProxyInterface) realmObject).realmSet$level4(((GetTradeListRealmProxyInterface) newObject).realmGet$level4());
        ((GetTradeListRealmProxyInterface) realmObject).realmSet$active(((GetTradeListRealmProxyInterface) newObject).realmGet$active());
        ((GetTradeListRealmProxyInterface) realmObject).realmSet$pic_name1(((GetTradeListRealmProxyInterface) newObject).realmGet$pic_name1());
        ((GetTradeListRealmProxyInterface) realmObject).realmSet$pic_name2(((GetTradeListRealmProxyInterface) newObject).realmGet$pic_name2());
        ((GetTradeListRealmProxyInterface) realmObject).realmSet$created(((GetTradeListRealmProxyInterface) newObject).realmGet$created());
        ((GetTradeListRealmProxyInterface) realmObject).realmSet$modified(((GetTradeListRealmProxyInterface) newObject).realmGet$modified());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("GetTradeList = [");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{company_name:");
        stringBuilder.append(realmGet$company_name() != null ? realmGet$company_name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{parent_icb:");
        stringBuilder.append(realmGet$parent_icb() != null ? realmGet$parent_icb() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{parent_tt:");
        stringBuilder.append(realmGet$parent_tt() != null ? realmGet$parent_tt() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{country:");
        stringBuilder.append(realmGet$country() != null ? realmGet$country() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{market:");
        stringBuilder.append(realmGet$market() != null ? realmGet$market() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{type:");
        stringBuilder.append(realmGet$type() != null ? realmGet$type() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{group1:");
        stringBuilder.append(realmGet$group1() != null ? realmGet$group1() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{level1:");
        stringBuilder.append(realmGet$level1() != null ? realmGet$level1() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{level2:");
        stringBuilder.append(realmGet$level2() != null ? realmGet$level2() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{level3:");
        stringBuilder.append(realmGet$level3() != null ? realmGet$level3() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{level4:");
        stringBuilder.append(realmGet$level4() != null ? realmGet$level4() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{active:");
        stringBuilder.append(realmGet$active() != null ? realmGet$active() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{pic_name1:");
        stringBuilder.append(realmGet$pic_name1() != null ? realmGet$pic_name1() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{pic_name2:");
        stringBuilder.append(realmGet$pic_name2() != null ? realmGet$pic_name2() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{created:");
        stringBuilder.append(realmGet$created() != null ? realmGet$created() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{modified:");
        stringBuilder.append(realmGet$modified() != null ? realmGet$modified() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetTradeListRealmProxy aGetTradeList = (GetTradeListRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aGetTradeList.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aGetTradeList.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aGetTradeList.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
