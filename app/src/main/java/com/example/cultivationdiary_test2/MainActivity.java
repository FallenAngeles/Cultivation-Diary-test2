package com.example.cultivationdiary_test2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cultivationdiary_test2.Fragments.ActivityFragment;
import com.example.cultivationdiary_test2.Fragments.CalendarFragment;
import com.example.cultivationdiary_test2.Fragments.ProjectFragment;
import com.example.cultivationdiary_test2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        replaceFragment(new CalendarFragment());
        binding.BottomAppBarNavigation.setBackground(null);
        binding.BottomAppBarNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.Calendar) {
                replaceFragment(new CalendarFragment());
            } else if (itemId == R.id.Project) {
                replaceFragment(new ProjectFragment());
            } else if (itemId == R.id.Activity) {
                replaceFragment(new ActivityFragment());
            }

            return true;
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Events) {
            Intent intent = new Intent(this, EventsActivity.class);
            startActivity(intent);
        }
        return true;
    }
}