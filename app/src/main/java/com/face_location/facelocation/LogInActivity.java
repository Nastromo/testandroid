package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    Button cancelLoginButton, successLoginButton;
    EditText userEmail, userPass;
    TextView registrationTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        cancelLoginButton = (Button) findViewById(R.id.cancelLoginButton);
        cancelLoginButton.setOnClickListener(this);

        successLoginButton = (Button) findViewById(R.id.successLoginButton);
        successLoginButton.setOnClickListener(this);

        userEmail = (EditText) findViewById(R.id.userEmail);
        userPass = (EditText) findViewById(R.id.userPass);

        registrationTextView = (TextView) findViewById(R.id.registrationTextView);
        registrationTextView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.cancelLoginButton:
                onBackPressed();
                break;
            case R.id.successLoginButton:
                //TODO implement login logic
                Intent registrationFirstActivity = new Intent (this, MainActivity.class);
                startActivity(registrationFirstActivity);
                break;
            case R.id.registrationTextView:
                Intent registrationTextView = new Intent (this, RegistrationFirstActivity.class);
                startActivity(registrationTextView);
        }
    }
}
