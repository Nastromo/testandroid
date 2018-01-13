package com.face_location.facelocation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.face_location.facelocation.model.DataBase.DataBaseHelper;
import com.face_location.facelocation.model.FacelocationAPI;
import com.face_location.facelocation.model.GetMainChat.MainChatResponse;
import com.face_location.facelocation.model.GetMainChat.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 30.11.17.
 */

public class ChatFragment extends Fragment {

    private static final String TAG = "ChatFragment";

    View rootView;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    EditText messageEditText;
    Button sendBtn;
    String url, token, eventID, myID;
    String[] applicationData;

    com.github.nkzawa.socketio.client.Socket socket;

    List<String> chatMessages = new ArrayList<>();
    List<String> senders = new ArrayList<>();
    List<String> avatars = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.chat_fragment, container, false);

        url = getString(R.string.base_url);
        DataBaseHelper applicationDB = DataBaseHelper.getInstance(getContext());
        applicationData = applicationDB.retrieveFirstLoginValues();
        token = applicationData[5];
        myID = applicationData[0];

        Bundle bundle = getArguments();
        eventID = bundle.getString("eventID");
        Log.i(TAG, "Какой ивент проверяем: " + eventID);

        chatMessages.clear();
        senders.clear();
        avatars.clear();
        getChatData();
        
        messageEditText = (EditText) rootView.findViewById(R.id.messageEditText);
        sendBtn = (Button) rootView.findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "ОТПРАВИТЬ СООБЩЕНИЕ: ");
                sendMessage();
            }
        });

//        try{
//            socket = IO.socket("https://face-location.com:80");
//        } catch (URISyntaxException e){
//            Log.i(TAG, "ОШИБКА СОКЕТА: ");
//        }
//
//        socket.connect();
//        socket.on("message", handling);

        return rootView;
    }

//    private Emitter.Listener handling = new Emitter.Listener() {
//        @Override
//        public void call(Object... args) {
//            addMessage(args[0].toString(), null);
//        }
//    };

    private void sendMessage(){
        String message = messageEditText.getText().toString().trim();
        messageEditText.setText("");
        addMessage(message, myID);
//        socket.emit("message", message);
    }

    private void addMessage(String message, String senderID) {
        chatMessages.add(message);
        senders.add(senderID);

        adapter.notifyItemInserted(chatMessages.size() - 1);
        adapter.notifyItemInserted(senders.size() - 1);

        recyclerView.smoothScrollToPosition(chatMessages.size() - 1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        socket.disconnect();
    }

    private void getChatData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        FacelocationAPI api = retrofit.create(FacelocationAPI.class);

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Auth", token);

        Log.i(TAG, "Какой ивент проверяем: " + eventID);

        Call <List<MainChatResponse>> call = api.getMainChat(headers, eventID);
        call.enqueue(new Callback<List<MainChatResponse>>() {
            @Override
            public void onResponse(Call<List<MainChatResponse>> call, Response<List<MainChatResponse>> response) {
                Log.i(TAG, "ОТВЕТ СЕРВЕРА - ПОЛУЧИТЬ ЧАТ: " + response.toString());

                List<MainChatResponse> chatResponse = response.body();
                for (int i = 0; i < chatResponse.size(); i++) {
                    MainChatResponse mainChat = chatResponse.get(i);

                    if (mainChat.getType() == 0){
                        List<Message> messages = mainChat.getMessages();

                        String userID;
                        String userMessage;
                        String userAvatar;
                        Log.i(TAG, "РАЗМЕР messages: " + messages.size());
                        for (int j = 0; j < messages.size(); j++) {
                            Message message = messages.get(j);

                            userID = message.getUser().getId();
                            Log.i(TAG, "ID ЮЕЗАРА В ЧАТЕ: " + userID);
                            senders.add(userID);

                            userMessage = message.getText();
                            Log.i(TAG, "СООБЩЕНИЕ ЮЕЗАРА В ЧАТЕ: " + userMessage);
                            chatMessages.add(userMessage);

                            userAvatar = message.getUser().getAvatarMob();
                            Log.i(TAG, "АВАТАР ЮЕЗАРА В ЧАТЕ: " + userAvatar);
                            avatars.add(userAvatar);

                        }

                        recyclerView = (RecyclerView) rootView.findViewById(R.id.mainChat);
                        adapter = new ChatAdapter(chatMessages, senders, avatars, myID);
                        recyclerView.setAdapter(adapter);

                        manager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(manager);
                        recyclerView.scrollToPosition(chatMessages.size() - 1);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<MainChatResponse>> call, Throwable t) {
                Log.i(TAG, "ОШИБКА НА ПОЛУЧЕНИЕ ЧАТА: " + t.toString());

            }
        });
    }
}
