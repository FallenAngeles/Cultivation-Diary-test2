package com.example.cultivationdiary_test2.Adapter.Reminder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultivationdiary_test2.R;

public class ReminderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ReminderAdapter.onItemListener onItemListener;
    public TextView nameRemind;
    public TextView DatetimeRemind;

    public ReminderViewHolder(@NonNull View itemView, ReminderAdapter.onItemListener onItemListener) {
        super(itemView);
        nameRemind = itemView.findViewById(R.id.NameRemind);
        DatetimeRemind = itemView.findViewById(R.id.RemindDateTime);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
