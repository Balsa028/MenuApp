package com.balsa.menuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.balsa.menuapp.Login.LoginFragment;
import com.balsa.menuapp.Utils.Util;
import com.balsa.menuapp.Venues.VenuesListFragment;


public class MainActivity extends AppCompatActivity {

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    private void decideStartScreen() {
        if(token.equals("")){
            Util.replaceFragment(getSupportFragmentManager(), R.id.fragment_container, LoginFragment.newInstance());
        } else{
            Util.replaceFragment(getSupportFragmentManager(), R.id.fragment_container, VenuesListFragment.newInstance());
        }
    }
}