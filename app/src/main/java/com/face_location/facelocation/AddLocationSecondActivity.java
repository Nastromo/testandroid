package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class AddLocationSecondActivity extends AppCompatActivity implements View.OnClickListener,
        OnMapReadyCallback{
//        GoogleMap.OnMapLongClickListener{

    Button markerNo, markerYes, searchButton;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location_second);

        markerNo = (Button) findViewById(R.id.markerNo);
        markerNo.setOnClickListener(this);

        markerYes = (Button) findViewById(R.id.markerYes);
        markerYes.setOnClickListener(this);

        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.markerNo:
                onBackPressed();
                break;

            case R.id.markerYes:
                // TODO need to add logic for cheking if there is a marker coordinates
                Intent thirdStep = new Intent(this, AddLocationThirdActivity.class);
                startActivity(thirdStep);
                break;

            case R.id.searchButton:
                //Getting autocomplete overlay for geo search
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN) //TODO remove this comment before Release: don't change MODE_FULLSCREEN on MODE_OVERLAY
                                    .build(this);
                    startActivityForResult(intent, MainActivity.PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.setOnMapLongClickListener(this);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MainActivity.mDefaultLocation, MainActivity.DEFAULT_ZOOM));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainActivity.PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);

                LatLng toLatLng = place.getLatLng();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(toLatLng, MainActivity.DEFAULT_ZOOM));

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(toLatLng);
                markerOptions.draggable(true);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));

                Marker draggableMarker = mMap.addMarker(markerOptions);
                LatLng draggableMarkerPosition = draggableMarker.getPosition();
//                Toast.makeText(this, "Lat " + draggableMarkerPosition.latitude + " " + "Long " + draggableMarkerPosition.longitude, Toast.LENGTH_LONG).show();

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Toast.makeText(this, "An error!!!", Toast.LENGTH_LONG).show();

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }


    //Adding the marker with long press
//    @Override
//    public void onMapLongClick(LatLng point) {
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(point);
//        markerOptions.draggable(true);
//        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
//
//        Marker draggableMarker = mMap.addMarker(markerOptions);
//
//        Toast.makeText(this, point.latitude+" "+point.longitude, Toast.LENGTH_SHORT).show();
//
//    }
}
