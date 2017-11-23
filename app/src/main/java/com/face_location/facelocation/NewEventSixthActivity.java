package com.face_location.facelocation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class NewEventSixthActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonChoseSchedule;

    //To check on server sent info
//    TextView textView2;
//    ImageView imageView2;

    String title, type, publicity, about, places, period, startDate, endDate, url;
    TextView buttonBackView, forwardButtonTextView;
    ImageView createEvent;
    private static final String CREATE_EVENT = "/api/events";
    Intent mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_sixth);

        buttonChoseSchedule = (Button) findViewById(R.id.buttonChoseSchedule);
        buttonChoseSchedule.setOnClickListener(this);

        //To check on server sent info
//        imageView2 = (ImageView) findViewById(R.id.imageView2);
//        textView2 = (TextView) findViewById(R.id.textView2);

        buttonBackView = (TextView) findViewById(R.id.buttonBackView);
        buttonBackView.setOnClickListener(this);

        forwardButtonTextView = (TextView) findViewById(R.id.forwardButtonTextView);
        forwardButtonTextView.setOnClickListener(this);

        createEvent = (ImageView) findViewById(R.id.createEvent);
        createEvent.setOnClickListener(this);

        url = getResources().getString(R.string.base_url);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.buttonChoseSchedule:
                Intent newEventSeventhActivity = new Intent(this, NewEventSeventhActivity.class);
                startActivity(newEventSeventhActivity);
                break;

            case R.id.buttonBackView:
                onBackPressed();
                break;

            case R.id.createEvent:
                createEvent();
                mainActivity = new Intent(this, MainActivity.class);
                startActivity(mainActivity);
                break;

            case R.id.forwardButtonTextView:
                createEvent();
                mainActivity = new Intent(this, MainActivity.class);
                startActivity(mainActivity);
                break;
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

    private void createEvent(){
        //Extract data from shared preferences
        SharedPreferences sharedPref = getSharedPreferences(NewEventFirstActivity.FILE_EVENT_DETAILS, Context.MODE_PRIVATE);

        title = sharedPref.getString(NewEventFirstActivity.EVENT_TITLE, "No key like " + NewEventFirstActivity.EVENT_TITLE);
        type = sharedPref.getString(NewEventFirstActivity.EVENT_TYPE, "No key like " + NewEventFirstActivity.EVENT_TYPE);
        about = sharedPref.getString(NewEventSecondActivity.EVENT_ABOUT, "No key like " + NewEventSecondActivity.EVENT_ABOUT);
        publicity = sharedPref.getString(NewEventThirdActivity.EVENT_PUBLICITY, "No key like " + NewEventSecondActivity.EVENT_ABOUT);
        places = sharedPref.getString(NewEventFourthActivity.EVENT_PLACES, "No key like " + NewEventFourthActivity.EVENT_PLACES);
        period = sharedPref.getString(NewEventFourthActivity.EVENT_PERIOD, "No key like " + NewEventFourthActivity.EVENT_PERIOD);
        startDate = sharedPref.getString(NewEventFifthActivity.EVENT_START_DATE, "No key like " + NewEventFifthActivity.EVENT_START_DATE);
        endDate = sharedPref.getString(NewEventFifthActivity.EVENT_END_DATE, "No key like " + NewEventFifthActivity.EVENT_END_DATE);

        //Converts bytes to Bitmap
//        byte[] bytesArray = readBytesFromFile(NewEventThirdActivity.imgFilePath);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(bytesArray, 0, bytesArray.length);

        //Display the Bitmap as an ImageView
//        imageView2.setImageBitmap(decodedByte);
//        imageView2.setVisibility(View.VISIBLE);

        //Test data which has to be sent on server
//        textView2.setText(title + "\n" +
//                type + "\n" +
//                about + "\n" +
//                publicity + "\n" +
//                places + "\n" +
//                period + "\n" +
//                startDate + "\n" +
//                endDate);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url + CREATE_EVENT, new Response.Listener<String>() {
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

                params.put(NewEventFirstActivity.EVENT_TITLE, title);
                params.put(NewEventFirstActivity.EVENT_TYPE, type);
                params.put(NewEventSecondActivity.EVENT_ABOUT, about);
                params.put(NewEventThirdActivity.EVENT_PUBLICITY, publicity);
                params.put(NewEventFourthActivity.EVENT_PLACES, places);
                params.put(NewEventFourthActivity.EVENT_PERIOD, period);
                params.put(NewEventFifthActivity.EVENT_START_DATE, startDate);
                params.put(NewEventFifthActivity.EVENT_END_DATE, endDate);
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
