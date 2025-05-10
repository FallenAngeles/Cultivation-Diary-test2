package com.example.cultivationdiary_test2.Data.Database;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DiaryDao {

    @Query("SELECT * FROM Diary")
    List<Diary> getAll();

    @Query("SELECT * FROM Diary WHERE Diary_id = :Diary_id")
    Diary getById(int Diary_id);

    @Query("SELECT * FROM Diary WHERE CreateDate = :CreateDate")
    LiveData<Diary> getDiaryByDate(String CreateDate);

    @Query("SELECT * FROM Diary WHERE substr(CreateDate, 1, 7) = :monthYear")
    LiveData<List<Diary>> getDiariesByMonth(String monthYear);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Diary diary);

    /*@Insert
    void insert(Diary diary);*/

    @Update
    void update(Diary diary);

    @Delete
    void delete(Diary diary);

}
