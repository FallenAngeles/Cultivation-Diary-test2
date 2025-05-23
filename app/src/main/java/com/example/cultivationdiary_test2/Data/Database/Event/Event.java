package com.example.cultivationdiary_test2.Data.Database.Event;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Event {

    @PrimaryKey(autoGenerate = true)
    public int id_event;
    public String name_event;
    public String Date;
    public String Start_datetime;

    public Event(String name_event, String Start_datetime, String Date) {
        this.name_event = name_event;
        this.Start_datetime = Start_datetime;
        this.Date = Date;
    }

    public String getName_event() {return name_event;}
    public void setName_event(String newId) {this.name_event = newId;}

    public String getDate() {return Date;}
    public void setDate(String newId) {this.Date = newId;}

    public String getStart_datetime() {return Start_datetime;}
    public void setStart_datetime(String newId) {this.Start_datetime = newId;}

}
