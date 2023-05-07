package com.example.sim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private JobScheduler mScheduler;
    private static final String TAG_PUSH_NOTIFICATION_WORKER = "push_notification_worker";
    private PowerChangeReceiver powerChangeReceiver;
    BottomNavigationView bottomNavigationView;
    private static final int JOB_ID = 0;
    HomeFragment homeFragment = new HomeFragment();
    CoursesFragment coursesFragment = new CoursesFragment();
    ServicesFragment servicesFragment = new ServicesFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("primary_notification_channel", "SIM Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Important Announcement");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


        // PowerChangeReceiver
        powerChangeReceiver = new PowerChangeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        this.registerReceiver(powerChangeReceiver, filter);



        // Network Change Receiver
        NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(networkChangeReceiver, intentFilter);





        ImageView notification =  findViewById(R.id.notificationIcon);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Notification Triggered", Toast.LENGTH_SHORT).show();
                ComponentName serviceComponent = new ComponentName(getApplication(), MyJobService.class);
                JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceComponent);
                JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
                jobScheduler.schedule(builder.build());

            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);


        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String universityId = sharedPreferences.getString("userName", null);
        if (universityId != null) {
            TextView universityIdTextView = findViewById(R.id.welcomeName);
            universityIdTextView.setText(universityId);
        } else {
            Toast.makeText(this, "NO Username in shared preferences", Toast.LENGTH_SHORT).show();
        }




    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_fragment, homeFragment).commit();
            return true;
        } else if (item.getItemId() == R.id.service) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_fragment, servicesFragment).commit();
            return true;
        } else if (item.getItemId() == R.id.courses) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_fragment, coursesFragment).commit();
            return true;
        } else if (item.getItemId() == R.id.profile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_fragment, profileFragment).commit();
            return true;
        } else {
            return false;
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(powerChangeReceiver);
    }
}