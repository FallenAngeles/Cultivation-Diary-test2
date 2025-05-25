package com.example.cultivationdiary_test2.Adapter.Events;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultivationdiary_test2.Data.Database.Event.Event;
import com.example.cultivationdiary_test2.R;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private final ArrayList<Event> events;
    private final onItemListener onItemListener;

    public EventAdapter(ArrayList<Event> events, onItemListener onItemListener) {
        this.events = events;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.event_list, parent, false);
        return new EventViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = events.get(position);
        holder.name_event.setText(event.getName_event());
        holder.datetime_event.setText(event.getStart_datetime());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public interface onItemListener {
        void onItemClicks(Event event);
    }
}
