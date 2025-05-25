package com.example.cultivationdiary_test2.Data.Database.Project;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProjectDAO {

    @Query("SELECT * FROM Project")
    LiveData<List<Project>> getAll();

    @Query("SELECT * FROM Project WHERE id_project = :id_project")
    LiveData<Project> getById(int id_project);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Project project);

    @Update
    void update(Project project);

    @Delete
    void delete(Project project);

}
