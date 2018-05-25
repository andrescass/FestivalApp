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

public class ArtistRealmProxy extends com.camacuasoft.jazzfestivalapp.Models.Artist
    implements RealmObjectProxy, ArtistRealmProxyInterface {

    static final class ArtistColumnInfo extends ColumnInfo
        implements Cloneable {

        public long IDIndex;
        public long nameIndex;
        public long photoResIndex;
        public long photoResIntIndex;
        public long photoResUriIndex;
        public long originIndex;
        public long bioIndex;
        public long isFavoriteIndex;

        ArtistColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(8);
            this.IDIndex = getValidColumnIndex(path, table, "Artist", "ID");
            indicesMap.put("ID", this.IDIndex);
            this.nameIndex = getValidColumnIndex(path, table, "Artist", "name");
            indicesMap.put("name", this.nameIndex);
            this.photoResIndex = getValidColumnIndex(path, table, "Artist", "photoRes");
            indicesMap.put("photoRes", this.photoResIndex);
            this.photoResIntIndex = getValidColumnIndex(path, table, "Artist", "photoResInt");
            indicesMap.put("photoResInt", this.photoResIntIndex);
            this.photoResUriIndex = getValidColumnIndex(path, table, "Artist", "photoResUri");
            indicesMap.put("photoResUri", this.photoResUriIndex);
            this.originIndex = getValidColumnIndex(path, table, "Artist", "origin");
            indicesMap.put("origin", this.originIndex);
            this.bioIndex = getValidColumnIndex(path, table, "Artist", "bio");
            indicesMap.put("bio", this.bioIndex);
            this.isFavoriteIndex = getValidColumnIndex(path, table, "Artist", "isFavorite");
            indicesMap.put("isFavorite", this.isFavoriteIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final ArtistColumnInfo otherInfo = (ArtistColumnInfo) other;
            this.IDIndex = otherInfo.IDIndex;
            this.nameIndex = otherInfo.nameIndex;
            this.photoResIndex = otherInfo.photoResIndex;
            this.photoResIntIndex = otherInfo.photoResIntIndex;
            this.photoResUriIndex = otherInfo.photoResUriIndex;
            this.originIndex = otherInfo.originIndex;
            this.bioIndex = otherInfo.bioIndex;
            this.isFavoriteIndex = otherInfo.isFavoriteIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final ArtistColumnInfo clone() {
            return (ArtistColumnInfo) super.clone();
        }

    }
    private ArtistColumnInfo columnInfo;
    private ProxyState<com.camacuasoft.jazzfestivalapp.Models.Artist> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("ID");
        fieldNames.add("name");
        fieldNames.add("photoRes");
        fieldNames.add("photoResInt");
        fieldNames.add("photoResUri");
        fieldNames.add("origin");
        fieldNames.add("bio");
        fieldNames.add("isFavorite");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ArtistRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (ArtistColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.camacuasoft.jazzfestivalapp.Models.Artist>(this);
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
    public String realmGet$name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameIndex);
    }

    public void realmSet$name(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'name' to null.");
            }
            row.getTable().setString(columnInfo.nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'name' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.nameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$photoRes() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.photoResIndex);
    }

    public void realmSet$photoRes(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.photoResIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.photoResIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.photoResIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.photoResIndex, value);
    }

    @SuppressWarnings("cast")
    public int realmGet$photoResInt() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.photoResIntIndex);
    }

    public void realmSet$photoResInt(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.photoResIntIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.photoResIntIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$photoResUri() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.photoResUriIndex);
    }

    public void realmSet$photoResUri(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.photoResUriIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.photoResUriIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.photoResUriIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.photoResUriIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$origin() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.originIndex);
    }

    public void realmSet$origin(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.originIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.originIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.originIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.originIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$bio() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.bioIndex);
    }

    public void realmSet$bio(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.bioIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.bioIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.bioIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.bioIndex, value);
    }

    @SuppressWarnings("cast")
    public boolean realmGet$isFavorite() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.isFavoriteIndex);
    }

    public void realmSet$isFavorite(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.isFavoriteIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.isFavoriteIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Artist")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Artist");
            realmObjectSchema.add(new Property("ID", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("photoRes", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("photoResInt", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("photoResUri", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("origin", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("bio", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("isFavorite", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("Artist");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_Artist")) {
            Table table = sharedRealm.getTable("class_Artist");
            table.addColumn(RealmFieldType.INTEGER, "ID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "name", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "photoRes", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "photoResInt", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "photoResUri", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "origin", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "bio", Table.NULLABLE);
            table.addColumn(RealmFieldType.BOOLEAN, "isFavorite", Table.NOT_NULLABLE);
            table.addSearchIndex(table.getColumnIndex("ID"));
            table.setPrimaryKey("ID");
            return table;
        }
        return sharedRealm.getTable("class_Artist");
    }

    public static ArtistColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_Artist")) {
            Table table = sharedRealm.getTable("class_Artist");
            final long columnCount = table.getColumnCount();
            if (columnCount != 8) {
                if (columnCount < 8) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 8 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 8 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 8 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < columnCount; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final ArtistColumnInfo columnInfo = new ArtistColumnInfo(sharedRealm.getPath(), table);

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
            if (!columnTypes.containsKey("name")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("name") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'name' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.nameIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'name' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'name' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("photoRes")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'photoRes' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("photoRes") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'photoRes' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.photoResIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'photoRes' is required. Either set @Required to field 'photoRes' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("photoResInt")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'photoResInt' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("photoResInt") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'photoResInt' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.photoResIntIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'photoResInt' does support null values in the existing Realm file. Use corresponding boxed type for field 'photoResInt' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("photoResUri")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'photoResUri' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("photoResUri") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'photoResUri' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.photoResUriIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'photoResUri' is required. Either set @Required to field 'photoResUri' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("origin")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'origin' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("origin") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'origin' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.originIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'origin' is required. Either set @Required to field 'origin' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("bio")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'bio' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("bio") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'bio' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.bioIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'bio' is required. Either set @Required to field 'bio' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("isFavorite")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isFavorite' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("isFavorite") != RealmFieldType.BOOLEAN) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isFavorite' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.isFavoriteIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isFavorite' does support null values in the existing Realm file. Use corresponding boxed type for field 'isFavorite' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Artist' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Artist";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.camacuasoft.jazzfestivalapp.Models.Artist createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.camacuasoft.jazzfestivalapp.Models.Artist obj = null;
        if (update) {
            Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Artist.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("ID")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("ID"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Artist.class), false, Collections.<String> emptyList());
                    obj = new io.realm.ArtistRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("ID")) {
                if (json.isNull("ID")) {
                    obj = (io.realm.ArtistRealmProxy) realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.Artist.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.ArtistRealmProxy) realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.Artist.class, json.getInt("ID"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'ID'.");
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                ((ArtistRealmProxyInterface) obj).realmSet$name(null);
            } else {
                ((ArtistRealmProxyInterface) obj).realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("photoRes")) {
            if (json.isNull("photoRes")) {
                ((ArtistRealmProxyInterface) obj).realmSet$photoRes(null);
            } else {
                ((ArtistRealmProxyInterface) obj).realmSet$photoRes((String) json.getString("photoRes"));
            }
        }
        if (json.has("photoResInt")) {
            if (json.isNull("photoResInt")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'photoResInt' to null.");
            } else {
                ((ArtistRealmProxyInterface) obj).realmSet$photoResInt((int) json.getInt("photoResInt"));
            }
        }
        if (json.has("photoResUri")) {
            if (json.isNull("photoResUri")) {
                ((ArtistRealmProxyInterface) obj).realmSet$photoResUri(null);
            } else {
                ((ArtistRealmProxyInterface) obj).realmSet$photoResUri((String) json.getString("photoResUri"));
            }
        }
        if (json.has("origin")) {
            if (json.isNull("origin")) {
                ((ArtistRealmProxyInterface) obj).realmSet$origin(null);
            } else {
                ((ArtistRealmProxyInterface) obj).realmSet$origin((String) json.getString("origin"));
            }
        }
        if (json.has("bio")) {
            if (json.isNull("bio")) {
                ((ArtistRealmProxyInterface) obj).realmSet$bio(null);
            } else {
                ((ArtistRealmProxyInterface) obj).realmSet$bio((String) json.getString("bio"));
            }
        }
        if (json.has("isFavorite")) {
            if (json.isNull("isFavorite")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'isFavorite' to null.");
            } else {
                ((ArtistRealmProxyInterface) obj).realmSet$isFavorite((boolean) json.getBoolean("isFavorite"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.camacuasoft.jazzfestivalapp.Models.Artist createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.camacuasoft.jazzfestivalapp.Models.Artist obj = new com.camacuasoft.jazzfestivalapp.Models.Artist();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("ID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'ID' to null.");
                } else {
                    ((ArtistRealmProxyInterface) obj).realmSet$ID((int) reader.nextInt());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ArtistRealmProxyInterface) obj).realmSet$name(null);
                } else {
                    ((ArtistRealmProxyInterface) obj).realmSet$name((String) reader.nextString());
                }
            } else if (name.equals("photoRes")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ArtistRealmProxyInterface) obj).realmSet$photoRes(null);
                } else {
                    ((ArtistRealmProxyInterface) obj).realmSet$photoRes((String) reader.nextString());
                }
            } else if (name.equals("photoResInt")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'photoResInt' to null.");
                } else {
                    ((ArtistRealmProxyInterface) obj).realmSet$photoResInt((int) reader.nextInt());
                }
            } else if (name.equals("photoResUri")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ArtistRealmProxyInterface) obj).realmSet$photoResUri(null);
                } else {
                    ((ArtistRealmProxyInterface) obj).realmSet$photoResUri((String) reader.nextString());
                }
            } else if (name.equals("origin")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ArtistRealmProxyInterface) obj).realmSet$origin(null);
                } else {
                    ((ArtistRealmProxyInterface) obj).realmSet$origin((String) reader.nextString());
                }
            } else if (name.equals("bio")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ArtistRealmProxyInterface) obj).realmSet$bio(null);
                } else {
                    ((ArtistRealmProxyInterface) obj).realmSet$bio((String) reader.nextString());
                }
            } else if (name.equals("isFavorite")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isFavorite' to null.");
                } else {
                    ((ArtistRealmProxyInterface) obj).realmSet$isFavorite((boolean) reader.nextBoolean());
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

    public static com.camacuasoft.jazzfestivalapp.Models.Artist copyOrUpdate(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Artist object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.camacuasoft.jazzfestivalapp.Models.Artist) cachedRealmObject;
        } else {
            com.camacuasoft.jazzfestivalapp.Models.Artist realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Artist.class);
                long pkColumnIndex = table.getPrimaryKey();
                long rowIndex = table.findFirstLong(pkColumnIndex, ((ArtistRealmProxyInterface) object).realmGet$ID());
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Artist.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.ArtistRealmProxy();
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

    public static com.camacuasoft.jazzfestivalapp.Models.Artist copy(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Artist newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.camacuasoft.jazzfestivalapp.Models.Artist) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.camacuasoft.jazzfestivalapp.Models.Artist realmObject = realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.Artist.class, ((ArtistRealmProxyInterface) newObject).realmGet$ID(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((ArtistRealmProxyInterface) realmObject).realmSet$name(((ArtistRealmProxyInterface) newObject).realmGet$name());
            ((ArtistRealmProxyInterface) realmObject).realmSet$photoRes(((ArtistRealmProxyInterface) newObject).realmGet$photoRes());
            ((ArtistRealmProxyInterface) realmObject).realmSet$photoResInt(((ArtistRealmProxyInterface) newObject).realmGet$photoResInt());
            ((ArtistRealmProxyInterface) realmObject).realmSet$photoResUri(((ArtistRealmProxyInterface) newObject).realmGet$photoResUri());
            ((ArtistRealmProxyInterface) realmObject).realmSet$origin(((ArtistRealmProxyInterface) newObject).realmGet$origin());
            ((ArtistRealmProxyInterface) realmObject).realmSet$bio(((ArtistRealmProxyInterface) newObject).realmGet$bio());
            ((ArtistRealmProxyInterface) realmObject).realmSet$isFavorite(((ArtistRealmProxyInterface) newObject).realmGet$isFavorite());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Artist object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Artist.class);
        long tableNativePtr = table.getNativeTablePointer();
        ArtistColumnInfo columnInfo = (ArtistColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Artist.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((ArtistRealmProxyInterface) object).realmGet$ID();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((ArtistRealmProxyInterface) object).realmGet$ID());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(((ArtistRealmProxyInterface) object).realmGet$ID(), false);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$name = ((ArtistRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$photoRes = ((ArtistRealmProxyInterface)object).realmGet$photoRes();
        if (realmGet$photoRes != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.photoResIndex, rowIndex, realmGet$photoRes, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.photoResIntIndex, rowIndex, ((ArtistRealmProxyInterface)object).realmGet$photoResInt(), false);
        String realmGet$photoResUri = ((ArtistRealmProxyInterface)object).realmGet$photoResUri();
        if (realmGet$photoResUri != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.photoResUriIndex, rowIndex, realmGet$photoResUri, false);
        }
        String realmGet$origin = ((ArtistRealmProxyInterface)object).realmGet$origin();
        if (realmGet$origin != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.originIndex, rowIndex, realmGet$origin, false);
        }
        String realmGet$bio = ((ArtistRealmProxyInterface)object).realmGet$bio();
        if (realmGet$bio != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.bioIndex, rowIndex, realmGet$bio, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isFavoriteIndex, rowIndex, ((ArtistRealmProxyInterface)object).realmGet$isFavorite(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Artist.class);
        long tableNativePtr = table.getNativeTablePointer();
        ArtistColumnInfo columnInfo = (ArtistColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Artist.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.camacuasoft.jazzfestivalapp.Models.Artist object = null;
        while (objects.hasNext()) {
            object = (com.camacuasoft.jazzfestivalapp.Models.Artist) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((ArtistRealmProxyInterface) object).realmGet$ID();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((ArtistRealmProxyInterface) object).realmGet$ID());
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(((ArtistRealmProxyInterface) object).realmGet$ID(), false);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                String realmGet$name = ((ArtistRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                }
                String realmGet$photoRes = ((ArtistRealmProxyInterface)object).realmGet$photoRes();
                if (realmGet$photoRes != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.photoResIndex, rowIndex, realmGet$photoRes, false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.photoResIntIndex, rowIndex, ((ArtistRealmProxyInterface)object).realmGet$photoResInt(), false);
                String realmGet$photoResUri = ((ArtistRealmProxyInterface)object).realmGet$photoResUri();
                if (realmGet$photoResUri != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.photoResUriIndex, rowIndex, realmGet$photoResUri, false);
                }
                String realmGet$origin = ((ArtistRealmProxyInterface)object).realmGet$origin();
                if (realmGet$origin != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.originIndex, rowIndex, realmGet$origin, false);
                }
                String realmGet$bio = ((ArtistRealmProxyInterface)object).realmGet$bio();
                if (realmGet$bio != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.bioIndex, rowIndex, realmGet$bio, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isFavoriteIndex, rowIndex, ((ArtistRealmProxyInterface)object).realmGet$isFavorite(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Artist object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Artist.class);
        long tableNativePtr = table.getNativeTablePointer();
        ArtistColumnInfo columnInfo = (ArtistColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Artist.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((ArtistRealmProxyInterface) object).realmGet$ID();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((ArtistRealmProxyInterface) object).realmGet$ID());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(((ArtistRealmProxyInterface) object).realmGet$ID(), false);
        }
        cache.put(object, rowIndex);
        String realmGet$name = ((ArtistRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$photoRes = ((ArtistRealmProxyInterface)object).realmGet$photoRes();
        if (realmGet$photoRes != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.photoResIndex, rowIndex, realmGet$photoRes, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.photoResIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.photoResIntIndex, rowIndex, ((ArtistRealmProxyInterface)object).realmGet$photoResInt(), false);
        String realmGet$photoResUri = ((ArtistRealmProxyInterface)object).realmGet$photoResUri();
        if (realmGet$photoResUri != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.photoResUriIndex, rowIndex, realmGet$photoResUri, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.photoResUriIndex, rowIndex, false);
        }
        String realmGet$origin = ((ArtistRealmProxyInterface)object).realmGet$origin();
        if (realmGet$origin != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.originIndex, rowIndex, realmGet$origin, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.originIndex, rowIndex, false);
        }
        String realmGet$bio = ((ArtistRealmProxyInterface)object).realmGet$bio();
        if (realmGet$bio != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.bioIndex, rowIndex, realmGet$bio, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.bioIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isFavoriteIndex, rowIndex, ((ArtistRealmProxyInterface)object).realmGet$isFavorite(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Artist.class);
        long tableNativePtr = table.getNativeTablePointer();
        ArtistColumnInfo columnInfo = (ArtistColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Artist.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.camacuasoft.jazzfestivalapp.Models.Artist object = null;
        while (objects.hasNext()) {
            object = (com.camacuasoft.jazzfestivalapp.Models.Artist) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((ArtistRealmProxyInterface) object).realmGet$ID();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((ArtistRealmProxyInterface) object).realmGet$ID());
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(((ArtistRealmProxyInterface) object).realmGet$ID(), false);
                }
                cache.put(object, rowIndex);
                String realmGet$name = ((ArtistRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                }
                String realmGet$photoRes = ((ArtistRealmProxyInterface)object).realmGet$photoRes();
                if (realmGet$photoRes != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.photoResIndex, rowIndex, realmGet$photoRes, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.photoResIndex, rowIndex, false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.photoResIntIndex, rowIndex, ((ArtistRealmProxyInterface)object).realmGet$photoResInt(), false);
                String realmGet$photoResUri = ((ArtistRealmProxyInterface)object).realmGet$photoResUri();
                if (realmGet$photoResUri != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.photoResUriIndex, rowIndex, realmGet$photoResUri, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.photoResUriIndex, rowIndex, false);
                }
                String realmGet$origin = ((ArtistRealmProxyInterface)object).realmGet$origin();
                if (realmGet$origin != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.originIndex, rowIndex, realmGet$origin, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.originIndex, rowIndex, false);
                }
                String realmGet$bio = ((ArtistRealmProxyInterface)object).realmGet$bio();
                if (realmGet$bio != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.bioIndex, rowIndex, realmGet$bio, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.bioIndex, rowIndex, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isFavoriteIndex, rowIndex, ((ArtistRealmProxyInterface)object).realmGet$isFavorite(), false);
            }
        }
    }

    public static com.camacuasoft.jazzfestivalapp.Models.Artist createDetachedCopy(com.camacuasoft.jazzfestivalapp.Models.Artist realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.camacuasoft.jazzfestivalapp.Models.Artist unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.camacuasoft.jazzfestivalapp.Models.Artist)cachedObject.object;
            } else {
                unmanagedObject = (com.camacuasoft.jazzfestivalapp.Models.Artist)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.camacuasoft.jazzfestivalapp.Models.Artist();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((ArtistRealmProxyInterface) unmanagedObject).realmSet$ID(((ArtistRealmProxyInterface) realmObject).realmGet$ID());
        ((ArtistRealmProxyInterface) unmanagedObject).realmSet$name(((ArtistRealmProxyInterface) realmObject).realmGet$name());
        ((ArtistRealmProxyInterface) unmanagedObject).realmSet$photoRes(((ArtistRealmProxyInterface) realmObject).realmGet$photoRes());
        ((ArtistRealmProxyInterface) unmanagedObject).realmSet$photoResInt(((ArtistRealmProxyInterface) realmObject).realmGet$photoResInt());
        ((ArtistRealmProxyInterface) unmanagedObject).realmSet$photoResUri(((ArtistRealmProxyInterface) realmObject).realmGet$photoResUri());
        ((ArtistRealmProxyInterface) unmanagedObject).realmSet$origin(((ArtistRealmProxyInterface) realmObject).realmGet$origin());
        ((ArtistRealmProxyInterface) unmanagedObject).realmSet$bio(((ArtistRealmProxyInterface) realmObject).realmGet$bio());
        ((ArtistRealmProxyInterface) unmanagedObject).realmSet$isFavorite(((ArtistRealmProxyInterface) realmObject).realmGet$isFavorite());
        return unmanagedObject;
    }

    static com.camacuasoft.jazzfestivalapp.Models.Artist update(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Artist realmObject, com.camacuasoft.jazzfestivalapp.Models.Artist newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((ArtistRealmProxyInterface) realmObject).realmSet$name(((ArtistRealmProxyInterface) newObject).realmGet$name());
        ((ArtistRealmProxyInterface) realmObject).realmSet$photoRes(((ArtistRealmProxyInterface) newObject).realmGet$photoRes());
        ((ArtistRealmProxyInterface) realmObject).realmSet$photoResInt(((ArtistRealmProxyInterface) newObject).realmGet$photoResInt());
        ((ArtistRealmProxyInterface) realmObject).realmSet$photoResUri(((ArtistRealmProxyInterface) newObject).realmGet$photoResUri());
        ((ArtistRealmProxyInterface) realmObject).realmSet$origin(((ArtistRealmProxyInterface) newObject).realmGet$origin());
        ((ArtistRealmProxyInterface) realmObject).realmSet$bio(((ArtistRealmProxyInterface) newObject).realmGet$bio());
        ((ArtistRealmProxyInterface) realmObject).realmSet$isFavorite(((ArtistRealmProxyInterface) newObject).realmGet$isFavorite());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Artist = [");
        stringBuilder.append("{ID:");
        stringBuilder.append(realmGet$ID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{photoRes:");
        stringBuilder.append(realmGet$photoRes() != null ? realmGet$photoRes() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{photoResInt:");
        stringBuilder.append(realmGet$photoResInt());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{photoResUri:");
        stringBuilder.append(realmGet$photoResUri() != null ? realmGet$photoResUri() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{origin:");
        stringBuilder.append(realmGet$origin() != null ? realmGet$origin() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{bio:");
        stringBuilder.append(realmGet$bio() != null ? realmGet$bio() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{isFavorite:");
        stringBuilder.append(realmGet$isFavorite());
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
        ArtistRealmProxy aArtist = (ArtistRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aArtist.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aArtist.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aArtist.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
