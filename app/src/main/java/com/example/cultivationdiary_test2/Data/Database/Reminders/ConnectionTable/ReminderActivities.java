package com.example.cultivationdiary_test2.Data.Database.Reminders.ConnectionTable;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.cultivationdiary_test2.Data.Database.Activity.Activity;
import com.example.cultivationdiary_test2.Data.Database.Reminders.Reminders;

@Entity(foreignKeys = {
        @ForeignKey(entity = Activity.class, parentColumns = "id_activity", childColumns = "Act_id"),
        @ForeignKey(entity = Reminders.class, parentColumns = "id_rmd", childColumns = "Rmd_id") })
public class ReminderActivities {

    @PrimaryKey(autoGenerate = true)
    public long Id_reminders_act;
    public long Act_id;
    public long Rmd_id;

    public ReminderActivities(long Act_id, long Rmd_id) {
        this.Act_id = Act_id;
        this.Rmd_id = Rmd_id;
    }

    public long getAct_id() {return Act_id;}
    public void setAct_id(long newId) {this.Act_id = newId;}

    public long getRmd_id() {return Rmd_id;}
    public void setRmd_id(long newId) {this.Rmd_id = newId;}


}
