package com.example.sim;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyJobService extends JobService {
    NotificationManager notificationManager;
    NotificationChannel notificationChannel;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    @Override
    public boolean onStartJob(JobParameters params) {
        // Implement logic to show push notification when user clicks on ImageView
        notificationManager = getSystemService(NotificationManager.class);
        Notification.Builder builder = new Notification.Builder(this, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.simlogo)
                .setContentTitle("Dr. khaled_elwazan ")
                .setContentText("My dear students your mid term marks are available")
                .setAutoCancel(true)
                .setTimeoutAfter(10000);
        notificationManager.notify(1, builder.build());
        return false;
    }
    @Override
    public boolean onStopJob(JobParameters params) {
        // Return true to indicate that the job has been stopped.
        return true;
    }
}
