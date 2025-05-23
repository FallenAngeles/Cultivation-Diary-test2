package com.example.cultivationdiary_test2.Adds;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cultivationdiary_test2.Adapter.Task.TaskAdapter;
import com.example.cultivationdiary_test2.Data.Database.Task.Repository;
import com.example.cultivationdiary_test2.Data.Database.Task.Task;
import com.example.cultivationdiary_test2.R;

public class ProjectListActivity extends AppCompatActivity {
    private ListView taskListView;
    private Repository repository;
    private TaskAdapter adapter;
    private int projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        projectId = getIntent().getIntExtra("PROJECT_ID", -1);
        if (projectId == -1) finish();

        repository = new Repository(getApplicationContext());

        setupListView();
        setupAddButton();
    }

    private void setupListView() {
        taskListView = findViewById(R.id.taskListView);
        repository.getByGroup(projectId).observe(this, tasks -> {
            adapter = new TaskAdapter(this, tasks, repository);
            taskListView.setAdapter(adapter);
        });
    }

    private void setupAddButton() {
        findViewById(R.id.addTaskButton).setOnClickListener(v -> {
            showAddTaskDialog();
        });
    }

    private void showAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_add_task, null);
        EditText taskName = view.findViewById(R.id.editTaskName);

        builder.setView(view)
                .setTitle("Новая задача")
                .setPositiveButton("Добавить", (dialog, which) -> {
                    String name = taskName.getText().toString();
                    if (!name.isEmpty()) {
                        Task newTask = new Task(projectId, name, false);
                        repository.saveTask(newTask);
                    }
                })
                .setNegativeButton("Отмена", null)
                .show();
    }

}