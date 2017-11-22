package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class NewEventSecondActivity extends AppCompatActivity implements View.OnClickListener{

    TextView btnBackView, forwardButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_second);

        btnBackView = (TextView) findViewById(R.id.btnBackView);
        btnBackView.setOnClickListener(this);

        forwardButtonView = (TextView) findViewById(R.id.forwardButtonView);
        forwardButtonView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnBackView:
                onBackPressed();
                break;

            case R.id.forwardButtonView:
                Intent newEventThirdActivity = new Intent (this, NewEventThirdActivity.class);
                startActivity(newEventThirdActivity);
                break;
        }
    }
}
