package io.realm;


import android.util.JsonReader;
import io.realm.RealmObjectSchema;
import io.realm.internal.ColumnInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmModel>> modelClasses = new HashSet<Class<? extends RealmModel>>();
        modelClasses.add(com.camacuasoft.jazzfestivalapp.Models.Info.class);
        modelClasses.add(com.camacuasoft.jazzfestivalapp.Models.Artist.class);
        modelClasses.add(com.camacuasoft.jazzfestivalapp.Models.Show.class);
        modelClasses.add(com.camacuasoft.jazzfestivalapp.Models.News.class);
        modelClasses.add(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class);
        modelClasses.add(com.camacuasoft.jazzfestivalapp.Models.Favorites.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public Table createTable(Class<? extends RealmModel> clazz, SharedRealm sharedRealm) {
        checkClass(clazz);

        if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Info.class)) {
            return io.realm.InfoRealmProxy.initTable(sharedRealm);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Artist.class)) {
            return io.realm.ArtistRealmProxy.initTable(sharedRealm);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Show.class)) {
            return io.realm.ShowRealmProxy.initTable(sharedRealm);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.News.class)) {
            return io.realm.NewsRealmProxy.initTable(sharedRealm);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class)) {
            return io.realm.TimeStampsRealmProxy.initTable(sharedRealm);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Favorites.class)) {
            return io.realm.FavoritesRealmProxy.initTable(sharedRealm);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public RealmObjectSchema createRealmObjectSchema(Class<? extends RealmModel> clazz, RealmSchema realmSchema) {
        checkClass(clazz);

        if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Info.class)) {
            return io.realm.InfoRealmProxy.createRealmObjectSchema(realmSchema);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Artist.class)) {
            return io.realm.ArtistRealmProxy.createRealmObjectSchema(realmSchema);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Show.class)) {
            return io.realm.ShowRealmProxy.createRealmObjectSchema(realmSchema);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.News.class)) {
            return io.realm.NewsRealmProxy.createRealmObjectSchema(realmSchema);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class)) {
            return io.realm.TimeStampsRealmProxy.createRealmObjectSchema(realmSchema);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Favorites.class)) {
            return io.realm.FavoritesRealmProxy.createRealmObjectSchema(realmSchema);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public ColumnInfo validateTable(Class<? extends RealmModel> clazz, SharedRealm sharedRealm, boolean allowExtraColumns) {
        checkClass(clazz);

        if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Info.class)) {
            return io.realm.InfoRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Artist.class)) {
            return io.realm.ArtistRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Show.class)) {
            return io.realm.ShowRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.News.class)) {
            return io.realm.NewsRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class)) {
            return io.realm.TimeStampsRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Favorites.class)) {
            return io.realm.FavoritesRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public List<String> getFieldNames(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Info.class)) {
            return io.realm.InfoRealmProxy.getFieldNames();
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Artist.class)) {
            return io.realm.ArtistRealmProxy.getFieldNames();
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Show.class)) {
            return io.realm.ShowRealmProxy.getFieldNames();
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.News.class)) {
            return io.realm.NewsRealmProxy.getFieldNames();
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class)) {
            return io.realm.TimeStampsRealmProxy.getFieldNames();
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Favorites.class)) {
            return io.realm.FavoritesRealmProxy.getFieldNames();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public String getTableName(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Info.class)) {
            return io.realm.InfoRealmProxy.getTableName();
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Artist.class)) {
            return io.realm.ArtistRealmProxy.getTableName();
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Show.class)) {
            return io.realm.ShowRealmProxy.getTableName();
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.News.class)) {
            return io.realm.NewsRealmProxy.getTableName();
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class)) {
            return io.realm.TimeStampsRealmProxy.getTableName();
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Favorites.class)) {
            return io.realm.FavoritesRealmProxy.getTableName();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmModel> E newInstance(Class<E> clazz, Object baseRealm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        try {
            objectContext.set((BaseRealm) baseRealm, row, columnInfo, acceptDefaultValue, excludeFields);
            checkClass(clazz);

            if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Info.class)) {
                return clazz.cast(new io.realm.InfoRealmProxy());
            } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Artist.class)) {
                return clazz.cast(new io.realm.ArtistRealmProxy());
            } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Show.class)) {
                return clazz.cast(new io.realm.ShowRealmProxy());
            } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.News.class)) {
                return clazz.cast(new io.realm.NewsRealmProxy());
            } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class)) {
                return clazz.cast(new io.realm.TimeStampsRealmProxy());
            } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Favorites.class)) {
                return clazz.cast(new io.realm.FavoritesRealmProxy());
            } else {
                throw getMissingProxyClassException(clazz);
            }
        } finally {
            objectContext.clear();
        }
    }

    @Override
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmModel> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Info.class)) {
            return clazz.cast(io.realm.InfoRealmProxy.copyOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.Info) obj, update, cache));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Artist.class)) {
            return clazz.cast(io.realm.ArtistRealmProxy.copyOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.Artist) obj, update, cache));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Show.class)) {
            return clazz.cast(io.realm.ShowRealmProxy.copyOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.Show) obj, update, cache));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.News.class)) {
            return clazz.cast(io.realm.NewsRealmProxy.copyOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.News) obj, update, cache));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class)) {
            return clazz.cast(io.realm.TimeStampsRealmProxy.copyOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.TimeStamps) obj, update, cache));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Favorites.class)) {
            return clazz.cast(io.realm.FavoritesRealmProxy.copyOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.Favorites) obj, update, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

        if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Info.class)) {
            io.realm.InfoRealmProxy.insert(realm, (com.camacuasoft.jazzfestivalapp.Models.Info) object, cache);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Artist.class)) {
            io.realm.ArtistRealmProxy.insert(realm, (com.camacuasoft.jazzfestivalapp.Models.Artist) object, cache);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Show.class)) {
            io.realm.ShowRealmProxy.insert(realm, (com.camacuasoft.jazzfestivalapp.Models.Show) object, cache);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.News.class)) {
            io.realm.NewsRealmProxy.insert(realm, (com.camacuasoft.jazzfestivalapp.Models.News) object, cache);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class)) {
            io.realm.TimeStampsRealmProxy.insert(realm, (com.camacuasoft.jazzfestivalapp.Models.TimeStamps) object, cache);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Favorites.class)) {
            io.realm.FavoritesRealmProxy.insert(realm, (com.camacuasoft.jazzfestivalapp.Models.Favorites) object, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Info.class)) {
                io.realm.InfoRealmProxy.insert(realm, (com.camacuasoft.jazzfestivalapp.Models.Info) object, cache);
            } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Artist.class)) {
                io.realm.ArtistRealmProxy.insert(realm, (com.camacuasoft.jazzfestivalapp.Models.Artist) object, cache);
            } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Show.class)) {
                io.realm.ShowRealmProxy.insert(realm, (com.camacuasoft.jazzfestivalapp.Models.Show) object, cache);
            } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.News.class)) {
                io.realm.NewsRealmProxy.insert(realm, (com.camacuasoft.jazzfestivalapp.Models.News) object, cache);
            } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class)) {
                io.realm.TimeStampsRealmProxy.insert(realm, (com.camacuasoft.jazzfestivalapp.Models.TimeStamps) object, cache);
            } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Favorites.class)) {
                io.realm.FavoritesRealmProxy.insert(realm, (com.camacuasoft.jazzfestivalapp.Models.Favorites) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Info.class)) {
                    io.realm.InfoRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Artist.class)) {
                    io.realm.ArtistRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Show.class)) {
                    io.realm.ShowRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.News.class)) {
                    io.realm.NewsRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class)) {
                    io.realm.TimeStampsRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Favorites.class)) {
                    io.realm.FavoritesRealmProxy.insert(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, RealmModel obj, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Info.class)) {
            io.realm.InfoRealmProxy.insertOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.Info) obj, cache);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Artist.class)) {
            io.realm.ArtistRealmProxy.insertOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.Artist) obj, cache);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Show.class)) {
            io.realm.ShowRealmProxy.insertOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.Show) obj, cache);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.News.class)) {
            io.realm.NewsRealmProxy.insertOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.News) obj, cache);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class)) {
            io.realm.TimeStampsRealmProxy.insertOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.TimeStamps) obj, cache);
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Favorites.class)) {
            io.realm.FavoritesRealmProxy.insertOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.Favorites) obj, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Info.class)) {
                io.realm.InfoRealmProxy.insertOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.Info) object, cache);
            } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Artist.class)) {
                io.realm.ArtistRealmProxy.insertOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.Artist) object, cache);
            } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Show.class)) {
                io.realm.ShowRealmProxy.insertOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.Show) object, cache);
            } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.News.class)) {
                io.realm.NewsRealmProxy.insertOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.News) object, cache);
            } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class)) {
                io.realm.TimeStampsRealmProxy.insertOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.TimeStamps) object, cache);
            } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Favorites.class)) {
                io.realm.FavoritesRealmProxy.insertOrUpdate(realm, (com.camacuasoft.jazzfestivalapp.Models.Favorites) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Info.class)) {
                    io.realm.InfoRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Artist.class)) {
                    io.realm.ArtistRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Show.class)) {
                    io.realm.ShowRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.News.class)) {
                    io.realm.NewsRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class)) {
                    io.realm.TimeStampsRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Favorites.class)) {
                    io.realm.FavoritesRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Info.class)) {
            return clazz.cast(io.realm.InfoRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Artist.class)) {
            return clazz.cast(io.realm.ArtistRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Show.class)) {
            return clazz.cast(io.realm.ShowRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.News.class)) {
            return clazz.cast(io.realm.NewsRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class)) {
            return clazz.cast(io.realm.TimeStampsRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Favorites.class)) {
            return clazz.cast(io.realm.FavoritesRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Info.class)) {
            return clazz.cast(io.realm.InfoRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Artist.class)) {
            return clazz.cast(io.realm.ArtistRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Show.class)) {
            return clazz.cast(io.realm.ShowRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.News.class)) {
            return clazz.cast(io.realm.NewsRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class)) {
            return clazz.cast(io.realm.TimeStampsRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Favorites.class)) {
            return clazz.cast(io.realm.FavoritesRealmProxy.createUsingJsonStream(realm, reader));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Info.class)) {
            return clazz.cast(io.realm.InfoRealmProxy.createDetachedCopy((com.camacuasoft.jazzfestivalapp.Models.Info) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Artist.class)) {
            return clazz.cast(io.realm.ArtistRealmProxy.createDetachedCopy((com.camacuasoft.jazzfestivalapp.Models.Artist) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Show.class)) {
            return clazz.cast(io.realm.ShowRealmProxy.createDetachedCopy((com.camacuasoft.jazzfestivalapp.Models.Show) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.News.class)) {
            return clazz.cast(io.realm.NewsRealmProxy.createDetachedCopy((com.camacuasoft.jazzfestivalapp.Models.News) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.TimeStamps.class)) {
            return clazz.cast(io.realm.TimeStampsRealmProxy.createDetachedCopy((com.camacuasoft.jazzfestivalapp.Models.TimeStamps) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(com.camacuasoft.jazzfestivalapp.Models.Favorites.class)) {
            return clazz.cast(io.realm.FavoritesRealmProxy.createDetachedCopy((com.camacuasoft.jazzfestivalapp.Models.Favorites) realmObject, 0, maxDepth, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

}
