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

public class FavoritesRealmProxy extends com.camacuasoft.jazzfestivalapp.Models.Favorites
    implements RealmObjectProxy, FavoritesRealmProxyInterface {

    static final class FavoritesColumnInfo extends ColumnInfo
        implements Cloneable {

        public long IDIndex;
        public long favArtistIndex;

        FavoritesColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(2);
            this.IDIndex = getValidColumnIndex(path, table, "Favorites", "ID");
            indicesMap.put("ID", this.IDIndex);
            this.favArtistIndex = getValidColumnIndex(path, table, "Favorites", "favArtist");
            indicesMap.put("favArtist", this.favArtistIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final FavoritesColumnInfo otherInfo = (FavoritesColumnInfo) other;
            this.IDIndex = otherInfo.IDIndex;
            this.favArtistIndex = otherInfo.favArtistIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final FavoritesColumnInfo clone() {
            return (FavoritesColumnInfo) super.clone();
        }

    }
    private FavoritesColumnInfo columnInfo;
    private ProxyState<com.camacuasoft.jazzfestivalapp.Models.Favorites> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("ID");
        fieldNames.add("favArtist");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    FavoritesRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (FavoritesColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.camacuasoft.jazzfestivalapp.Models.Favorites>(this);
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

    public com.camacuasoft.jazzfestivalapp.Models.Artist realmGet$favArtist() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNullLink(columnInfo.favArtistIndex)) {
            return null;
        }
        return proxyState.getRealm$realm().get(com.camacuasoft.jazzfestivalapp.Models.Artist.class, proxyState.getRow$realm().getLink(columnInfo.favArtistIndex), false, Collections.<String>emptyList());
    }

    public void realmSet$favArtist(com.camacuasoft.jazzfestivalapp.Models.Artist value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("favArtist")) {
                return;
            }
            if (value != null && !RealmObject.isManaged(value)) {
                value = ((Realm) proxyState.getRealm$realm()).copyToRealm(value);
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                // Table#nullifyLink() does not support default value. Just using Row.
                row.nullifyLink(columnInfo.favArtistIndex);
                return;
            }
            if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            }
            if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            }
            row.getTable().setLink(columnInfo.favArtistIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().nullifyLink(columnInfo.favArtistIndex);
            return;
        }
        if (!(RealmObject.isManaged(value) && RealmObject.isValid(value))) {
            throw new IllegalArgumentException("'value' is not a valid managed object.");
        }
        if (((RealmObjectProxy)value).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
            throw new IllegalArgumentException("'value' belongs to a different Realm.");
        }
        proxyState.getRow$realm().setLink(columnInfo.favArtistIndex, ((RealmObjectProxy)value).realmGet$proxyState().getRow$realm().getIndex());
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Favorites")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Favorites");
            realmObjectSchema.add(new Property("ID", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED));
            if (!realmSchema.contains("Artist")) {
                ArtistRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add(new Property("favArtist", RealmFieldType.OBJECT, realmSchema.get("Artist")));
            return realmObjectSchema;
        }
        return realmSchema.get("Favorites");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_Favorites")) {
            Table table = sharedRealm.getTable("class_Favorites");
            table.addColumn(RealmFieldType.INTEGER, "ID", Table.NOT_NULLABLE);
            if (!sharedRealm.hasTable("class_Artist")) {
                ArtistRealmProxy.initTable(sharedRealm);
            }
            table.addColumnLink(RealmFieldType.OBJECT, "favArtist", sharedRealm.getTable("class_Artist"));
            table.addSearchIndex(table.getColumnIndex("ID"));
            table.setPrimaryKey("ID");
            return table;
        }
        return sharedRealm.getTable("class_Favorites");
    }

    public static FavoritesColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_Favorites")) {
            Table table = sharedRealm.getTable("class_Favorites");
            final long columnCount = table.getColumnCount();
            if (columnCount != 2) {
                if (columnCount < 2) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 2 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 2 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 2 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < columnCount; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final FavoritesColumnInfo columnInfo = new FavoritesColumnInfo(sharedRealm.getPath(), table);

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
            if (!columnTypes.containsKey("favArtist")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'favArtist' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("favArtist") != RealmFieldType.OBJECT) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Artist' for field 'favArtist'");
            }
            if (!sharedRealm.hasTable("class_Artist")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_Artist' for field 'favArtist'");
            }
            Table table_1 = sharedRealm.getTable("class_Artist");
            if (!table.getLinkTarget(columnInfo.favArtistIndex).hasSameSchema(table_1)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmObject for field 'favArtist': '" + table.getLinkTarget(columnInfo.favArtistIndex).getName() + "' expected - was '" + table_1.getName() + "'");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Favorites' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Favorites";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.camacuasoft.jazzfestivalapp.Models.Favorites createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        com.camacuasoft.jazzfestivalapp.Models.Favorites obj = null;
        if (update) {
            Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Favorites.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("ID")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("ID"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Favorites.class), false, Collections.<String> emptyList());
                    obj = new io.realm.FavoritesRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("favArtist")) {
                excludeFields.add("favArtist");
            }
            if (json.has("ID")) {
                if (json.isNull("ID")) {
                    obj = (io.realm.FavoritesRealmProxy) realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.Favorites.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.FavoritesRealmProxy) realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.Favorites.class, json.getInt("ID"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'ID'.");
            }
        }
        if (json.has("favArtist")) {
            if (json.isNull("favArtist")) {
                ((FavoritesRealmProxyInterface) obj).realmSet$favArtist(null);
            } else {
                com.camacuasoft.jazzfestivalapp.Models.Artist favArtistObj = ArtistRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("favArtist"), update);
                ((FavoritesRealmProxyInterface) obj).realmSet$favArtist(favArtistObj);
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.camacuasoft.jazzfestivalapp.Models.Favorites createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.camacuasoft.jazzfestivalapp.Models.Favorites obj = new com.camacuasoft.jazzfestivalapp.Models.Favorites();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("ID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'ID' to null.");
                } else {
                    ((FavoritesRealmProxyInterface) obj).realmSet$ID((int) reader.nextInt());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("favArtist")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((FavoritesRealmProxyInterface) obj).realmSet$favArtist(null);
                } else {
                    com.camacuasoft.jazzfestivalapp.Models.Artist favArtistObj = ArtistRealmProxy.createUsingJsonStream(realm, reader);
                    ((FavoritesRealmProxyInterface) obj).realmSet$favArtist(favArtistObj);
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

    public static com.camacuasoft.jazzfestivalapp.Models.Favorites copyOrUpdate(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Favorites object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.camacuasoft.jazzfestivalapp.Models.Favorites) cachedRealmObject;
        } else {
            com.camacuasoft.jazzfestivalapp.Models.Favorites realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Favorites.class);
                long pkColumnIndex = table.getPrimaryKey();
                long rowIndex = table.findFirstLong(pkColumnIndex, ((FavoritesRealmProxyInterface) object).realmGet$ID());
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Favorites.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.FavoritesRealmProxy();
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

    public static com.camacuasoft.jazzfestivalapp.Models.Favorites copy(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Favorites newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.camacuasoft.jazzfestivalapp.Models.Favorites) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.camacuasoft.jazzfestivalapp.Models.Favorites realmObject = realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.Favorites.class, ((FavoritesRealmProxyInterface) newObject).realmGet$ID(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);

            com.camacuasoft.jazzfestivalapp.Models.Artist favArtistObj = ((FavoritesRealmProxyInterface) newObject).realmGet$favArtist();
            if (favArtistObj != null) {
                com.camacuasoft.jazzfestivalapp.Models.Artist cachefavArtist = (com.camacuasoft.jazzfestivalapp.Models.Artist) cache.get(favArtistObj);
                if (cachefavArtist != null) {
                    ((FavoritesRealmProxyInterface) realmObject).realmSet$favArtist(cachefavArtist);
                } else {
                    ((FavoritesRealmProxyInterface) realmObject).realmSet$favArtist(ArtistRealmProxy.copyOrUpdate(realm, favArtistObj, update, cache));
                }
            } else {
                ((FavoritesRealmProxyInterface) realmObject).realmSet$favArtist(null);
            }
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Favorites object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Favorites.class);
        long tableNativePtr = table.getNativeTablePointer();
        FavoritesColumnInfo columnInfo = (FavoritesColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Favorites.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((FavoritesRealmProxyInterface) object).realmGet$ID();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((FavoritesRealmProxyInterface) object).realmGet$ID());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(((FavoritesRealmProxyInterface) object).realmGet$ID(), false);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);

        com.camacuasoft.jazzfestivalapp.Models.Artist favArtistObj = ((FavoritesRealmProxyInterface) object).realmGet$favArtist();
        if (favArtistObj != null) {
            Long cachefavArtist = cache.get(favArtistObj);
            if (cachefavArtist == null) {
                cachefavArtist = ArtistRealmProxy.insert(realm, favArtistObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.favArtistIndex, rowIndex, cachefavArtist, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Favorites.class);
        long tableNativePtr = table.getNativeTablePointer();
        FavoritesColumnInfo columnInfo = (FavoritesColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Favorites.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.camacuasoft.jazzfestivalapp.Models.Favorites object = null;
        while (objects.hasNext()) {
            object = (com.camacuasoft.jazzfestivalapp.Models.Favorites) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((FavoritesRealmProxyInterface) object).realmGet$ID();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((FavoritesRealmProxyInterface) object).realmGet$ID());
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(((FavoritesRealmProxyInterface) object).realmGet$ID(), false);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);

                com.camacuasoft.jazzfestivalapp.Models.Artist favArtistObj = ((FavoritesRealmProxyInterface) object).realmGet$favArtist();
                if (favArtistObj != null) {
                    Long cachefavArtist = cache.get(favArtistObj);
                    if (cachefavArtist == null) {
                        cachefavArtist = ArtistRealmProxy.insert(realm, favArtistObj, cache);
                    }
                    table.setLink(columnInfo.favArtistIndex, rowIndex, cachefavArtist, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Favorites object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Favorites.class);
        long tableNativePtr = table.getNativeTablePointer();
        FavoritesColumnInfo columnInfo = (FavoritesColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Favorites.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((FavoritesRealmProxyInterface) object).realmGet$ID();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((FavoritesRealmProxyInterface) object).realmGet$ID());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(((FavoritesRealmProxyInterface) object).realmGet$ID(), false);
        }
        cache.put(object, rowIndex);

        com.camacuasoft.jazzfestivalapp.Models.Artist favArtistObj = ((FavoritesRealmProxyInterface) object).realmGet$favArtist();
        if (favArtistObj != null) {
            Long cachefavArtist = cache.get(favArtistObj);
            if (cachefavArtist == null) {
                cachefavArtist = ArtistRealmProxy.insertOrUpdate(realm, favArtistObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.favArtistIndex, rowIndex, cachefavArtist, false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.favArtistIndex, rowIndex);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Favorites.class);
        long tableNativePtr = table.getNativeTablePointer();
        FavoritesColumnInfo columnInfo = (FavoritesColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Favorites.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.camacuasoft.jazzfestivalapp.Models.Favorites object = null;
        while (objects.hasNext()) {
            object = (com.camacuasoft.jazzfestivalapp.Models.Favorites) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((FavoritesRealmProxyInterface) object).realmGet$ID();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((FavoritesRealmProxyInterface) object).realmGet$ID());
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(((FavoritesRealmProxyInterface) object).realmGet$ID(), false);
                }
                cache.put(object, rowIndex);

                com.camacuasoft.jazzfestivalapp.Models.Artist favArtistObj = ((FavoritesRealmProxyInterface) object).realmGet$favArtist();
                if (favArtistObj != null) {
                    Long cachefavArtist = cache.get(favArtistObj);
                    if (cachefavArtist == null) {
                        cachefavArtist = ArtistRealmProxy.insertOrUpdate(realm, favArtistObj, cache);
                    }
                    Table.nativeSetLink(tableNativePtr, columnInfo.favArtistIndex, rowIndex, cachefavArtist, false);
                } else {
                    Table.nativeNullifyLink(tableNativePtr, columnInfo.favArtistIndex, rowIndex);
                }
            }
        }
    }

    public static com.camacuasoft.jazzfestivalapp.Models.Favorites createDetachedCopy(com.camacuasoft.jazzfestivalapp.Models.Favorites realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.camacuasoft.jazzfestivalapp.Models.Favorites unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.camacuasoft.jazzfestivalapp.Models.Favorites)cachedObject.object;
            } else {
                unmanagedObject = (com.camacuasoft.jazzfestivalapp.Models.Favorites)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.camacuasoft.jazzfestivalapp.Models.Favorites();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((FavoritesRealmProxyInterface) unmanagedObject).realmSet$ID(((FavoritesRealmProxyInterface) realmObject).realmGet$ID());

        // Deep copy of favArtist
        ((FavoritesRealmProxyInterface) unmanagedObject).realmSet$favArtist(ArtistRealmProxy.createDetachedCopy(((FavoritesRealmProxyInterface) realmObject).realmGet$favArtist(), currentDepth + 1, maxDepth, cache));
        return unmanagedObject;
    }

    static com.camacuasoft.jazzfestivalapp.Models.Favorites update(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Favorites realmObject, com.camacuasoft.jazzfestivalapp.Models.Favorites newObject, Map<RealmModel, RealmObjectProxy> cache) {
        com.camacuasoft.jazzfestivalapp.Models.Artist favArtistObj = ((FavoritesRealmProxyInterface) newObject).realmGet$favArtist();
        if (favArtistObj != null) {
            com.camacuasoft.jazzfestivalapp.Models.Artist cachefavArtist = (com.camacuasoft.jazzfestivalapp.Models.Artist) cache.get(favArtistObj);
            if (cachefavArtist != null) {
                ((FavoritesRealmProxyInterface) realmObject).realmSet$favArtist(cachefavArtist);
            } else {
                ((FavoritesRealmProxyInterface) realmObject).realmSet$favArtist(ArtistRealmProxy.copyOrUpdate(realm, favArtistObj, true, cache));
            }
        } else {
            ((FavoritesRealmProxyInterface) realmObject).realmSet$favArtist(null);
        }
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Favorites = [");
        stringBuilder.append("{ID:");
        stringBuilder.append(realmGet$ID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{favArtist:");
        stringBuilder.append(realmGet$favArtist() != null ? "Artist" : "null");
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
        FavoritesRealmProxy aFavorites = (FavoritesRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aFavorites.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aFavorites.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aFavorites.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
