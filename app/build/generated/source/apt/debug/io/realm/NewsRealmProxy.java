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

public class NewsRealmProxy extends com.camacuasoft.jazzfestivalapp.Models.News
    implements RealmObjectProxy, NewsRealmProxyInterface {

    static final class NewsColumnInfo extends ColumnInfo
        implements Cloneable {

        public long IDIndex;
        public long dateIndex;
        public long titleIndex;
        public long artistIndex;
        public long infoIndex;

        NewsColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(5);
            this.IDIndex = getValidColumnIndex(path, table, "News", "ID");
            indicesMap.put("ID", this.IDIndex);
            this.dateIndex = getValidColumnIndex(path, table, "News", "date");
            indicesMap.put("date", this.dateIndex);
            this.titleIndex = getValidColumnIndex(path, table, "News", "title");
            indicesMap.put("title", this.titleIndex);
            this.artistIndex = getValidColumnIndex(path, table, "News", "artist");
            indicesMap.put("artist", this.artistIndex);
            this.infoIndex = getValidColumnIndex(path, table, "News", "info");
            indicesMap.put("info", this.infoIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final NewsColumnInfo otherInfo = (NewsColumnInfo) other;
            this.IDIndex = otherInfo.IDIndex;
            this.dateIndex = otherInfo.dateIndex;
            this.titleIndex = otherInfo.titleIndex;
            this.artistIndex = otherInfo.artistIndex;
            this.infoIndex = otherInfo.infoIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final NewsColumnInfo clone() {
            return (NewsColumnInfo) super.clone();
        }

    }
    private NewsColumnInfo columnInfo;
    private ProxyState<com.camacuasoft.jazzfestivalapp.Models.News> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("ID");
        fieldNames.add("date");
        fieldNames.add("title");
        fieldNames.add("artist");
        fieldNames.add("info");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    NewsRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (NewsColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.camacuasoft.jazzfestivalapp.Models.News>(this);
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
        return (java.util.Date) proxyState.getRow$realm().getDate(columnInfo.dateIndex);
    }

    public void realmSet$date(Date value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'date' to null.");
            }
            row.getTable().setDate(columnInfo.dateIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'date' to null.");
        }
        proxyState.getRow$realm().setDate(columnInfo.dateIndex, value);
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

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("News")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("News");
            realmObjectSchema.add(new Property("ID", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("date", RealmFieldType.DATE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("title", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            if (!realmSchema.contains("Artist")) {
                ArtistRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add(new Property("artist", RealmFieldType.OBJECT, realmSchema.get("Artist")));
            realmObjectSchema.add(new Property("info", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("News");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_News")) {
            Table table = sharedRealm.getTable("class_News");
            table.addColumn(RealmFieldType.INTEGER, "ID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.DATE, "date", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "title", Table.NOT_NULLABLE);
            if (!sharedRealm.hasTable("class_Artist")) {
                ArtistRealmProxy.initTable(sharedRealm);
            }
            table.addColumnLink(RealmFieldType.OBJECT, "artist", sharedRealm.getTable("class_Artist"));
            table.addColumn(RealmFieldType.STRING, "info", Table.NULLABLE);
            table.addSearchIndex(table.getColumnIndex("ID"));
            table.setPrimaryKey("ID");
            return table;
        }
        return sharedRealm.getTable("class_News");
    }

    public static NewsColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_News")) {
            Table table = sharedRealm.getTable("class_News");
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

            final NewsColumnInfo columnInfo = new NewsColumnInfo(sharedRealm.getPath(), table);

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
            if (table.isColumnNullable(columnInfo.dateIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'date' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'date' or migrate using RealmObjectSchema.setNullable().");
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
            if (!columnTypes.containsKey("artist")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'artist' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("artist") != RealmFieldType.OBJECT) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Artist' for field 'artist'");
            }
            if (!sharedRealm.hasTable("class_Artist")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_Artist' for field 'artist'");
            }
            Table table_3 = sharedRealm.getTable("class_Artist");
            if (!table.getLinkTarget(columnInfo.artistIndex).hasSameSchema(table_3)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmObject for field 'artist': '" + table.getLinkTarget(columnInfo.artistIndex).getName() + "' expected - was '" + table_3.getName() + "'");
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
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'News' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_News";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.camacuasoft.jazzfestivalapp.Models.News createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        com.camacuasoft.jazzfestivalapp.Models.News obj = null;
        if (update) {
            Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.News.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("ID")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("ID"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.News.class), false, Collections.<String> emptyList());
                    obj = new io.realm.NewsRealmProxy();
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
                    obj = (io.realm.NewsRealmProxy) realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.News.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.NewsRealmProxy) realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.News.class, json.getInt("ID"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'ID'.");
            }
        }
        if (json.has("date")) {
            if (json.isNull("date")) {
                ((NewsRealmProxyInterface) obj).realmSet$date(null);
            } else {
                Object timestamp = json.get("date");
                if (timestamp instanceof String) {
                    ((NewsRealmProxyInterface) obj).realmSet$date(JsonUtils.stringToDate((String) timestamp));
                } else {
                    ((NewsRealmProxyInterface) obj).realmSet$date(new Date(json.getLong("date")));
                }
            }
        }
        if (json.has("title")) {
            if (json.isNull("title")) {
                ((NewsRealmProxyInterface) obj).realmSet$title(null);
            } else {
                ((NewsRealmProxyInterface) obj).realmSet$title((String) json.getString("title"));
            }
        }
        if (json.has("artist")) {
            if (json.isNull("artist")) {
                ((NewsRealmProxyInterface) obj).realmSet$artist(null);
            } else {
                com.camacuasoft.jazzfestivalapp.Models.Artist artistObj = ArtistRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("artist"), update);
                ((NewsRealmProxyInterface) obj).realmSet$artist(artistObj);
            }
        }
        if (json.has("info")) {
            if (json.isNull("info")) {
                ((NewsRealmProxyInterface) obj).realmSet$info(null);
            } else {
                ((NewsRealmProxyInterface) obj).realmSet$info((String) json.getString("info"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.camacuasoft.jazzfestivalapp.Models.News createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.camacuasoft.jazzfestivalapp.Models.News obj = new com.camacuasoft.jazzfestivalapp.Models.News();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("ID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'ID' to null.");
                } else {
                    ((NewsRealmProxyInterface) obj).realmSet$ID((int) reader.nextInt());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("date")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((NewsRealmProxyInterface) obj).realmSet$date(null);
                } else if (reader.peek() == JsonToken.NUMBER) {
                    long timestamp = reader.nextLong();
                    if (timestamp > -1) {
                        ((NewsRealmProxyInterface) obj).realmSet$date(new Date(timestamp));
                    }
                } else {
                    ((NewsRealmProxyInterface) obj).realmSet$date(JsonUtils.stringToDate(reader.nextString()));
                }
            } else if (name.equals("title")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((NewsRealmProxyInterface) obj).realmSet$title(null);
                } else {
                    ((NewsRealmProxyInterface) obj).realmSet$title((String) reader.nextString());
                }
            } else if (name.equals("artist")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((NewsRealmProxyInterface) obj).realmSet$artist(null);
                } else {
                    com.camacuasoft.jazzfestivalapp.Models.Artist artistObj = ArtistRealmProxy.createUsingJsonStream(realm, reader);
                    ((NewsRealmProxyInterface) obj).realmSet$artist(artistObj);
                }
            } else if (name.equals("info")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((NewsRealmProxyInterface) obj).realmSet$info(null);
                } else {
                    ((NewsRealmProxyInterface) obj).realmSet$info((String) reader.nextString());
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

    public static com.camacuasoft.jazzfestivalapp.Models.News copyOrUpdate(Realm realm, com.camacuasoft.jazzfestivalapp.Models.News object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.camacuasoft.jazzfestivalapp.Models.News) cachedRealmObject;
        } else {
            com.camacuasoft.jazzfestivalapp.Models.News realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.News.class);
                long pkColumnIndex = table.getPrimaryKey();
                long rowIndex = table.findFirstLong(pkColumnIndex, ((NewsRealmProxyInterface) object).realmGet$ID());
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.News.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.NewsRealmProxy();
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

    public static com.camacuasoft.jazzfestivalapp.Models.News copy(Realm realm, com.camacuasoft.jazzfestivalapp.Models.News newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.camacuasoft.jazzfestivalapp.Models.News) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.camacuasoft.jazzfestivalapp.Models.News realmObject = realm.createObjectInternal(com.camacuasoft.jazzfestivalapp.Models.News.class, ((NewsRealmProxyInterface) newObject).realmGet$ID(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((NewsRealmProxyInterface) realmObject).realmSet$date(((NewsRealmProxyInterface) newObject).realmGet$date());
            ((NewsRealmProxyInterface) realmObject).realmSet$title(((NewsRealmProxyInterface) newObject).realmGet$title());

            com.camacuasoft.jazzfestivalapp.Models.Artist artistObj = ((NewsRealmProxyInterface) newObject).realmGet$artist();
            if (artistObj != null) {
                com.camacuasoft.jazzfestivalapp.Models.Artist cacheartist = (com.camacuasoft.jazzfestivalapp.Models.Artist) cache.get(artistObj);
                if (cacheartist != null) {
                    ((NewsRealmProxyInterface) realmObject).realmSet$artist(cacheartist);
                } else {
                    ((NewsRealmProxyInterface) realmObject).realmSet$artist(ArtistRealmProxy.copyOrUpdate(realm, artistObj, update, cache));
                }
            } else {
                ((NewsRealmProxyInterface) realmObject).realmSet$artist(null);
            }
            ((NewsRealmProxyInterface) realmObject).realmSet$info(((NewsRealmProxyInterface) newObject).realmGet$info());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.camacuasoft.jazzfestivalapp.Models.News object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.News.class);
        long tableNativePtr = table.getNativeTablePointer();
        NewsColumnInfo columnInfo = (NewsColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.News.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((NewsRealmProxyInterface) object).realmGet$ID();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((NewsRealmProxyInterface) object).realmGet$ID());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(((NewsRealmProxyInterface) object).realmGet$ID(), false);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        java.util.Date realmGet$date = ((NewsRealmProxyInterface)object).realmGet$date();
        if (realmGet$date != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date.getTime(), false);
        }
        String realmGet$title = ((NewsRealmProxyInterface)object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        }

        com.camacuasoft.jazzfestivalapp.Models.Artist artistObj = ((NewsRealmProxyInterface) object).realmGet$artist();
        if (artistObj != null) {
            Long cacheartist = cache.get(artistObj);
            if (cacheartist == null) {
                cacheartist = ArtistRealmProxy.insert(realm, artistObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.artistIndex, rowIndex, cacheartist, false);
        }
        String realmGet$info = ((NewsRealmProxyInterface)object).realmGet$info();
        if (realmGet$info != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.infoIndex, rowIndex, realmGet$info, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.News.class);
        long tableNativePtr = table.getNativeTablePointer();
        NewsColumnInfo columnInfo = (NewsColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.News.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.camacuasoft.jazzfestivalapp.Models.News object = null;
        while (objects.hasNext()) {
            object = (com.camacuasoft.jazzfestivalapp.Models.News) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((NewsRealmProxyInterface) object).realmGet$ID();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((NewsRealmProxyInterface) object).realmGet$ID());
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(((NewsRealmProxyInterface) object).realmGet$ID(), false);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                java.util.Date realmGet$date = ((NewsRealmProxyInterface)object).realmGet$date();
                if (realmGet$date != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date.getTime(), false);
                }
                String realmGet$title = ((NewsRealmProxyInterface)object).realmGet$title();
                if (realmGet$title != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
                }

                com.camacuasoft.jazzfestivalapp.Models.Artist artistObj = ((NewsRealmProxyInterface) object).realmGet$artist();
                if (artistObj != null) {
                    Long cacheartist = cache.get(artistObj);
                    if (cacheartist == null) {
                        cacheartist = ArtistRealmProxy.insert(realm, artistObj, cache);
                    }
                    table.setLink(columnInfo.artistIndex, rowIndex, cacheartist, false);
                }
                String realmGet$info = ((NewsRealmProxyInterface)object).realmGet$info();
                if (realmGet$info != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.infoIndex, rowIndex, realmGet$info, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.camacuasoft.jazzfestivalapp.Models.News object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.News.class);
        long tableNativePtr = table.getNativeTablePointer();
        NewsColumnInfo columnInfo = (NewsColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.News.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((NewsRealmProxyInterface) object).realmGet$ID();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((NewsRealmProxyInterface) object).realmGet$ID());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(((NewsRealmProxyInterface) object).realmGet$ID(), false);
        }
        cache.put(object, rowIndex);
        java.util.Date realmGet$date = ((NewsRealmProxyInterface)object).realmGet$date();
        if (realmGet$date != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date.getTime(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dateIndex, rowIndex, false);
        }
        String realmGet$title = ((NewsRealmProxyInterface)object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
        }

        com.camacuasoft.jazzfestivalapp.Models.Artist artistObj = ((NewsRealmProxyInterface) object).realmGet$artist();
        if (artistObj != null) {
            Long cacheartist = cache.get(artistObj);
            if (cacheartist == null) {
                cacheartist = ArtistRealmProxy.insertOrUpdate(realm, artistObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.artistIndex, rowIndex, cacheartist, false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.artistIndex, rowIndex);
        }
        String realmGet$info = ((NewsRealmProxyInterface)object).realmGet$info();
        if (realmGet$info != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.infoIndex, rowIndex, realmGet$info, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.infoIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.camacuasoft.jazzfestivalapp.Models.News.class);
        long tableNativePtr = table.getNativeTablePointer();
        NewsColumnInfo columnInfo = (NewsColumnInfo) realm.schema.getColumnInfo(com.camacuasoft.jazzfestivalapp.Models.News.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.camacuasoft.jazzfestivalapp.Models.News object = null;
        while (objects.hasNext()) {
            object = (com.camacuasoft.jazzfestivalapp.Models.News) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((NewsRealmProxyInterface) object).realmGet$ID();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((NewsRealmProxyInterface) object).realmGet$ID());
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(((NewsRealmProxyInterface) object).realmGet$ID(), false);
                }
                cache.put(object, rowIndex);
                java.util.Date realmGet$date = ((NewsRealmProxyInterface)object).realmGet$date();
                if (realmGet$date != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date.getTime(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.dateIndex, rowIndex, false);
                }
                String realmGet$title = ((NewsRealmProxyInterface)object).realmGet$title();
                if (realmGet$title != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
                }

                com.camacuasoft.jazzfestivalapp.Models.Artist artistObj = ((NewsRealmProxyInterface) object).realmGet$artist();
                if (artistObj != null) {
                    Long cacheartist = cache.get(artistObj);
                    if (cacheartist == null) {
                        cacheartist = ArtistRealmProxy.insertOrUpdate(realm, artistObj, cache);
                    }
                    Table.nativeSetLink(tableNativePtr, columnInfo.artistIndex, rowIndex, cacheartist, false);
                } else {
                    Table.nativeNullifyLink(tableNativePtr, columnInfo.artistIndex, rowIndex);
                }
                String realmGet$info = ((NewsRealmProxyInterface)object).realmGet$info();
                if (realmGet$info != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.infoIndex, rowIndex, realmGet$info, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.infoIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.camacuasoft.jazzfestivalapp.Models.News createDetachedCopy(com.camacuasoft.jazzfestivalapp.Models.News realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.camacuasoft.jazzfestivalapp.Models.News unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.camacuasoft.jazzfestivalapp.Models.News)cachedObject.object;
            } else {
                unmanagedObject = (com.camacuasoft.jazzfestivalapp.Models.News)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.camacuasoft.jazzfestivalapp.Models.News();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((NewsRealmProxyInterface) unmanagedObject).realmSet$ID(((NewsRealmProxyInterface) realmObject).realmGet$ID());
        ((NewsRealmProxyInterface) unmanagedObject).realmSet$date(((NewsRealmProxyInterface) realmObject).realmGet$date());
        ((NewsRealmProxyInterface) unmanagedObject).realmSet$title(((NewsRealmProxyInterface) realmObject).realmGet$title());

        // Deep copy of artist
        ((NewsRealmProxyInterface) unmanagedObject).realmSet$artist(ArtistRealmProxy.createDetachedCopy(((NewsRealmProxyInterface) realmObject).realmGet$artist(), currentDepth + 1, maxDepth, cache));
        ((NewsRealmProxyInterface) unmanagedObject).realmSet$info(((NewsRealmProxyInterface) realmObject).realmGet$info());
        return unmanagedObject;
    }

    static com.camacuasoft.jazzfestivalapp.Models.News update(Realm realm, com.camacuasoft.jazzfestivalapp.Models.News realmObject, com.camacuasoft.jazzfestivalapp.Models.News newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((NewsRealmProxyInterface) realmObject).realmSet$date(((NewsRealmProxyInterface) newObject).realmGet$date());
        ((NewsRealmProxyInterface) realmObject).realmSet$title(((NewsRealmProxyInterface) newObject).realmGet$title());
        com.camacuasoft.jazzfestivalapp.Models.Artist artistObj = ((NewsRealmProxyInterface) newObject).realmGet$artist();
        if (artistObj != null) {
            com.camacuasoft.jazzfestivalapp.Models.Artist cacheartist = (com.camacuasoft.jazzfestivalapp.Models.Artist) cache.get(artistObj);
            if (cacheartist != null) {
                ((NewsRealmProxyInterface) realmObject).realmSet$artist(cacheartist);
            } else {
                ((NewsRealmProxyInterface) realmObject).realmSet$artist(ArtistRealmProxy.copyOrUpdate(realm, artistObj, true, cache));
            }
        } else {
            ((NewsRealmProxyInterface) realmObject).realmSet$artist(null);
        }
        ((NewsRealmProxyInterface) realmObject).realmSet$info(((NewsRealmProxyInterface) newObject).realmGet$info());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("News = [");
        stringBuilder.append("{ID:");
        stringBuilder.append(realmGet$ID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{date:");
        stringBuilder.append(realmGet$date());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{title:");
        stringBuilder.append(realmGet$title());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{artist:");
        stringBuilder.append(realmGet$artist() != null ? "Artist" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{info:");
        stringBuilder.append(realmGet$info() != null ? realmGet$info() : "null");
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
        NewsRealmProxy aNews = (NewsRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aNews.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aNews.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aNews.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
