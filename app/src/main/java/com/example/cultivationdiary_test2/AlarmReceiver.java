package com.example.cultivationdiary_test2;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String reminder_name = intent.getStringExtra("REMINDER_NAME");

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "reminder_chanel")
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Напоминание")
                .setContentText(reminder_name)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        manager.notify( (int) System.currentTimeMillis(), builder.build());
    }

}
