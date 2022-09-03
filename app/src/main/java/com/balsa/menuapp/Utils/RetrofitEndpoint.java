package com.balsa.menuapp.Utils;

import com.balsa.menuapp.Models.User;
import com.balsa.menuapp.Response.Login.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitEndpoint {

    @Headers({"application:mobile-application", "Content-Type:application/json", "Device-UUID:123456", "Api-Version:3.7.0"})
    @POST("customers/login")
    Call<UserResponse> loginUser(@Body User user);

}
