package com.balsa.menuapp.Response.Venues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VenuesListResponse {

    @SerializedName("venues")
    @Expose
    List<VenueResponse> venuesList;

    public List<VenueResponse> getVenuesList() {
        return venuesList;
    }

    public void setVenuesList(List<VenueResponse> venuesList) {
        this.venuesList = venuesList;
    }

    @Override
    public String toString() {
        return "VenuesListResponse{" +
                "venuesList=" + venuesList +
                '}';
    }

}
