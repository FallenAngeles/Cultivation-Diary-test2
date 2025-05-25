package com.example.cultivationdiary_test2.Data.Database.Reminders.ConnectionTable;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReminderEventsDAO {

    @Query("SELECT * FROM ReminderEvents")
    LiveData<List<ReminderEvents>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ReminderEvents reminderEvents);

    @Update
    void update(ReminderEvents reminderEvents);

    @Delete
    void delete(ReminderEvents reminderEvents);

}
