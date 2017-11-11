package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AddLocationFirstActivity extends AppCompatActivity implements View.OnClickListener {

    TextView backButtonTextView, forwardButtonTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location_first);

        backButtonTextView = (TextView) findViewById(R.id.buttonBackView);
        backButtonTextView.setOnClickListener(this);

        forwardButtonTextView = (TextView) findViewById(R.id.forwardButtonTextView);
        forwardButtonTextView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonBackView:
                onBackPressed();
                break;
            case R.id.forwardButtonTextView:
                Intent secondStep = new Intent(this, AddLocationSecondActivity.class);
                startActivity(secondStep);
                break;
        }
    }
}
