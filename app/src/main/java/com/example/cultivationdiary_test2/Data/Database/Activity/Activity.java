package com.example.cultivationdiary_test2.Data.Database.Activity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Activity {

    @PrimaryKey(autoGenerate = true)
    public long id_activity;
    public String name_activity;
    public int duration;
    public String stard_datetime;
    public String end_datetime;

    public Activity(String name_activity, String stard_datetime, String end_datetime) {
        this.name_activity = name_activity;
        //this.duration = duration;
        this.stard_datetime = stard_datetime;
        this.end_datetime = end_datetime;
    }

    public String getName_activity() {return name_activity;}
    public void setName_activity(String newId) {this.name_activity = newId;}

    public int getDuration() {return duration;}
    public void setDuration(int newId) {this.duration = newId;}

    public String getStard_datetime() {return stard_datetime;}
    public void setStard_datetime(String newId) {this.stard_datetime = newId;}

    public String getEnd_datetime() {return end_datetime;}
    public void setEnd_datetime(String newId) {this.end_datetime = newId;}

}
