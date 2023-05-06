package com.example.sim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    CoursesFragment coursesFragment = new CoursesFragment();
    ServicesFragment servicesFragment = new ServicesFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

//        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
//        String universityId = sharedPreferences.getString("university_id", "");
//
//        TextView universityIdTextView = findViewById(R.id.fullNameMain);
//        universityIdTextView.setText(universityId);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_fragment, homeFragment).commit();
            return true;
        } else if (item.getItemId() == R.id.service) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_fragment, coursesFragment).commit();
            return true;
        } else if (item.getItemId() == R.id.courses) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_fragment, servicesFragment).commit();
            return true;
        } else if (item.getItemId() == R.id.profile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_fragment, profileFragment).commit();
            return true;
        } else {
            return false;
        }
    }
}