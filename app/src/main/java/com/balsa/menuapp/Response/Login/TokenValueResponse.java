package com.balsa.menuapp.Response.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenValueResponse {

    @SerializedName("value")
    @Expose
    private String value;

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "value='" + value + '\'' +
                '}';
    }
}
