package com.balsa.menuapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.balsa.menuapp.R;
import com.balsa.menuapp.Response.Venues.ServingTimes;
import com.balsa.menuapp.Response.Venues.VenueResponse;
import com.balsa.menuapp.VenueDetails.VenueDetailsFragment;
import com.balsa.menuapp.VenueDetails.VenueDetailsViewModel;
import com.balsa.menuapp.Venues.VenuesListFragment;
import java.util.ArrayList;
import java.util.List;

public class VenuesListAdapter extends RecyclerView.Adapter<VenuesListAdapter.VenueViewHolder> {

    private List<VenueResponse> venues;
    private VenuesListFragment fragment;
    private VenueDetailsViewModel venueDetailsViewModel;

    public VenuesListAdapter(VenuesListFragment fragment){
        venues = new ArrayList<>();
        this.fragment = fragment;
        this.venueDetailsViewModel = new ViewModelProvider(fragment.requireActivity()).get(VenueDetailsViewModel.class);
    }

    @NonNull
    @Override
    public VenueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.venues_list_item, parent, false);
        return new VenueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VenueViewHolder holder, int position) {

        holder.venueName.setText(venues.get(position).getVenue().getName());
        holder.venueAddressStateCity.setText(venues.get(position).getVenue().getAddress() + ", "
                + venues.get(position).getVenue().getCity() + ", "
                + venues.get(position).getVenue().getCountry().getName());

        handleDistanceAppearance(holder, position);
        handleServingTimeAppearance(holder, position);

        //click listener za item u rec view-u
        holder.parent.setOnClickListener(view -> {

            fragment.requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("VenueDetailsFragment")
                    .replace(R.id.fragment_container, new VenueDetailsFragment(), "VenueDetailsFragment")
                    .commit();

            venueDetailsViewModel.selectVenue(venues.get(position).getVenue());
        });

    }

    private void handleDistanceAppearance(VenueViewHolder holder, int position) {
        float distance = Float.parseFloat(venues.get(position).getDistance());
        if(distance < 1000){
            holder.venueDistance.setText((int)distance +" m");
        } else {
            //prikaz u kilometrima u ostalim slucajevima
            distance /= 1000;
            holder.venueDistance.setText(String.format("%.01f", distance) + " km");
        }
    }

    private void handleServingTimeAppearance(VenueViewHolder holder, int position) {
        boolean isOpen = venues.get(position).getVenue().isOpen();
        List<ServingTimes> servingTimesList = venues.get(position).getVenue().getServingTimesList();
        String openingTime = "";
        String closingTime = "";

        if(!servingTimesList.isEmpty()){
            openingTime = servingTimesList.get(0).getTimeFrom();
            closingTime = servingTimesList.get(0).getTimeTo();
        }

        if(isOpen){
            //ukoliko je prazna lista prikazuje se "Temporarily unavailable for digital orders" i disejblovan je klik
            if(servingTimesList.isEmpty()){
                setVenueDisabled(holder, fragment.requireActivity().getResources().getString(R.string.temporarily_unavailable_for_digital_orders));
            } else{
                setVenueEnabled(holder);

                if(openingTime == null || closingTime == null){ //desava se
                    holder.venueServingTime.setText(fragment.requireActivity().getResources().getString(R.string.unknown));
                } else if(openingTime.equalsIgnoreCase("00:00") && closingTime.equalsIgnoreCase("00:00")){
                    holder.venueServingTime.setText(fragment.requireActivity().getResources().getString(R.string.today_open_24));
                } else holder.venueServingTime.setText(fragment.requireActivity().getResources().getString(R.string.today) +" "+ openingTime + " - " +closingTime);

            }
        } else {
            setVenueDisabled(holder, fragment.requireActivity().getResources().getString(R.string.closed));
        }
    }

    private void setVenueEnabled(VenueViewHolder holder) {
        holder.venueName.setAlpha(1);
        holder.venueDistance.setAlpha(1);
    }

    private void setVenueDisabled(VenueViewHolder holder, String message) {
        holder.venueServingTime.setText(message);

        holder.venueName.setAlpha((float) 0.6);
        holder.venueDistance.setAlpha((float) 0.6);
    }


    @Override
    public int getItemCount() {

        if(venues == null){
            return 0;
        }
       return venues.size();
    }

    public void setVenues(List<VenueResponse> venues) {
        this.venues = venues;
        notifyDataSetChanged();
    }

    public class VenueViewHolder extends RecyclerView.ViewHolder {

        private TextView venueName, venueDistance, venueAddressStateCity, venueServingTime;
        private LinearLayout parent;

        public VenueViewHolder(@NonNull View itemView) {
            super(itemView);
            venueName = itemView.findViewById(R.id.venueName);
            venueDistance = itemView.findViewById(R.id.venueDistance);
            venueAddressStateCity = itemView.findViewById(R.id.venueAdressStateCity);
            venueServingTime = itemView.findViewById(R.id.venueServingTime);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
