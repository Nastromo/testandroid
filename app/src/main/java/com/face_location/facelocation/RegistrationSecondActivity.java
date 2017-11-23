package com.face_location.facelocation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistrationSecondActivity extends AppCompatActivity implements View.OnClickListener{

    Button backLoginButton, signupButton;
    EditText userPassForLogining;
    String userPass;
    TextView enterPassTextView;

    private static String url;
    private static final String REG = "/api/auth/register";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String TAG = "Registration";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_second);

        backLoginButton = (Button) findViewById(R.id.backLoginButton);
        backLoginButton.setOnClickListener(this);

        signupButton = (Button) findViewById(R.id.signupButton);
        signupButton.setOnClickListener(this);

        userPassForLogining = (EditText) findViewById(R.id.userPassForLogining);

        enterPassTextView = (TextView) findViewById(R.id.enterPassTextView);

        url = getResources().getString(R.string.base_url);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backLoginButton:
                onBackPressed();
                break;

            case R.id.signupButton:
                getUserPass();
                if(TextUtils.isEmpty(userPass)){
                    showPassRequires();
                    break;
                } else {
                    userRegistration();
                }
                break;
        }
    }

    public void showPassRequires(){
        enterPassTextView.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    private String getUserPass(){
        return userPass = userPassForLogining.getText().toString();
    }

    private void userRegistration(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url + REG, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Все прошло хорошо!", Toast.LENGTH_SHORT).show();
//                if (200 OK)
//                Intent mainActivity = new Intent(this, MainActivity.class);
//                startActivity(mainActivity);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                String errorDisplay = error.toString();
                Log.d(TAG, "Ошибка: " + errorDisplay);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
//                params.put(EMAIL, RegistrationFirstActivity.userEmail);
//                params.put(PASSWORD, userPass);
                params.put("email", "testemail@gmail.com");
                params.put("password", "qwerty12");
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
