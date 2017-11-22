package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NewEventFifthActivity extends AppCompatActivity implements View.OnClickListener{

    TextView buttonBackView, forwardButtonTextView;
    ImageView cancel;

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
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.buttonBackView:
                onBackPressed();
                break;

            case R.id.forwardButtonTextView:
                Intent newEventSixthActivity = new Intent (this, NewEventSixthActivity.class);
                startActivity(newEventSixthActivity);
                break;

            case  R.id.cancel:
                Intent mainActivity = new Intent (this, MainActivity.class);
                startActivity(mainActivity);
                break;
        }
    }
}
