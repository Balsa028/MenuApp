package com.balsa.menuapp.Login;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.balsa.menuapp.ApiService;

public class LoginViewModel extends AndroidViewModel {

    private ApiService apiService;
    private Application application;

    public LoginViewModel(Application application) {
        super(application);
        this.application = application;
        this.apiService = ApiService.getInstance();
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
    public MutableLiveData<Boolean> getIsLoadingLoginLiveData(){
        return apiService.getIsLoadingLoginLiveData();
    }

    public void performSignIn(String email, String password) {
        apiService.performSignIn(email, password, application.getApplicationContext());
    }

    public void configurationChanged(String email, String password) {
        apiService.configurationChanged(email, password);
    }

}