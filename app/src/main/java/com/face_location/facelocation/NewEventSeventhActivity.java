package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

public class NewEventSeventhActivity extends AppCompatActivity implements View.OnClickListener{

    TimePicker timePickerStart, timePickerEnd;
    Button addParagraf, addDay;
    TextView buttonBackView, dayCount;
    ImageView eventReady;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_seventh);

        timePickerStart = (TimePicker) findViewById(R.id.timePickerStart);
        timePickerStart.setIs24HourView(true);
        timePickerStart.setCurrentHour(9);
        timePickerStart.setCurrentMinute(0);

        timePickerEnd = (TimePicker) findViewById(R.id.timePickerEnd);
        timePickerEnd.setIs24HourView(true);
        timePickerEnd.setCurrentHour(10);
        timePickerEnd.setCurrentMinute(0);

        addParagraf = (Button) findViewById(R.id.addParagraf);
        addParagraf.setOnClickListener(this);

        addDay = (Button) findViewById(R.id.addDay);
        addDay.setOnClickListener(this);

        buttonBackView = (TextView) findViewById(R.id.buttonBackView);
        buttonBackView.setOnClickListener(this);

        eventReady = (ImageView) findViewById(R.id.eventReady);
        eventReady.setOnClickListener(this);

        dayCount = (TextView) findViewById(R.id.dayCount);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.buttonBackView:
                onBackPressed();
                break;

            case R.id.addParagraf:
                DayCounter.dayCount = 1;
                Intent newEventEighthActivity = new Intent(this, NewEventEighthActivity.class);
                startActivity(newEventEighthActivity);
                break;

            case R.id.addDay:
                DayCounter.dayCount = 1;
                DayCounter.addDay();
                Intent тewEventNinthActivity = new Intent(this, NewEventNinthActivity.class);
                startActivity(тewEventNinthActivity);
                break;

            case R.id.eventReady:
                //TODO Send event to server
                Intent mainActivity = new Intent(this, MainActivity.class);
                startActivity(mainActivity);
                break;
        }
    }
}
