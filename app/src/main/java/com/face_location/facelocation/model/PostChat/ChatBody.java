package com.face_location.facelocation.model.PostChat;

/**
 * Created by admin on 19.01.18.
 */

public class ChatBody {
    String title, event;
    String[] users;

    public ChatBody(String title, String event, String[] user) {
        this.title = title;
        this.event = event;
        this.users = user;
    }
}
