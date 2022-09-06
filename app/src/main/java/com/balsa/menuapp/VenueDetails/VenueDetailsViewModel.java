package com.balsa.menuapp.VenueDetails;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.balsa.menuapp.Models.Venue;

public class VenueDetailsViewModel extends ViewModel {

    private final MutableLiveData<Venue> venueMutableLiveData = new MutableLiveData<>();

    public void selectVenue(Venue venue){
        venueMutableLiveData.setValue(venue);
    }

    public MutableLiveData<Venue> getVenueMutableLiveData() {
        return venueMutableLiveData;
    }
}
