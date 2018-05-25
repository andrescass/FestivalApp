package com.camacuasoft.jazzfestivalapp.Models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by andres on 23/07/17.
 */

public class TimeStamps extends RealmObject {
    @PrimaryKey
    private int ID;
    private String tsName;
    private long timeStamp;

    public TimeStamps() { }

    public TimeStamps(int ID, String tsName, int timeStamp) {
        this.ID = ID;
        this.tsName = tsName;
        this.timeStamp = timeStamp;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTsName() {
        return tsName;
    }

    public void setTsName(String tsName) {
        this.tsName = tsName;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}


