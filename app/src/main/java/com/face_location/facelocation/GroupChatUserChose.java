package com.face_location.facelocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.face_location.facelocation.model.GetEvent.User;

import java.util.ArrayList;
import java.util.HashMap;

public class GroupChatUserChose extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    ArrayList<User> localizedUsers;

    ImageView backBtn, createChatBtn;
    private static final String TAG = "GroupChatUserChose";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat_user_chose);

        localizedUsers = getIntent().getParcelableArrayListExtra("data");

        backBtn = (ImageView) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.chatUsersList);
        adapter = new GroupChatAdapter(GroupChatUserChose.this, localizedUsers);
        recyclerView.setAdapter(adapter);

        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        createChatBtn = (ImageView) findViewById(R.id.createChatBtn);
        createChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!GroupChatAdapter.checkedUsersID.isEmpty() && GroupChatAdapter.checkedUsersID.containsValue(true)){
                    createGroupChat(GroupChatAdapter.checkedUsersID);
                }else{
                    Toast.makeText(GroupChatUserChose.this, getString(R.string.chose_users), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createGroupChat(HashMap<String, Boolean> checkedUsersID){
        Log.i(TAG, "ПРОВЕРЯЕМ ID перед созданием группового чата: " + checkedUsersID.size());


    }
}
