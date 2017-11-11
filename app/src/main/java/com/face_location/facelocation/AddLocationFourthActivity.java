package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class AddLocationFourthActivity extends AppCompatActivity implements View.OnClickListener{

    TextView buttonBackView, createLocationReady;
    EditText contactsEditText;
    ImageView cancelButton;
    Switch switchDoPublic;
    Intent mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location_fourth);

        buttonBackView = (TextView) findViewById(R.id.buttonBackView);
        buttonBackView.setOnClickListener(this);

        createLocationReady = (TextView) findViewById(R.id.createLocationReady);
        createLocationReady.setOnClickListener(this);

        cancelButton = (ImageView) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

        contactsEditText = (EditText) findViewById(R.id.contactsEditText);
        switchDoPublic = (Switch) findViewById(R.id.switchDoPublic);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.buttonBackView:
                onBackPressed();
                break;
            case R.id.createLocationReady:
                //TODO push JSON to server | switcher and EditText field grab info
                mainActivity = new Intent(this, MainActivity.class);
                startActivity(mainActivity);
                break;
            case R.id.cancelButton:
                mainActivity = new Intent(this, MainActivity.class);
                startActivity(mainActivity);
                break;
        }
    }
}
