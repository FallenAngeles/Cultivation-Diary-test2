package com.example.cultivationdiary_test2;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.cultivationdiary_test2.Data.Database.Diary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DiaryActivity extends AppCompatActivity {
    private DiaryViewModel diaryViewModel;
    private EditText thoughtsEditText;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_layout);

        selectedDate = getIntent().getStringExtra("SELECTED_DATE");
        diaryViewModel = new ViewModelProvider(this).get(DiaryViewModel.class);

        TextView dateTextView = findViewById(R.id.DateDiary);
        thoughtsEditText = findViewById(R.id.EditThoughts);

        // Форматирование даты
        LocalDate date = LocalDate.parse(selectedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String formattedDate = date.format(DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("ru")));
        dateTextView.setText(formattedDate);

        // Загрузка существующей записи
        diaryViewModel.getDiaryByDate(selectedDate).observe(this, diary -> {
            if (diary != null) {
                thoughtsEditText.setText(diary.getText());
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveDiary();
    }

    private void saveDiary() {
        String text = thoughtsEditText.getText().toString().trim();
        Diary diary = new Diary(text, selectedDate);
        diaryViewModel.saveData(diary);
    }
}