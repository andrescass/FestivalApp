package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
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

public class InfoRealmProxy extends com.camacuasoft.jazzfestivalapp.Models.Info
    implements RealmObjectProxy, InfoRealmProxyInterface {

    static final class InfoColumnInfo extends ColumnInfo
        implements Cloneable {

        public long IDIndex;
        public long titleIndex;
        public long infoIndex;
        public long imageUriIndex;
        public long imageUrlIndex;

        InfoColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(5);
            this.IDIndex = getValidColumnIndex(path, table, "Info", "ID");
            indicesMap.put("ID", this.IDIndex);
            this.titleIndex = getValidColumnIndex(path, table, "Info", "title");
            indicesMap.put("title", this.titleIndex);
            this.infoIndex = getValidColumnIndex(path, table, "Info", "info");
            indicesMap.put("info", this.infoIndex);
            this.imageUriIndex = getValidColumnIndex(path, table, "Info", "imageUri");
            indicesMap.put("imageUri", this.imageUriIndex);
            this.imageUrlIndex = getValidColumnIndex(path, table, "Info", "imageUrl");
            indicesMap.put("imageUrl", this.imageUrlIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final InfoColumnInfo otherInfo = (InfoColumnInfo) other;
            this.IDIndex = otherInfo.IDIndex;
            this.titleIndex = otherInfo.titleIndex;
            this.infoIndex = otherInfo.infoIndex;
            this.imageUriIndex = otherInfo.imageUriIndex;
            this.imageUrlIndex = otherInfo.imageUrlIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final InfoColumnInfo clone() {
            return (InfoColumnInfo) super.clone();
        }

    }
    private InfoColumnInfo columnInfo;
    private ProxyState<com.camacuasoft.jazzfestivalapp.Models.Info> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("ID");
        fieldNames.add("title");
        fieldNames.add("info");
        fieldNames.add("imageUri");
        fieldNames.add("imageUrl");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    InfoRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (InfoColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.camacuasoft.jazzfestivalapp.Models.Info>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @SuppressWarnings("cast")
    public int realmGet$ID() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.IDIndex);
    }

    public void realmSet$ID(int value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'ID' cannot be changed after object was created.");
    }

    @SuppressWarnings("cast")
    public String realmGet$title() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.titleIndex);
    }

    public void realmSet$title(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'title' to null.");
            }
            row.getTable().setString(columnInfo.titleIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'title' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.titleIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$info() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.infoIndex);
    }

    public void realmSet$info(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.infoIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.infoIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.infoIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.infoIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$imageUri() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.imageUriIndex);
    }

    public void realmSet$imageUri(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.imageUriIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.imageUriIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.imageUriIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.imageUriIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$imageUrl() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.imageUrlIndex);
    }

    public void realmSet$imageUrl(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.imageUrlIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.imageUrlIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.imageUrlIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.imageUrlIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Info")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Info");
            realmObjectSchema.add(new Property("ID", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("title", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("info", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("imageUri", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("imageUrl", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("Info");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_Info")) {
            Table table = sharedRealm.getTable("class_Info");
            table.addColumn(RealmFieldType.INTEGER, "ID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "title", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "info", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "imageUri", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "imageUrl", Table.NULLABLE);
            table.addSearchIndex(table.getColumnIndex("ID"));
            table.setPrimaryKey("ID");
            return table;
        }
        return sharedRealm.getTable("class_Info");
    }

    public static InfoColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_Info")) {
            Table table = sharedRealm.getTable("class_Info");
            final long columnCount = table.getColumnCount();
            if (columnCount != 5) {
                if (columnCount < 5) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 5 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 5 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 5 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < columnCount; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final InfoColumnInfo columnInfo = new InfoColumnInfo(sharedRealm.getPath(), table);

            if (!table.hasPrimaryKey()) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'ID' in existing Realm file. @PrimaryKey was added.");
            } else {
                if (table.getPrimaryKey() != columnInfo.IDIndex) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key annotation definition was changed, from field " + table.getColumnName(table.getPrimaryKey()) + " to field ID");
                }
            }

            if (!columnTypes.containsKey("ID")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'ID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("ID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'ID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.IDIndex) && table.findFirstNull(columnInfo.IDIndex) != Table.NO_MATCH) {
                throw new IllegalStateException("Cannot migrate an object with null value in field 'ID'. Either maintain the same type for primary key field 'ID', or remove the object with null value before migration.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("ID"))) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'ID' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("title")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'title' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("title") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'title' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.titleIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'title' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'title' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("info")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'info' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("info") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'info' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.infoIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'info' is required. Either set @Required to field 'info' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("imageUri")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'imageUri' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("imageUri") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'imageUri' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.imageUriIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'imageUri' is required. Either set @Required to field 'imageUri' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("imageUrl")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'imageUrl' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("imageUrl") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'imageUrl' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.imageUrlIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'imageUrl' is required. Either set @Required to field 'imageUrl' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Info' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Info";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.camacuasoft.jazzfestivalapp.Models.Info createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.camacuasoft.jazzfestivalapp.Models.Info obj = null;
        if (update) {
            Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Info.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("ID")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("ID"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Info.class), false, Collections.<String> emptyList());
                    obj = new io.realm.InfoRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("ID")) {
                if (json.isNull("ID")) {
                    obj = (io.realm.InfoRealmProxy) realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.Info.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.InfoRealmProxy) realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.Info.class, json.getInt("ID"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'ID'.");
            }
        }
        if (json.has("title")) {
            if (json.isNull("title")) {
                ((InfoRealmProxyInterface) obj).realmSet$title(null);
            } else {
                ((InfoRealmProxyInterface) obj).realmSet$title((String) json.getString("title"));
            }
        }
        if (json.has("info")) {
            if (json.isNull("info")) {
                ((InfoRealmProxyInterface) obj).realmSet$info(null);
            } else {
                ((InfoRealmProxyInterface) obj).realmSet$info((String) json.getString("info"));
            }
        }
        if (json.has("imageUri")) {
            if (json.isNull("imageUri")) {
                ((InfoRealmProxyInterface) obj).realmSet$imageUri(null);
            } else {
                ((InfoRealmProxyInterface) obj).realmSet$imageUri((String) json.getString("imageUri"));
            }
        }
        if (json.has("imageUrl")) {
            if (json.isNull("imageUrl")) {
                ((InfoRealmProxyInterface) obj).realmSet$imageUrl(null);
            } else {
                ((InfoRealmProxyInterface) obj).realmSet$imageUrl((String) json.getString("imageUrl"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.camacuasoft.jazzfestivalapp.Models.Info createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.camacuasoft.jazzfestivalapp.Models.Info obj = new com.camacuasoft.jazzfestivalapp.Models.Info();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("ID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'ID' to null.");
                } else {
                    ((InfoRealmProxyInterface) obj).realmSet$ID((int) reader.nextInt());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("title")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((InfoRealmProxyInterface) obj).realmSet$title(null);
                } else {
                    ((InfoRealmProxyInterface) obj).realmSet$title((String) reader.nextString());
                }
            } else if (name.equals("info")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((InfoRealmProxyInterface) obj).realmSet$info(null);
                } else {
                    ((InfoRealmProxyInterface) obj).realmSet$info((String) reader.nextString());
                }
            } else if (name.equals("imageUri")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((InfoRealmProxyInterface) obj).realmSet$imageUri(null);
                } else {
                    ((InfoRealmProxyInterface) obj).realmSet$imageUri((String) reader.nextString());
                }
            } else if (name.equals("imageUrl")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((InfoRealmProxyInterface) obj).realmSet$imageUrl(null);
                } else {
                    ((InfoRealmProxyInterface) obj).realmSet$imageUrl((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'ID'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.camacuasoft.jazzfestivalapp.Models.Info copyOrUpdate(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Info object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.camacuasoft.jazzfestivalapp.Models.Info) cachedRealmObject;
        } else {
            com.camacuasoft.jazzfestivalapp.Models.Info realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Info.class);
                long pkColumnIndex = table.getPrimaryKey();
                long rowIndex = table.findFirstLong(pkColumnIndex, ((InfoRealmProxyInterface) object).realmGet$ID());
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Info.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.InfoRealmProxy();
                        cache.put(object, (RealmObjectProxy) realmObject);
                    } finally {
                        objectContext.clear();
                    }
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

    public static com.camacuasoft.jazzfestivalapp.Models.Info copy(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Info newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.camacuasoft.jazzfestivalapp.Models.Info) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.camacuasoft.jazzfestivalapp.Models.Info realmObject = realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.Info.class, ((InfoRealmProxyInterface) newObject).realmGet$ID(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((InfoRealmProxyInterface) realmObject).realmSet$title(((InfoRealmProxyInterface) newObject).realmGet$title());
            ((InfoRealmProxyInterface) realmObject).realmSet$info(((InfoRealmProxyInterface) newObject).realmGet$info());
            ((InfoRealmProxyInterface) realmObject).realmSet$imageUri(((InfoRealmProxyInterface) newObject).realmGet$imageUri());
            ((InfoRealmProxyInterface) realmObject).realmSet$imageUrl(((InfoRealmProxyInterface) newObject).realmGet$imageUrl());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Info object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Info.class);
        long tableNativePtr = table.getNativeTablePointer();
        InfoColumnInfo columnInfo = (InfoColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Info.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((InfoRealmProxyInterface) object).realmGet$ID();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((InfoRealmProxyInterface) object).realmGet$ID());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(((InfoRealmProxyInterface) object).realmGet$ID(), false);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$title = ((InfoRealmProxyInterface)object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        }
        String realmGet$info = ((InfoRealmProxyInterface)object).realmGet$info();
        if (realmGet$info != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.infoIndex, rowIndex, realmGet$info, false);
        }
        String realmGet$imageUri = ((InfoRealmProxyInterface)object).realmGet$imageUri();
        if (realmGet$imageUri != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.imageUriIndex, rowIndex, realmGet$imageUri, false);
        }
        String realmGet$imageUrl = ((InfoRealmProxyInterface)object).realmGet$imageUrl();
        if (realmGet$imageUrl != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.imageUrlIndex, rowIndex, realmGet$imageUrl, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Info.class);
        long tableNativePtr = table.getNativeTablePointer();
        InfoColumnInfo columnInfo = (InfoColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Info.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.camacuasoft.jazzfestivalapp.Models.Info object = null;
        while (objects.hasNext()) {
            object = (com.camacuasoft.jazzfestivalapp.Models.Info) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((InfoRealmProxyInterface) object).realmGet$ID();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((InfoRealmProxyInterface) object).realmGet$ID());
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(((InfoRealmProxyInterface) object).realmGet$ID(), false);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                String realmGet$title = ((InfoRealmProxyInterface)object).realmGet$title();
                if (realmGet$title != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
                }
                String realmGet$info = ((InfoRealmProxyInterface)object).realmGet$info();
                if (realmGet$info != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.infoIndex, rowIndex, realmGet$info, false);
                }
                String realmGet$imageUri = ((InfoRealmProxyInterface)object).realmGet$imageUri();
                if (realmGet$imageUri != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.imageUriIndex, rowIndex, realmGet$imageUri, false);
                }
                String realmGet$imageUrl = ((InfoRealmProxyInterface)object).realmGet$imageUrl();
                if (realmGet$imageUrl != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.imageUrlIndex, rowIndex, realmGet$imageUrl, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Info object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Info.class);
        long tableNativePtr = table.getNativeTablePointer();
        InfoColumnInfo columnInfo = (InfoColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Info.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((InfoRealmProxyInterface) object).realmGet$ID();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((InfoRealmProxyInterface) object).realmGet$ID());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(((InfoRealmProxyInterface) object).realmGet$ID(), false);
        }
        cache.put(object, rowIndex);
        String realmGet$title = ((InfoRealmProxyInterface)object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
        }
        String realmGet$info = ((InfoRealmProxyInterface)object).realmGet$info();
        if (realmGet$info != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.infoIndex, rowIndex, realmGet$info, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.infoIndex, rowIndex, false);
        }
        String realmGet$imageUri = ((InfoRealmProxyInterface)object).realmGet$imageUri();
        if (realmGet$imageUri != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.imageUriIndex, rowIndex, realmGet$imageUri, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.imageUriIndex, rowIndex, false);
        }
        String realmGet$imageUrl = ((InfoRealmProxyInterface)object).realmGet$imageUrl();
        if (realmGet$imageUrl != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.imageUrlIndex, rowIndex, realmGet$imageUrl, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.imageUrlIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Info.class);
        long tableNativePtr = table.getNativeTablePointer();
        InfoColumnInfo columnInfo = (InfoColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Info.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.camacuasoft.jazzfestivalapp.Models.Info object = null;
        while (objects.hasNext()) {
            object = (com.camacuasoft.jazzfestivalapp.Models.Info) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((InfoRealmProxyInterface) object).realmGet$ID();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((InfoRealmProxyInterface) object).realmGet$ID());
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(((InfoRealmProxyInterface) object).realmGet$ID(), false);
                }
                cache.put(object, rowIndex);
                String realmGet$title = ((InfoRealmProxyInterface)object).realmGet$title();
                if (realmGet$title != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
                }
                String realmGet$info = ((InfoRealmProxyInterface)object).realmGet$info();
                if (realmGet$info != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.infoIndex, rowIndex, realmGet$info, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.infoIndex, rowIndex, false);
                }
                String realmGet$imageUri = ((InfoRealmProxyInterface)object).realmGet$imageUri();
                if (realmGet$imageUri != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.imageUriIndex, rowIndex, realmGet$imageUri, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.imageUriIndex, rowIndex, false);
                }
                String realmGet$imageUrl = ((InfoRealmProxyInterface)object).realmGet$imageUrl();
                if (realmGet$imageUrl != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.imageUrlIndex, rowIndex, realmGet$imageUrl, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.imageUrlIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.camacuasoft.jazzfestivalapp.Models.Info createDetachedCopy(com.camacuasoft.jazzfestivalapp.Models.Info realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.camacuasoft.jazzfestivalapp.Models.Info unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.camacuasoft.jazzfestivalapp.Models.Info)cachedObject.object;
            } else {
                unmanagedObject = (com.camacuasoft.jazzfestivalapp.Models.Info)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.camacuasoft.jazzfestivalapp.Models.Info();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((InfoRealmProxyInterface) unmanagedObject).realmSet$ID(((InfoRealmProxyInterface) realmObject).realmGet$ID());
        ((InfoRealmProxyInterface) unmanagedObject).realmSet$title(((InfoRealmProxyInterface) realmObject).realmGet$title());
        ((InfoRealmProxyInterface) unmanagedObject).realmSet$info(((InfoRealmProxyInterface) realmObject).realmGet$info());
        ((InfoRealmProxyInterface) unmanagedObject).realmSet$imageUri(((InfoRealmProxyInterface) realmObject).realmGet$imageUri());
        ((InfoRealmProxyInterface) unmanagedObject).realmSet$imageUrl(((InfoRealmProxyInterface) realmObject).realmGet$imageUrl());
        return unmanagedObject;
    }

    static com.camacuasoft.jazzfestivalapp.Models.Info update(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Info realmObject, com.camacuasoft.jazzfestivalapp.Models.Info newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((InfoRealmProxyInterface) realmObject).realmSet$title(((InfoRealmProxyInterface) newObject).realmGet$title());
        ((InfoRealmProxyInterface) realmObject).realmSet$info(((InfoRealmProxyInterface) newObject).realmGet$info());
        ((InfoRealmProxyInterface) realmObject).realmSet$imageUri(((InfoRealmProxyInterface) newObject).realmGet$imageUri());
        ((InfoRealmProxyInterface) realmObject).realmSet$imageUrl(((InfoRealmProxyInterface) newObject).realmGet$imageUrl());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Info = [");
        stringBuilder.append("{ID:");
        stringBuilder.append(realmGet$ID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{title:");
        stringBuilder.append(realmGet$title());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{info:");
        stringBuilder.append(realmGet$info() != null ? realmGet$info() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{imageUri:");
        stringBuilder.append(realmGet$imageUri() != null ? realmGet$imageUri() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{imageUrl:");
        stringBuilder.append(realmGet$imageUrl() != null ? realmGet$imageUrl() : "null");
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
        InfoRealmProxy aInfo = (InfoRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aInfo.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aInfo.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aInfo.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
