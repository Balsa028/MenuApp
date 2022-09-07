package com.balsa.menuapp.Venues;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.balsa.menuapp.Adapters.VenuesListAdapter;
import com.balsa.menuapp.R;
import com.balsa.menuapp.Utils.Util;

public class VenuesListFragment extends Fragment {

    private VenuesListViewModel venuesListViewModel;
    private VenuesListAdapter adapter;
    private RecyclerView venuesRecyclerView;

    public static VenuesListFragment newInstance() {
        return new VenuesListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_venues_list, container, false);
        setupVenuesRecView(view);
        observeVenuesListChanges();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //da su ovde dve linije u onCreateView posle popBackStack-a (sa details screen-a) opet bi iznova pozivale showVenues metodu sto meni ovde to ponasanje ne odgovara
        venuesListViewModel = new ViewModelProvider(this).get(VenuesListViewModel.class);
        venuesListViewModel.showVenues();
    }

    private void observeVenuesListChanges() {

        venuesListViewModel.getVenuesList().observe(requireActivity(), venues -> {
            if (venues != null) {
                adapter.setVenues(venues);
                venuesRecyclerView.setAdapter(adapter);
            } else {
                Util.showAlertDialog(this, requireActivity().getResources().getString(R.string.error),
                        requireActivity().getResources().getString(R.string.dialog_error_text),
                        requireActivity().getResources().getString(R.string.ok));
            }
        });

        venuesListViewModel.getIsLoadingVenuesLiveData().observe(this.requireActivity(), isLoading -> {
            try{
                if(isLoading){
                    Util.showProgressDialog(requireActivity(), requireActivity().getResources().getString(R.string.fetching_data));
                } else Util.dismissProgressDialog();
            } catch (IllegalStateException e){
                e.printStackTrace();
            }
        });
    }

    private void setupVenuesRecView(View view) {
        venuesRecyclerView = view.findViewById(R.id.VenuesRecyclerView);
        venuesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        venuesRecyclerView.setHasFixedSize(true);
        venuesRecyclerView.setNestedScrollingEnabled(true);
        adapter = new VenuesListAdapter(this);
    }


}