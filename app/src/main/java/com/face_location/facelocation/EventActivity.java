package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity implements View.OnClickListener{

    TextView backTextView;
    Button localizButton;
    RecyclerView recyclerView;
    ImageView onAirImage;
    private String eventID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        eventID = getIntent().getStringExtra("id");

        onAirImage = (ImageView) findViewById(R.id.onAirImage);

        backTextView = (TextView) findViewById(R.id.backTextView);
        backTextView.setOnClickListener(this);

        localizButton = (Button) findViewById(R.id.localizButton);
        localizButton.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.similarEvents);
        recyclerView.setFocusable(false);

        getEvent(eventID);

        List<SimilarEvent> similarEventList = new ArrayList<>();

        SimilarEvent events = new SimilarEvent("Большое событие", "17 января");
        SimilarEvent events2 = new SimilarEvent("Событие", "21 декабря");
        SimilarEvent events3 = new SimilarEvent("Маленькое событие", "13 марта");

        similarEventList.add(events);
        similarEventList.add(events2);
        similarEventList.add(events3);

            RecyclerViewAdapter adapter = new RecyclerViewAdapter(similarEventList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backTextView:
                onBackPressed();
                break;

            case R.id.localizButton:
                Intent localizedActivity = new Intent(this, LocalizedActivity.class);
                startActivity(localizedActivity);
                break;
        }
    }

    public void getEvent(String eventID){

    }
}
