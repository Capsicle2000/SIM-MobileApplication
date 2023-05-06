package com.example.sim;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        if (intentAction != null) {
            String toastMessage = "";
            switch (intentAction) {
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = "Power connected";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = "Power disconnected";
                    break;
            }
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }
}