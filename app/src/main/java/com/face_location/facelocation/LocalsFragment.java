package com.face_location.facelocation;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.face_location.facelocation.model.GetEvent.User;

import java.util.ArrayList;

/**
 * Created by admin on 30.11.17.
 */

public class LocalsFragment extends Fragment{


    ListView groupList, localsList;
    ArrayList<User> parcelables;

    private static final String TAG = "LocalsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.locals_fragment, container, false);

        Bundle bundle = getArguments();
        parcelables = bundle.getParcelableArrayList("data");


        FloatingActionButton createGroupChatFab = (FloatingActionButton) rootView.findViewById(R.id.createGroupChatFab);
        createGroupChatFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                View mView = getLayoutInflater().inflate(R.layout.new_group_chat_title, null);
                final EditText groupChatTitle = (EditText) mView.findViewById(R.id.groupChatTitle);

                Button cancelBtn = (Button) mView.findViewById(R.id.cancelBtn);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getActivity().onBackPressed();
                    }
                });

                Button createBtn = (Button) mView.findViewById(R.id.createBtn);
                createBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent groupChatUserChose = new Intent(getContext(), GroupChatUserChose.class);
                        groupChatUserChose.putExtra("chat_name", groupChatTitle.getText().toString().trim());
                        groupChatUserChose.putParcelableArrayListExtra("data", parcelables);
                        startActivity(groupChatUserChose);
                    }
                });


                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }
        });

        ArrayList<ChatUser> users = new ArrayList<>();
        ArrayList<Group> groups = new ArrayList<>();

        ArrayList<User> localizedUser = getActivity().getIntent().getParcelableArrayListExtra("data");
        Log.i(TAG, "РАЗМЕР СПИСКА ВО ФРАГМЕНТЕ: " + localizedUser.size());

        for (int k = 0; k < 1; k++) {
            groups.add(new Group("Название группового чата", "Афанасий Петрович, Анна Лаврова"));
        }

        for (int i = 0; i < localizedUser.size(); i++) {
            users.add(new ChatUser(
                    localizedUser.get(i).getUsername(),
                    localizedUser.get(i).getEmail(),
                    localizedUser.get(i).getAvatarMob(),
                    localizedUser.get(i).getEventID()));
        }


        groupList = (ListView) rootView.findViewById(R.id.groupsListView);
        GroupAdapter groupAdapter = new GroupAdapter(getContext(), R.layout.group_card, groups);
        groupList.setAdapter(groupAdapter);

        boolean isMyEventActivity = getActivity().getIntent().getBooleanExtra("isMyEvent", true);
        localsList = (ListView) rootView.findViewById(R.id.localsListView);
        LocalsAdapter localsAdapter = new LocalsAdapter(getContext(), R.layout.locals_card, users, isMyEventActivity);
        localsList.setAdapter(localsAdapter);

        return rootView;
    }
}
