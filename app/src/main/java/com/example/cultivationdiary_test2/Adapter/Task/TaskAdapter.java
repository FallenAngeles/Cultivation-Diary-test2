package com.example.cultivationdiary_test2.Adapter.Task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.cultivationdiary_test2.Data.Database.Task.Repository;
import com.example.cultivationdiary_test2.Data.Database.Task.Task;
import com.example.cultivationdiary_test2.R;

import java.util.List;

public class TaskAdapter extends BaseAdapter {
    private final List<Task> tasks;
    private final LayoutInflater inflater;
    private final Repository repository;

    public TaskAdapter(Context context, List<Task> tasks, Repository repository) {
        this.tasks = tasks;
        this.inflater = LayoutInflater.from(context);
        this.repository = repository;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tasks.get(position).id_task;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.task_item, parent, false);
        }

        Task task = (Task) getItem(position);
        TextView taskName = view.findViewById(R.id.taskName);
        RadioButton taskStatus = view.findViewById(R.id.taskStatus);

        taskName.setText(task.getName_task());
        taskStatus.setChecked(task.getExecution());

        taskStatus.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setExecution(isChecked);
            repository.updateTask(task);
        });

        return view;
    }
}
