package com.face_location.facelocation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AddLocationSecondActivity extends AppCompatActivity implements View.OnClickListener,
        OnMapReadyCallback{
//        GoogleMap.OnMapLongClickListener{

    Button markerNo, markerYes, searchButton;
    private GoogleMap mMap;
    public static final String LOCATION_LATITUDE = "latitude";
    public static final String LOCATION_LONGITUDE = "longitude";

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

        //Read from SharedPreferences file
//        TextView textView2 = (TextView) findViewById(R.id.textView2);
//        SharedPreferences sharedPref = getSharedPreferences(AddLocationFirstActivity.FILE_LOCATION_DETAILS, Context.MODE_PRIVATE);
//        String savedValue = sharedPref.getString(AddLocationFirstActivity.LOCATION_TITLE, "Ничего нету");
//        textView2.setText(savedValue);

        //Converts bytes to Bitmap
//        byte[] bytesArray = readBytesFromFile(AddLocationFirstActivity.imgFilePath);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(bytesArray, 0, bytesArray.length);

        //Display the Bitmap as an ImageView
//        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
//        imageView2.setImageBitmap(decodedByte);
//        imageView2.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.markerNo:
                onBackPressed();
                break;

            case R.id.markerYes:
                // TODO need to add logic for cheking if there are marker coordinates
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
//                markerOptions.draggable(true);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                Marker marker = mMap.addMarker(markerOptions);

                double lattt = toLatLng.latitude;
                double longg = toLatLng.longitude;

                String latttt = String.valueOf(lattt);
                String lngggg = String.valueOf(longg);

                float lat = Float.parseFloat(latttt);
                float lng = Float.parseFloat(lngggg);

                //Save Location LatLong to shared preferences file
                SharedPreferences sharedPref = getSharedPreferences(AddLocationFirstActivity.FILE_LOCATION_DETAILS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putFloat(LOCATION_LATITUDE, lat);
                editor.putFloat(LOCATION_LONGITUDE, lng);
                editor.commit();


            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Toast.makeText(this, "An error!!!", Toast.LENGTH_LONG).show();

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }


    private static byte[] readBytesFromFile(String filePath) {

        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;

        try {

            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];

            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return bytesArray;

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
