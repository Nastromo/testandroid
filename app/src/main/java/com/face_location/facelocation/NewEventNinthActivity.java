package com.face_location.facelocation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

public class NewEventNinthActivity extends AppCompatActivity implements View.OnClickListener{

    TimePicker timePickerStart, timePickerEnd;
    TextView buttonBackView, dayCount;
    ImageView eventReady;
    Button addDay, addParagraf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_ninth);

        timePickerStart = (TimePicker) findViewById(R.id.timePickerStart);
        timePickerStart.setIs24HourView(true);
        timePickerStart.setCurrentHour(9);
        timePickerStart.setCurrentMinute(0);

        timePickerEnd = (TimePicker) findViewById(R.id.timePickerEnd);
        timePickerEnd.setIs24HourView(true);
        timePickerEnd.setCurrentHour(10);
        timePickerEnd.setCurrentMinute(0);

        buttonBackView = (TextView) findViewById(R.id.buttonBackView);
        buttonBackView.setOnClickListener(this);

        dayCount = (TextView) findViewById(R.id.dayCount);
//        if (DayCounter.dayCount == 1){
//            return;
//        } else {
//            dayCount.setText(String.valueOf(DayCounter.dayCount));
//        }
        dayCount.setText(String.valueOf(DayCounter.dayCount));

        eventReady = (ImageView) findViewById(R.id.eventReady);
        eventReady.setOnClickListener(this);

        addDay = (Button) findViewById(R.id.addDay);
        addDay.setOnClickListener(this);

        addParagraf = (Button) findViewById(R.id.addParagraf);
        addParagraf.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.buttonBackView:
                Intent newEventSixthActivity = new Intent(this, NewEventSixthActivity.class);
                startActivity(newEventSixthActivity);
                break;

            case R.id.addDay:
                String currentCountString = dayCount.getText().toString();
                int currentCountInteger = Integer.parseInt(currentCountString);
                if (DayCounter.dayCount == currentCountInteger){
                    DayCounter.addDay();
                } else {
                    DayCounter.dayCount = currentCountInteger;
                    DayCounter.addDay();
                }

                Intent newEventNinthActivity = new Intent(this, NewEventNinthActivity.class);
                startActivity(newEventNinthActivity);
                break;

            case R.id.addParagraf:
                DayCounter.dayCount = Integer.parseInt(dayCount.getText().toString());
                Intent newEventEighthActivity = new Intent(this, NewEventEighthActivity.class);
                startActivity(newEventEighthActivity);
                break;
        }
    }
}
