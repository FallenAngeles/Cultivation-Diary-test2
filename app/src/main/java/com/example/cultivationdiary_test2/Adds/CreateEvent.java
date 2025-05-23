package com.example.cultivationdiary_test2.Adds;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cultivationdiary_test2.Data.Database.Event.Event;
import com.example.cultivationdiary_test2.Data.Database.Event.Repository;
import com.example.cultivationdiary_test2.R;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateEvent extends AppCompatActivity {

    private EditText nameEvent;
    private LocalDate startdate;
    private LocalTime starttime;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        repository = new Repository(getApplicationContext());
        nameEvent = findViewById(R.id.addEventName);

        DatePicker startDate = findViewById(R.id.addEventStart);
        TimePicker startTime = findViewById(R.id.EventTime);
        startTime.setIs24HourView(true);

        Button button = findViewById(R.id.buttonSaveEvent);

        startDate.init(2025, 5, 17, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                startdate = LocalDate.of(year, monthOfYear + 1, dayOfMonth);
                Log.e("Event Date", String.valueOf(startDate));
            }
        });

        startTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                starttime = LocalTime.of(hourOfDay, minute);
            }
        });

        button.setOnClickListener(v -> saveEvent());

    }

    private void saveEvent() {

        if (startdate == null) {
            startdate = LocalDate.now();}

        String DateTime = startdate + " " + starttime;
        String name = nameEvent.getText().toString().trim();

        if (name.isEmpty()) {
            nameEvent.setError("Введите название");
            return;}

        Log.e("Events date", name);
        Log.e("Events date", DateTime);

        Event event = new Event(name, DateTime, startdate.toString());
        repository.saveEvent(event);
        finish();
    }

}