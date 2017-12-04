package com.face_location.facelocation;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.face_location.facelocation.model.FacelocationAPI;
import com.face_location.facelocation.model.MyProfile.ProfileBody;
import com.face_location.facelocation.model.MyProfile.ProfileResponse;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyProfileActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = "MyProfileActivity";
    public static final int PICK_IMAGE = 1;
    Bitmap avatarImageBitmap;
    Intent getIntent, chooserIntent;

    TextView buttonBackView, addPhotoText;
    EditText editEmail, editName, editSoname, editNumber, editJob;
    ImageView avatar;
    Button saveButton;

    String userEmail, userName, userSoname, userNumber, userJob, userAvatar, url, imgFileName, token;
    Uri returnUri;

    int requestCodeGlobal, resultCodeGlobal;
    Intent dataGlobal;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        url = getResources().getString(R.string.base_url);

        buttonBackView = (TextView) findViewById(R.id.buttonBackView);
        buttonBackView.setOnClickListener(this);

        addPhotoText = (TextView) findViewById(R.id.addPhotoText);
        addPhotoText.setOnClickListener(this);

        editEmail = (EditText) findViewById(R.id.editEmail);

        avatar = (ImageView) findViewById(R.id.avatar);
        avatar.setOnClickListener(this);

        //Extract user profile data from shared preferences
        sharedPref = getSharedPreferences(getString(R.string.APPLICATION_DATA_FILE), Context.MODE_PRIVATE);
        userEmail = sharedPref.getString(getResources()
                .getString(R.string.USER_EMAIL), "No key like " + getResources().getString(R.string.USER_EMAIL));
        userAvatar = sharedPref.getString(getResources()
                .getString(R.string.USER_AVATAR_URL), "No key like " + getString(R.string.USER_EMAIL));
        token = sharedPref.getString(getString(R.string.USER_TOKEN), "No key like " + getString(R.string.USER_TOKEN));


        if (userAvatar.equals("/assets/img/icons/avatar.svg")){
            //just go further
        } else {
            avatar.setBackground(null);
            addPhotoText.setText(getString(R.string.edit_avatar));
            Glide
                    .with(MyProfileActivity.this)
                    .load("https://goo.gl/2q7E7e")
                    .thumbnail(0.1f) //shows mini image which weights 0.1 from real image while real image is downloading
                    .apply(RequestOptions
                            .circleCropTransform())
//                            .placeholder(R.drawable.oval)) //shows drawable while real/mini image is downloading
                    .into(avatar);

            //circled imageView with Glide 3.5.2
//            Glide
//                    .with(MyProfileActivity.this)
//                    .load("https://goo.gl/2q7E7e")
//                    .asBitmap()
//                    .centerCrop()
//                    .into(new BitmapImageViewTarget(avatar) {
//                @Override
//                protected void setResource(Bitmap resource) {
//                    RoundedBitmapDrawable circularBitmapDrawable =
//                            RoundedBitmapDrawableFactory.create(MyProfileActivity.this.getResources(), resource);
//                    circularBitmapDrawable.setCircular(true);
//                    avatar.setImageDrawable(circularBitmapDrawable);
//                    avatar.setBackground(null);
//                }
//            });
        }

        Log.i(TAG, "EMAIL ПОЛЬЗОВАТЕЛЯ: \n" + userEmail);

        editEmail.setText(userEmail);
        editName = (EditText) findViewById(R.id.editName);
        editSoname = (EditText) findViewById(R.id.editSoname);
        editNumber = (EditText) findViewById(R.id.editNumber);
        editJob = (EditText) findViewById(R.id.editJob);

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.buttonBackView:
                onBackPressed();
                break;

            case R.id.addPhotoText:
                getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");
                chooserIntent = Intent.createChooser(getIntent, "Select Image");
                startActivityForResult(chooserIntent, PICK_IMAGE);
                break;

            case R.id.avatar:
                ActivityCompat.requestPermissions(MyProfileActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                break;

            case R.id.saveButton:
                userEmail = editEmail.getText().toString();
                Log.i(TAG, "Сразу после написания: " + userEmail);

                userName = editName.getText().toString();
                Log.i(TAG, "Сразу после написания: " + userName);

                userSoname = editSoname.getText().toString();
                Log.i(TAG, "Сразу после написания: " + userSoname);

                userNumber = editNumber.getText().toString();
                Log.i(TAG, "Сразу после написания: " + userNumber);

                userJob = editJob.getText().toString();
                Log.i(TAG, "Сразу после написания: " + userJob);


                if (requestCodeGlobal == PICK_IMAGE
                        && resultCodeGlobal == RESULT_OK
                        && dataGlobal != null){
                    uploadAvatarOnServer();
                }

                updateMyProfile();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {



        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null){
            returnUri = data.getData();

            Cursor returnCursor =
                    getContentResolver().query(returnUri, null, null, null, null);
            returnCursor.moveToFirst();

            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            imgFileName = returnCursor.getString(nameIndex);
            Log.i(TAG, "ИМЯ ФАЙЛА: \n" + imgFileName);

            //Get Bitmap from gallery's chosen photo
            try {
                avatarImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), returnUri);
                avatar.setBackground(null);
                avatar.setImageBitmap(avatarImageBitmap);
                avatar.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            returnCursor.close();
        }
    }

    private void uploadAvatarOnServer(){

        String realPAth = getRealPathFromURI(MyProfileActivity.this, returnUri);
        Log.i(TAG, "СТАРТ ЗАГРУЗКИ \n");
        File image = new File(realPAth);

        RequestBody filePart = RequestBody.create(MediaType.parse("image/*"), image);
        MultipartBody.Part avatar = MultipartBody.Part.createFormData("file", image.getName(), filePart);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FacelocationAPI api = retrofit.create(FacelocationAPI.class);
        Log.i(TAG, "ТОКЕН: \n" + token);

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "multipart/form-data");
        headers.put("X-Auth", token);

        Call<ResponseBody> call = api.uploadAvatar(headers, avatar);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(MyProfileActivity.this, "Все прошло хорошо", Toast.LENGTH_LONG).show();
                Log.i(TAG, "onResponse: " + response.toString());

                //TODO save user avatar URL to shared pref file
