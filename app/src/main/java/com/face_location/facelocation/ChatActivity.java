package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.face_location.facelocation.model.DataBase.DataBaseHelper;

public class ChatActivity extends AppCompatActivity {

    ImageView avatar, sendBtn;
    TextView backTextView, eventName, usersQuantity;
    EditText messageEditText;
    DataBaseHelper applicationDB;
    String[] userArrayData;
    private static final String TAG = "ChatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        backTextView = (TextView) findViewById(R.id.backTextView);
        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        avatar = (ImageView) findViewById(R.id.avatar);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myProfile = new Intent (ChatActivity.this, MyProfileActivity.class);
                startActivity(myProfile);
            }
        });

        applicationDB = DataBaseHelper.getInstance(this);
        userArrayData = applicationDB.retrieveFirstLoginValues();
        String userAvatar = userArrayData[10];

        if (userAvatar != null){
            avatar.setBackground(null);
            Glide
                    .with(ChatActivity.this)
                    .load(userAvatar)
                    .thumbnail(0.1f) //shows mini image which weight 0.1 from real image while real image is downloading
                    .apply(RequestOptions
                            .circleCropTransform())
                    //      .placeholder(R.drawable.oval)) //shows drawable while real/mini image is downloading
                    .into(avatar);
        }


        eventName = (TextView) findViewById(R.id.eventName);
        eventName.setText(getIntent().getStringExtra("chat_name"));

        usersQuantity = (TextView) findViewById(R.id.usersQuantity);
        usersQuantity.setText(String.valueOf(getIntent().getIntExtra("quantity", -1)));
    }
}
