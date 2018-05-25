package com.camacuasoft.jazzfestivalapp.App;

import android.app.Application;

import com.camacuasoft.jazzfestivalapp.Models.Artist;
import com.camacuasoft.jazzfestivalapp.Models.Favorites;
import com.camacuasoft.jazzfestivalapp.Models.Info;
import com.camacuasoft.jazzfestivalapp.Models.News;
import com.camacuasoft.jazzfestivalapp.Models.Show;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * Created by nosotros on 20/06/2017.
 */

public class FestivalMainApp extends Application {
    public static AtomicInteger artistID = new AtomicInteger();
    public static AtomicInteger showID = new AtomicInteger();
    public static AtomicInteger newsID = new AtomicInteger();
    public static AtomicInteger favsID = new AtomicInteger();
    public static AtomicInteger infoID = new AtomicInteger();

    public static int finalHeight;
    public static int finalWidth;
    public static int totalHeight;
    public static int totalWidth;

    public static int bannerHeight;

    public static List<Integer> bannerList;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpRealmConfig();
        //Realm realm = Realm.getDefaultInstance();
    }

    private void setUpRealmConfig() {
        /*RealmConfiguration config = new RealmConfiguration
                .Builder(getApplicationContext())
                .deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);*/
        Realm.init(this);
        Realm realm;

        try {
            realm = Realm.getDefaultInstance();
        } catch ( RealmMigrationNeededException e) {
            RealmConfiguration config = new RealmConfiguration
                    .Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();

            realm = Realm.getInstance(config);
        }

        /*RealmConfiguration config = new RealmConfiguration.Builder().
                schemaVersion(0).
                migration(new MyMigration()).
                build();

        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();*/

        artistID = getIdByTable(realm, Artist.class);
        showID = getIdByTable(realm, Show.class);
        newsID = getIdByTable(realm, News.class);
        favsID = getIdByTable(realm, Favorites.class);
        infoID = getIdByTable(realm, Info.class);
        realm.close();
    }

    private <T extends RealmObject> AtomicInteger getIdByTable (Realm realm, Class<T> anyclass) {

        RealmResults<T> results = realm.where(anyclass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("ID").intValue()) : new AtomicInteger();
    }

}
