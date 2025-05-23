package com.example.cultivationdiary_test2.Adapter.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultivationdiary_test2.Data.Database.Activity.Activity;
import com.example.cultivationdiary_test2.R;

import java.util.ArrayList;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityViewHolder> {

    private final ArrayList<Activity> activities;
    private final OnItemListener onItemListener;

    public ActivityAdapter(ArrayList<Activity> activities, OnItemListener onItemListener) {
        this.activities = activities;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activities, parent, false);
        return new ActivityViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        Activity activity = activities.get(position);
        holder.nameActivity.setText(activity.getName_activity());
        holder.Start_dateTime.setText(activity.getStard_datetime());
        holder.End_dateTime.setText(activity.getEnd_datetime());
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public interface OnItemListener {
        void onItemClicks(Activity activity);
    }
}
