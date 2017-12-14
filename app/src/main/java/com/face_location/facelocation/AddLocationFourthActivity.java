package com.face_location.facelocation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.face_location.facelocation.model.DataBase.DataBaseHelper;
import com.face_location.facelocation.model.FacelocationAPI;
import com.face_location.facelocation.model.Location.LocationBody;
import com.face_location.facelocation.model.Location.LocationPutResponse;
import com.face_location.facelocation.model.Location.LocationResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddLocationFourthActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    public static final String TAG ="AddLocationFourth";
    TextView buttonBackView, createLocationReady;
    EditText contactsEditText;
    ImageView cancelButton;
    Switch switchDoPublic;
    Intent stepFifth, mainActivity;
    private String url;
    String title, text, contact, token;
    boolean isPublished = false;

    DataBaseHelper applicationDB;
    String locationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location_fourth);

        locationID = getIntent().getStringExtra("id");
        Log.i(TAG, "ID ВЫБРАННОЙ ЛОКАЦИИ: " + locationID);

        applicationDB = DataBaseHelper.getInstance(this);

        buttonBackView = (TextView) findViewById(R.id.buttonBackView);
        buttonBackView.setOnClickListener(this);

        createLocationReady = (TextView) findViewById(R.id.createLocationReady);
        createLocationReady.setOnClickListener(this);

        cancelButton = (ImageView) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

        contactsEditText = (EditText) findViewById(R.id.contactsEditText);

        switchDoPublic = (Switch) findViewById(R.id.switchDoPublic);
        switchDoPublic.setOnCheckedChangeListener(this);

        url = getResources().getString(R.string.base_url);

        if (MyLocationsFragment.isEditable){
            createLocationReady.setText(getString(R.string.save_changes));
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.buttonBackView:
                onBackPressed();
                break;

            case R.id.createLocationReady:
                if (MyLocationsFragment.isEditable && locationID != null){
                    updateLocation(locationID);
                } else {
                    createLocation();
                }


//                //Converts bytes to Bitmap
//                byte[] bytesArray = readBytesFromFile(AddLocationFirstActivity.imgFilePath);
//                Bitmap decodedByte = BitmapFactory.decodeByteArray(bytesArray, 0, bytesArray.length);
//
//                //Display the Bitmap as an ImageView
//                imageView2.setImageBitmap(decodedByte);
//                imageView2.setVisibility(View.VISIBLE);
                break;

            case R.id.cancelButton:
                mainActivity = new Intent(this, MainActivity.class);
                startActivity(mainActivity);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if(isChecked) {
            isPublished = true;
        }
    }

    // delete after tests
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

    private void createLocation(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        contact = contactsEditText.getText().toString();

        //Extract data from shared preferences
        SharedPreferences sharedPref = getSharedPreferences(AddLocationFirstActivity.FILE_LOCATION_DETAILS, Context.MODE_PRIVATE);

        title = sharedPref.getString(AddLocationFirstActivity.LOCATION_TITLE, "No key like " + AddLocationFirstActivity.LOCATION_TITLE);
        float latitude = sharedPref.getFloat(AddLocationSecondActivity.LOCATION_LATITUDE, 0f);
        float longitude = sharedPref.getFloat(AddLocationSecondActivity.LOCATION_LONGITUDE, 0f);
        text = sharedPref.getString(AddLocationThirdActivity.LOCATION_ABOUT, "No key like " + AddLocationThirdActivity.LOCATION_ABOUT);

        String[] userInfo = applicationDB.retrieveFirstLoginValues();
        token = userInfo[5];

        FacelocationAPI api = retrofit.create(FacelocationAPI.class);

        LocationBody location = new LocationBody(title, text, contact, longitude, latitude, isPublished);

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Auth", token);

        Call<LocationResponse> call = api.addLocation(headers, location);
        call.enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {

                if (response.code() == 200) {
                    stepFifth = new Intent(AddLocationFourthActivity.this, NewEventFirstActivity.class);
                    startActivity(stepFifth);
                } else {
                    Log.i(TAG, "onResponse: \n" + response.code());
                }
            }


            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public void updateLocation(String locationID){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        contact = contactsEditText.getText().toString();

        //Extract data from shared preferences
        SharedPreferences sharedPref = getSharedPreferences(AddLocationFirstActivity.FILE_LOCATION_DETAILS, Context.MODE_PRIVATE);

        title = sharedPref.getString(AddLocationFirstActivity.LOCATION_TITLE, "No key like " + AddLocationFirstActivity.LOCATION_TITLE);
        float latitude = sharedPref.getFloat(AddLocationSecondActivity.LOCATION_LATITUDE, 0f);
        float longitude = sharedPref.getFloat(AddLocationSecondActivity.LOCATION_LONGITUDE, 0f);
        text = sharedPref.getString(AddLocationThirdActivity.LOCATION_ABOUT, "No key like " + AddLocationThirdActivity.LOCATION_ABOUT);

        String[] userInfo = applicationDB.retrieveFirstLoginValues();
        token = userInfo[5];

        FacelocationAPI api = retrofit.create(FacelocationAPI.class);

        LocationBody location = new LocationBody(title, text, contact, longitude, latitude, isPublished);

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Auth", token);

        Call<LocationPutResponse> call = api.updateLocation(headers, locationID, location);
        call.enqueue(new Callback<LocationPutResponse>() {
            @Override
            public void onResponse(Call<LocationPutResponse> call, Response<LocationPutResponse> response) {

                if (response.code() == 200) {
                    stepFifth = new Intent(AddLocationFourthActivity.this, NewEventFirstActivity.class);
                    startActivity(stepFifth);
                } else {
                    Log.i(TAG, "onResponse: \n" + response.code());
                }
            }


            @Override
            public void onFailure(Call<LocationPutResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.toString());
            }
        });
    }
}


