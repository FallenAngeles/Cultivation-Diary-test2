package com.example.cultivationdiary_test2.Data.Database.Event;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventDAO {

    @Query("SELECT * FROM Event")
    LiveData<List<Event>> getAll();

    @Query("SELECT * FROm Event WHERE Date = :Date")
    LiveData<Event> getEventByDate(String Date);

    @Query("SELECT * FROM Event WHERE substr(Date, 1, 7) = :monthYear")
    LiveData<List<Event>> getEventsByMonth(String monthYear);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Event event);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);

}
