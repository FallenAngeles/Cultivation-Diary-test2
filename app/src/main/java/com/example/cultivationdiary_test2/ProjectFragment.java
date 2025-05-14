/*
package com.example.cultivationdiary_test2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cultivationdiary_test2.Adapter.Project.ProjectAdapter;
import com.example.cultivationdiary_test2.Data.Database.Project.Project;

import java.util.ArrayList;

public class ProjectFragment extends Fragment {

    ArrayList<Project> projects = new ArrayList<Project>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_activity, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.RecycleProject);
        ProjectAdapter adapter = new ProjectAdapter(projects, (ProjectAdapter.OnItemListener) this);
        recyclerView.setAdapter(adapter);

        return view;
    }
}*/