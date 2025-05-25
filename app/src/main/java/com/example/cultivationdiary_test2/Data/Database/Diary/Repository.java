package com.example.cultivationdiary_test2.Data.Database.Diary;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.cultivationdiary_test2.Data.Database.AppDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repository {
    private final DiaryDao Dao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public Repository(Context context) {
        Dao = AppDatabase.getInstance(context).diaryDao();
    }

    public LiveData<List<Diary>> DiaryByMonth(String monthYear) {
        return  Dao.getDiariesByMonth(monthYear);
    }

    public void saveDiary(Diary diary) {
        executor.execute(() -> Dao.insert(diary));
    }

    public void updateDiary(Diary diary) {
        executor.execute(() -> Dao.update(diary));
    }

    public void deleteDiary(Diary diary) {
        executor.execute(() -> Dao.delete(diary));
    }

    public LiveData<Diary> getDiary(String date) {
        return Dao.getDiaryByDate(date);
    }

}
