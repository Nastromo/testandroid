package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NewEventSixthActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonChoseSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_sixth);

        buttonChoseSchedule = (Button) findViewById(R.id.buttonChoseSchedule);
        buttonChoseSchedule.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.buttonChoseSchedule:
                Intent newEventSeventhActivity = new Intent(this, NewEventSeventhActivity.class);
                startActivity(newEventSeventhActivity);
                break;
        }
    }
}
