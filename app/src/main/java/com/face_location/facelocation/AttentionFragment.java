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

public class AttentionFragment extends Fragment {

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.attention_fragment, container, false);

        ArrayList<Attention> attentions = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            attentions.add(new Attention("Первое событие с длинным заголовком",
                    "Много текста свамого тела обїявденія і еще чуть чуть текста",
                    "Никита Русаков",
                    "время"));
        }

        listView = (ListView) rootView.findViewById(R.id.attentionListView);
        AttentionAdapter attentionAdapter = new AttentionAdapter(getContext(), R.layout.attention_card, attentions);
        listView.setAdapter(attentionAdapter);

        return rootView;
    }
}
