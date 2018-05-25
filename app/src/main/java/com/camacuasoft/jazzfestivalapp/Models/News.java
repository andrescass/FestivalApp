package com.camacuasoft.jazzfestivalapp.Models;

import com.camacuasoft.jazzfestivalapp.App.FestivalMainApp;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by nosotros on 20/06/2017.
 */

public class News extends RealmObject {
    @PrimaryKey
    private int ID;
    @Required
    private Date date;
    @Required
    private String title;
    private Artist artist;
    private String info;

    public News() {

    }

    public News (Date date, String title, Artist artist, String info) {
        this.ID = FestivalMainApp.newsID.incrementAndGet();
        this.date = date;
        this.title = title;
        this.artist = artist;
        this.info = info;
    }

    public int getID() {
        return ID;
    }
    public Date getDate() {
        return date;
    }
    public String getTitle() {
        return title;
    }
    public Artist getArtist() {
        return artist;
    }
    public String getInfo() {
        return info;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    public void setInfo(String info) {
        this.info = info;
    }
}
