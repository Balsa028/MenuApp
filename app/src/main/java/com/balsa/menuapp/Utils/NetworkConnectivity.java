package com.balsa.menuapp.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class NetworkConnectivity extends LiveData<Boolean> {

    ConnectivityManager connectivityManager;

    //constructor
    public NetworkConnectivity(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager = context.getSystemService(ConnectivityManager.class);
        }
    }

    //creating network request object
    NetworkRequest networkRequest = new NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build();

    //creating network callback
    private ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {

        @Override
        public void onAvailable(@NonNull Network network) {
            super.onAvailable(network);
            postValue(true);
        }

        @Override
        public void onLost(@NonNull Network network) {
            super.onLost(network);
            postValue(false);
        }

        @Override
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            boolean isInternetWorking = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            boolean isInternetValidated = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
            postValue(isInternetWorking && isInternetValidated);
        }
    };

    //kada postoji makar jedan aktivan observer
    @Override
    protected void onActive() {
        super.onActive();
        //ukoliko je aktivan neki observer registrujemo network callback
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback);
    }

    //kada nema aktivnih observera
    @Override
    protected void onInactive() {
        super.onInactive();
        //ukoliko nema aktivnih observera odregistrujemo network callback
        connectivityManager.unregisterNetworkCallback(networkCallback);
    }


}
