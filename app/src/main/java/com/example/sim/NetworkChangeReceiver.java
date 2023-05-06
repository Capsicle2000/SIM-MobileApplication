package com.example.sim;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.widget.Toast;
public class NetworkChangeReceiver extends BroadcastReceiver {

    private boolean isToastShown = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkRequest request = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build();

        ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                // Called when network is available
                if (isToastShown) {
                    Toast.makeText(context, "Network connection restored", Toast.LENGTH_LONG).show();
                    isToastShown = false;
                }
            }

            @Override
            public void onLost(Network network) {
                // Called when network is lost
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);

                if (networkCapabilities == null || !networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    // There is no internet connection
                    if (!isToastShown) {
                        Toast.makeText(context, "Please turn on Wi-Fi or mobile data", Toast.LENGTH_LONG).show();
                        isToastShown = true;
                    }
                }
            }
        };

        connectivityManager.registerNetworkCallback(request, networkCallback);
    }
}
