package com.camacuasoft.jazzfestivalapp.Models;

import com.camacuasoft.jazzfestivalapp.App.FestivalMainApp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by andres on 09/07/17.
 */

public class Info extends RealmObject {

    @PrimaryKey
    private int ID;
    @Required
    private String title;
    private String info;
    private String imageUri;
    private String imageUrl;

    public Info(){

    }

    public Info(String title, String info, String imageUri) {
        this.ID = FestivalMainApp.infoID.incrementAndGet();
        this.title = title;
        this.info = info;
        this.imageUri = imageUri;
    }

    public int getID() {
        return ID;
    }
    public String getTitle() {
        return title;
    }
    public String getInfo() {
        return info;
    }
    public String getImageUri() {
        return imageUri;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
