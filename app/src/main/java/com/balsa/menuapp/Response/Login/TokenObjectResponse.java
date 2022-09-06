package com.balsa.menuapp.Response.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenObjectResponse {

    @SerializedName("token")
    @Expose
    private TokenValueResponse tokenValueResponse;

    public TokenValueResponse getTokenValueResponse() {
        return tokenValueResponse;
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "token='" + tokenValueResponse + '\'' +
                '}';
    }
}
