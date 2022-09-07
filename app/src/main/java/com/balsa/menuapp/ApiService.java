package com.balsa.menuapp;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;

import com.balsa.menuapp.Models.Coordinates;
import com.balsa.menuapp.Models.User;
import com.balsa.menuapp.Response.Login.UserResponse;
import com.balsa.menuapp.Response.Venues.VenueResponse;
import com.balsa.menuapp.Response.Venues.VenuesDataResponse;
import com.balsa.menuapp.Utils.Constants;
import com.balsa.menuapp.Utils.RetrofitEndpoint;
import com.balsa.menuapp.Utils.Util;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static ApiService instance;
    RetrofitEndpoint retrofitEndpoint = setupRetrofit();

    //login
    private final MutableLiveData<String> isLoginSuccessfull = new MutableLiveData<>();
    private final MutableLiveData<String> emailLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> passwordLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoadingLogin = new MutableLiveData<>();

    //venues
    private final MutableLiveData<List<VenueResponse>> venuesMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoadingVenues = new MutableLiveData<>();


    //Login logic
    public void performSignIn(String email, String password, Context context) {

        isLoadingLogin.postValue(true);
        User user = new User(email, password);
        Call<UserResponse> userResponseCall = retrofitEndpoint.loginUser(user);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    isLoadingLogin.postValue(false);
                    String token = response.body().getTokenObjectResponse().getTokenValueResponse().getValue();
                    Util.saveTokenInSharedPrefs(token, context);
                    isLoginSuccessfull.postValue(Constants.LOGIN_SUCCESS_KEY);
                } else if (response.code() == 401) {
                    isLoadingLogin.postValue(false);
                    isLoginSuccessfull.postValue(Constants.LOGIN_WRONG_CREDENTIALS_KEY);
                } else {
                    isLoadingLogin.postValue(false);
                    isLoginSuccessfull.postValue(Constants.LOGIN_SOMETHING_WENT_WRONG_KEY);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public MutableLiveData<String> getIsLoginSuccessfull() {
        return isLoginSuccessfull;
    }

    public MutableLiveData<String> getEmailLiveData() {
        return emailLiveData;
    }

    public MutableLiveData<String> getPasswordLiveData() {
        return passwordLiveData;
    }

    public MutableLiveData<Boolean> getIsLoadingLoginLiveData() {
        return isLoadingLogin;
    }

    public MutableLiveData<Boolean> getIsLoadingVenuesLiveData() {
        return isLoadingVenues;
    }

    public void configurationChanged(String email, String password) {
        emailLiveData.postValue(email);
        passwordLiveData.postValue(password);
    }

    //Venues logic
    public void getVenuesList() {

        isLoadingVenues.postValue(true);
        Coordinates coordinates = new Coordinates(Constants.latitude, Constants.longitude);
        Call<VenuesDataResponse> venuesListCall = retrofitEndpoint.getVenuesList(coordinates);
        venuesListCall.enqueue(new Callback<VenuesDataResponse>() {
            @Override
            public void onResponse(Call<VenuesDataResponse> call, Response<VenuesDataResponse> response) {
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    isLoadingVenues.postValue(false);
                    List<VenueResponse> venues = response.body().getVenuesListResponse().getVenuesList();
                    venuesMutableLiveData.postValue(venues);
                } else {
                    isLoadingVenues.postValue(false);
                    venuesMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<VenuesDataResponse> call, Throwable t) {
                venuesMutableLiveData.postValue(null);
                t.printStackTrace();
            }
        });

    }

    public MutableLiveData<List<VenueResponse>> getVenuesMutableLiveData() {
        return venuesMutableLiveData;
    }

    //===================================================

    public static ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    private RetrofitEndpoint setupRetrofit() {
        //logging interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        //initializing Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(RetrofitEndpoint.class);
    }

}
