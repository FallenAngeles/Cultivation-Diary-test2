package com.example.cultivationdiary_test2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;

import com.example.cultivationdiary_test2.Adapter.Caledar.CalendarAdapter;
import com.example.cultivationdiary_test2.Adapter.Caledar.GridSpacingItemDecoration;
import com.example.cultivationdiary_test2.Adapter.Caledar.ViewDay;
import com.example.cultivationdiary_test2.Data.Database.Diary.Diary;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CalendarFragment extends Fragment implements CalendarAdapter.OnItemListener {

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        diaryViewModel = new ViewModelProvider(this).get(DiaryViewModel.class);
        View view;
        view = inflater.inflate(R.layout.fragment_calendar, container, false);

        view.findViewById(R.id.btn_previous_month).setOnClickListener(v -> previousMonthAction());
        view.findViewById(R.id.btn_next_month).setOnClickListener(v -> nextMonthAction());

        selectedDate = LocalDate.now();
        calendarRecyclerView = view.findViewById(R.id.RecyclerCalendar);
        monthYearText = view.findViewById(R.id.monthYearTV);
        calendarRecyclerView.addItemDecoration(new GridSpacingItemDecoration(12, 7));
        updateDiariesObservation();

        setMonthView();
        return view;
    }

    private void setMonthView() {
        monthYearText.setText(MonthFromDate(selectedDate));
        ArrayList<ViewDay> daysInMonth = daysInMonthArray(selectedDate, currentMonthDiaries);
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(requireContext(), 7);
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
            boolean hasDiary = diaries.stream().anyMatch(d -> d.getCreateDate().equals(day.getFullDate()));
            day.setHasDiary(hasDiary);
        }
        return  daysInMonthArray;
    }

    private void updateDiariesObservation() {
        String monthYear = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        diaryViewModel.getDiaryByMonth(monthYear).observe(getViewLifecycleOwner(), diariesObserver);
    }

    private String MonthFromDate(@NonNull LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
        return date.format(formatter);
    }

    public void previousMonthAction()
    {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction()
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

    }
}