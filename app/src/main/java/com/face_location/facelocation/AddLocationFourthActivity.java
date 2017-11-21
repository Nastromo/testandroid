package com.face_location.facelocation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class AddLocationFourthActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    TextView buttonBackView, createLocationReady;
    EditText contactsEditText;
    ImageView cancelButton;
    Switch switchDoPublic;
    Intent stepFifth, mainActivity;
    public static final String LOCATION_CONTACT = "contact";
    public static final String LOCATION_ISPUBLIC = "published";
    private static String url;
    private static final String CREATE_LOCATION = "/api/locations";
    String title, latitude, longitude, about, contact;
    boolean isPublic = false;

    //Delete after tests
    TextView textView2;
    ImageView imageView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location_fourth);

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

        //Delete after tests
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        textView2 = (TextView) findViewById(R.id.textView2);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.buttonBackView:
                onBackPressed();
                break;

            case R.id.createLocationReady:
                contact = contactsEditText.getText().toString();

                //Save Location contact and isPublic to shared preferences file
//                SharedPreferences sharedPref = getSharedPreferences(AddLocationFirstActivity.FILE_LOCATION_DETAILS, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putString(LOCATION_CONTACT, contact);
//                editor.putString(LOCATION_ISPUBLIC, isPublicString);
//                editor.commit();

                //Extract data from shared preferences
                SharedPreferences sharedPref = getSharedPreferences(AddLocationFirstActivity.FILE_LOCATION_DETAILS, Context.MODE_PRIVATE);

                title = sharedPref.getString(AddLocationFirstActivity.LOCATION_TITLE, "No key like " + AddLocationFirstActivity.LOCATION_TITLE);
                latitude = sharedPref.getString(AddLocationSecondActivity.LOCATION_LATITUDE, "No key like " + AddLocationSecondActivity.LOCATION_LATITUDE);
                longitude = sharedPref.getString(AddLocationSecondActivity.LOCATION_LONGITUDE, "No key like " + AddLocationSecondActivity.LOCATION_LONGITUDE);
                about = sharedPref.getString(AddLocationThirdActivity.LOCATION_ABOUT, "No key like " + AddLocationThirdActivity.LOCATION_ABOUT);

                createLocation();

                //Test data which has to be sent on server
                textView2.setText(title + "\n" +
                        latitude + "\n" +
                        longitude + "\n" +
                        about + "\n" +
                        contact + "\n" +
                        String.valueOf(isPublic));

                //Converts bytes to Bitmap
                byte[] bytesArray = readBytesFromFile(AddLocationFirstActivity.imgFilePath);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(bytesArray, 0, bytesArray.length);

                //Display the Bitmap as an ImageView
                imageView2.setImageBitmap(decodedByte);
                imageView2.setVisibility(View.VISIBLE);

//                stepFifth = new Intent(this, AddLocationFifthActivity.class);
//                startActivity(stepFifth);
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
            isPublic = true;
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url + CREATE_LOCATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Все прошло хорошо!", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put(AddLocationFirstActivity.LOCATION_TITLE, title);
                params.put(AddLocationSecondActivity.LOCATION_LATITUDE, latitude);
                params.put(AddLocationSecondActivity.LOCATION_LONGITUDE, longitude);
                params.put(AddLocationThirdActivity.LOCATION_ABOUT, about);
                params.put(LOCATION_CONTACT, contact);
                params.put(LOCATION_ISPUBLIC, String.valueOf(isPublic));
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}


