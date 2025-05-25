package com.example.cultivationdiary_test2.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cultivationdiary_test2.Adapter.Project.ProjectAdapter;
import com.example.cultivationdiary_test2.Adds.CreateProjectActivity;
import com.example.cultivationdiary_test2.Data.Database.Project.Project;
import com.example.cultivationdiary_test2.Data.Database.Project.Repository;
import com.example.cultivationdiary_test2.R;

import java.util.ArrayList;

public class ProjectFragment extends Fragment implements ProjectAdapter.OnItemListener {

    ArrayList<Project> projects = new ArrayList<Project>();
    ImageButton button;
    private Repository repository;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.RecycleProject);
        ProjectAdapter adapter = new ProjectAdapter(projects, (ProjectAdapter.OnItemListener) this);
        recyclerView.setAdapter(adapter);

        repository = new Repository(requireContext());
        repository.getAllProject().observe(getViewLifecycleOwner(), projects -> {
            this.projects.clear();
            this.projects.addAll(projects);
            adapter.notifyDataSetChanged();
        });


        button = view.findViewById(R.id.btn_AddProject);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreateProjectActivity.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onItemClick(Project project) {

    }
}