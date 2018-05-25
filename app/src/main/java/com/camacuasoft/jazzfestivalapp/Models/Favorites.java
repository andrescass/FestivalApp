package com.camacuasoft.jazzfestivalapp.Models;

import com.camacuasoft.jazzfestivalapp.App.FestivalMainApp;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;


/**
 * Created by nosotros on 20/06/2017.
 */

public class Favorites extends RealmObject {
    @PrimaryKey
    private int ID;
    private Artist favArtist;

    public Favorites() {
    }

    public Favorites(Artist artist){
        this.ID = artist.getID();
        this.favArtist = artist;
    }

    public int getId() {
        return ID;
    }
    public Artist getFavArtist() {
        return favArtist;
    }

    public void setId(int id) {
        this.ID = id;
    }
    public void setFavArtist(Artist favArtist) {
        this.favArtist = favArtist;
    }
}
