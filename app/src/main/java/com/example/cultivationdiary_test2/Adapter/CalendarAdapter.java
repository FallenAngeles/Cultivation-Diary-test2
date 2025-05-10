package com.example.cultivationdiary_test2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final ArrayList<ViewDay> daysOfMonth;
    private final OnItemListener onItemListener;

    public CalendarAdapter(ArrayList<ViewDay> daysOfMonth, OnItemListener onItemListener)
    {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(com.example.cultivationdiary_test2.R.layout.calendar_day, parent, false);
        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position)
    {
        ViewDay day = daysOfMonth.get(position);
        holder.dayOfMonth.setText(day.getDay());
        holder.Month.setText(day.getMonth());
        holder.dayOfWeek.setText(day.getDayOfWeek());
        holder.dayOfMonth.setAlpha(day.getAlpha());
        holder.Month.setAlpha(day.getAlpha());
        holder.dayOfWeek.setAlpha(day.getAlpha());
        holder.DayLayout.setAlpha(day.getAlpha() + (float) 0.46);
        holder.itemView.setTag(day.getFullDate());

        if (!day.getHasDiary()) {
            holder.iconDiary.setVisibility(View.GONE);
        }
        else {
            holder.iconDiary.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount()
    {
        return daysOfMonth.size();
    }

    public interface OnItemListener
    {
        void onItemClick(int position, String dayText, String fulldate);
    }
}