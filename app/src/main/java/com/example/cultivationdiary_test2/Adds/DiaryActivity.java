package com.example.cultivationdiary_test2.Adds;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.cultivationdiary_test2.Data.Database.Diary.Diary;
import com.example.cultivationdiary_test2.ViewModel.DiaryViewModel;
import com.example.cultivationdiary_test2.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DiaryActivity extends AppCompatActivity {
    private DiaryViewModel diaryViewModel;
    private EditText thoughtsEditText;
    private String selectedDate;
    private Diary currentDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_layout);

        selectedDate = getIntent().getStringExtra("SELECTED_DATE");
        Log.e("Diary", selectedDate);
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
                currentDiary = diary;
                Log.e("Diary", "isExistingEntry = true");
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveOrUpdateDiary();
    }

    private void saveOrUpdateDiary() {
        String text = thoughtsEditText.getText().toString().trim();

        if (currentDiary != null) {
            currentDiary.setText(text);
            diaryViewModel.updateData(currentDiary);
            Log.e("Diary", "Update");
            Log.e("Diary", selectedDate);
        } else {
            Diary diary = new Diary(text, selectedDate);
            diaryViewModel.saveData(diary);
            Log.e("Diary", "Save");
            Log.e("Diary", selectedDate);
        }
    }
}