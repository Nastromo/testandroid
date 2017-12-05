package com.face_location.facelocation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.face_location.facelocation.model.FacelocationAPI;
import com.face_location.facelocation.model.Registration.RegistrationBody;
import com.face_location.facelocation.model.Registration.RegistrationResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = "LogInActivity";

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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        FacelocationAPI api = retrofit.create(FacelocationAPI.class);

        RegistrationBody reg = new RegistrationBody(emailUser, passUser);
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");

        Call<RegistrationResponse> call = api.loginUser(headerMap, reg);
        call.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                Log.i(TAG, "onResponse: " + response.toString());

                if (response.code() == 200) {
                    String userID = response.body().getUser().getId();
                    String userEmail = response.body().getUser().getEmail();
                    String userAvatarURL = response.body().getUser().getAvatar();
                    String userToken = response.body().getToken();

                    Log.i(TAG, "onResponse: \n" +
                            userID + "\n" +
                            userEmail + "\n" +
                            userAvatarURL + "\n" +
                            userToken);

                    GLOBAL_CONSTANTS.sharedPrefFileName = response.body().getUser().getEmail();

                    //Save server response data to shared preferences file
                    SharedPreferences sharedPref = getSharedPreferences(GLOBAL_CONSTANTS.sharedPrefFileName, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();

                    editor.putString(getString(R.string.USER_ID), userID);
                    editor.putString(getString(R.string.USER_EMAIL), userEmail);
                    editor.putString(getString(R.string.USER_AVATAR_URL), userAvatarURL);
                    editor.putString(getString(R.string.USER_TOKEN), userToken);
                    editor.commit();

                    Intent mainActivity = new Intent(LogInActivity.this, MainActivity.class);
                    startActivity(mainActivity);

                    //Check saved information from SharedPref
//                String userIDExtracted = sharedPref.getString(getString(R.string.USER_ID),
//                        "No key like " + getString(R.string.USER_ID));
//
//                String userEmailExtracted = sharedPref.getString(getString(R.string.USER_EMAIL),
//                        "No key like " + getString(R.string.USER_EMAIL));
//
//                String userAvatarURLExtracted = sharedPref.getString(getString(R.string.USER_AVATAR_URL),
//                        "No key like " + getString(R.string.USER_AVATAR_URL));
//
//                String userTokenExtracted = sharedPref.getString(getString(R.string.USER_TOKEN),
//                        "No key like " + getString(R.string.USER_TOKEN));
//
//                Log.i(TAG, "onResponse: \n" +
//                        userIDExtracted + "\n" +
//                        userEmailExtracted + "\n" +
//                        userAvatarURLExtracted + "\n" +
//                        userTokenExtracted);

                } else {
                    Log.i(TAG, "onResponse: \n" +
                            response.code());
                }
            }


            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.toString());
            }
        });
    }
}
