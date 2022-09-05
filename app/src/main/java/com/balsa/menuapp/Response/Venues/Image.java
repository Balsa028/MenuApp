package com.balsa.menuapp.Response.Venues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("thumbnail_medium")
    @Expose
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
