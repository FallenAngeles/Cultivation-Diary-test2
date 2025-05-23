package com.example.cultivationdiary_test2.Data.Database.Reminders;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.cultivationdiary_test2.Data.Database.AppDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RepositoryRemind {

    private final RemindersDAO Dao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public RepositoryRemind(Context context) {
        Dao = AppDatabase.getInstance(context).remindersDAO();
    }

    public void saveReminder(Reminders reminders) {
        executor.execute(() -> Dao.insert(reminders));
    }

    public void updateReminder(Reminders reminders) {
        executor.execute(() -> Dao.update(reminders));
    }

    public void deleteReminder(Reminders reminders) {
        executor.execute(() -> Dao.delete(reminders));
    }

    public LiveData<List<Reminders>> getAllReminders() {
        return Dao.getAll();
    }

}
