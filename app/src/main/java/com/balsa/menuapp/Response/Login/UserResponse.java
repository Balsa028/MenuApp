package com.balsa.menuapp.Response.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("data")
    @Expose
    private TokenObjectResponse tokenObjectResponse;

    public TokenObjectResponse getTokenObjectResponse(){
        return tokenObjectResponse;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "tokenResponse=" + tokenObjectResponse +
                '}';
    }
}
