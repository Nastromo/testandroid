package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.face_location.facelocation.model.DataBase.DataBaseHelper;
import com.face_location.facelocation.model.GetEvent.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GroupChatUserChose extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    ArrayList<User> localizedUsers;

    ImageView backBtn, createChatBtn;
    private static final String TAG = "GroupChatUserChose";
    String chatName, eventID, url, token;
    String[] applicationData;
    public static String[] usersIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat_user_chose);

        DataBaseHelper applicationDB = DataBaseHelper.getInstance(this);
        applicationData = applicationDB.retrieveFirstLoginValues();
        token = applicationData[5];

        localizedUsers = getIntent().getParcelableArrayListExtra("data");

        Iterator<User> iterUser = localizedUsers.iterator();
        while(iterUser.hasNext()){
            User user = iterUser.next();
            if (user.getStatus() == 1){
                iterUser.remove();
            }
        }

        chatName = getIntent().getStringExtra("chat_name");
        eventID = getIntent().getStringExtra("eventID");
        url = getString(R.string.base_url);

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

        usersIDs = checkedUsersID.keySet().toArray(new String[checkedUsersID.size()]);

        Intent chatActivity = new Intent(GroupChatUserChose.this, ChatActivity.class);
        chatActivity.putExtra("chat_name", chatName);
        chatActivity.putExtra("quantity", usersIDs.length);
        startActivity(chatActivity);
    }
















//    private void createGroupChat(HashMap<String, Boolean> checkedUsersID){
//        Log.i(TAG, "ПРОВЕРЯЕМ ID перед созданием группового чата: " + checkedUsersID.size());
//
//        final String[] usersIDs = checkedUsersID.keySet().toArray(new String[checkedUsersID.size()]);
//
//        ChatBody chatBody = new ChatBody(chatName, eventID, usersIDs);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//
//        FacelocationAPI api = retrofit.create(FacelocationAPI.class);
//
//        HashMap<String, String> headers = new HashMap<String, String>();
//        headers.put("Content-Type", "application/json");
//        headers.put("X-Auth", token);
//
//        Call<ChatResponse> call = api.createChat(headers, chatBody);
//        call.enqueue(new Callback<ChatResponse>() {
//            @Override
//            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
//                Log.i(TAG, "СОЗДАТЬ ГРУППОЙ ЧАТ " + response.toString());
//
//                if (response.code() == 200){
//                    Intent chatActivity = new Intent(GroupChatUserChose.this, ChatActivity.class);
//                    chatActivity.putExtra("chat_name", chatName);
//                    chatActivity.putExtra("quantity", usersIDs.length);
//                    startActivity(chatActivity);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ChatResponse> call, Throwable t) {
//                Log.i(TAG, "onFailure: " + t.toString());
//            }
//        });
//    }
}
