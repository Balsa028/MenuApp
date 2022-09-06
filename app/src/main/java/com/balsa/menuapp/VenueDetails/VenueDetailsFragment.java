package com.balsa.menuapp.VenueDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balsa.menuapp.Login.LoginFragment;
import com.balsa.menuapp.R;
import com.balsa.menuapp.Utils.Util;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class VenueDetailsFragment extends Fragment {

    private VenueDetailsViewModel venueDetailsViewModel;
    private ImageView thumbnail;
    private MaterialButton btnLogout;
    private TextView name, welcomeMessage, description, isOpen;

    public VenueDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View viewToReturn = inflater.inflate(R.layout.fragment_venue_details, container, false);
        initViews(viewToReturn);

        venueDetailsViewModel = new ViewModelProvider(requireActivity()).get(VenueDetailsViewModel.class);
        displaySelectedVenue();

        btnLogout.setOnClickListener(view -> {
            Util.saveTokenInSharedPrefs("", requireActivity());
            Util.replaceFragment(requireActivity().getSupportFragmentManager(), R.id.fragment_container, LoginFragment.newInstance(), "LoginFragment");
        });

        return viewToReturn;
    }


    private void displaySelectedVenue() {
        venueDetailsViewModel.getVenueMutableLiveData().observe(requireActivity(), venue -> {

            if (venue != null) {
                boolean open = venue.isOpen();

                name.setText(venue.getName());
                welcomeMessage.setText(venue.getWelcomeMessage().equals("") ? "Welcome to " + venue.getName() : venue.getWelcomeMessage());
                description.setText(venue.getDescription().equals("") ? "No description" : venue.getDescription());

                if (open) {
                    isOpen.setText("OPEN");
                    try {
                        isOpen.setBackground(requireActivity().getResources().getDrawable(R.drawable.custom_textview_open));
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                } else isOpen.setText("CURRENTLY CLOSED");

                //ukoliko slika bude null ostavio sam da bude ic_launcher_foreground
                if (getActivity() != null && venue.getImage() != null) {
                    Glide.with(getActivity())
                            .load(venue.getImage().getImageUrl())
                            .fitCenter()
                            .into(thumbnail);
                }
            } else {
                try {
                    Util.showAlertDialog(this, requireActivity().getResources().getString(R.string.error),
                            requireActivity().getResources().getString(R.string.no_data),
                            requireActivity().getResources().getString(R.string.ok));
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void initViews(View view) {
        thumbnail = view.findViewById(R.id.thumbnailImageView);
        btnLogout = view.findViewById(R.id.btnLogout);
        name = view.findViewById(R.id.venueDetailsName);
        welcomeMessage = view.findViewById(R.id.venueDetailsWelcomeMessage);
        description = view.findViewById(R.id.venueDetailsDescription);
        isOpen = view.findViewById(R.id.venueDetailsIsOpen);
    }
}