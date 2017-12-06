package com.face_location.facelocation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity implements View.OnClickListener{

    TextView backTextView;
    Button localizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        backTextView = (TextView) findViewById(R.id.backTextView);
        backTextView.setOnClickListener(this);

        localizButton = (Button) findViewById(R.id.localizButton);
        localizButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backTextView:
                onBackPressed();
                break;

            case R.id.localizButton:
                Intent localizedActivity = new Intent(this, LocalizedActivity.class);
                startActivity(localizedActivity);
                break;
        }
    }
}
