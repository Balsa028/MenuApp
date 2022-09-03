package com.balsa.menuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.balsa.menuapp.Login.LoginFragment;
import com.balsa.menuapp.Utils.NetworkConnectivity;
import com.balsa.menuapp.Utils.Util;
import com.balsa.menuapp.Venues.VenuesListFragment;


public class MainActivity extends AppCompatActivity {

    private String token;
    private NetworkConnectivity networkConnectivity;
    private TextView connectivityLost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleConnectivityChanges();

       // Util.saveTokenInSharedPrefs("", this); //---> za logout, dok se ne napravi logout onClick
        token = Util.readTokenFromSharedPrefs(this);

        //cisto radi simuliranja splasha
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                decideStartScreen();
            }
        }, 2000);
    }

    private void handleConnectivityChanges() {
        connectivityLost = findViewById(R.id.txtConnectivityLost);
        networkConnectivity = new NetworkConnectivity(this);
        networkConnectivity.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isOnline) {
                if(!isOnline){
                    connectivityLost.setVisibility(View.VISIBLE);
                } else connectivityLost.setVisibility(View.INVISIBLE);
            }
        });
    }
    private void decideStartScreen() {
        if(token.equals("")){
            Util.replaceFragment(getSupportFragmentManager(), R.id.fragment_container, LoginFragment.newInstance());
        } else{
            Util.replaceFragment(getSupportFragmentManager(), R.id.fragment_container, VenuesListFragment.newInstance());
        }
    }
}