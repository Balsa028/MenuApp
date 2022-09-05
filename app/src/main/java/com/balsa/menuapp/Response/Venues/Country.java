package com.balsa.menuapp.Response.Venues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {

        if(name.equalsIgnoreCase("")){
            return "unknown";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                '}';
    }
}
