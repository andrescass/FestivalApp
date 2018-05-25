package com.camacuasoft.jazzfestivalapp.Models;

import android.net.Uri;

import com.camacuasoft.jazzfestivalapp.App.FestivalMainApp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by nosotros on 20/06/2017.
 */

public class Artist extends RealmObject {
    @PrimaryKey
    private int ID;
    @Required
    private String name;
    private String photoRes;
    private int photoResInt;
    private String photoResUri;
    private String origin;
    private String bio;
    private boolean isFavorite;

    public Artist() {
    }

    public Artist (String name, int photoResInt, String origin, String bio) {
        this.ID = FestivalMainApp.artistID.incrementAndGet();
        this.name = name;
        this.photoResInt = photoResInt;
        this.origin = origin;
        this.bio = bio;
        isFavorite = false;
    }

    public Artist (int ID, String name, String photoRes, String origin, String bio) {
        this.ID = ID;
        this.name = name;
        this.photoRes = photoRes;
        this.origin = origin;
        this.bio = bio;
        isFavorite = false;
    }

    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public String getPhotoRes() {
        return photoRes;
    }
    public String getOrigin() {
        return origin;
    }
    public String getBio() {
        return bio;
    }
    public boolean isFavorite() {
        return isFavorite;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public void setPhotoRes(String photoRes) {
        this.photoRes = photoRes;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getPhotoResInt() {
        return photoResInt;
    }

    public void setPhotoResInt(int photoResInt) {
        this.photoResInt = photoResInt;
    }

    public String getPhotoResUri() {
        return photoResUri;
    }

    public void setPhotoResUri(String photoResUri) {
        this.photoResUri = photoResUri;
    }
}
