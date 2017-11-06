package com.face_location.facelocation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.skyfishjy.library.RippleBackground;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    RippleBackground rippleBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rippleBackground = (RippleBackground)findViewById(R.id.ripple);
        rippleBackground.startRippleAnimation();
    }

    //Custom fonts any (assets/fonts/*)
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void stopRipple(View view) {
        rippleBackground.stopRippleAnimation();
    }

    public void startRipple(View view) {
        rippleBackground.startRippleAnimation();
    }

    public void showTos(View view){
        Intent intent = new Intent(this, TermsOfUse.class);
        startActivity(intent);
    }

    public void showMap(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