//                String userAvatarURL = response.body().toString();
//
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putString(getString(R.string.USER_AVATAR_URL), userAvatarURL);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MyProfileActivity.this, "ОШИБКА", Toast.LENGTH_LONG).show();
                Log.i(TAG, "onFailure: " + t.toString());

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission granted and now can proceed
                    getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    getIntent.setType("image/*");
                    chooserIntent = Intent.createChooser(getIntent, "Select Image");
                    startActivityForResult(chooserIntent, PICK_IMAGE);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MyProfileActivity.this, "Доступ не надано", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            // add other cases for more permissions
        }
    }

    public static String getRealPathFromURI(Context context, Uri uri){
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = { MediaStore.Images.Media.DATA };

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{ id }, null);

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }

    public void updateMyProfile(){
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    FacelocationAPI api = retrofit.create(FacelocationAPI.class);

        Log.i(TAG, "Перед формирование тела: " +
                userEmail + "\n" +
                userName + "\n" +
                userSoname + "\n" +
                userNumber + "\n" +
                userJob);


    ProfileBody myProfile = new ProfileBody(userEmail, userName, userSoname, userNumber, userJob);


    HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Auth", token);


    Call<ProfileResponse> call = api.updateMyProfile(headers, myProfile);
        call.enqueue(new Callback<ProfileResponse>() {
        @Override
        public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
            Log.i(TAG, "onResponse: " + response.toString());

            if (response.code() == 200) {
                String userEmailResponse = response.body().getEmail();
                String userNameResponse = response.body().getUsername();
                String userSonameResponse = response.body().getLastname();
                String userPhoneResponse = response.body().getPhone();
                String userJobResponse = response.body().getJob();

                Log.i(TAG, "onResponse: \n" +
                        userEmailResponse + "\n" +
                        userNameResponse + "\n" +
                        userSonameResponse + "\n" +
                        userPhoneResponse + "\n" +
                        userJobResponse);

                //Save server response data to shared preferences file
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString(getString(R.string.USER_EMAIL), userEmailResponse);
                editor.putString(getString(R.string.USER_NAME), userNameResponse);
                editor.putString(getString(R.string.USER_SONAME), userSonameResponse);
                editor.putString(getString(R.string.USER_PHONE), userPhoneResponse);
                editor.putString(getString(R.string.USER_JOB), userJobResponse);
                editor.commit();

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
                Log.i(TAG, "onResponse: \n" + response.code());
            }
        }


        @Override
        public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.toString());
            }
        });
    }
}
