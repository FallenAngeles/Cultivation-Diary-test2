package com.example.cultivationdiary_test2.Data.Database.Project;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.cultivationdiary_test2.Data.Database.AppDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repository {
    private final ProjectDAO Dao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public Repository(Context context) {
        Dao = AppDatabase.getInstance(context).projectDAO();
    }

    public void saveProject(Project project) {
        executor.execute(() -> Dao.insert(project));
    }

    public void updateProject(Project project) {
        executor.execute(() -> Dao.update(project));
    }

    public void deleteProject(Project project) {
        executor.execute(() -> Dao.delete(project));
    }

    public LiveData<Project> getProject(int id) {
        return Dao.getById(id);
    }

}
