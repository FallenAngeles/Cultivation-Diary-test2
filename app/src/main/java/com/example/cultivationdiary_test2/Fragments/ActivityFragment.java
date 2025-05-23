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

import com.example.cultivationdiary_test2.Adapter.Activity.ActivityAdapter;
import com.example.cultivationdiary_test2.Adds.CreateActivity;
import com.example.cultivationdiary_test2.Data.Database.Activity.Activity;
import com.example.cultivationdiary_test2.Data.Database.Activity.Repository;
import com.example.cultivationdiary_test2.R;

import java.util.ArrayList;

public class ActivityFragment extends Fragment implements ActivityAdapter.OnItemListener {

    ArrayList<Activity> activity = new ArrayList<Activity>();
    ImageButton button;
    private Repository repository;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);
        
        RecyclerView recyclerView = view.findViewById(R.id.RecycleActivity);
        ActivityAdapter adapter = new ActivityAdapter(activity, (ActivityAdapter.OnItemListener) this);
        recyclerView.setAdapter(adapter);

        repository = new Repository(requireContext());
        repository.getActivity().observe(getViewLifecycleOwner(), activities -> {
            this.activity.clear();
            this.activity.addAll(activities);
            adapter.notifyDataSetChanged();
        });

        button = view.findViewById(R.id.btn_addActivity);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreateActivity.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onItemClicks(Activity activity) {

    }
}