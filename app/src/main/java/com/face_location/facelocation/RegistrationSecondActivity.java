package com.face_location.facelocation;

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

public class RegistrationSecondActivity extends AppCompatActivity implements View.OnClickListener{

    Button backLoginButton, signupButton;
    EditText userPassForLogining;
    String userPass;
    TextView enterPassTextView;

    private static final String TAG = "Registration";
    private String url;



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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        FacelocationAPI api = retrofit.create(FacelocationAPI.class);
        final RegistrationBody reg = new RegistrationBody(RegistrationFirstActivity.userEmail, userPass);


        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");

        Call<RegistrationResponse> call = api.registerUser(headerMap, reg);
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

                    Intent mainActivity = new Intent(RegistrationSecondActivity.this, MainActivity.class);
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
