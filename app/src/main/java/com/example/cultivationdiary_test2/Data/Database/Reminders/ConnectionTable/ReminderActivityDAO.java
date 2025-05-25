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
public interface ReminderActivityDAO {

    @Query("SELECT * FROM ReminderActivities")
    LiveData<List<ReminderActivities>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ReminderActivities reminderActivities);

    @Update
    void update(ReminderActivities reminderActivities);

    @Delete
    void delete(ReminderActivities reminderActivities);

}
