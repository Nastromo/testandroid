package com.face_location.facelocation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class NewEventFifthActivity extends AppCompatActivity implements View.OnClickListener, CalendarView.OnDateChangeListener{

    TextView buttonBackView, forwardButtonTextView;
    ImageView cancel;
    CalendarView calendarStart, calendarEnd;
    public static final String EVENT_START_DATE = "start";
    public static final String EVENT_END_DATE = "end";
    public static final String TAG = "newEvent";
    String startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_fifth);

        buttonBackView = (TextView) findViewById(R.id.buttonBackView);
        buttonBackView.setOnClickListener(this);

        forwardButtonTextView = (TextView) findViewById(R.id.forwardButtonTextView);
        forwardButtonTextView.setOnClickListener(this);

        cancel = (ImageView) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);

        Calendar c = Calendar.getInstance();

        calendarStart = (CalendarView) findViewById(R.id.calendarStart);
        long startDateLong = calendarStart.getDate();


        c.setTimeInMillis(startDateLong);
        int startYear = c.get(Calendar.YEAR);
        int startMonth = c.get(Calendar.MONTH);
        int startDay = c.get(Calendar.DAY_OF_MONTH);
        Log.i(TAG, "ДАТА: " + startYear + " " + startMonth + " " + startDay);
        startDate = createStartDate(startYear, startMonth, startDay);
        calendarStart.setOnDateChangeListener(this);

        calendarEnd = (CalendarView) findViewById(R.id.calendarEnd);
        int endYear = c.get(Calendar.YEAR);
        int endMonth = c.get(Calendar.MONTH);
        int endDay = c.get(Calendar.DAY_OF_MONTH);
        Log.i(TAG, "ДАТА: " + endYear + " " + endMonth + " " + endDay);
        endDate = createEndDate(endYear, endMonth, endDay);
        calendarEnd.setOnDateChangeListener(this);
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
        switch (calendarView.getId()){
            case R.id.calendarStart:
                StringBuilder stringBuilderStart = new StringBuilder();
                stringBuilderStart.append(year);
                stringBuilderStart.append("-");
                stringBuilderStart.append(month);
                stringBuilderStart.append("-");
                stringBuilderStart.append(day);
                stringBuilderStart.append("T");
                stringBuilderStart.append("00:00:00.000Z");
                startDate = stringBuilderStart.toString();
                Log.i(TAG,"Дата старта: " + startDate);
                break;
            case R.id.calendarEnd:
                StringBuilder stringBuilderEnd = new StringBuilder();
                stringBuilderEnd.append(year);
                stringBuilderEnd.append("-");
                stringBuilderEnd.append(month);
                stringBuilderEnd.append("-");
                stringBuilderEnd.append(day);
                stringBuilderEnd.append("T");
                stringBuilderEnd.append("00:00:00.000Z");
                endDate = stringBuilderEnd.toString();
                Log.i(TAG,"Дата окончания: " + endDate);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.buttonBackView:
                onBackPressed();
                break;

            case R.id.forwardButtonTextView:
                //Save Event start/end date to shared preferences file
                SharedPreferences sharedPref = getSharedPreferences(NewEventFirstActivity.FILE_EVENT_DETAILS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(EVENT_START_DATE, startDate);
                editor.putString(EVENT_END_DATE, endDate);
                editor.commit();

                Intent newEventSixthActivity = new Intent (this, NewEventSixthActivity.class);
                startActivity(newEventSixthActivity);
                break;

            case  R.id.cancel:
                Intent mainActivity = new Intent (this, MainActivity.class);
                startActivity(mainActivity);
                break;
        }
    }

    private static String createStartDate(int year, int month, int day){

        StringBuilder stringBuilderStart = new StringBuilder();
        stringBuilderStart.append(year);
        stringBuilderStart.append("-");
        stringBuilderStart.append(month);
        stringBuilderStart.append("-");
        stringBuilderStart.append(day);
        stringBuilderStart.append("T");
        stringBuilderStart.append("00:00:00.000Z");
        String startDate = stringBuilderStart.toString();
        Log.i(TAG,"Дата старта: " + startDate);
        return startDate;
    }

    private static String createEndDate(int year, int month, int day){

        StringBuilder stringBuilderEnd = new StringBuilder();
        stringBuilderEnd.append(year);
        stringBuilderEnd.append("-");
        stringBuilderEnd.append(month);
        stringBuilderEnd.append("-");
        stringBuilderEnd.append(day);
        stringBuilderEnd.append("T");
        stringBuilderEnd.append("00:00:00.000Z");
        String endDate = stringBuilderEnd.toString();
        Log.i(TAG,"Дата окончания: " + endDate);
        return endDate;
    }
}
