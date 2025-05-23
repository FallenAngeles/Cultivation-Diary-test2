package com.example.cultivationdiary_test2.Data.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cultivationdiary_test2.Data.Database.Activity.Activity;
import com.example.cultivationdiary_test2.Data.Database.Activity.ActivityDAO;
import com.example.cultivationdiary_test2.Data.Database.Diary.Diary;
import com.example.cultivationdiary_test2.Data.Database.Diary.DiaryDao;
import com.example.cultivationdiary_test2.Data.Database.Event.Event;
import com.example.cultivationdiary_test2.Data.Database.Event.EventDAO;
import com.example.cultivationdiary_test2.Data.Database.Project.Project;
import com.example.cultivationdiary_test2.Data.Database.Project.ProjectDAO;
import com.example.cultivationdiary_test2.Data.Database.Reminders.Reminders;
import com.example.cultivationdiary_test2.Data.Database.Reminders.RemindersDAO;
import com.example.cultivationdiary_test2.Data.Database.Task.Task;
import com.example.cultivationdiary_test2.Data.Database.Task.TaskDAO;

@Database(entities = {Diary.class, Project.class, Task.class, Activity.class, Event.class, Reminders.class}, version = 16)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DiaryDao diaryDao();
    public abstract ProjectDAO projectDAO();
    public abstract TaskDAO taskDAO();
    public abstract ActivityDAO activityDAO();
    public abstract EventDAO eventDAO();
    public abstract RemindersDAO remindersDAO();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "app_database.db"
                    )
                            .fallbackToDestructiveMigration(true)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
