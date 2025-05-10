package com.example.cultivationdiary_test2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cultivationdiary_test2.Data.Database.Diary;
import com.example.cultivationdiary_test2.Data.Repository;

import java.util.List;

public class DiaryViewModel extends AndroidViewModel {
    private Repository repository;

    public DiaryViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<Diary>> getDiaryByMonth(String monthYear) {
        return repository.DiaryByMonth(monthYear);
    }

    public LiveData<Diary> getDiaryByDate(String date) {
        return repository.getDiary(date);
    }

    public void saveData(Diary diary) {
        repository.saveDiary(diary);
    }

    public void updateData(Diary diary) {
        repository.updateDiary(diary);
    }

    public void deleteData(Diary diary) {
        repository.deleteDiary(diary);
    }
}
