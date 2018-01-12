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

import com.face_location.facelocation.Utils.ChatAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 30.11.17.
 */

public class ChatFragment extends Fragment {

    private static final String TAG = "ChatFragment";

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    EditText messageEditText;
    Button sendBtn;

    com.github.nkzawa.socketio.client.Socket socket;

    List<String> chatMessages = new ArrayList<>();
    List<String> senders = new ArrayList<>();
    List<String> avatars = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chat_fragment, container, false);

        chatMessages.add("В Черновицкой области коли");
        chatMessages.add("ет государственное учреждение Черновицкий областной лаб");
        chatMessages.add("По состоянию на 12 января в Черновицкой области зарегистрировано 468 случаев кори. Среди заболевших 372 детей (79%)");
        chatMessages.add("По данным центра");
        chatMessages.add("Также в столице временно приостановлена работа цирка Кобзов из-за выявленного случая");

        senders.add("Никита");
        senders.add("Валентин");
        senders.add("Маша");
        senders.add("Вероника");
        senders.add("Наташа");

        avatars.add("https://smhttp-ssl-33667.nexcesscdn.net/manual/wp-content/uploads/2017/01/triangle-face-shape-beard-2.jpg");
        avatars.add("http://v.img.com.ua/b/1100x999999/8/05/65b315f1f62d9a4ace47fb3bf8a92058.jpg");
        avatars.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTktdpqq93bqVmiIuozLHHj2sEpFEtz0HdKaqaPw1TgMZuh06tGKA");
        avatars.add("https://www.bolde.com/wp-content/uploads/2015/08/iStock_000061900384_Small-400x400.jpg");
        avatars.add("http://www.sostav.ru/app/public/images/news/2017/05/10/compressed/lico01.jpeg");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.mainChat);
        adapter = new ChatAdapter(chatMessages, senders, avatars);
        recyclerView.setAdapter(adapter);

        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);



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
        addMessage(message, "me");
//        socket.emit("message", message);
    }

    private void addMessage(String message, String senderName) {
        chatMessages.add(message);
        senders.add(senderName);

        adapter.notifyItemInserted(chatMessages.size() - 1);
        recyclerView.smoothScrollToPosition(chatMessages.size() - 1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        socket.disconnect();
    }
}
