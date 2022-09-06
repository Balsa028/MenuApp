package com.balsa.menuapp.Adapters;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class VenuesListAdapterTest {

    @Test
    void inputNineHundredMeters(){
        VenuesListAdapter adapter = new VenuesListAdapter();
        String output = adapter.handleDistanceAppearance("900");
        String expected = "900 m";

        assertEquals(expected, output);
    }

    @Test
    void inputFortyEightThousandsMeters(){
        VenuesListAdapter adapter = new VenuesListAdapter();
        String output = adapter.handleDistanceAppearance("48700.7532");
        String expected = "48.7 km";

        assertEquals(expected, output);
    }

    @Test
    void inputNullInsteadOfString(){
        VenuesListAdapter adapter = new VenuesListAdapter();
        String output = adapter.handleDistanceAppearance(null);
        String expected = "Distance unknown";

        assertEquals(expected, output);
    }

}