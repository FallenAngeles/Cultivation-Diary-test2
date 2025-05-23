package com.example.cultivationdiary_test2.Data.Database.Event;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.cultivationdiary_test2.Data.Database.AppDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repository {

    private final EventDAO Dao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public Repository(Context context) {
        Dao = AppDatabase.getInstance(context).eventDAO();
    }

    public LiveData<List<Event>> EventByMonth(String monthYear) {
        return  Dao.getEventsByMonth(monthYear);
    }

    public void saveEvent(Event event) {
        executor.execute(() -> Dao.insert(event));
    }

    public void updateEvent(Event event) {
        executor.execute(() -> Dao.update(event));
    }

    public void deleteEvent(Event event) {
        executor.execute(() -> Dao.delete(event));
    }

    public LiveData<List<Event>> getAllEvents() {
        return Dao.getAll();
    }

    public LiveData<Event> getEvent(String Date) {return Dao.getEventByDate(Date);}

}
