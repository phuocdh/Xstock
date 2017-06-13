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

public class GetUserProductRealmProxy extends com.xstock.models.GetUserProduct
    implements RealmObjectProxy, GetUserProductRealmProxyInterface {

    static final class GetUserProductColumnInfo extends ColumnInfo {

        public final long user_idIndex;
        public final long product_idIndex;
        public final long product_nameIndex;

        GetUserProductColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(3);
            this.user_idIndex = getValidColumnIndex(path, table, "GetUserProduct", "user_id");
            indicesMap.put("user_id", this.user_idIndex);

            this.product_idIndex = getValidColumnIndex(path, table, "GetUserProduct", "product_id");
            indicesMap.put("product_id", this.product_idIndex);

            this.product_nameIndex = getValidColumnIndex(path, table, "GetUserProduct", "product_name");
            indicesMap.put("product_name", this.product_nameIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final GetUserProductColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("user_id");
        fieldNames.add("product_id");
        fieldNames.add("product_name");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    GetUserProductRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (GetUserProductColumnInfo) columnInfo;
        this.proxyState = new ProxyState(com.xstock.models.GetUserProduct.class, this);
    }

    @SuppressWarnings("cast")
    public String realmGet$user_id() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.user_idIndex);
    }

    public void realmSet$user_id(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.user_idIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.user_idIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$product_id() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.product_idIndex);
    }

    public void realmSet$product_id(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.product_idIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.product_idIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$product_name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.product_nameIndex);
    }

    public void realmSet$product_name(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.product_nameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.product_nameIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_GetUserProduct")) {
            Table table = transaction.getTable("class_GetUserProduct");
            table.addColumn(RealmFieldType.STRING, "user_id", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "product_id", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "product_name", Table.NULLABLE);
            table.addSearchIndex(table.getColumnIndex("user_id"));
            table.setPrimaryKey("user_id");
            return table;
        }
        return transaction.getTable("class_GetUserProduct");
    }

    public static GetUserProductColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_GetUserProduct")) {
            Table table = transaction.getTable("class_GetUserProduct");
            if (table.getColumnCount() != 3) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 3 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 3; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final GetUserProductColumnInfo columnInfo = new GetUserProductColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("user_id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'user_id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("user_id") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'user_id' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.user_idIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(),"@PrimaryKey field 'user_id' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("user_id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Primary key not defined for field 'user_id' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("user_id"))) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Index not defined for field 'user_id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("product_id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'product_id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("product_id") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'product_id' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.product_idIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'product_id' is required. Either set @Required to field 'product_id' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("product_name")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'product_name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("product_name") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'product_name' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.product_nameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'product_name' is required. Either set @Required to field 'product_name' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The GetUserProduct class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_GetUserProduct";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.xstock.models.GetUserProduct createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        com.xstock.models.GetUserProduct obj = null;
        if (update) {
            Table table = realm.getTable(com.xstock.models.GetUserProduct.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = TableOrView.NO_MATCH;
            if (json.isNull("user_id")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("user_id"));
            }
            if (rowIndex != TableOrView.NO_MATCH) {
                obj = new io.realm.GetUserProductRealmProxy(realm.schema.getColumnInfo(com.xstock.models.GetUserProduct.class));
                ((RealmObjectProxy)obj).realmGet$proxyState().setRealm$realm(realm);
                ((RealmObjectProxy)obj).realmGet$proxyState().setRow$realm(table.getUncheckedRow(rowIndex));
            }
        }
        if (obj == null) {
            if (json.has("user_id")) {
                if (json.isNull("user_id")) {
                    obj = (io.realm.GetUserProductRealmProxy) realm.createObject(com.xstock.models.GetUserProduct.class, null);
                } else {
                    obj = (io.realm.GetUserProductRealmProxy) realm.createObject(com.xstock.models.GetUserProduct.class, json.getString("user_id"));
                }
            } else {
                obj = (io.realm.GetUserProductRealmProxy) realm.createObject(com.xstock.models.GetUserProduct.class);
            }
        }
        if (json.has("user_id")) {
            if (json.isNull("user_id")) {
                ((GetUserProductRealmProxyInterface) obj).realmSet$user_id(null);
            } else {
                ((GetUserProductRealmProxyInterface) obj).realmSet$user_id((String) json.getString("user_id"));
            }
        }
        if (json.has("product_id")) {
            if (json.isNull("product_id")) {
                ((GetUserProductRealmProxyInterface) obj).realmSet$product_id(null);
            } else {
                ((GetUserProductRealmProxyInterface) obj).realmSet$product_id((String) json.getString("product_id"));
            }
        }
        if (json.has("product_name")) {
            if (json.isNull("product_name")) {
                ((GetUserProductRealmProxyInterface) obj).realmSet$product_name(null);
            } else {
                ((GetUserProductRealmProxyInterface) obj).realmSet$product_name((String) json.getString("product_name"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static com.xstock.models.GetUserProduct createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.xstock.models.GetUserProduct obj = realm.createObject(com.xstock.models.GetUserProduct.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("user_id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetUserProductRealmProxyInterface) obj).realmSet$user_id(null);
                } else {
                    ((GetUserProductRealmProxyInterface) obj).realmSet$user_id((String) reader.nextString());
                }
            } else if (name.equals("product_id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetUserProductRealmProxyInterface) obj).realmSet$product_id(null);
                } else {
                    ((GetUserProductRealmProxyInterface) obj).realmSet$product_id((String) reader.nextString());
                }
            } else if (name.equals("product_name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GetUserProductRealmProxyInterface) obj).realmSet$product_name(null);
                } else {
                    ((GetUserProductRealmProxyInterface) obj).realmSet$product_name((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static com.xstock.models.GetUserProduct copyOrUpdate(Realm realm, com.xstock.models.GetUserProduct object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.xstock.models.GetUserProduct) cachedRealmObject;
        } else {
            com.xstock.models.GetUserProduct realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.xstock.models.GetUserProduct.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((GetUserProductRealmProxyInterface) object).realmGet$user_id();
                long rowIndex = TableOrView.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != TableOrView.NO_MATCH) {
                    realmObject = new io.realm.GetUserProductRealmProxy(realm.schema.getColumnInfo(com.xstock.models.GetUserProduct.class));
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

    public static com.xstock.models.GetUserProduct copy(Realm realm, com.xstock.models.GetUserProduct newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.xstock.models.GetUserProduct) cachedRealmObject;
        } else {
            com.xstock.models.GetUserProduct realmObject = realm.createObject(com.xstock.models.GetUserProduct.class, ((GetUserProductRealmProxyInterface) newObject).realmGet$user_id());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((GetUserProductRealmProxyInterface) realmObject).realmSet$user_id(((GetUserProductRealmProxyInterface) newObject).realmGet$user_id());
            ((GetUserProductRealmProxyInterface) realmObject).realmSet$product_id(((GetUserProductRealmProxyInterface) newObject).realmGet$product_id());
            ((GetUserProductRealmProxyInterface) realmObject).realmSet$product_name(((GetUserProductRealmProxyInterface) newObject).realmGet$product_name());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.xstock.models.GetUserProduct object, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.xstock.models.GetUserProduct.class);
        long tableNativePtr = table.getNativeTablePointer();
        GetUserProductColumnInfo columnInfo = (GetUserProductColumnInfo) realm.schema.getColumnInfo(com.xstock.models.GetUserProduct.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$user_id = ((GetUserProductRealmProxyInterface)object).realmGet$user_id();
        if (realmGet$user_id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.user_idIndex, rowIndex, realmGet$user_id);
        }
        String realmGet$product_id = ((GetUserProductRealmProxyInterface)object).realmGet$product_id();
        if (realmGet$product_id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.product_idIndex, rowIndex, realmGet$product_id);
        }
        String realmGet$product_name = ((GetUserProductRealmProxyInterface)object).realmGet$product_name();
        if (realmGet$product_name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.product_nameIndex, rowIndex, realmGet$product_name);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.xstock.models.GetUserProduct.class);
        long tableNativePtr = table.getNativeTablePointer();
        GetUserProductColumnInfo columnInfo = (GetUserProductColumnInfo) realm.schema.getColumnInfo(com.xstock.models.GetUserProduct.class);
        com.xstock.models.GetUserProduct object = null;
        while (objects.hasNext()) {
            object = (com.xstock.models.GetUserProduct) objects.next();
            if(!cache.containsKey(object)) {
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$user_id = ((GetUserProductRealmProxyInterface)object).realmGet$user_id();
                if (realmGet$user_id != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.user_idIndex, rowIndex, realmGet$user_id);
                }
                String realmGet$product_id = ((GetUserProductRealmProxyInterface)object).realmGet$product_id();
                if (realmGet$product_id != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.product_idIndex, rowIndex, realmGet$product_id);
                }
                String realmGet$product_name = ((GetUserProductRealmProxyInterface)object).realmGet$product_name();
                if (realmGet$product_name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.product_nameIndex, rowIndex, realmGet$product_name);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.xstock.models.GetUserProduct object, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.xstock.models.GetUserProduct.class);
        long tableNativePtr = table.getNativeTablePointer();
        GetUserProductColumnInfo columnInfo = (GetUserProductColumnInfo) realm.schema.getColumnInfo(com.xstock.models.GetUserProduct.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((GetUserProductRealmProxyInterface) object).realmGet$user_id();
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
        String realmGet$user_id = ((GetUserProductRealmProxyInterface)object).realmGet$user_id();
        if (realmGet$user_id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.user_idIndex, rowIndex, realmGet$user_id);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.user_idIndex, rowIndex);
        }
        String realmGet$product_id = ((GetUserProductRealmProxyInterface)object).realmGet$product_id();
        if (realmGet$product_id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.product_idIndex, rowIndex, realmGet$product_id);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.product_idIndex, rowIndex);
        }
        String realmGet$product_name = ((GetUserProductRealmProxyInterface)object).realmGet$product_name();
        if (realmGet$product_name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.product_nameIndex, rowIndex, realmGet$product_name);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.product_nameIndex, rowIndex);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.xstock.models.GetUserProduct.class);
        long tableNativePtr = table.getNativeTablePointer();
        GetUserProductColumnInfo columnInfo = (GetUserProductColumnInfo) realm.schema.getColumnInfo(com.xstock.models.GetUserProduct.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.xstock.models.GetUserProduct object = null;
        while (objects.hasNext()) {
            object = (com.xstock.models.GetUserProduct) objects.next();
            if(!cache.containsKey(object)) {
                String primaryKeyValue = ((GetUserProductRealmProxyInterface) object).realmGet$user_id();
                long rowIndex = TableOrView.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                    if (primaryKeyValue != null) {
                        Table.nativeSetString(tableNativePtr, pkColumnIndex, rowIndex, ((GetUserProductRealmProxyInterface) object).realmGet$user_id());
                    }
                }
                cache.put(object, rowIndex);
                String realmGet$user_id = ((GetUserProductRealmProxyInterface)object).realmGet$user_id();
                if (realmGet$user_id != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.user_idIndex, rowIndex, realmGet$user_id);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.user_idIndex, rowIndex);
                }
                String realmGet$product_id = ((GetUserProductRealmProxyInterface)object).realmGet$product_id();
                if (realmGet$product_id != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.product_idIndex, rowIndex, realmGet$product_id);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.product_idIndex, rowIndex);
                }
                String realmGet$product_name = ((GetUserProductRealmProxyInterface)object).realmGet$product_name();
                if (realmGet$product_name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.product_nameIndex, rowIndex, realmGet$product_name);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.product_nameIndex, rowIndex);
                }
            }
        }
    }

    public static com.xstock.models.GetUserProduct createDetachedCopy(com.xstock.models.GetUserProduct realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.xstock.models.GetUserProduct unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.xstock.models.GetUserProduct)cachedObject.object;
            } else {
                unmanagedObject = (com.xstock.models.GetUserProduct)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.xstock.models.GetUserProduct();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((GetUserProductRealmProxyInterface) unmanagedObject).realmSet$user_id(((GetUserProductRealmProxyInterface) realmObject).realmGet$user_id());
        ((GetUserProductRealmProxyInterface) unmanagedObject).realmSet$product_id(((GetUserProductRealmProxyInterface) realmObject).realmGet$product_id());
        ((GetUserProductRealmProxyInterface) unmanagedObject).realmSet$product_name(((GetUserProductRealmProxyInterface) realmObject).realmGet$product_name());
        return unmanagedObject;
    }

    static com.xstock.models.GetUserProduct update(Realm realm, com.xstock.models.GetUserProduct realmObject, com.xstock.models.GetUserProduct newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((GetUserProductRealmProxyInterface) realmObject).realmSet$product_id(((GetUserProductRealmProxyInterface) newObject).realmGet$product_id());
        ((GetUserProductRealmProxyInterface) realmObject).realmSet$product_name(((GetUserProductRealmProxyInterface) newObject).realmGet$product_name());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("GetUserProduct = [");
        stringBuilder.append("{user_id:");
        stringBuilder.append(realmGet$user_id() != null ? realmGet$user_id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{product_id:");
        stringBuilder.append(realmGet$product_id() != null ? realmGet$product_id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{product_name:");
        stringBuilder.append(realmGet$product_name() != null ? realmGet$product_name() : "null");
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
        GetUserProductRealmProxy aGetUserProduct = (GetUserProductRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aGetUserProduct.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aGetUserProduct.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aGetUserProduct.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
