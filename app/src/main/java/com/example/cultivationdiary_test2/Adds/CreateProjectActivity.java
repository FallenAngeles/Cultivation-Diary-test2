package com.example.cultivationdiary_test2.Adds;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cultivationdiary_test2.Data.Database.Project.Project;
import com.example.cultivationdiary_test2.Data.Database.Project.Repository;
import com.example.cultivationdiary_test2.R;

import java.time.LocalDate;

public class CreateProjectActivity extends AppCompatActivity {

    private EditText editTextProjectName;
    private EditText editTextProjectDescription;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        repository = new Repository(getApplicationContext());

        editTextProjectName = findViewById(R.id.editTextProjectName);
        editTextProjectDescription = findViewById(R.id.editTextProjectDescription);
        Button buttonSaveProject = findViewById(R.id.buttonSaveProject);

        buttonSaveProject.setOnClickListener(v -> saveProject());
    }

    private void saveProject() {
        String name = editTextProjectName.getText().toString().trim();
        String description = editTextProjectDescription.getText().toString().trim();

        if (name.isEmpty()) {
            editTextProjectName.setError("Введите название");
            return;
        }
        Project project = new Project(LocalDate.now().toString(), name, description);
        // Установите остальные поля, если необходимо

        repository.saveProject(project);
        finish(); // Закрыть Activity
    }
}