package com.balsa.menuapp.Utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Util {

    public static ProgressDialog progressDialog;

    public static void replaceFragment(FragmentManager manager, int containerId, Fragment fragment, String tag){
        if(!manager.isDestroyed()){
            manager.beginTransaction()
                    .replace(containerId, fragment, tag)
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

    public static void showProgressDialog(Context context, String message){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public static void dismissProgressDialog(){
        if(progressDialog != null) progressDialog.dismiss();
    }

    public static void showAlertDialog(Fragment fragment, String title, String message, String buttonText){
        new AlertDialog.Builder(fragment.requireActivity())
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(buttonText, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    fragment.requireActivity().getSupportFragmentManager().popBackStack();
                }).create().show();
    }

}
