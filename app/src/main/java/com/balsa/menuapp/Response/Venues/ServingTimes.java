package com.balsa.menuapp.Response.Venues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServingTimes {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("time_from")
    @Expose
    private String timeFrom;

    @SerializedName("time_to")
    @Expose
    private String timeTo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    @Override
    public String toString() {
        return "ServingTimes{" +
                "id='" + id + '\'' +
                ", timeFrom='" + timeFrom + '\'' +
                ", timeTo='" + timeTo + '\'' +
                '}';
    }
}
