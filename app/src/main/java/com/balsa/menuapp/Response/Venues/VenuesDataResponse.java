package com.balsa.menuapp.Response.Venues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenuesDataResponse {

    @SerializedName("data")
    @Expose
    private VenuesListResponse venuesListResponse;

    public VenuesListResponse getVenuesListResponse() {
        return venuesListResponse;
    }

    public void setVenuesListResponse(VenuesListResponse venuesListResponse) {
        this.venuesListResponse = venuesListResponse;
    }

    @Override
    public String toString() {
        return "DataResponse{" +
                "venuesListResponse=" + venuesListResponse +
                '}';
    }
}
