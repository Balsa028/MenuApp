package com.balsa.menuapp.Utils;

import com.balsa.menuapp.Models.Coordinates;
import com.balsa.menuapp.Models.User;
import com.balsa.menuapp.Response.Login.UserResponse;
import com.balsa.menuapp.Response.Venues.VenueResponse;
import com.balsa.menuapp.Response.Venues.VenuesDataResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitEndpoint {

    @Headers({"application:mobile-application", "Content-Type:application/json", "Device-UUID:123456", "Api-Version:3.7.0"})
    @POST("customers/login")
    Call<UserResponse> loginUser(@Body User user);

    @Headers({"application:mobile-application", "Content-Type:application/json", "Device-UUID:123456", "Api-Version:3.7.0"})
    @POST("directory/search")
    Call<VenuesDataResponse> getVenuesList(@Body Coordinates coordinates);

}
