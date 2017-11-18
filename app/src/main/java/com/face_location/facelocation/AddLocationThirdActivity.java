package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AddLocationThirdActivity extends AppCompatActivity implements View.OnClickListener {

    TextView backButtonView, forwardButtonView;
    ImageView cancel;
    EditText somethingAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location_third);

        backButtonView = (TextView) findViewById(R.id.buttonBackView);
        backButtonView.setOnClickListener(this);

        cancel = (ImageView) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);

        forwardButtonView = (TextView) findViewById(R.id.forwardButtonView);
        forwardButtonView.setOnClickListener(this);

        somethingAbout = (EditText) findViewById(R.id.somethingAbout);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonBackView:
                onBackPressed();
                break;
            case R.id.forwardButtonView:
                Intent fourthStep = new Intent(this, AddLocationFourthActivity.class);
                startActivity(fourthStep);
                break;
            case R.id.cancel:
                Intent mainActivity = new Intent(this, MainActivity.class);
                startActivity(mainActivity);
                break;
        }
    }
}
