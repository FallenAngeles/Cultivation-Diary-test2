package com.example.cultivationdiary_test2.Adapter.Project;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cultivationdiary_test2.Data.Database.Project.Project;
import com.example.cultivationdiary_test2.R;

import java.util.ArrayList;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectViewHolder> {

    private final ArrayList<Project> projects;
    private final OnItemListener onItemListener;

    public ProjectAdapter(ArrayList<Project> projects, OnItemListener onItemListener) {
        this.projects = projects;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.projects, parent, false);
        return new ProjectViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int i) {
        Project project = projects.get(i);
        holder.NameProject.setText(project.getName_project());
        holder.Description.setText(project.getProject_description());
        Drawable icon = holder.itemView.getContext().getResources().getDrawable(project.getIcon());
        holder.Icon.setImageDrawable(icon);

    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public interface OnItemListener
    {
        void onItemClick(int i, String nameProject, String description);
    }

}
