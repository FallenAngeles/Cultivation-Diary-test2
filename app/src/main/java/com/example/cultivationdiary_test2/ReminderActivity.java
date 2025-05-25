package com.example.cultivationdiary_test2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultivationdiary_test2.Adapter.Reminder.ReminderAdapter;
import com.example.cultivationdiary_test2.Adds.CreateEvent;
import com.example.cultivationdiary_test2.Data.Database.Reminders.Reminders;
import com.example.cultivationdiary_test2.Data.Database.Reminders.RepositoryRemind;

import java.util.ArrayList;

public class ReminderActivity extends AppCompatActivity implements ReminderAdapter.onItemListener {

    ArrayList<Reminders> reminders = new ArrayList<Reminders>();
    private RepositoryRemind repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        RecyclerView recyclerView = findViewById(R.id.RecycleReminder);
        ReminderAdapter adapter = new ReminderAdapter(reminders, (ReminderAdapter.onItemListener) this);
        recyclerView.setAdapter(adapter);

        repository = new RepositoryRemind(getApplicationContext());
        repository.getAllReminders().observe(this, reminders -> {
            this.reminders.clear();
            this.reminders.addAll(reminders);
            adapter.notifyDataSetChanged();
        });

        Toolbar toolbar = findViewById(R.id.toolbarReminders);
        setSupportActionBar(toolbar);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.events_top_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.EventsBack) {
            finish();
        }
        if (id == R.id.EventsAdd) {
            Intent intent = new Intent(this, CreateEvent.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onItemClick(Reminders reminders) {

    }
}