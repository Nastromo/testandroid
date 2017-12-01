package com.face_location.facelocation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.skyfishjy.library.RippleBackground;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = "StartActivity";
    RippleBackground rippleBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        rippleBackground = (RippleBackground)findViewById(R.id.ripple);
        rippleBackground.startRippleAnimation();

        Button localize = (Button) findViewById(R.id.localize);
        localize.setOnClickListener(this);

        TextView tos = (TextView) findViewById(R.id.tos);
        tos.setOnClickListener(this);
    }

    //Custom fonts any (assets/fonts/*)
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.localize:
                SharedPreferences sharedPref =
                        getSharedPreferences(getString(R.string.APPLICATION_DATA_FILE),
                                Context.MODE_PRIVATE);

                Boolean isFirstLogined = sharedPref.getBoolean(getString(R.string.FIRST_LOGIN), false);
                Log.i(TAG, "onClick: " + isFirstLogined.toString());

                if (isFirstLogined){
                    Intent mainActivity = new Intent(this, MainActivity.class);
                    startActivity(mainActivity);
                } else {
                    Intent logInActivity = new Intent(this, LogInActivity.class);
                    startActivity(logInActivity);
                }
                break;

            case R.id.tos:
                Intent tosActivity = new Intent(this, TermsOfUse.class);
                startActivityForResult(tosActivity, 1);
                break;
        }
    }

    //Preparation for a future work depends on tos choice
    //if needed
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
}
