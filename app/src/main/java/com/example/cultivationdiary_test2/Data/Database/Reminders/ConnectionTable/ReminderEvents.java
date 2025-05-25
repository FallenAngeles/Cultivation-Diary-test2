package com.example.cultivationdiary_test2.Data.Database.Reminders.ConnectionTable;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.cultivationdiary_test2.Data.Database.Event.Event;
import com.example.cultivationdiary_test2.Data.Database.Reminders.Reminders;

@Entity(foreignKeys = {
    @ForeignKey(entity = Event.class, parentColumns = "id_event", childColumns = "Event_id"),
    @ForeignKey(entity = Reminders.class, parentColumns = "id_rmd", childColumns = "Rmd_id") })
public class ReminderEvents {

    @PrimaryKey(autoGenerate = true)
    public long Id_reminders_ev;
    public long Event_id;
    public long Rmd_id;

    public ReminderEvents(long Event_id, long Rmd_id) {
        this.Event_id = Event_id;
        this.Rmd_id = Rmd_id;
    }

    public long getEvent_id() {return Event_id;}
    public void setEvent_id(long newId) {this.Event_id = newId;}

    public long getRmd_id() {return Rmd_id;}
    public void setRmd_id(long newId) {this.Rmd_id = newId;}

}
