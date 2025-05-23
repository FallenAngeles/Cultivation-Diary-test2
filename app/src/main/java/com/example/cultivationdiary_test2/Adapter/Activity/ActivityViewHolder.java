package com.example.cultivationdiary_test2.Adapter.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultivationdiary_test2.Adds.ProjectListActivity;
import com.example.cultivationdiary_test2.R;

public class ActivityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ActivityAdapter.OnItemListener onItemListener;
    public final TextView nameActivity;
    public final TextView Start_dateTime;
    public final TextView End_dateTime;

    public ActivityViewHolder(@NonNull View itemView, ActivityAdapter.OnItemListener onItemListener) {
        super(itemView);
        nameActivity = itemView.findViewById(R.id.ActivityName);
        Start_dateTime = itemView.findViewById(R.id.StartDateTime);
        End_dateTime = itemView.findViewById(R.id.EndDateTime);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(@NonNull View view) {
        Intent intent = new Intent(view.getContext(), ProjectListActivity.class);
        view.getContext().startActivity(intent);
    }
}
