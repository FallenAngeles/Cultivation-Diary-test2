package com.example.cultivationdiary_test2.Data.Database.Activity;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ActivityDAO {

    @Query("SELECT * FROM Activity")
    LiveData<List<Activity>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Activity activity);

    @Update
    void update(Activity activity);

    @Delete
    void delete(Activity activity);

}
