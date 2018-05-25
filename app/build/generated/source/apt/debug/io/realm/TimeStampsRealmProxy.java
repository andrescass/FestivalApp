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

public class TimeStampsRealmProxy extends com.camacuasoft.jazzfestivalapp.Models.TimeStamps
    implements RealmObjectProxy, TimeStampsRealmProxyInterface {

    static final class TimeStampsColumnInfo extends ColumnInfo
        implements Cloneable {

        public long IDIndex;
        public long tsNameIndex;
        public long timeStampIndex;

        TimeStampsColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(3);
            this.IDIndex = getValidColumnIndex(path, table, "TimeStamps", "ID");
            indicesMap.put("ID", this.IDIndex);
            this.tsNameIndex = getValidColumnIndex(path, table, "TimeStamps", "tsName");
            indicesMap.put("tsName", this.tsNameIndex);
            this.timeStampIndex = getValidColumnIndex(path, table, "TimeStamps", "timeStamp");
            indicesMap.put("timeStamp", this.timeStampIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final TimeStampsColumnInfo otherInfo = (TimeStampsColumnInfo) other;
            this.IDIndex = otherInfo.IDIndex;
            this.tsNameIndex = otherInfo.tsNameIndex;
            this.timeStampIndex = otherInfo.timeStampIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final TimeStampsColumnInfo clone() {
            return (TimeStampsColumnInfo) super.clone();
        }

    }
    private TimeStampsColumnInfo columnInfo;
    private ProxyState<com.camacuasoft.jazzfestivalapp.Models.TimeStamps> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("ID");
        fieldNames.add("tsName");
        fieldNames.add("timeStamp");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TimeStampsRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (TimeStampsColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.camacuasoft.jazzfestivalapp.Models.TimeStamps>(this);
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
    public String realmGet$tsName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.tsNameIndex);
    }

    public void realmSet$tsName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.tsNameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.tsNameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.tsNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.tsNameIndex, value);
    }

    @SuppressWarnings("cast")
    public long realmGet$timeStamp() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.timeStampIndex);
    }

    public void realmSet$timeStamp(long value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.timeStampIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.timeStampIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("TimeStamps")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("TimeStamps");
            realmObjectSchema.add(new Property("ID", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("tsName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("timeStamp", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("TimeStamps");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_TimeStamps")) {
            Table table = sharedRealm.getTable("class_TimeStamps");
            table.addColumn(RealmFieldType.INTEGER, "ID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "tsName", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "timeStamp", Table.NOT_NULLABLE);
            table.addSearchIndex(table.getColumnIndex("ID"));
            table.setPrimaryKey("ID");
            return table;
        }
        return sharedRealm.getTable("class_TimeStamps");
    }

    public static TimeStampsColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_TimeStamps")) {
            Table table = sharedRealm.getTable("class_TimeStamps");
            final long columnCount = table.getColumnCount();
            if (columnCount != 3) {
                if (columnCount < 3) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 3 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 3 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 3 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < columnCount; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final TimeStampsColumnInfo columnInfo = new TimeStampsColumnInfo(sharedRealm.getPath(), table);

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
            if (!columnTypes.containsKey("tsName")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'tsName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("tsName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'tsName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.tsNameIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'tsName' is required. Either set @Required to field 'tsName' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("timeStamp")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'timeStamp' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("timeStamp") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'long' for field 'timeStamp' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.timeStampIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'timeStamp' does support null values in the existing Realm file. Use corresponding boxed type for field 'timeStamp' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'TimeStamps' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_TimeStamps";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.camacuasoft.jazzfestivalapp.Models.TimeStamps createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.camacuasoft.jazzfestivalapp.Models.TimeStamps obj = null;
        if (update) {
            Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("ID")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("ID"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class), false, Collections.<String> emptyList());
                    obj = new io.realm.TimeStampsRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("ID")) {
                if (json.isNull("ID")) {
                    obj = (io.realm.TimeStampsRealmProxy) realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.TimeStampsRealmProxy) realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class, json.getInt("ID"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'ID'.");
            }
        }
        if (json.has("tsName")) {
            if (json.isNull("tsName")) {
                ((TimeStampsRealmProxyInterface) obj).realmSet$tsName(null);
            } else {
                ((TimeStampsRealmProxyInterface) obj).realmSet$tsName((String) json.getString("tsName"));
            }
        }
        if (json.has("timeStamp")) {
            if (json.isNull("timeStamp")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'timeStamp' to null.");
            } else {
                ((TimeStampsRealmProxyInterface) obj).realmSet$timeStamp((long) json.getLong("timeStamp"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.camacuasoft.jazzfestivalapp.Models.TimeStamps createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.camacuasoft.jazzfestivalapp.Models.TimeStamps obj = new com.camacuasoft.jazzfestivalapp.Models.TimeStamps();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("ID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'ID' to null.");
                } else {
                    ((TimeStampsRealmProxyInterface) obj).realmSet$ID((int) reader.nextInt());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("tsName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TimeStampsRealmProxyInterface) obj).realmSet$tsName(null);
                } else {
                    ((TimeStampsRealmProxyInterface) obj).realmSet$tsName((String) reader.nextString());
                }
            } else if (name.equals("timeStamp")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'timeStamp' to null.");
                } else {
                    ((TimeStampsRealmProxyInterface) obj).realmSet$timeStamp((long) reader.nextLong());
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

    public static com.camacuasoft.jazzfestivalapp.Models.TimeStamps copyOrUpdate(Realm realm, com.camacuasoft.jazzfestivalapp.Models.TimeStamps object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.camacuasoft.jazzfestivalapp.Models.TimeStamps) cachedRealmObject;
        } else {
            com.camacuasoft.jazzfestivalapp.Models.TimeStamps realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class);
                long pkColumnIndex = table.getPrimaryKey();
                long rowIndex = table.findFirstLong(pkColumnIndex, ((TimeStampsRealmProxyInterface) object).realmGet$ID());
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.TimeStampsRealmProxy();
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

    public static com.camacuasoft.jazzfestivalapp.Models.TimeStamps copy(Realm realm, com.camacuasoft.jazzfestivalapp.Models.TimeStamps newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.camacuasoft.jazzfestivalapp.Models.TimeStamps) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.camacuasoft.jazzfestivalapp.Models.TimeStamps realmObject = realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class, ((TimeStampsRealmProxyInterface) newObject).realmGet$ID(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((TimeStampsRealmProxyInterface) realmObject).realmSet$tsName(((TimeStampsRealmProxyInterface) newObject).realmGet$tsName());
            ((TimeStampsRealmProxyInterface) realmObject).realmSet$timeStamp(((TimeStampsRealmProxyInterface) newObject).realmGet$timeStamp());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.camacuasoft.jazzfestivalapp.Models.TimeStamps object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class);
        long tableNativePtr = table.getNativeTablePointer();
        TimeStampsColumnInfo columnInfo = (TimeStampsColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((TimeStampsRealmProxyInterface) object).realmGet$ID();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((TimeStampsRealmProxyInterface) object).realmGet$ID());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(((TimeStampsRealmProxyInterface) object).realmGet$ID(), false);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$tsName = ((TimeStampsRealmProxyInterface)object).realmGet$tsName();
        if (realmGet$tsName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tsNameIndex, rowIndex, realmGet$tsName, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.timeStampIndex, rowIndex, ((TimeStampsRealmProxyInterface)object).realmGet$timeStamp(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class);
        long tableNativePtr = table.getNativeTablePointer();
        TimeStampsColumnInfo columnInfo = (TimeStampsColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.camacuasoft.jazzfestivalapp.Models.TimeStamps object = null;
        while (objects.hasNext()) {
            object = (com.camacuasoft.jazzfestivalapp.Models.TimeStamps) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((TimeStampsRealmProxyInterface) object).realmGet$ID();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((TimeStampsRealmProxyInterface) object).realmGet$ID());
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(((TimeStampsRealmProxyInterface) object).realmGet$ID(), false);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                String realmGet$tsName = ((TimeStampsRealmProxyInterface)object).realmGet$tsName();
                if (realmGet$tsName != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.tsNameIndex, rowIndex, realmGet$tsName, false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.timeStampIndex, rowIndex, ((TimeStampsRealmProxyInterface)object).realmGet$timeStamp(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.camacuasoft.jazzfestivalapp.Models.TimeStamps object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class);
        long tableNativePtr = table.getNativeTablePointer();
        TimeStampsColumnInfo columnInfo = (TimeStampsColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((TimeStampsRealmProxyInterface) object).realmGet$ID();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((TimeStampsRealmProxyInterface) object).realmGet$ID());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(((TimeStampsRealmProxyInterface) object).realmGet$ID(), false);
        }
        cache.put(object, rowIndex);
        String realmGet$tsName = ((TimeStampsRealmProxyInterface)object).realmGet$tsName();
        if (realmGet$tsName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tsNameIndex, rowIndex, realmGet$tsName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.tsNameIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.timeStampIndex, rowIndex, ((TimeStampsRealmProxyInterface)object).realmGet$timeStamp(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class);
        long tableNativePtr = table.getNativeTablePointer();
        TimeStampsColumnInfo columnInfo = (TimeStampsColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.camacuasoft.jazzfestivalapp.Models.TimeStamps object = null;
        while (objects.hasNext()) {
            object = (com.camacuasoft.jazzfestivalapp.Models.TimeStamps) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((TimeStampsRealmProxyInterface) object).realmGet$ID();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((TimeStampsRealmProxyInterface) object).realmGet$ID());
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(((TimeStampsRealmProxyInterface) object).realmGet$ID(), false);
                }
                cache.put(object, rowIndex);
                String realmGet$tsName = ((TimeStampsRealmProxyInterface)object).realmGet$tsName();
                if (realmGet$tsName != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.tsNameIndex, rowIndex, realmGet$tsName, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.tsNameIndex, rowIndex, false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.timeStampIndex, rowIndex, ((TimeStampsRealmProxyInterface)object).realmGet$timeStamp(), false);
            }
        }
    }

    public static com.camacuasoft.jazzfestivalapp.Models.TimeStamps createDetachedCopy(com.camacuasoft.jazzfestivalapp.Models.TimeStamps realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.camacuasoft.jazzfestivalapp.Models.TimeStamps unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.camacuasoft.jazzfestivalapp.Models.TimeStamps)cachedObject.object;
            } else {
                unmanagedObject = (com.camacuasoft.jazzfestivalapp.Models.TimeStamps)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.camacuasoft.jazzfestivalapp.Models.TimeStamps();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((TimeStampsRealmProxyInterface) unmanagedObject).realmSet$ID(((TimeStampsRealmProxyInterface) realmObject).realmGet$ID());
        ((TimeStampsRealmProxyInterface) unmanagedObject).realmSet$tsName(((TimeStampsRealmProxyInterface) realmObject).realmGet$tsName());
        ((TimeStampsRealmProxyInterface) unmanagedObject).realmSet$timeStamp(((TimeStampsRealmProxyInterface) realmObject).realmGet$timeStamp());
        return unmanagedObject;
    }

    static com.camacuasoft.jazzfestivalapp.Models.TimeStamps update(Realm realm, com.camacuasoft.jazzfestivalapp.Models.TimeStamps realmObject, com.camacuasoft.jazzfestivalapp.Models.TimeStamps newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((TimeStampsRealmProxyInterface) realmObject).realmSet$tsName(((TimeStampsRealmProxyInterface) newObject).realmGet$tsName());
        ((TimeStampsRealmProxyInterface) realmObject).realmSet$timeStamp(((TimeStampsRealmProxyInterface) newObject).realmGet$timeStamp());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("TimeStamps = [");
        stringBuilder.append("{ID:");
        stringBuilder.append(realmGet$ID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tsName:");
        stringBuilder.append(realmGet$tsName() != null ? realmGet$tsName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{timeStamp:");
        stringBuilder.append(realmGet$timeStamp());
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
        TimeStampsRealmProxy aTimeStamps = (TimeStampsRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aTimeStamps.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTimeStamps.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aTimeStamps.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
