package com.balsa.menuapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Util {

    public static void replaceFragment(FragmentManager manager, int containerId, Fragment fragment){
        if(!manager.isDestroyed()){
            manager
                    .beginTransaction()
                    .replace(containerId, fragment)
                    .commit();
        }
    }

    public static void saveTokenInSharedPrefs(String token, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.USER_TOKEN, token);
        editor.apply();
    }

    public static String readTokenFromSharedPrefs(Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        return  preferences.getString(Constants.USER_TOKEN, "");
    }

}
