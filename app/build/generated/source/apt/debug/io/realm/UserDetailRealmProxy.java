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

public class UserDetailRealmProxy extends com.xstock.models.UserDetail
    implements RealmObjectProxy, UserDetailRealmProxyInterface {

    static final class UserDetailColumnInfo extends ColumnInfo {

        public final long emailIndex;
        public final long passwordIndex;
        public final long usernameIndex;
        public final long firstnameIndex;
        public final long lastnameIndex;
        public final long sexidIndex;
        public final long is_activeIndex;
        public final long groupIDIndex;

        UserDetailColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(8);
            this.emailIndex = getValidColumnIndex(path, table, "UserDetail", "email");
            indicesMap.put("email", this.emailIndex);

            this.passwordIndex = getValidColumnIndex(path, table, "UserDetail", "password");
            indicesMap.put("password", this.passwordIndex);

            this.usernameIndex = getValidColumnIndex(path, table, "UserDetail", "username");
            indicesMap.put("username", this.usernameIndex);

            this.firstnameIndex = getValidColumnIndex(path, table, "UserDetail", "firstname");
            indicesMap.put("firstname", this.firstnameIndex);

            this.lastnameIndex = getValidColumnIndex(path, table, "UserDetail", "lastname");
            indicesMap.put("lastname", this.lastnameIndex);

            this.sexidIndex = getValidColumnIndex(path, table, "UserDetail", "sexid");
            indicesMap.put("sexid", this.sexidIndex);

            this.is_activeIndex = getValidColumnIndex(path, table, "UserDetail", "is_active");
            indicesMap.put("is_active", this.is_activeIndex);

            this.groupIDIndex = getValidColumnIndex(path, table, "UserDetail", "groupID");
            indicesMap.put("groupID", this.groupIDIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final UserDetailColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("email");
        fieldNames.add("password");
        fieldNames.add("username");
        fieldNames.add("firstname");
        fieldNames.add("lastname");
        fieldNames.add("sexid");
        fieldNames.add("is_active");
        fieldNames.add("groupID");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    UserDetailRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (UserDetailColumnInfo) columnInfo;
        this.proxyState = new ProxyState(com.xstock.models.UserDetail.class, this);
    }

    @SuppressWarnings("cast")
    public String realmGet$email() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.emailIndex);
    }

    public void realmSet$email(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.emailIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.emailIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$password() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.passwordIndex);
    }

    public void realmSet$password(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.passwordIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.passwordIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$username() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.usernameIndex);
    }

    public void realmSet$username(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.usernameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.usernameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$firstname() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.firstnameIndex);
    }

    public void realmSet$firstname(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.firstnameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.firstnameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$lastname() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.lastnameIndex);
    }

    public void realmSet$lastname(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.lastnameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.lastnameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$sexid() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.sexidIndex);
    }

    public void realmSet$sexid(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.sexidIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.sexidIndex, value);
    }

    @SuppressWarnings("cast")
    public int realmGet$is_active() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.is_activeIndex);
    }

    public void realmSet$is_active(int value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.is_activeIndex, value);
    }

    @SuppressWarnings("cast")
    public int realmGet$groupID() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.groupIDIndex);
    }

    public void realmSet$groupID(int value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.groupIDIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_UserDetail")) {
            Table table = transaction.getTable("class_UserDetail");
            table.addColumn(RealmFieldType.STRING, "email", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "password", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "username", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "firstname", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "lastname", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "sexid", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "is_active", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "groupID", Table.NOT_NULLABLE);
            table.addSearchIndex(table.getColumnIndex("email"));
            table.setPrimaryKey("email");
            return table;
        }
        return transaction.getTable("class_UserDetail");
    }

    public static UserDetailColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_UserDetail")) {
            Table table = transaction.getTable("class_UserDetail");
            if (table.getColumnCount() != 8) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 8 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 8; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final UserDetailColumnInfo columnInfo = new UserDetailColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("email")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'email' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("email") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'email' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.emailIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(),"@PrimaryKey field 'email' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("email")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Primary key not defined for field 'email' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("email"))) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Index not defined for field 'email' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("password")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'password' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("password") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'password' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.passwordIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'password' is required. Either set @Required to field 'password' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("username")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'username' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("username") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'username' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.usernameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'username' is required. Either set @Required to field 'username' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("firstname")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'firstname' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("firstname") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'firstname' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.firstnameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'firstname' is required. Either set @Required to field 'firstname' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("lastname")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'lastname' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("lastname") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'lastname' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.lastnameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'lastname' is required. Either set @Required to field 'lastname' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("sexid")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'sexid' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("sexid") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'sexid' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.sexidIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'sexid' is required. Either set @Required to field 'sexid' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("is_active")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'is_active' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("is_active") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'is_active' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.is_activeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'is_active' does support null values in the existing Realm file. Use corresponding boxed type for field 'is_active' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("groupID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'groupID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("groupID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'groupID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.groupIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'groupID' does support null values in the existing Realm file. Use corresponding boxed type for field 'groupID' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The UserDetail class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_UserDetail";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.xstock.models.UserDetail createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        com.xstock.models.UserDetail obj = null;
        if (update) {
            Table table = realm.getTable(com.xstock.models.UserDetail.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = TableOrView.NO_MATCH;
            if (json.isNull("email")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("email"));
            }
            if (rowIndex != TableOrView.NO_MATCH) {
                obj = new io.realm.UserDetailRealmProxy(realm.schema.getColumnInfo(com.xstock.models.UserDetail.class));
                ((RealmObjectProxy)obj).realmGet$proxyState().setRealm$realm(realm);
                ((RealmObjectProxy)obj).realmGet$proxyState().setRow$realm(table.getUncheckedRow(rowIndex));
            }
        }
        if (obj == null) {
            if (json.has("email")) {
                if (json.isNull("email")) {
                    obj = (io.realm.UserDetailRealmProxy) realm.createObject(com.xstock.models.UserDetail.class, null);
                } else {
                    obj = (io.realm.UserDetailRealmProxy) realm.createObject(com.xstock.models.UserDetail.class, json.getString("email"));
                }
            } else {
                obj = (io.realm.UserDetailRealmProxy) realm.createObject(com.xstock.models.UserDetail.class);
            }
        }
        if (json.has("email")) {
            if (json.isNull("email")) {
                ((UserDetailRealmProxyInterface) obj).realmSet$email(null);
            } else {
                ((UserDetailRealmProxyInterface) obj).realmSet$email((String) json.getString("email"));
            }
        }
        if (json.has("password")) {
            if (json.isNull("password")) {
                ((UserDetailRealmProxyInterface) obj).realmSet$password(null);
            } else {
                ((UserDetailRealmProxyInterface) obj).realmSet$password((String) json.getString("password"));
            }
        }
        if (json.has("username")) {
            if (json.isNull("username")) {
                ((UserDetailRealmProxyInterface) obj).realmSet$username(null);
            } else {
                ((UserDetailRealmProxyInterface) obj).realmSet$username((String) json.getString("username"));
            }
        }
        if (json.has("firstname")) {
            if (json.isNull("firstname")) {
                ((UserDetailRealmProxyInterface) obj).realmSet$firstname(null);
            } else {
                ((UserDetailRealmProxyInterface) obj).realmSet$firstname((String) json.getString("firstname"));
            }
        }
        if (json.has("lastname")) {
            if (json.isNull("lastname")) {
                ((UserDetailRealmProxyInterface) obj).realmSet$lastname(null);
            } else {
                ((UserDetailRealmProxyInterface) obj).realmSet$lastname((String) json.getString("lastname"));
            }
        }
        if (json.has("sexid")) {
            if (json.isNull("sexid")) {
                ((UserDetailRealmProxyInterface) obj).realmSet$sexid(null);
            } else {
                ((UserDetailRealmProxyInterface) obj).realmSet$sexid((String) json.getString("sexid"));
            }
        }
        if (json.has("is_active")) {
            if (json.isNull("is_active")) {
                throw new IllegalArgumentException("Trying to set non-nullable field is_active to null.");
            } else {
                ((UserDetailRealmProxyInterface) obj).realmSet$is_active((int) json.getInt("is_active"));
            }
        }
        if (json.has("groupID")) {
            if (json.isNull("groupID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field groupID to null.");
            } else {
                ((UserDetailRealmProxyInterface) obj).realmSet$groupID((int) json.getInt("groupID"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static com.xstock.models.UserDetail createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.xstock.models.UserDetail obj = realm.createObject(com.xstock.models.UserDetail.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("email")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserDetailRealmProxyInterface) obj).realmSet$email(null);
                } else {
                    ((UserDetailRealmProxyInterface) obj).realmSet$email((String) reader.nextString());
                }
            } else if (name.equals("password")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserDetailRealmProxyInterface) obj).realmSet$password(null);
                } else {
                    ((UserDetailRealmProxyInterface) obj).realmSet$password((String) reader.nextString());
                }
            } else if (name.equals("username")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserDetailRealmProxyInterface) obj).realmSet$username(null);
                } else {
                    ((UserDetailRealmProxyInterface) obj).realmSet$username((String) reader.nextString());
                }
            } else if (name.equals("firstname")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserDetailRealmProxyInterface) obj).realmSet$firstname(null);
                } else {
                    ((UserDetailRealmProxyInterface) obj).realmSet$firstname((String) reader.nextString());
                }
            } else if (name.equals("lastname")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserDetailRealmProxyInterface) obj).realmSet$lastname(null);
                } else {
                    ((UserDetailRealmProxyInterface) obj).realmSet$lastname((String) reader.nextString());
                }
            } else if (name.equals("sexid")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserDetailRealmProxyInterface) obj).realmSet$sexid(null);
                } else {
                    ((UserDetailRealmProxyInterface) obj).realmSet$sexid((String) reader.nextString());
                }
            } else if (name.equals("is_active")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field is_active to null.");
                } else {
                    ((UserDetailRealmProxyInterface) obj).realmSet$is_active((int) reader.nextInt());
                }
            } else if (name.equals("groupID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field groupID to null.");
                } else {
                    ((UserDetailRealmProxyInterface) obj).realmSet$groupID((int) reader.nextInt());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static com.xstock.models.UserDetail copyOrUpdate(Realm realm, com.xstock.models.UserDetail object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.xstock.models.UserDetail) cachedRealmObject;
        } else {
            com.xstock.models.UserDetail realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.xstock.models.UserDetail.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((UserDetailRealmProxyInterface) object).realmGet$email();
                long rowIndex = TableOrView.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != TableOrView.NO_MATCH) {
                    realmObject = new io.realm.UserDetailRealmProxy(realm.schema.getColumnInfo(com.xstock.models.UserDetail.class));
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

    public static com.xstock.models.UserDetail copy(Realm realm, com.xstock.models.UserDetail newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.xstock.models.UserDetail) cachedRealmObject;
        } else {
            com.xstock.models.UserDetail realmObject = realm.createObject(com.xstock.models.UserDetail.class, ((UserDetailRealmProxyInterface) newObject).realmGet$email());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((UserDetailRealmProxyInterface) realmObject).realmSet$email(((UserDetailRealmProxyInterface) newObject).realmGet$email());
            ((UserDetailRealmProxyInterface) realmObject).realmSet$password(((UserDetailRealmProxyInterface) newObject).realmGet$password());
            ((UserDetailRealmProxyInterface) realmObject).realmSet$username(((UserDetailRealmProxyInterface) newObject).realmGet$username());
            ((UserDetailRealmProxyInterface) realmObject).realmSet$firstname(((UserDetailRealmProxyInterface) newObject).realmGet$firstname());
            ((UserDetailRealmProxyInterface) realmObject).realmSet$lastname(((UserDetailRealmProxyInterface) newObject).realmGet$lastname());
            ((UserDetailRealmProxyInterface) realmObject).realmSet$sexid(((UserDetailRealmProxyInterface) newObject).realmGet$sexid());
            ((UserDetailRealmProxyInterface) realmObject).realmSet$is_active(((UserDetailRealmProxyInterface) newObject).realmGet$is_active());
            ((UserDetailRealmProxyInterface) realmObject).realmSet$groupID(((UserDetailRealmProxyInterface) newObject).realmGet$groupID());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.xstock.models.UserDetail object, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.xstock.models.UserDetail.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserDetailColumnInfo columnInfo = (UserDetailColumnInfo) realm.schema.getColumnInfo(com.xstock.models.UserDetail.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$email = ((UserDetailRealmProxyInterface)object).realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email);
        }
        String realmGet$password = ((UserDetailRealmProxyInterface)object).realmGet$password();
        if (realmGet$password != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.passwordIndex, rowIndex, realmGet$password);
        }
        String realmGet$username = ((UserDetailRealmProxyInterface)object).realmGet$username();
        if (realmGet$username != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.usernameIndex, rowIndex, realmGet$username);
        }
        String realmGet$firstname = ((UserDetailRealmProxyInterface)object).realmGet$firstname();
        if (realmGet$firstname != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.firstnameIndex, rowIndex, realmGet$firstname);
        }
        String realmGet$lastname = ((UserDetailRealmProxyInterface)object).realmGet$lastname();
        if (realmGet$lastname != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.lastnameIndex, rowIndex, realmGet$lastname);
        }
        String realmGet$sexid = ((UserDetailRealmProxyInterface)object).realmGet$sexid();
        if (realmGet$sexid != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.sexidIndex, rowIndex, realmGet$sexid);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.is_activeIndex, rowIndex, ((UserDetailRealmProxyInterface)object).realmGet$is_active());
        Table.nativeSetLong(tableNativePtr, columnInfo.groupIDIndex, rowIndex, ((UserDetailRealmProxyInterface)object).realmGet$groupID());
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.xstock.models.UserDetail.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserDetailColumnInfo columnInfo = (UserDetailColumnInfo) realm.schema.getColumnInfo(com.xstock.models.UserDetail.class);
        com.xstock.models.UserDetail object = null;
        while (objects.hasNext()) {
            object = (com.xstock.models.UserDetail) objects.next();
            if(!cache.containsKey(object)) {
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$email = ((UserDetailRealmProxyInterface)object).realmGet$email();
                if (realmGet$email != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email);
                }
                String realmGet$password = ((UserDetailRealmProxyInterface)object).realmGet$password();
                if (realmGet$password != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.passwordIndex, rowIndex, realmGet$password);
                }
                String realmGet$username = ((UserDetailRealmProxyInterface)object).realmGet$username();
                if (realmGet$username != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.usernameIndex, rowIndex, realmGet$username);
                }
                String realmGet$firstname = ((UserDetailRealmProxyInterface)object).realmGet$firstname();
                if (realmGet$firstname != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.firstnameIndex, rowIndex, realmGet$firstname);
                }
                String realmGet$lastname = ((UserDetailRealmProxyInterface)object).realmGet$lastname();
                if (realmGet$lastname != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.lastnameIndex, rowIndex, realmGet$lastname);
                }
                String realmGet$sexid = ((UserDetailRealmProxyInterface)object).realmGet$sexid();
                if (realmGet$sexid != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.sexidIndex, rowIndex, realmGet$sexid);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.is_activeIndex, rowIndex, ((UserDetailRealmProxyInterface)object).realmGet$is_active());
                Table.nativeSetLong(tableNativePtr, columnInfo.groupIDIndex, rowIndex, ((UserDetailRealmProxyInterface)object).realmGet$groupID());
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.xstock.models.UserDetail object, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.xstock.models.UserDetail.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserDetailColumnInfo columnInfo = (UserDetailColumnInfo) realm.schema.getColumnInfo(com.xstock.models.UserDetail.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((UserDetailRealmProxyInterface) object).realmGet$email();
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
        String realmGet$email = ((UserDetailRealmProxyInterface)object).realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.emailIndex, rowIndex);
        }
        String realmGet$password = ((UserDetailRealmProxyInterface)object).realmGet$password();
        if (realmGet$password != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.passwordIndex, rowIndex, realmGet$password);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.passwordIndex, rowIndex);
        }
        String realmGet$username = ((UserDetailRealmProxyInterface)object).realmGet$username();
        if (realmGet$username != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.usernameIndex, rowIndex, realmGet$username);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.usernameIndex, rowIndex);
        }
        String realmGet$firstname = ((UserDetailRealmProxyInterface)object).realmGet$firstname();
        if (realmGet$firstname != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.firstnameIndex, rowIndex, realmGet$firstname);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.firstnameIndex, rowIndex);
        }
        String realmGet$lastname = ((UserDetailRealmProxyInterface)object).realmGet$lastname();
        if (realmGet$lastname != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.lastnameIndex, rowIndex, realmGet$lastname);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.lastnameIndex, rowIndex);
        }
        String realmGet$sexid = ((UserDetailRealmProxyInterface)object).realmGet$sexid();
        if (realmGet$sexid != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.sexidIndex, rowIndex, realmGet$sexid);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.sexidIndex, rowIndex);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.is_activeIndex, rowIndex, ((UserDetailRealmProxyInterface)object).realmGet$is_active());
        Table.nativeSetLong(tableNativePtr, columnInfo.groupIDIndex, rowIndex, ((UserDetailRealmProxyInterface)object).realmGet$groupID());
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.xstock.models.UserDetail.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserDetailColumnInfo columnInfo = (UserDetailColumnInfo) realm.schema.getColumnInfo(com.xstock.models.UserDetail.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.xstock.models.UserDetail object = null;
        while (objects.hasNext()) {
            object = (com.xstock.models.UserDetail) objects.next();
            if(!cache.containsKey(object)) {
                String primaryKeyValue = ((UserDetailRealmProxyInterface) object).realmGet$email();
                long rowIndex = TableOrView.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                    if (primaryKeyValue != null) {
                        Table.nativeSetString(tableNativePtr, pkColumnIndex, rowIndex, ((UserDetailRealmProxyInterface) object).realmGet$email());
                    }
                }
                cache.put(object, rowIndex);
                String realmGet$email = ((UserDetailRealmProxyInterface)object).realmGet$email();
                if (realmGet$email != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.emailIndex, rowIndex);
                }
                String realmGet$password = ((UserDetailRealmProxyInterface)object).realmGet$password();
                if (realmGet$password != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.passwordIndex, rowIndex, realmGet$password);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.passwordIndex, rowIndex);
                }
                String realmGet$username = ((UserDetailRealmProxyInterface)object).realmGet$username();
                if (realmGet$username != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.usernameIndex, rowIndex, realmGet$username);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.usernameIndex, rowIndex);
                }
                String realmGet$firstname = ((UserDetailRealmProxyInterface)object).realmGet$firstname();
                if (realmGet$firstname != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.firstnameIndex, rowIndex, realmGet$firstname);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.firstnameIndex, rowIndex);
                }
                String realmGet$lastname = ((UserDetailRealmProxyInterface)object).realmGet$lastname();
                if (realmGet$lastname != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.lastnameIndex, rowIndex, realmGet$lastname);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.lastnameIndex, rowIndex);
                }
                String realmGet$sexid = ((UserDetailRealmProxyInterface)object).realmGet$sexid();
                if (realmGet$sexid != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.sexidIndex, rowIndex, realmGet$sexid);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.sexidIndex, rowIndex);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.is_activeIndex, rowIndex, ((UserDetailRealmProxyInterface)object).realmGet$is_active());
                Table.nativeSetLong(tableNativePtr, columnInfo.groupIDIndex, rowIndex, ((UserDetailRealmProxyInterface)object).realmGet$groupID());
            }
        }
    }

    public static com.xstock.models.UserDetail createDetachedCopy(com.xstock.models.UserDetail realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.xstock.models.UserDetail unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.xstock.models.UserDetail)cachedObject.object;
            } else {
                unmanagedObject = (com.xstock.models.UserDetail)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.xstock.models.UserDetail();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((UserDetailRealmProxyInterface) unmanagedObject).realmSet$email(((UserDetailRealmProxyInterface) realmObject).realmGet$email());
        ((UserDetailRealmProxyInterface) unmanagedObject).realmSet$password(((UserDetailRealmProxyInterface) realmObject).realmGet$password());
        ((UserDetailRealmProxyInterface) unmanagedObject).realmSet$username(((UserDetailRealmProxyInterface) realmObject).realmGet$username());
        ((UserDetailRealmProxyInterface) unmanagedObject).realmSet$firstname(((UserDetailRealmProxyInterface) realmObject).realmGet$firstname());
        ((UserDetailRealmProxyInterface) unmanagedObject).realmSet$lastname(((UserDetailRealmProxyInterface) realmObject).realmGet$lastname());
        ((UserDetailRealmProxyInterface) unmanagedObject).realmSet$sexid(((UserDetailRealmProxyInterface) realmObject).realmGet$sexid());
        ((UserDetailRealmProxyInterface) unmanagedObject).realmSet$is_active(((UserDetailRealmProxyInterface) realmObject).realmGet$is_active());
        ((UserDetailRealmProxyInterface) unmanagedObject).realmSet$groupID(((UserDetailRealmProxyInterface) realmObject).realmGet$groupID());
        return unmanagedObject;
    }

    static com.xstock.models.UserDetail update(Realm realm, com.xstock.models.UserDetail realmObject, com.xstock.models.UserDetail newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((UserDetailRealmProxyInterface) realmObject).realmSet$password(((UserDetailRealmProxyInterface) newObject).realmGet$password());
        ((UserDetailRealmProxyInterface) realmObject).realmSet$username(((UserDetailRealmProxyInterface) newObject).realmGet$username());
        ((UserDetailRealmProxyInterface) realmObject).realmSet$firstname(((UserDetailRealmProxyInterface) newObject).realmGet$firstname());
        ((UserDetailRealmProxyInterface) realmObject).realmSet$lastname(((UserDetailRealmProxyInterface) newObject).realmGet$lastname());
        ((UserDetailRealmProxyInterface) realmObject).realmSet$sexid(((UserDetailRealmProxyInterface) newObject).realmGet$sexid());
        ((UserDetailRealmProxyInterface) realmObject).realmSet$is_active(((UserDetailRealmProxyInterface) newObject).realmGet$is_active());
        ((UserDetailRealmProxyInterface) realmObject).realmSet$groupID(((UserDetailRealmProxyInterface) newObject).realmGet$groupID());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("UserDetail = [");
        stringBuilder.append("{email:");
        stringBuilder.append(realmGet$email() != null ? realmGet$email() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{password:");
        stringBuilder.append(realmGet$password() != null ? realmGet$password() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{username:");
        stringBuilder.append(realmGet$username() != null ? realmGet$username() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{firstname:");
        stringBuilder.append(realmGet$firstname() != null ? realmGet$firstname() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{lastname:");
        stringBuilder.append(realmGet$lastname() != null ? realmGet$lastname() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{sexid:");
        stringBuilder.append(realmGet$sexid() != null ? realmGet$sexid() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{is_active:");
        stringBuilder.append(realmGet$is_active());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{groupID:");
        stringBuilder.append(realmGet$groupID());
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
        UserDetailRealmProxy aUserDetail = (UserDetailRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aUserDetail.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aUserDetail.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aUserDetail.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
