package com.example.cultivationdiary_test2.Data.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cultivationdiary_test2.Data.Database.Diary.Diary;
import com.example.cultivationdiary_test2.Data.Database.Diary.DiaryDao;
import com.example.cultivationdiary_test2.Data.Database.Project.Project;
import com.example.cultivationdiary_test2.Data.Database.Project.ProjectDAO;

@Database(entities = {Diary.class, Project.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract DiaryDao diaryDao();
    public abstract ProjectDAO projectDAO();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "app_database.db"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
