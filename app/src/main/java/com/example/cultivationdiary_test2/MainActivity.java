package com.example.cultivationdiary_test2;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cultivationdiary_test2.Fragments.ActivityFragment;
import com.example.cultivationdiary_test2.Fragments.CalendarFragment;
import com.example.cultivationdiary_test2.Fragments.ProjectFragment;
import com.example.cultivationdiary_test2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{

    private static final int PERMISSION_REQUEST_CODE = 1001;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        replaceFragment(new CalendarFragment());
        binding.BottomAppBarNavigation.setBackground(null);
        binding.BottomAppBarNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.Calendar) {
                replaceFragment(new CalendarFragment());
            } else if (itemId == R.id.Project) {
                replaceFragment(new ProjectFragment());
            } else if (itemId == R.id.Activity) {
                replaceFragment(new ActivityFragment());
            }

            return true;
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }

        createNotificationChannel();

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Events) {
            Intent intent = new Intent(this, EventsActivity.class);
            startActivity(intent);
        }
        if (id == R.id.Reminder) {
            Intent intent = new Intent(this, ReminderActivity.class);
            startActivity(intent);
        }
        return true;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "reminder_chanel", "Reminders", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Channel for reminder notifications");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}