package com.face_location.facelocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

public class NewEventSeventhActivity extends AppCompatActivity implements View.OnClickListener{

    TimePicker timePickerStart, timePickerEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_seventh);

        timePickerStart = (TimePicker) findViewById(R.id.timePickerStart);
        timePickerStart.setIs24HourView(true);

        timePickerEnd = (TimePicker) findViewById(R.id.timePickerEnd);
        timePickerEnd.setIs24HourView(true);
    }

    @Override
    public void onClick(View view) {

    }
}
