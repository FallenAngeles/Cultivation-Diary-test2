package com.example.cultivationdiary_test2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultivationdiary_test2.Adapter.CalendarAdapter;
import com.example.cultivationdiary_test2.Adapter.GridSpacingItemDecoration;
import com.example.cultivationdiary_test2.Adapter.ViewDay;
import com.example.cultivationdiary_test2.Data.Database.Diary;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private DiaryViewModel diaryViewModel;
    private ArrayList<ViewDay> dayInMonthArray;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private List<Diary> currentMonthDiaries = new ArrayList<>();
    private Observer<List<Diary>> diariesObserver = diaries -> {
        currentMonthDiaries = diaries;
        setMonthView();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.RecyclerCalendar);
        monthYearText = findViewById(R.id.monthYearTV);
        calendarRecyclerView.addItemDecoration(new GridSpacingItemDecoration(12, 7));
    }

    private void setMonthView() {
        monthYearText.setText(MonthFromDate(selectedDate));
        ArrayList<ViewDay> daysInMonth = daysInMonthArray(selectedDate, currentMonthDiaries);
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);

        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    @NonNull
    private ArrayList<ViewDay> daysInMonthArray(LocalDate date, List<Diary> diaries) {
        ArrayList<ViewDay> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate LastMonth = selectedDate.minusMonths(1);
        LocalDate NextMonth = selectedDate.plusMonths(1);
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        LocalDate firstOfNextMonth = NextMonth.withDayOfMonth(1);
        LocalDate endOfMonth = LastMonth.withDayOfMonth(LastMonth.lengthOfMonth());
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
        DateTimeFormatter fullDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d");

        for(int i = 1; i <= 42; i++)
        {
            if(i < dayOfWeek)
            {
                int dayNumber = i - dayOfWeek + 1;
                LocalDate currentDay = endOfMonth.plusDays(dayNumber);
                String dayOfWeekName = currentDay.getDayOfWeek().getDisplayName(TextStyle.SHORT,
                        Locale.getDefault());
                String fullDate = currentDay.format(fullDateFormatter);
                daysInMonthArray.add(new ViewDay(String.valueOf(currentDay.format(format)),
                        MonthFromDate(LastMonth), dayOfWeekName, (float) 0.3, fullDate));
            }
            else if (i > daysInMonth + dayOfWeek - 1) {

                LocalDate currentDay = firstOfNextMonth.plusDays(i - dayOfWeek
                        - firstOfMonth.lengthOfMonth());
                String fullDate = currentDay.format(fullDateFormatter);
                String dayOfWeekName = currentDay.getDayOfWeek().getDisplayName(TextStyle.SHORT,
                        Locale.getDefault());
                daysInMonthArray.add(new ViewDay(String.valueOf(currentDay.format(format)),
                        MonthFromDate(NextMonth), dayOfWeekName, (float) 0.3, fullDate));
            }
            else
            {
                int dayNumber = i - dayOfWeek + 1;
                LocalDate currentDay = firstOfMonth.plusDays(dayNumber - 1);
                String dayOfWeekName = currentDay.getDayOfWeek().getDisplayName(TextStyle.SHORT,
                        Locale.getDefault());
                String fullDate = currentDay.format(fullDateFormatter);
                daysInMonthArray.add(new ViewDay(String.valueOf(dayNumber),
                        MonthFromDate(selectedDate), dayOfWeekName, (float) 1.0, fullDate));
            }
        }
        for (ViewDay day : daysInMonthArray) {
            boolean hasDiary = diaries.stream().anyMatch(d -> d.getDate().equals(day.getFullDate()));
            day.setHasDiary(hasDiary);
        }
        return  daysInMonthArray;
    }

    private void updateDiariesObservation() {
        String monthYear = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        diaryViewModel.getDiaryByMonth(monthYear).observe(this, diariesObserver);
    }

    private String MonthFromDate(@NonNull LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
        return date.format(formatter);
    }

    public void previousMonthAction(View view)
    {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    private String YearFromDate(@NonNull LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        return date.format(formatter);
    }

    @Override
    public void onItemClick(int position, @NonNull String dayText, String fullDate)
    {
        Intent intent = new Intent(this, DiaryActivity.class);
        intent.putExtra("SELECTED_DATE", fullDate);
        startActivity(intent);
    }

}