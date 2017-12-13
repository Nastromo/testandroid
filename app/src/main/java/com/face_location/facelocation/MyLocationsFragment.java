package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.face_location.facelocation.model.DataBase.DataBaseHelper;
import com.face_location.facelocation.model.FacelocationAPI;
import com.face_location.facelocation.model.Location.LocationGetResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 28.11.17.
 */

public class MyLocationsFragment extends Fragment {

    ListView listView;
    ArrayList<LocationForAdapter> locations;
    List<String> locationsForAdapter;
    View rootView;
    String locationName, url, token;
    String[] applicationData;
    private static final String TAG = "MyLocationsFragment";
    Button createNewBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.my_locations_fragment, container, false);

        url = getString(R.string.base_url);

        DataBaseHelper applicationDB = DataBaseHelper.getInstance(getContext());
        applicationData = applicationDB.retrieveFirstLoginValues();
        token = applicationData[5];

        createNewBtn = (Button) rootView.findViewById(R.id.createNewBtn);
        createNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addLocationFirstActivity = new Intent(getContext(), AddLocationFirstActivity.class);
                startActivity(addLocationFirstActivity);
            }
        });

        getMyLocations();

        return rootView;
    }

    public void getMyLocations() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        FacelocationAPI api = retrofit.create(FacelocationAPI.class);

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Auth", token);

        String userID = applicationData[0];

        Call<List<LocationGetResponse>> call = api.getMyLocations(headers, userID);
        call.enqueue(new Callback<List<LocationGetResponse>>() {
            @Override
            public void onResponse(Call<List<LocationGetResponse>> call, Response<List<LocationGetResponse>> response) {

                Log.i(TAG, "ОТВЕТ СЕРВЕРА: " + response.toString());

                locations = new ArrayList<>();
                List<LocationGetResponse> mylocations = response.body();
                for (LocationGetResponse location: mylocations){
                    locationName = location.getTitle();
                    Log.i(TAG, "НАЗВАНИЕ ЛОКАЦИИ: " + locationName);
                    locations.add(new LocationForAdapter(locationName));
                }

                listView = (ListView) rootView.findViewById(R.id.locationListView);
                MyLocationsListAdapter myLocationsFragment = new MyLocationsListAdapter(getContext(), R.layout.my_location_card, locations);
                listView.setAdapter(myLocationsFragment);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //TODO редактирование данных локации

                        Intent addLocationFirstActivity = new Intent(getContext(), AddLocationFirstActivity.class);
                        startActivity(addLocationFirstActivity);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<LocationGetResponse>> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.toString());
            }
        });
    }
}
