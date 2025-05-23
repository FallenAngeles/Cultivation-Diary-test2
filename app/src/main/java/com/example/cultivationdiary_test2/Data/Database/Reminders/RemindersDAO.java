package com.example.cultivationdiary_test2.Data.Database.Reminders;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RemindersDAO {

    @Query("SELECT * FROM Reminders")
    LiveData<List<Reminders>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Reminders reminders);

    @Update
    void update(Reminders reminders);

    @Delete
    void delete(Reminders reminders);


}
