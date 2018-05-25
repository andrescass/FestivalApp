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

public class ShowRealmProxy extends com.camacuasoft.jazzfestivalapp.Models.Show
    implements RealmObjectProxy, ShowRealmProxyInterface {

    static final class ShowColumnInfo extends ColumnInfo
        implements Cloneable {

        public long IDIndex;
        public long dateIndex;
        public long artistIndex;
        public long descriptionIndex;
        public long artistIDIndex;
        public long dateIntIndex;

        ShowColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(6);
            this.IDIndex = getValidColumnIndex(path, table, "Show", "ID");
            indicesMap.put("ID", this.IDIndex);
            this.dateIndex = getValidColumnIndex(path, table, "Show", "date");
            indicesMap.put("date", this.dateIndex);
            this.artistIndex = getValidColumnIndex(path, table, "Show", "artist");
            indicesMap.put("artist", this.artistIndex);
            this.descriptionIndex = getValidColumnIndex(path, table, "Show", "description");
            indicesMap.put("description", this.descriptionIndex);
            this.artistIDIndex = getValidColumnIndex(path, table, "Show", "artistID");
            indicesMap.put("artistID", this.artistIDIndex);
            this.dateIntIndex = getValidColumnIndex(path, table, "Show", "dateInt");
            indicesMap.put("dateInt", this.dateIntIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final ShowColumnInfo otherInfo = (ShowColumnInfo) other;
            this.IDIndex = otherInfo.IDIndex;
            this.dateIndex = otherInfo.dateIndex;
            this.artistIndex = otherInfo.artistIndex;
            this.descriptionIndex = otherInfo.descriptionIndex;
            this.artistIDIndex = otherInfo.artistIDIndex;
            this.dateIntIndex = otherInfo.dateIntIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final ShowColumnInfo clone() {
            return (ShowColumnInfo) super.clone();
        }

    }
    private ShowColumnInfo columnInfo;
    private ProxyState<com.camacuasoft.jazzfestivalapp.Models.Show> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("ID");
        fieldNames.add("date");
        fieldNames.add("artist");
        fieldNames.add("description");
        fieldNames.add("artistID");
        fieldNames.add("dateInt");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ShowRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (ShowColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.camacuasoft.jazzfestivalapp.Models.Show>(this);
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
    public Date realmGet$date() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.dateIndex)) {
            return null;
        }
        return (java.util.Date) proxyState.getRow$realm().getDate(columnInfo.dateIndex);
    }

    public void realmSet$date(Date value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.dateIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setDate(columnInfo.dateIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.dateIndex);
            return;
        }
        proxyState.getRow$realm().setDate(columnInfo.dateIndex, value);
    }

    public com.camacuasoft.jazzfestivalapp.Models.Artist realmGet$artist() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNullLink(columnInfo.artistIndex)) {
            return null;
        }
        return proxyState.getRealm$realm().get(com.camacuasoft.jazzfestivalapp.Models.Artist.class, proxyState.getRow$realm().getLink(columnInfo.artistIndex), false, Collections.<String>emptyList());
    }

    public void realmSet$artist(com.camacuasoft.jazzfestivalapp.Models.Artist value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("artist")) {
                return;
            }
            if (value != null && !RealmObject.isManaged(value)) {
                value = ((Realm) proxyState.getRealm$realm()).copyToRealm(value);
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                // Table#nullifyLink() does not support default value. Just using Row.
                row.nullifyLink(columnInfo.artistIndex);
                return;
            }
            if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            }
            if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            }
            row.getTable().setLink(columnInfo.artistIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().nullifyLink(columnInfo.artistIndex);
            return;
        }
        if (!(RealmObject.isManaged(value) && RealmObject.isValid(value))) {
            throw new IllegalArgumentException("'value' is not a valid managed object.");
        }
        if (((RealmObjectProxy)value).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
            throw new IllegalArgumentException("'value' belongs to a different Realm.");
        }
        proxyState.getRow$realm().setLink(columnInfo.artistIndex, ((RealmObjectProxy)value).realmGet$proxyState().getRow$realm().getIndex());
    }

    @SuppressWarnings("cast")
    public String realmGet$description() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.descriptionIndex);
    }

    public void realmSet$description(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.descriptionIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.descriptionIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.descriptionIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.descriptionIndex, value);
    }

    @SuppressWarnings("cast")
    public int realmGet$artistID() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.artistIDIndex);
    }

    public void realmSet$artistID(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.artistIDIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.artistIDIndex, value);
    }

    @SuppressWarnings("cast")
    public long realmGet$dateInt() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.dateIntIndex);
    }

    public void realmSet$dateInt(long value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.dateIntIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.dateIntIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Show")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Show");
            realmObjectSchema.add(new Property("ID", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("date", RealmFieldType.DATE, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            if (!realmSchema.contains("Artist")) {
                ArtistRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add(new Property("artist", RealmFieldType.OBJECT, realmSchema.get("Artist")));
            realmObjectSchema.add(new Property("description", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("artistID", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("dateInt", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("Show");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_Show")) {
            Table table = sharedRealm.getTable("class_Show");
            table.addColumn(RealmFieldType.INTEGER, "ID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.DATE, "date", Table.NULLABLE);
            if (!sharedRealm.hasTable("class_Artist")) {
                ArtistRealmProxy.initTable(sharedRealm);
            }
            table.addColumnLink(RealmFieldType.OBJECT, "artist", sharedRealm.getTable("class_Artist"));
            table.addColumn(RealmFieldType.STRING, "description", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "artistID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "dateInt", Table.NOT_NULLABLE);
            table.addSearchIndex(table.getColumnIndex("ID"));
            table.setPrimaryKey("ID");
            return table;
        }
        return sharedRealm.getTable("class_Show");
    }

    public static ShowColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_Show")) {
            Table table = sharedRealm.getTable("class_Show");
            final long columnCount = table.getColumnCount();
            if (columnCount != 6) {
                if (columnCount < 6) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 6 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 6 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 6 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < columnCount; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final ShowColumnInfo columnInfo = new ShowColumnInfo(sharedRealm.getPath(), table);

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
            if (!columnTypes.containsKey("date")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'date' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("date") != RealmFieldType.DATE) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Date' for field 'date' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.dateIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'date' is required. Either set @Required to field 'date' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("artist")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'artist' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("artist") != RealmFieldType.OBJECT) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Artist' for field 'artist'");
            }
            if (!sharedRealm.hasTable("class_Artist")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_Artist' for field 'artist'");
            }
            Table table_2 = sharedRealm.getTable("class_Artist");
            if (!table.getLinkTarget(columnInfo.artistIndex).hasSameSchema(table_2)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmObject for field 'artist': '" + table.getLinkTarget(columnInfo.artistIndex).getName() + "' expected - was '" + table_2.getName() + "'");
            }
            if (!columnTypes.containsKey("description")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'description' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("description") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'description' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.descriptionIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'description' is required. Either set @Required to field 'description' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("artistID")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'artistID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("artistID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'artistID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.artistIDIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'artistID' does support null values in the existing Realm file. Use corresponding boxed type for field 'artistID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("dateInt")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'dateInt' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("dateInt") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'long' for field 'dateInt' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.dateIntIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'dateInt' does support null values in the existing Realm file. Use corresponding boxed type for field 'dateInt' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Show' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Show";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.camacuasoft.jazzfestivalapp.Models.Show createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        com.camacuasoft.jazzfestivalapp.Models.Show obj = null;
        if (update) {
            Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Show.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("ID")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("ID"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Show.class), false, Collections.<String> emptyList());
                    obj = new io.realm.ShowRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("artist")) {
                excludeFields.add("artist");
            }
            if (json.has("ID")) {
                if (json.isNull("ID")) {
                    obj = (io.realm.ShowRealmProxy) realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.Show.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.ShowRealmProxy) realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.Show.class, json.getInt("ID"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'ID'.");
            }
        }
        if (json.has("date")) {
            if (json.isNull("date")) {
                ((ShowRealmProxyInterface) obj).realmSet$date(null);
            } else {
                Object timestamp = json.get("date");
                if (timestamp instanceof String) {
                    ((ShowRealmProxyInterface) obj).realmSet$date(JsonUtils.stringToDate((String) timestamp));
                } else {
                    ((ShowRealmProxyInterface) obj).realmSet$date(new Date(json.getLong("date")));
                }
            }
        }
        if (json.has("artist")) {
            if (json.isNull("artist")) {
                ((ShowRealmProxyInterface) obj).realmSet$artist(null);
            } else {
                com.camacuasoft.jazzfestivalapp.Models.Artist artistObj = ArtistRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("artist"), update);
                ((ShowRealmProxyInterface) obj).realmSet$artist(artistObj);
            }
        }
        if (json.has("description")) {
            if (json.isNull("description")) {
                ((ShowRealmProxyInterface) obj).realmSet$description(null);
            } else {
                ((ShowRealmProxyInterface) obj).realmSet$description((String) json.getString("description"));
            }
        }
        if (json.has("artistID")) {
            if (json.isNull("artistID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'artistID' to null.");
            } else {
                ((ShowRealmProxyInterface) obj).realmSet$artistID((int) json.getInt("artistID"));
            }
        }
        if (json.has("dateInt")) {
            if (json.isNull("dateInt")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'dateInt' to null.");
            } else {
                ((ShowRealmProxyInterface) obj).realmSet$dateInt((long) json.getLong("dateInt"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.camacuasoft.jazzfestivalapp.Models.Show createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.camacuasoft.jazzfestivalapp.Models.Show obj = new com.camacuasoft.jazzfestivalapp.Models.Show();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("ID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'ID' to null.");
                } else {
                    ((ShowRealmProxyInterface) obj).realmSet$ID((int) reader.nextInt());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("date")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ShowRealmProxyInterface) obj).realmSet$date(null);
                } else if (reader.peek() == JsonToken.NUMBER) {
                    long timestamp = reader.nextLong();
                    if (timestamp > -1) {
                        ((ShowRealmProxyInterface) obj).realmSet$date(new Date(timestamp));
                    }
                } else {
                    ((ShowRealmProxyInterface) obj).realmSet$date(JsonUtils.stringToDate(reader.nextString()));
                }
            } else if (name.equals("artist")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ShowRealmProxyInterface) obj).realmSet$artist(null);
                } else {
                    com.camacuasoft.jazzfestivalapp.Models.Artist artistObj = ArtistRealmProxy.createUsingJsonStream(realm, reader);
                    ((ShowRealmProxyInterface) obj).realmSet$artist(artistObj);
                }
            } else if (name.equals("description")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ShowRealmProxyInterface) obj).realmSet$description(null);
                } else {
                    ((ShowRealmProxyInterface) obj).realmSet$description((String) reader.nextString());
                }
            } else if (name.equals("artistID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'artistID' to null.");
                } else {
                    ((ShowRealmProxyInterface) obj).realmSet$artistID((int) reader.nextInt());
                }
            } else if (name.equals("dateInt")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'dateInt' to null.");
                } else {
                    ((ShowRealmProxyInterface) obj).realmSet$dateInt((long) reader.nextLong());
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

    public static com.camacuasoft.jazzfestivalapp.Models.Show copyOrUpdate(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Show object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.camacuasoft.jazzfestivalapp.Models.Show) cachedRealmObject;
        } else {
            com.camacuasoft.jazzfestivalapp.Models.Show realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Show.class);
                long pkColumnIndex = table.getPrimaryKey();
                long rowIndex = table.findFirstLong(pkColumnIndex, ((ShowRealmProxyInterface) object).realmGet$ID());
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Show.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.ShowRealmProxy();
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

    public static com.camacuasoft.jazzfestivalapp.Models.Show copy(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Show newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.camacuasoft.jazzfestivalapp.Models.Show) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.camacuasoft.jazzfestivalapp.Models.Show realmObject = realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.Show.class, ((ShowRealmProxyInterface) newObject).realmGet$ID(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((ShowRealmProxyInterface) realmObject).realmSet$date(((ShowRealmProxyInterface) newObject).realmGet$date());

            com.camacuasoft.jazzfestivalapp.Models.Artist artistObj = ((ShowRealmProxyInterface) newObject).realmGet$artist();
            if (artistObj != null) {
                com.camacuasoft.jazzfestivalapp.Models.Artist cacheartist = (com.camacuasoft.jazzfestivalapp.Models.Artist) cache.get(artistObj);
                if (cacheartist != null) {
                    ((ShowRealmProxyInterface) realmObject).realmSet$artist(cacheartist);
                } else {
                    ((ShowRealmProxyInterface) realmObject).realmSet$artist(ArtistRealmProxy.copyOrUpdate(realm, artistObj, update, cache));
                }
            } else {
                ((ShowRealmProxyInterface) realmObject).realmSet$artist(null);
            }
            ((ShowRealmProxyInterface) realmObject).realmSet$description(((ShowRealmProxyInterface) newObject).realmGet$description());
            ((ShowRealmProxyInterface) realmObject).realmSet$artistID(((ShowRealmProxyInterface) newObject).realmGet$artistID());
            ((ShowRealmProxyInterface) realmObject).realmSet$dateInt(((ShowRealmProxyInterface) newObject).realmGet$dateInt());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Show object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Show.class);
        long tableNativePtr = table.getNativeTablePointer();
        ShowColumnInfo columnInfo = (ShowColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Show.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((ShowRealmProxyInterface) object).realmGet$ID();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((ShowRealmProxyInterface) object).realmGet$ID());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(((ShowRealmProxyInterface) object).realmGet$ID(), false);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        java.util.Date realmGet$date = ((ShowRealmProxyInterface)object).realmGet$date();
        if (realmGet$date != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date.getTime(), false);
        }

        com.camacuasoft.jazzfestivalapp.Models.Artist artistObj = ((ShowRealmProxyInterface) object).realmGet$artist();
        if (artistObj != null) {
            Long cacheartist = cache.get(artistObj);
            if (cacheartist == null) {
                cacheartist = ArtistRealmProxy.insert(realm, artistObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.artistIndex, rowIndex, cacheartist, false);
        }
        String realmGet$description = ((ShowRealmProxyInterface)object).realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.artistIDIndex, rowIndex, ((ShowRealmProxyInterface)object).realmGet$artistID(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.dateIntIndex, rowIndex, ((ShowRealmProxyInterface)object).realmGet$dateInt(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Show.class);
        long tableNativePtr = table.getNativeTablePointer();
        ShowColumnInfo columnInfo = (ShowColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Show.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.camacuasoft.jazzfestivalapp.Models.Show object = null;
        while (objects.hasNext()) {
            object = (com.camacuasoft.jazzfestivalapp.Models.Show) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((ShowRealmProxyInterface) object).realmGet$ID();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((ShowRealmProxyInterface) object).realmGet$ID());
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(((ShowRealmProxyInterface) object).realmGet$ID(), false);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                java.util.Date realmGet$date = ((ShowRealmProxyInterface)object).realmGet$date();
                if (realmGet$date != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date.getTime(), false);
                }

                com.camacuasoft.jazzfestivalapp.Models.Artist artistObj = ((ShowRealmProxyInterface) object).realmGet$artist();
                if (artistObj != null) {
                    Long cacheartist = cache.get(artistObj);
                    if (cacheartist == null) {
                        cacheartist = ArtistRealmProxy.insert(realm, artistObj, cache);
                    }
                    table.setLink(columnInfo.artistIndex, rowIndex, cacheartist, false);
                }
                String realmGet$description = ((ShowRealmProxyInterface)object).realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.artistIDIndex, rowIndex, ((ShowRealmProxyInterface)object).realmGet$artistID(), false);
                Table.nativeSetLong(tableNativePtr, columnInfo.dateIntIndex, rowIndex, ((ShowRealmProxyInterface)object).realmGet$dateInt(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Show object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Show.class);
        long tableNativePtr = table.getNativeTablePointer();
        ShowColumnInfo columnInfo = (ShowColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Show.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((ShowRealmProxyInterface) object).realmGet$ID();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((ShowRealmProxyInterface) object).realmGet$ID());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(((ShowRealmProxyInterface) object).realmGet$ID(), false);
        }
        cache.put(object, rowIndex);
        java.util.Date realmGet$date = ((ShowRealmProxyInterface)object).realmGet$date();
        if (realmGet$date != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date.getTime(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dateIndex, rowIndex, false);
        }

        com.camacuasoft.jazzfestivalapp.Models.Artist artistObj = ((ShowRealmProxyInterface) object).realmGet$artist();
        if (artistObj != null) {
            Long cacheartist = cache.get(artistObj);
            if (cacheartist == null) {
                cacheartist = ArtistRealmProxy.insertOrUpdate(realm, artistObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.artistIndex, rowIndex, cacheartist, false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.artistIndex, rowIndex);
        }
        String realmGet$description = ((ShowRealmProxyInterface)object).realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.descriptionIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.artistIDIndex, rowIndex, ((ShowRealmProxyInterface)object).realmGet$artistID(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.dateIntIndex, rowIndex, ((ShowRealmProxyInterface)object).realmGet$dateInt(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.Show.class);
        long tableNativePtr = table.getNativeTablePointer();
        ShowColumnInfo columnInfo = (ShowColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.Show.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.camacuasoft.jazzfestivalapp.Models.Show object = null;
        while (objects.hasNext()) {
            object = (com.camacuasoft.jazzfestivalapp.Models.Show) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((ShowRealmProxyInterface) object).realmGet$ID();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((ShowRealmProxyInterface) object).realmGet$ID());
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(((ShowRealmProxyInterface) object).realmGet$ID(), false);
                }
                cache.put(object, rowIndex);
                java.util.Date realmGet$date = ((ShowRealmProxyInterface)object).realmGet$date();
                if (realmGet$date != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date.getTime(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.dateIndex, rowIndex, false);
                }

                com.camacuasoft.jazzfestivalapp.Models.Artist artistObj = ((ShowRealmProxyInterface) object).realmGet$artist();
                if (artistObj != null) {
                    Long cacheartist = cache.get(artistObj);
                    if (cacheartist == null) {
                        cacheartist = ArtistRealmProxy.insertOrUpdate(realm, artistObj, cache);
                    }
                    Table.nativeSetLink(tableNativePtr, columnInfo.artistIndex, rowIndex, cacheartist, false);
                } else {
                    Table.nativeNullifyLink(tableNativePtr, columnInfo.artistIndex, rowIndex);
                }
                String realmGet$description = ((ShowRealmProxyInterface)object).realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.descriptionIndex, rowIndex, false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.artistIDIndex, rowIndex, ((ShowRealmProxyInterface)object).realmGet$artistID(), false);
                Table.nativeSetLong(tableNativePtr, columnInfo.dateIntIndex, rowIndex, ((ShowRealmProxyInterface)object).realmGet$dateInt(), false);
            }
        }
    }

    public static com.camacuasoft.jazzfestivalapp.Models.Show createDetachedCopy(com.camacuasoft.jazzfestivalapp.Models.Show realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.camacuasoft.jazzfestivalapp.Models.Show unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.camacuasoft.jazzfestivalapp.Models.Show)cachedObject.object;
            } else {
                unmanagedObject = (com.camacuasoft.jazzfestivalapp.Models.Show)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.camacuasoft.jazzfestivalapp.Models.Show();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((ShowRealmProxyInterface) unmanagedObject).realmSet$ID(((ShowRealmProxyInterface) realmObject).realmGet$ID());
        ((ShowRealmProxyInterface) unmanagedObject).realmSet$date(((ShowRealmProxyInterface) realmObject).realmGet$date());

        // Deep copy of artist
        ((ShowRealmProxyInterface) unmanagedObject).realmSet$artist(ArtistRealmProxy.createDetachedCopy(((ShowRealmProxyInterface) realmObject).realmGet$artist(), currentDepth + 1, maxDepth, cache));
        ((ShowRealmProxyInterface) unmanagedObject).realmSet$description(((ShowRealmProxyInterface) realmObject).realmGet$description());
        ((ShowRealmProxyInterface) unmanagedObject).realmSet$artistID(((ShowRealmProxyInterface) realmObject).realmGet$artistID());
        ((ShowRealmProxyInterface) unmanagedObject).realmSet$dateInt(((ShowRealmProxyInterface) realmObject).realmGet$dateInt());
        return unmanagedObject;
    }

    static com.camacuasoft.jazzfestivalapp.Models.Show update(Realm realm, com.camacuasoft.jazzfestivalapp.Models.Show realmObject, com.camacuasoft.jazzfestivalapp.Models.Show newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((ShowRealmProxyInterface) realmObject).realmSet$date(((ShowRealmProxyInterface) newObject).realmGet$date());
        com.camacuasoft.jazzfestivalapp.Models.Artist artistObj = ((ShowRealmProxyInterface) newObject).realmGet$artist();
        if (artistObj != null) {
            com.camacuasoft.jazzfestivalapp.Models.Artist cacheartist = (com.camacuasoft.jazzfestivalapp.Models.Artist) cache.get(artistObj);
            if (cacheartist != null) {
                ((ShowRealmProxyInterface) realmObject).realmSet$artist(cacheartist);
            } else {
                ((ShowRealmProxyInterface) realmObject).realmSet$artist(ArtistRealmProxy.copyOrUpdate(realm, artistObj, true, cache));
            }
        } else {
            ((ShowRealmProxyInterface) realmObject).realmSet$artist(null);
        }
        ((ShowRealmProxyInterface) realmObject).realmSet$description(((ShowRealmProxyInterface) newObject).realmGet$description());
        ((ShowRealmProxyInterface) realmObject).realmSet$artistID(((ShowRealmProxyInterface) newObject).realmGet$artistID());
        ((ShowRealmProxyInterface) realmObject).realmSet$dateInt(((ShowRealmProxyInterface) newObject).realmGet$dateInt());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Show = [");
        stringBuilder.append("{ID:");
        stringBuilder.append(realmGet$ID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{date:");
        stringBuilder.append(realmGet$date() != null ? realmGet$date() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{artist:");
        stringBuilder.append(realmGet$artist() != null ? "Artist" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{description:");
        stringBuilder.append(realmGet$description() != null ? realmGet$description() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{artistID:");
        stringBuilder.append(realmGet$artistID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dateInt:");
        stringBuilder.append(realmGet$dateInt());
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
        ShowRealmProxy aShow = (ShowRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aShow.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aShow.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aShow.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
