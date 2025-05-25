package com.example.cultivationdiary_test2.Adapter.Events;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultivationdiary_test2.R;

public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final EventAdapter.onItemListener onItemListener;
    public TextView name_event;
    public TextView datetime_event;

    public EventViewHolder(@NonNull View itemView, EventAdapter.onItemListener onItemListener) {
        super(itemView);
        name_event = itemView.findViewById(R.id.EventName);
        datetime_event = itemView.findViewById(R.id.EventDatetime);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
