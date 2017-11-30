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

public class FilesFragment extends Fragment {

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.files_fragment, container, false);

        ArrayList<File> files = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            files.add(new File("Первое событие с длинным заголовком" + i));
        }


        listView = (ListView) rootView.findViewById(R.id.filesListView);
        FileAdapter myLocationsFragment = new FileAdapter(getContext(), R.layout.file_card, files);
        listView.setAdapter(myLocationsFragment);

        return rootView;
    }
}
