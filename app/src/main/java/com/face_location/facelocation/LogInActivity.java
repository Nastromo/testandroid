package com.face_location.facelocation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

import java.util.*;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    Button cancelLoginButton, successLoginButton;
    EditText userEmail, userPass;
    TextView registrationTextView;
    public static ProgressDialog pDialog;
    String emailUser, passUser;
    TextView enterPassTitle, enterEmailTextView;
    private static String url;
    private static final String LOGIN = "/api/auth/login";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";


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

        enterEmailTextView = (TextView) findViewById(R.id.enterEmailTextView);
        enterPassTitle = (TextView) findViewById(R.id.enterPassTitle);

        url = getResources().getString(R.string.base_url);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.cancelLoginButton:
                onBackPressed();
                break;

            case R.id.successLoginButton:
                //TODO delete this if don't get paid for progressbar
//                pDialog = new ProgressDialog(this);
//                pDialog.setMessage("Завантажую...");
//                pDialog.setTitle("Вхід");
//                pDialog.show();

                emailUser = userEmail.getText().toString().trim();
                passUser = userPass.getText().toString();

                if (TextUtils.isEmpty(emailUser)){
                    showEmailRequires();
                }else {
                    if (TextUtils.isEmpty(passUser)){
                        showPassRequires();
                    }else {
                        userLogin();
                    }
                }

                //TODO implement login logic
                Intent registrationFirstActivity = new Intent (this, MainActivity.class);
                startActivity(registrationFirstActivity);
                break;

            case R.id.registrationTextView:
                Intent registrationTextView = new Intent (this, RegistrationFirstActivity.class);
                startActivity(registrationTextView);
        }
    }

    public void showEmailRequires(){
        enterEmailTextView.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    public void showPassRequires(){
        enterPassTitle.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    private void userLogin(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url + LOGIN, new Response.Listener<String>() {
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
            }
        }){
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put(EMAIL, emailUser);
                params.put(PASSWORD, passUser);
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
