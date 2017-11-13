package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationSecondActivity extends AppCompatActivity implements View.OnClickListener{

    Button backLoginButton, signupButton;
    EditText userPassForLogining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_second);

        backLoginButton = (Button) findViewById(R.id.backLoginButton);
        backLoginButton.setOnClickListener(this);

        signupButton = (Button) findViewById(R.id.signupButton);
        signupButton.setOnClickListener(this);

        userPassForLogining = (EditText) findViewById(R.id.userPassForLogining);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backLoginButton:
                onBackPressed();
                break;
            case R.id.signupButton:
                //TODO create account logic
                Intent mainActivity = new Intent(this, MainActivity.class);
                startActivity(mainActivity);
                break;
        }
    }
}
