package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationFirstActivity extends AppCompatActivity implements View.OnClickListener{

    Button backLoginButton, nextLoginButton;
    EditText userEmailForLogining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_first);

        backLoginButton = (Button) findViewById(R.id.backLoginButton);
        backLoginButton.setOnClickListener(this);

        nextLoginButton = (Button) findViewById(R.id.nextLoginButton);
        nextLoginButton.setOnClickListener(this);

        userEmailForLogining = (EditText) findViewById(R.id.userEmailForLogining);
        userEmailForLogining.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLoginButton:
                onBackPressed();
                break;
            case R.id.nextLoginButton:
                //TODO check user email for uniq if OK save and go further
                Intent registrationSecondActivity = new Intent(this, RegistrationSecondActivity.class);
                startActivity(registrationSecondActivity);
        }
    }
}
