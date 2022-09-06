package com.balsa.menuapp.Models;

import com.balsa.menuapp.Response.Venues.Country;
import com.balsa.menuapp.Response.Venues.Image;
import com.balsa.menuapp.Response.Venues.ServingTimes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Venue {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("welcome_message")
    @Expose
    private String welcomeMessage;

    @SerializedName("is_open")
    @Expose
    private boolean isOpen;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("image")
    @Expose
    private Image image;

    @SerializedName("country")
    @Expose
    private Country country;

    @SerializedName("serving_times")
    @Expose
    private List<ServingTimes> servingTimesList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getAddress() {

        if (address.equalsIgnoreCase("")) {
            return "unknown";
        }
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {

        if (city.equalsIgnoreCase("")) {
            return "unknown";
        }
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<ServingTimes> getServingTimesList() {
        return servingTimesList;
    }

    public void setServingTimesList(List<ServingTimes> servingTimesList) {
        this.servingTimesList = servingTimesList;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", welcomeMessage='" + welcomeMessage + '\'' +
                ", isOpen=" + isOpen +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", image=" + image +
                ", country=" + country +
                ", servingTimesList=" + servingTimesList +
                '}';
    }
}
