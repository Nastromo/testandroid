package com.face_location.facelocation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by admin on 29.11.17.
 */

public class MyEventsFragment extends Fragment {

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_events_fragment, container, false);

        ArrayList<Event> events = new ArrayList<>();
        events.add(new Event(
                "Первое событие",
                "Описание, что это за событие такое интересно тут у нас и Описание, что это за событие такое интересно тут у нас  еще много текста для наглядносит",
                "Уже прошло",
                "412"));
        events.add(new Event(
                "Второе большое событие",
                "Описание, что это за событие такое интересно тут у нас и еще много такое интересно тут у нас и еще много такое интересно тут у нас и еще много такое интересно тут у нас и еще много такое интересно тут у нас и еще много  текста для наглядносит",
                "Скоро начнется",
                "1002"));
        events.add(new Event(
                "Ужасно длинное название события",
                "Описание, что это за событие такое интересно тут у нас и еще много текста для наглядносит",
                "Скоро начнется",
                "23"));
        events.add(new Event(
                "Крутое событие с коротким хвостом",
                "Описание, что это за событие такое интересно тут у нас и еще много текста для наглядносит",
                "Уже прошло",
                "178"));
        events.add(new Event(
                "Еще пятое событие",
                "Описание, что это за событие такое такое интересно тут у нас и еще много такое интересно тут у нас и еще много такое интересно тут у нас и еще много  интересно тут у нас и еще много текста для наглядносит",
                "Уже прошло",
                "42"));

        listView = (ListView) rootView.findViewById(R.id.listView);
        MyEventsListAdapter myEventsListAdapter = new MyEventsListAdapter(getContext(), R.layout.my_event_card, events);
        listView.setAdapter(myEventsListAdapter);

        return rootView;
    }
}
