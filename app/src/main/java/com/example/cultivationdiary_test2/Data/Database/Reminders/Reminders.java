package com.example.cultivationdiary_test2.Data.Database.Reminders;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Reminders {

    @PrimaryKey(autoGenerate = true)
    public long id_rmd;
    public String name_rmd;
    public String Datetime_rmd;

    public Reminders(String name_rmd, String Datetime_rmd) {
        this.name_rmd = name_rmd;
        this.Datetime_rmd = Datetime_rmd;
    }

    public long getId_rmd() {return id_rmd;}
    public void setId_rmd(long newId) {this.id_rmd = newId;}

    public String getName_rmd() {return name_rmd;}
    public void setName_rmd(String newId) {this.name_rmd = newId;}

    public String getDatetime_rmd() {return Datetime_rmd;}
    public void setDatetime_rmd(String newId) {this.Datetime_rmd = newId;}

}
