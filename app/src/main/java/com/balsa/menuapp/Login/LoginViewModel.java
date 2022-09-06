package com.balsa.menuapp.Login;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.balsa.menuapp.ApiService;

public class LoginViewModel extends ViewModel {

    private ApiService apiService;

    public LoginViewModel() {
        apiService = ApiService.getInstance();
    }

    public MutableLiveData<String> getIsLoginSuccessfull() {
        return apiService.getIsLoginSuccessfull();
    }

    public MutableLiveData<String> getEmailEditText() {
        return apiService.getEmailLiveData();
    }

    public MutableLiveData<String> getPasswordEditText() {
        return apiService.getPasswordLiveData();
    }

    public void performSignIn(String email, String password, LoginFragment fragment) {
        apiService.performSignIn(email, password, fragment);
    }

    public void configurationChanged(String email, String password) {
        apiService.configurationChanged(email, password);
    }

}