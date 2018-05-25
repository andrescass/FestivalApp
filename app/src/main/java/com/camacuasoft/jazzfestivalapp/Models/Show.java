package com.camacuasoft.jazzfestivalapp.Models;

import com.camacuasoft.jazzfestivalapp.App.FestivalMainApp;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by nosotros on 20/06/2017.
 */

public class Show extends RealmObject {
    @PrimaryKey
    private int ID;
    private Date date;
    private Artist artist;
    private String description;
    private int artistID;
    private long dateInt;

    public Show(){

    }

    public Show(Date date, Artist artist, String description) {
        this.ID = FestivalMainApp.showID.incrementAndGet();
        this.date = date;
        this.artist = artist;
        this.description = description;
    }

    public int getID() {
        return ID;
    }
    public Artist getArtist() {
        return artist;
    }
    public Date getDate() {
        return date;
    }
    public String getDescription() {
        return description;
    }
    public int getArtistID() {
        return artistID;
    }
    public long getDateInt() {
        return dateInt;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }
    public void setDateInt(long dateInt) {
        this.dateInt = dateInt;
    }
}
