package com.example.cultivationdiary_test2.Fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cultivationdiary_test2.Adapter.Caledar.CalendarAdapter;
import com.example.cultivationdiary_test2.Adapter.Caledar.GridSpacingItemDecoration;
import com.example.cultivationdiary_test2.Adapter.Caledar.ViewDay;
import com.example.cultivationdiary_test2.Data.Database.Diary.Diary;
import com.example.cultivationdiary_test2.Data.Database.Event.Event;
import com.example.cultivationdiary_test2.Data.Database.Event.Repository;
import com.example.cultivationdiary_test2.R;
import com.example.cultivationdiary_test2.ViewModel.DiaryViewModel;
import com.example.cultivationdiary_test2.ViewModel.EventViewModel;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CalendarFragment extends Fragment implements CalendarAdapter.OnItemListener {

    private DiaryViewModel diaryViewModel;
    private EventViewModel eventViewModel;
    private ArrayList<ViewDay> dayInMonthArray;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private List<Diary> currentMonthDiaries = new ArrayList<>();
    private Observer<List<Diary>> diariesObserver = diaries -> {
        currentMonthDiaries = diaries;
        setMonthView();
    };
    private List<Event> currentMonthEvents = new ArrayList<>();
    private Observer<List<Event>> eventsObserver = events -> {
        currentMonthEvents = events;
        setMonthView();
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        diaryViewModel = new ViewModelProvider(this).get(DiaryViewModel.class);
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        View view;
        view = inflater.inflate(R.layout.fragment_calendar, container, false);

        view.findViewById(R.id.btn_previous_month).setOnClickListener(v -> previousMonthAction());
        view.findViewById(R.id.btn_next_month).setOnClickListener(v -> nextMonthAction());

        selectedDate = LocalDate.now();
        calendarRecyclerView = view.findViewById(R.id.RecyclerCalendar);
        monthYearText = view.findViewById(R.id.monthYearTV);
        calendarRecyclerView.addItemDecoration(new GridSpacingItemDecoration(12, 7));
        updateDiariesObservation();

        monthYearText.setOnClickListener(v -> showMonthPicker());
        //setMonthView();
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
            if (hasDiary) {
                Log.e("Icons", day.getDay());
            }
            day.setHasDiary(hasDiary);
            boolean hasEvent = currentMonthEvents.stream().anyMatch(e -> e.getDate().equals(day.getFullDate()));
            day.setHasEvent(hasEvent);
        }
        return  daysInMonthArray;
    }

    private void updateDiariesObservation() {
        String monthYear = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        diaryViewModel.getDiaryByMonth(monthYear).observe(getViewLifecycleOwner(), diariesObserver);
        eventViewModel.getEventByMonth(monthYear).observe(getViewLifecycleOwner(), eventsObserver);
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

    public void showMonthPicker() {
        // Создаем кастомное View для диалога
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View dialogView = inflater.inflate(R.layout.month_year_picker, null);

        // Инициализируем Spinner
        Spinner monthSpinner = dialogView.findViewById(R.id.month_spinner);
        Spinner yearSpinner = dialogView.findViewById(R.id.year_spinner);

        // Настройка месяцев
        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.months_array,
                android.R.layout.simple_spinner_item
        );
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);
        monthSpinner.setSelection(selectedDate.getMonthValue() - 1);

        List<Integer> years = new ArrayList<>();
        int currentYear = Year.now().getValue();
        for (int i = currentYear - 5; i <= currentYear + 5; i++) {
            years.add(i);
        }

        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                years
        );
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setSelection(years.indexOf(selectedDate.getYear()));

        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .setPositiveButton("OK", (d, which) -> {
                    int selectedYear = (int) yearSpinner.getSelectedItem();
                    int selectedMonth = monthSpinner.getSelectedItemPosition() + 1;

                    selectedDate = LocalDate.of(selectedYear, selectedMonth, 1);
                    setMonthView();
                    updateDiariesObservation();
                })
                .setNegativeButton("Cancel", null)
                .create();

        dialog.show();
    }

    @Override
    public void onItemClick(int position, @NonNull String dayText, String fullDate)
    {

    }
}