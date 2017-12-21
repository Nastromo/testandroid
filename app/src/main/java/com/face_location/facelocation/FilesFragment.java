package com.face_location.facelocation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.face_location.facelocation.model.DataBase.DataBaseHelper;
import com.face_location.facelocation.model.FacelocationAPI;
import com.face_location.facelocation.model.GetEvent.EventResponse;
import com.face_location.facelocation.model.GetEvent.File;

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

public class FilesFragment extends Fragment {

    ListView listView;
    String url, token;
    private static final String TAG = "FilesFragment";
    ArrayList<Files> filezzz;
    String[] applicationData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.files_fragment, container, false);

        url = getString(R.string.base_url);

        DataBaseHelper applicationDB = DataBaseHelper.getInstance(getContext());
        applicationData = applicationDB.retrieveFirstLoginValues();
        token = applicationData[5];

        filezzz = new ArrayList<>();
        listView = (ListView) rootView.findViewById(R.id.filesListView);
        getEvent("5a3b8a7a0da8446ca86fca31");

        return rootView;
    }

    public void getEvent(String eventID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        FacelocationAPI api = retrofit.create(FacelocationAPI.class);

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Auth", token);

        Call<EventResponse> call = api.getMyEvent(headers, eventID);
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                Log.i(TAG, "ОТВЕТ СЕРВЕРА: " + response.toString());

                List<File> files = response.body().getFiles();
                File file;
                String fileName;
                String fileURL;

                for (int i = 0; i < files.size(); i++) {
                    file = files.get(i);
                    fileName = file.getFilename();
                    fileURL = file.getLocation();

                    Log.i(TAG, "ДАННЫЕ ФАЙЛА: " + fileName + "\n"
                            + fileURL);


                    filezzz.add(new Files(fileName, fileURL));
                }

                FileAdapter myFileAdapter = new FileAdapter(getContext(), R.layout.file_card, filezzz);
                listView.setAdapter(myFileAdapter);
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.toString());

            }
        });
    }
}
