package com.balsa.menuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.balsa.menuapp.Login.LoginFragment;
import com.balsa.menuapp.Venues.VenuesListFragment;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private Executor executor;
    private Handler handler;
    // TODO: 8/29/2022 Umesto sample kasnije proveriti da li je sacuvan Token ako da idi na listu ako ne idi na login screen
    private int sample = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    private void startSplashScreenBackgroundTask() {
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                int i = sample;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if( i == 1){
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, LoginFragment.newInstance())
                                    .commit();
                        } else{
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, VenuesListFragment.newInstance())
                                    .commit();
                        }
                    }
                });
            }
        });
    }
}