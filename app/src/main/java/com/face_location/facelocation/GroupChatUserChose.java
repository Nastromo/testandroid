package com.face_location.facelocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.face_location.facelocation.model.GetEvent.User;

import java.util.ArrayList;

public class GroupChatUserChose extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    ArrayList<User> localizedUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat_user_chose);

        localizedUsers = getIntent().getParcelableArrayListExtra("data");

        recyclerView = (RecyclerView) findViewById(R.id.chatUsersList);
        adapter = new GroupChatAdapter(GroupChatUserChose.this, localizedUsers);
        recyclerView.setAdapter(adapter);

        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }
}
