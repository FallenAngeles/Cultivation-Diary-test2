package com.example.cultivationdiary_test2.Data.Database.Activity;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import com.example.cultivationdiary_test2.Data.Database.AppDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repository {
    private final ActivityDAO Dao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public Repository(Context context) {
        Dao = AppDatabase.getInstance(context).activityDAO();
    }

    public void saveActivity(Activity activity) {
        executor.execute(() -> Dao.insert(activity));
    }

    public void updateActivity(Activity activity) {
        executor.execute(() -> Dao.update(activity));
    }

    public void deleteActivity(Activity activity) {
        executor.execute(() -> Dao.delete(activity));
    }

    public LiveData<List<Activity>> getActivity() {
        return Dao.getAll();
    }

}
