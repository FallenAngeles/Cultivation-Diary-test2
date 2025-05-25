package com.example.cultivationdiary_test2.Adapter.Reminder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultivationdiary_test2.Adapter.Events.EventViewHolder;
import com.example.cultivationdiary_test2.Data.Database.Reminders.Reminders;
import com.example.cultivationdiary_test2.R;

import java.util.ArrayList;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderViewHolder> {

    private final ArrayList<Reminders> reminders;
    private final onItemListener onItemListener;

    public ReminderAdapter(ArrayList<Reminders> reminders, onItemListener onItemListener) {
        this.reminders = reminders;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.reminder_info, parent, false);
        return new ReminderViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        Reminders reminder = reminders.get(position);
        holder.nameRemind.setText(reminder.getName_rmd());
        holder.DatetimeRemind.setText(reminder.getDatetime_rmd());
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }

    public interface onItemListener {
        void onItemClick(Reminders reminders);
    }
}
