package com.face_location.facelocation;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by admin on 28.11.17.
 */

public class MyLocationsFragment extends Fragment {

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_locations_fragment, container, false);

        ArrayList<Location> locations = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            locations.add(new Location("Первое событие с длинным заголовком" + i));
        }


        listView = (ListView) rootView.findViewById(R.id.locationListView);
        MyLocationsListAdapter myLocationsFragment = new MyLocationsListAdapter(getContext(), R.layout.my_location_card, locations);
        listView.setAdapter(myLocationsFragment);

        return rootView;
    }
}
