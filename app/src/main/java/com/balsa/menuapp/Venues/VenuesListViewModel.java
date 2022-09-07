package com.balsa.menuapp.Venues;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.balsa.menuapp.ApiService;
import com.balsa.menuapp.Response.Venues.VenueResponse;

import java.util.List;

public class VenuesListViewModel extends ViewModel {

    private ApiService apiService;

    public VenuesListViewModel() {
        apiService = ApiService.getInstance();
    }

    public MutableLiveData<List<VenueResponse>> getVenuesList() {
        return apiService.getVenuesMutableLiveData();
    }

    public MutableLiveData<Boolean> getIsLoadingVenuesLiveData(){
        return apiService.getIsLoadingVenuesLiveData();
    }

    public void showVenues() {
        apiService.getVenuesList();
    }

}