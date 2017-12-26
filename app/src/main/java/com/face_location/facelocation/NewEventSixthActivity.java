package com.face_location.facelocation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class NewEventSixthActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonChoseSchedule;

    //To check on server sent info
//    TextView textView2;
//    ImageView imageView2;

    String title, about, startDate, endDate, url, locationID;
    int type, frequency, places;
    boolean isPublic;
    TextView buttonBackView, forwardButtonTextView;
    ImageView createEvent;
    private static final String CREATE_EVENT = "/api/events";
    Intent mainActivity;
    private static final String TAG = "NewEventSixthActivity";

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
        type = sharedPref.getInt(NewEventFirstActivity.EVENT_TYPE, -1);
        about = sharedPref.getString(NewEventSecondActivity.EVENT_ABOUT, "No key like " + NewEventSecondActivity.EVENT_ABOUT);
        isPublic = sharedPref.getBoolean(NewEventThirdActivity.EVENT_PUBLICITY, true);
        places = sharedPref.getInt(NewEventFourthActivity.EVENT_PLACES, -1);
        frequency = sharedPref.getInt(NewEventFourthActivity.EVENT_PERIOD, -1);
        startDate = sharedPref.getString(NewEventFifthActivity.EVENT_START_DATE, "No key like " + NewEventFifthActivity.EVENT_START_DATE);
        endDate = sharedPref.getString(NewEventFifthActivity.EVENT_END_DATE, "No key like " + NewEventFifthActivity.EVENT_END_DATE);
        locationID = sharedPref.getString(NewEventFirstActivity.EVENT_LOCATION_ID, "No key like " + NewEventFirstActivity.EVENT_LOCATION_ID);

        Log.i(TAG, "РЕЗУЛЬТАТ: " + title + "\n" +
                type + "\n" +
                about + "\n" +
                isPublic + "\n" +
                places + "\n" +
                frequency + "\n" +
                startDate + "\n" +
                endDate + "\n" +
                locationID);
    }
}
