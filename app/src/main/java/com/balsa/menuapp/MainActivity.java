package com.balsa.menuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        new Handler().postDelayed(() -> decideStartScreen(), 2000);
    }

    private void handleConnectivityChanges() {
        connectivityLost = findViewById(R.id.txtConnectivityLost);
        networkConnectivity = new NetworkConnectivity(this);
        networkConnectivity.observe(this, isOnline -> {
            if(!isOnline){
                connectivityLost.setVisibility(View.VISIBLE);
            } else connectivityLost.setVisibility(View.INVISIBLE);
        });
    }
    private void decideStartScreen() {
        if(token.equals("")){
            Util.replaceFragment(getSupportFragmentManager(), R.id.fragment_container, LoginFragment.newInstance(), "LoginFragment");
        } else{
            Util.replaceFragment(getSupportFragmentManager(), R.id.fragment_container, VenuesListFragment.newInstance(), "VenuesListFragment");
        }
    }

    boolean doubleClickToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0
                && getSupportFragmentManager().findFragmentByTag("VenueDetailsFragment") != null
                && getSupportFragmentManager().findFragmentByTag("VenueDetailsFragment").isVisible()){
            //jedini slucaj kada mi ustvari treba back na prethodni screen
            getSupportFragmentManager().popBackStack();
        } else{
            if(!doubleClickToExitPressedOnce){
                // prvi put je back, ispisuje poruku
                doubleClickToExitPressedOnce = true;
                Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

                //posle dve sekunde zaboravlja da je kliknuto back dugme jedan put
                new Handler().postDelayed(() -> doubleClickToExitPressedOnce = false, 2000);
                return;
            }
            super.onBackPressed();
            if(doubleClickToExitPressedOnce){
                finish();
                System.exit(0);
            }
        }
    }
}