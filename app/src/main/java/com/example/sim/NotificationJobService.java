package com.example.sim;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class NotificationJobService extends JobService {

    NotificationManager notificationManager;
    NotificationChannel notificationChannel;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        createNotificationChannel();

        // launch application when notification is clicked
//        PendingIntent contentPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
        .setContentTitle("Job Service")
        .setContentText("Your Job ran to completion!");
//        builder.setContentIntent(contentPendingIntent);
        builder.setSmallIcon(R.mipmap.icon_round);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setAutoCancel(true);

        // Link builder to notification manager
        notificationManager.notify(0, builder.build());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }

    public void createNotificationChannel() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Job Service Notification", NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setLockscreenVisibility(NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.setDescription("Job Service Notification");

        // link notification channel to notification manager
        notificationManager.createNotificationChannel(notificationChannel);


    }
}
