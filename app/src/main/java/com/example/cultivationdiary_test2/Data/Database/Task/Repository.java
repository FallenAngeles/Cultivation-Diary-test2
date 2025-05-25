package com.example.cultivationdiary_test2.Data.Database.Task;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.cultivationdiary_test2.Data.Database.AppDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repository {
    private final TaskDAO Dao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public Repository(Context context) {
        Dao = AppDatabase.getInstance(context).taskDAO();
    }

    public void saveTask(Task task) {
        executor.execute(() -> Dao.insert(task));
    }

    public void updateTask(Task task) {
        executor.execute(() -> Dao.update(task));
    }

    public void deleteTask(Task task) {
        executor.execute(() -> Dao.delete(task));
    }

    public LiveData<List<Task>> getByGroup(int id_group) {
        return Dao.getByGroup(id_group);
    }
}
