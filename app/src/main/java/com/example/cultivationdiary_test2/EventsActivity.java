package com.example.cultivationdiary_test2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultivationdiary_test2.Adapter.Events.EventAdapter;
import com.example.cultivationdiary_test2.Adds.CreateEvent;
import com.example.cultivationdiary_test2.Data.Database.Event.Event;
import com.example.cultivationdiary_test2.Data.Database.Event.Repository;

import java.util.ArrayList;

public class EventsActivity extends AppCompatActivity implements EventAdapter.onItemListener{

    ArrayList<Event> events = new ArrayList<Event>();
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        RecyclerView recyclerView = findViewById(R.id.RecycleEvent);
        EventAdapter adapter = new EventAdapter(events, (EventAdapter.onItemListener) this);
        recyclerView.setAdapter(adapter);

        repository = new Repository(getApplicationContext());
        repository.getAllEvents().observe(this, events -> {
            this.events.clear();
            this.events.addAll(events);
            adapter.notifyDataSetChanged();
        });

        Toolbar toolbar = findViewById(R.id.toolbarEvents);
        setSupportActionBar(toolbar);

    }

    @Override
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
    public void onItemClicks(Event event) {

    }

}