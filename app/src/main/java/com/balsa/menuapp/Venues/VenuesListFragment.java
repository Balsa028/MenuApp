package com.balsa.menuapp.Venues;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
        venuesListViewModel = new ViewModelProvider(this).get(VenuesListViewModel.class);

        setupVenuesRecView(view);
        venuesListViewModel.showVenues(this);
        observeVenuesListChanges();


        return view;
    }

    private void observeVenuesListChanges() {

        venuesListViewModel.getVenuesList().observe(requireActivity(), venues -> {
            if(venues != null){
                adapter.setVenues(venues);
                venuesRecyclerView.setAdapter(adapter);
            } else{
                new AlertDialog.Builder(getActivity())
                        .setMessage(requireActivity().getResources().getString(R.string.dialog_error_text))
                        .setTitle(requireActivity().getResources().getString(R.string.error))
                        .setPositiveButton(requireActivity().getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
            }
        });
    }

    private void setupVenuesRecView(View view) {
        venuesRecyclerView = view.findViewById(R.id.VenuesRecyclerView);
        venuesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        venuesRecyclerView.setHasFixedSize(true);
        venuesRecyclerView.setNestedScrollingEnabled(true);
        adapter = new VenuesListAdapter(getActivity());
    }


}