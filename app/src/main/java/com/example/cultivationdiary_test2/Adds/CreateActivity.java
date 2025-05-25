package com.example.cultivationdiary_test2.Adds;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cultivationdiary_test2.Data.Database.Activity.Activity;
import com.example.cultivationdiary_test2.Data.Database.Activity.Repository;
import com.example.cultivationdiary_test2.Data.Database.Reminders.Reminders;
import com.example.cultivationdiary_test2.Data.Database.Reminders.RepositoryRemind;
import com.example.cultivationdiary_test2.R;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CreateActivity extends AppCompatActivity {

    private EditText nameActivity;
    private LocalDate StartDate;
    private LocalTime StartTime;
    private LocalTime EndTime;
    private RepositoryRemind repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        repository = new RepositoryRemind(getApplicationContext());
        nameActivity = findViewById(R.id.addActivityName);

        DatePicker startActivity = findViewById(R.id.addActivityStart);
        TimePicker startTime = findViewById(R.id.StartTime);
        startTime.setIs24HourView(true);
        TimePicker endTime = findViewById(R.id.EndTime);
        endTime.setIs24HourView(true);

        Button button = findViewById(R.id.buttonSaveActivity);

        startActivity.init(2025, 5, 17, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                StartDate = LocalDate.of(year, monthOfYear, dayOfMonth);
                Log.e("Activity Date", String.valueOf(StartDate));
            }
        });

        startTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                StartTime = LocalTime.of(hourOfDay, minute);
            }
        });

        endTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                EndTime = LocalTime.of(hourOfDay, minute);
            }
        });

        button.setOnClickListener(v -> saveActivity());

    }

    private void saveActivity() {
        String name = nameActivity.getText().toString().trim();

        if (name.isEmpty()) {
            nameActivity.setError("Введите название");
            return;
        }
        if (StartDate == null) {
            StartDate = LocalDate.now();
        }

        LocalDateTime StartDateTime = LocalDateTime.of(StartDate, StartTime);
        String EndDateTime;

        if (StartTime.isAfter(EndTime) || StartTime == EndTime) {
            StartDate = StartDate.plusDays(1);
            EndDateTime = StartDate + " " + EndTime;
        } else {
            EndDateTime = StartDate + " " + EndTime;
        }

        String remindDateTime = StartDateTime.minusMinutes(15).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String remindName = "Активность " + name;

        Activity activity = new Activity(name, StartDateTime.toString(), EndDateTime);
        Reminders reminders = new Reminders(remindName, remindDateTime);
        repository.saveActivityWithRemind(activity, reminders);
        finish();
    }
}