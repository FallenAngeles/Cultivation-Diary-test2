package com.example.cultivationdiary_test2.Data.Database.Reminders;

import static android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.cultivationdiary_test2.AlarmReceiver;
import com.example.cultivationdiary_test2.Data.Database.Activity.Activity;
import com.example.cultivationdiary_test2.Data.Database.Activity.ActivityDAO;
import com.example.cultivationdiary_test2.Data.Database.AppDatabase;
import com.example.cultivationdiary_test2.Data.Database.Event.Event;
import com.example.cultivationdiary_test2.Data.Database.Event.EventDAO;
import com.example.cultivationdiary_test2.Data.Database.Reminders.ConnectionTable.ReminderActivities;
import com.example.cultivationdiary_test2.Data.Database.Reminders.ConnectionTable.ReminderActivityDAO;
import com.example.cultivationdiary_test2.Data.Database.Reminders.ConnectionTable.ReminderEvents;
import com.example.cultivationdiary_test2.Data.Database.Reminders.ConnectionTable.ReminderEventsDAO;
import com.example.cultivationdiary_test2.ReminderActivity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RepositoryRemind {

    private final Context appContext;
    private final ReminderEventsDAO RemindEventDao;
    private final ReminderActivityDAO ReminderActivitysDAO;
    private final EventDAO EventsDao;
    private final ActivityDAO ActivitysDAO;
    private final RemindersDAO RemindDao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public RepositoryRemind(@NonNull Context context) {
        this.appContext = context.getApplicationContext();
        RemindDao = AppDatabase.getInstance(context).remindersDAO();
        EventsDao = AppDatabase.getInstance(context).eventDAO();
        RemindEventDao = AppDatabase.getInstance(context).reminderEventsDAO();
        ActivitysDAO = AppDatabase.getInstance(context).activityDAO();
        ReminderActivitysDAO = AppDatabase.getInstance(context).reminderActivityDAO();
    }

    public void saveActivityWithRemind(Activity activity, Reminders reminders) {
        executor.execute(() -> {
            long activityId = ActivitysDAO.insert(activity);
            long remindId = RemindDao.insert(reminders);

            ReminderActivities reminderActivities = new ReminderActivities(activityId, remindId);
            ReminderActivitysDAO.insert(reminderActivities);
            scheduleNotification(reminders);
        });
    }

    public void saveEventWithRemind(Event event, Reminders reminder) {
        executor.execute(() -> {
            long eventId = EventsDao.insert(event);
            long remindId = RemindDao.insert(reminder);

            ReminderEvents reminderEvents = new ReminderEvents(eventId, remindId);
            RemindEventDao.insert(reminderEvents);
            scheduleNotification(reminder);
        });
    }

    private void scheduleNotification(Reminders reminder) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(reminder.getDatetime_rmd(), formatter);
            long triggerTime = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

            AlarmManager alarmManager = (AlarmManager) appContext.getSystemService(Context.ALARM_SERVICE);
            if (alarmManager == null) return;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
                Intent intent = new Intent(ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
                intent.setData(Uri.parse("package:" + appContext.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                appContext.startActivity(intent);
                return;
            }

            Intent intent = new Intent(appContext, AlarmReceiver.class);
            intent.putExtra("REMINDER_NAME", reminder.getName_rmd());
            PendingIntent pendingIntent = PendingIntent.getBroadcast(appContext, (int) reminder.id_rmd,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
                } else {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
                }
            } catch (SecurityException e) {
                Log.e("AlarmError", "Permission denied for exact alarms", e);
            }

        } catch (Exception e) {
            Log.e("ScheduleError", "Error scheduling notification", e);
        }
    }

    public void saveReminder(Reminders reminders) {
        executor.execute(() -> RemindDao.insert(reminders));
    }

    public void updateReminder(Reminders reminders) {
        executor.execute(() -> RemindDao.update(reminders));
    }

    public void deleteReminder(Reminders reminders) {
        executor.execute(() -> RemindDao.delete(reminders));
    }

    public LiveData<List<Reminders>> getAllReminders() {
        return RemindDao.getAll();
    }

}
