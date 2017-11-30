package com.face_location.facelocation;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by admin on 30.11.17.
 */

public class LocalsFragment extends Fragment{


    ListView groupList, localsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.locals_fragment, container, false);

        ArrayList<ChatUser> users = new ArrayList<>();
        ArrayList<Group> groups = new ArrayList<>();


        for (int k = 0; k < 1; k++) {
            groups.add(new Group("Название группового чата", "Афанасий Петрович, Анна Лаврова"));
        }

        for (int i = 0; i < 15; i++) {
            users.add(new ChatUser("Имя Фамилия " + i));
        }


        groupList = (ListView) rootView.findViewById(R.id.groupsListView);
        GroupAdapter groupAdapter = new GroupAdapter(getContext(), R.layout.group_card, groups);
        groupList.setAdapter(groupAdapter);

        localsList = (ListView) rootView.findViewById(R.id.localsListView);
        LocalsAdapter localsAdapter = new LocalsAdapter(getContext(), R.layout.locals_card, users);
        localsList.setAdapter(localsAdapter);

        return rootView;
    }
}
