package com.face_location.facelocation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;

public class MyProfileActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int PICK_IMAGE = 1;
    Bitmap eventImageBitmap;
    Intent getIntent, chooserIntent;

    TextView buttonBackView, addPhotoText;
    EditText editEmail, editName, editSoname, editNumber, editJob;
    ImageView avatar;
    Button saveButton;

    String userEmail, userName, userSoname, userNumber, userJob, userAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        buttonBackView = (TextView) findViewById(R.id.buttonBackView);
        buttonBackView.setOnClickListener(this);

        addPhotoText = (TextView) findViewById(R.id.addPhotoText);
        addPhotoText.setOnClickListener(this);

        editEmail = (EditText) findViewById(R.id.editEmail);
//        editEmail.setOnClickListener(this);

        avatar = (ImageView) findViewById(R.id.avatar);
        avatar.setOnClickListener(this);

        //Extract user profile data from shared preferences
        SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.APPLICATION_DATA_FILE), Context.MODE_PRIVATE);
        userEmail = sharedPref.getString(getResources()
                .getString(R.string.user_email), "No key like " + getResources().getString(R.string.user_email));

        userAvatar = sharedPref.getString(getResources()
                .getString(R.string.user_avatar_url), "No key like " + getResources().getString(R.string.user_email));

//        userAvatar = "/assets/img/icons/avatar.svg";

        if (userAvatar.equals("/assets/img/icons/avatar.svg")){
            return;
        } else {
            avatar.setBackground(null);
            addPhotoText.setText(getString(R.string.edit_avatar));
            Glide
                    .with(MyProfileActivity.this)
                    .load("https://goo.gl/2q7E7e")
                    .thumbnail(0.1f) //shows mini image which weight 0.1 from real image while real image is downloading
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
                //TODO pick and change photo logic
                getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");
                chooserIntent = Intent.createChooser(getIntent, "Select Image");
                startActivityForResult(chooserIntent, PICK_IMAGE);
                break;

            case R.id.avatar:
                //TODO pick and change photo logic
                getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");
                chooserIntent = Intent.createChooser(getIntent, "Select Image");
                startActivityForResult(chooserIntent, PICK_IMAGE);
                break;

            case R.id.saveButton:
                userEmail = editEmail.getText().toString();
                userName = editName.getText().toString();
                userSoname = editSoname.getText().toString();
                userNumber = editNumber.getText().toString();
                userJob = editJob.getText().toString();

                //TODO send strings and photo to server
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null){
            Uri returnUri = data.getData();
            Cursor returnCursor =
                    getContentResolver().query(returnUri, null, null, null, null);

            //Get Bitmap from gallery's chosen photo
            try {
                eventImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), returnUri);
                avatar.setImageBitmap(eventImageBitmap);
                avatar.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            returnCursor.close();
        }
    }
}
