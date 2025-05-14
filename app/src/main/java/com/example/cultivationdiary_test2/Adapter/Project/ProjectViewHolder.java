package com.example.cultivationdiary_test2.Adapter.Project;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cultivationdiary_test2.R;

public class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ProjectAdapter.OnItemListener onItemListener;
    public final TextView NameProject;
    public final TextView Description;
    public final ImageView Icon;

    public ProjectViewHolder(@NonNull View itemView, ProjectAdapter.OnItemListener onItemListener) {
        super(itemView);
        NameProject = itemView.findViewById(R.id.nameProject);
        Description = itemView.findViewById(R.id.descriptionProject);
        Icon = itemView.findViewById(R.id.icon_prj);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(@NonNull View v) {

    }
}
