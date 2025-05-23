package com.example.cultivationdiary_test2.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cultivationdiary_test2.Data.Database.Event.Event;
import com.example.cultivationdiary_test2.Data.Database.Event.Repository;

import java.util.List;

public class EventViewModel extends AndroidViewModel {
    private Repository repository;

    public EventViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<Event>> getEventByMonth(String monthYear) {
        return repository.EventByMonth(monthYear);
    }

    public LiveData<Event> getEventByDate(String date) {
        return repository.getEvent(date);
    }

}
