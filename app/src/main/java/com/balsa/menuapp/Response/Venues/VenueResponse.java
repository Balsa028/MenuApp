package com.balsa.menuapp.Response.Venues;

import com.balsa.menuapp.Models.Venue;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenueResponse {

    @SerializedName("distance")
    @Expose
    private String distance;

    @SerializedName("venue")
    @Expose
    private Venue venue;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @Override
    public String toString() {
        return "VenueResponse{" +
                "distance='" + distance + '\'' +
                ", venue=" + venue +
                '}';
    }
}
