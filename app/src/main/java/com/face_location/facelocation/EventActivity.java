package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.face_location.facelocation.model.DataBase.DataBaseHelper;
import com.face_location.facelocation.model.FacelocationAPI;
import com.face_location.facelocation.model.GetEvent.EventResponse;
import com.face_location.facelocation.model.GetEvent.Location;
import com.face_location.facelocation.model.GetEvent.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventActivity extends AppCompatActivity implements View.OnClickListener{

    TextView backTextView;
    Button localizButton;
    RecyclerView recyclerView;
    ImageView onAirImage;
    private String eventID, url, token;
    private static final String TAG = "EventActivity";
    String[] applicationData;
    List<SimilarEvent> similarEventList;
    private TextView eventTitleView, eventDateView, eventLocationView, passTypeView, eventTimeView, eventDescriptionView, userQuantity, userQuantitySecond, userQuantityThird;
    ImageView eventPhoto, userAvatar, userAvatarSecond, userAvatarThird;
    String eventDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        url = getString(R.string.base_url);
        eventPhoto = (ImageView) findViewById(R.id.eventPhoto);

        DataBaseHelper applicationDB = DataBaseHelper.getInstance(this);
        applicationData = applicationDB.retrieveFirstLoginValues();
        token = applicationData[5];

        eventID = getIntent().getStringExtra("id");

        eventTitleView = (TextView) findViewById(R.id.eventTitle);
        eventDateView = (TextView) findViewById(R.id.eventDate);
        eventLocationView = (TextView) findViewById(R.id.eventLocation);
        passTypeView = (TextView) findViewById(R.id.passType);
        eventTimeView = (TextView) findViewById(R.id.eventTime);
        eventDescriptionView = (TextView) findViewById(R.id.eventDescription);
        userQuantity = (TextView) findViewById(R.id.usersQuantity);
        userQuantitySecond = (TextView) findViewById(R.id.usersQuantitySecond);
        userQuantityThird = (TextView) findViewById(R.id.usersQuantityThird);

        onAirImage = (ImageView) findViewById(R.id.onAirImage);
        userAvatar = (ImageView) findViewById(R.id.userAvatar);
        userAvatarSecond = (ImageView) findViewById(R.id.userAvatarSecond);
        userAvatarThird = (ImageView) findViewById(R.id.userAvatarThird);

        backTextView = (TextView) findViewById(R.id.backTextView);
        backTextView.setOnClickListener(this);

        localizButton = (Button) findViewById(R.id.localizButton);
        localizButton.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.similarEvents);
        recyclerView.setFocusable(false);

        getEvent(eventID);
        getSimilarEvent(eventID);
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

                String eventTitle = response.body().getTitle();
                eventDate = response.body().getTime().getStart();
                String eventPassType = response.body().getType().getTitle();

                List<Location> eventLocations = response.body().getLocations();
                Location eventLocation = eventLocations.get(0);
                String eventLocationTitle = eventLocation.getTitle();

                String eventCoverURL = response.body().getCover().getLocationMob();

                List<Subscriber> eventSubscribers = response.body().getSubscribers();
                Subscriber eventSubscriber = eventSubscribers.get(0);
                String eventSubscriberAvatar = eventSubscriber.getUser().getAvatarMob();

                String eventSubscriberSecondAvatar = null;
                Subscriber eventSubscriberSecond = null;
                try {
                    eventSubscriberSecond = eventSubscribers.get(1);
                    eventSubscriberSecondAvatar = eventSubscriber.getUser().getAvatarMob();
                }catch (IndexOutOfBoundsException e){
                    Log.i(TAG, "ОШИБКА ЭЛЛЕМЕНТА ЛИСТА: " + e.toString());
                }

                String eventSubscriberThirdAvatar = null;
                Subscriber eventSubscriberThird = null;
                try{
                    eventSubscriberThird = eventSubscribers.get(2);
                    eventSubscriberThirdAvatar = eventSubscriber.getUser().getAvatarMob();
                } catch (IndexOutOfBoundsException e){
                    Log.i(TAG, "ОШИБКА ЭЛЛЕМЕНТА ЛИСТА: " + e.toString());
                }

                String eventUserQuantity = String.valueOf(eventSubscribers.size());
                Log.i(TAG, "К-ВО ЮЗЕРОВ 1: " + eventUserQuantity);


                String eventTimeStart = response.body().getTime().getStart();
                String eventText = response.body().getText();


                if (eventTitle.length() > 17){
                    eventTitle = eventTitle.substring(0, Math.min(eventTitle.length(), 17)) + "...";
                    eventTitleView.setText(eventTitle);
                } else {
                    eventTitleView.setText(eventTitle);
                }

                eventDate = eventDate.substring(0, Math.min(eventDate.length(), 10));
                eventDateView.setText(eventDate);

                if (eventLocationTitle.length() > 10){
                    eventLocationTitle = eventLocationTitle.substring(0, Math.min(eventLocationTitle.length(), 10)) + "...";
                    eventLocationView.setText(eventLocationTitle);

                }else {
                    eventLocationView.setText(eventLocationTitle);
                }

                Log.i(TAG, "ТИП ПРОХОДА: " + eventPassType);
                passTypeView.setText(eventPassType);

                Log.i(TAG, "К-ВО ЮЗЕРОВ 2: " + eventUserQuantity);
                userQuantity.setText(eventUserQuantity);

                eventTimeStart = eventTimeStart.substring(11, 16);
                eventTimeView.setText(eventTimeStart);

                eventDescriptionView.setText(eventText);

                eventPhoto.setBackground(null);
                Glide
                        .with(EventActivity.this)
                        .load(eventCoverURL)
                        .thumbnail(0.1f)
                        .apply(RequestOptions
                                .centerCropTransform())
                        .into(eventPhoto);

                if (eventSubscribers != null && eventSubscribers.size() > 1){
                    switch (eventSubscribers.size()){
                        case 2:
                            userQuantity.setText("");
                            userQuantitySecond.setText("+" + String.valueOf(eventUserQuantity));
                            userQuantityThird.setText("");

                            userAvatarThird.setVisibility(View.INVISIBLE);

                            Glide
                                    .with(EventActivity.this)
                                    .load(eventSubscribers.get(0).getUser().getAvatarMob())
                                    .thumbnail(0.1f) //shows mini image which weight 0.1 from real image while real image is downloading
                                    .apply(RequestOptions
                                            .circleCropTransform())
                                    //      .placeholder(R.drawable.oval)) //shows drawable while real/mini image is downloading
                                    .into(userAvatar);
                            Glide
                                    .with(EventActivity.this)
                                    .load(eventSubscribers.get(1).getUser().getAvatarMob())
                                    .thumbnail(0.1f) //shows mini image which weight 0.1 from real image while real image is downloading
                                    .apply(RequestOptions
                                            .circleCropTransform())
                                    //      .placeholder(R.drawable.oval)) //shows drawable while real/mini image is downloading
                                    .into(userAvatarSecond);
                            break;

                        case 3:
                            userQuantity.setText("");
                            userQuantitySecond.setText("");
                            userQuantityThird.setText("+" + String.valueOf(eventUserQuantity));

                            Glide
                                    .with(EventActivity.this)
                                    .load(eventSubscribers.get(0).getUser().getAvatarMob())
                                    .thumbnail(0.1f) //shows mini image which weight 0.1 from real image while real image is downloading
                                    .apply(RequestOptions
                                            .circleCropTransform())
                                    //      .placeholder(R.drawable.oval)) //shows drawable while real/mini image is downloading
                                    .into(userAvatar);
                            Glide
                                    .with(EventActivity.this)
                                    .load(eventSubscribers.get(1).getUser().getAvatarMob())
                                    .thumbnail(0.1f) //shows mini image which weight 0.1 from real image while real image is downloading
                                    .apply(RequestOptions
                                            .circleCropTransform())
                                    //      .placeholder(R.drawable.oval)) //shows drawable while real/mini image is downloading
                                    .into(userAvatarSecond);
                            Glide
                                    .with(EventActivity.this)
                                    .load(eventSubscribers.get(2).getUser().getAvatarMob())
                                    .thumbnail(0.1f) //shows mini image which weight 0.1 from real image while real image is downloading
                                    .apply(RequestOptions
                                            .circleCropTransform())
                                    //      .placeholder(R.drawable.oval)) //shows drawable while real/mini image is downloading
                                    .into(userAvatarThird);
                            break;
                    }

                } else {
                    userQuantity.setText("+" + String.valueOf(eventUserQuantity));
                    userQuantitySecond.setText("");
                    userQuantityThird.setText("");

                    userAvatarSecond.setVisibility(View.INVISIBLE);
                    userAvatarThird.setVisibility(View.INVISIBLE);

                    Glide
                            .with(EventActivity.this)
                            .load(eventSubscribers.get(0).getUser().getAvatarMob())
                            .thumbnail(0.1f) //shows mini image which weight 0.1 from real image while real image is downloading
                            .apply(RequestOptions
                                    .circleCropTransform())
                            //      .placeholder(R.drawable.oval)) //shows drawable while real/mini image is downloading
                            .into(userAvatar);
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public void getSimilarEvent(String eventID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        FacelocationAPI api = retrofit.create(FacelocationAPI.class);

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Auth", token);

        int limit = 3;

        Call<List<EventResponse>> call = api.getSimilarEvent(headers, limit, eventID, eventDate);
        call.enqueue(new Callback<List<EventResponse>>() {
            @Override
            public void onResponse(Call<List<EventResponse>> call, Response<List<EventResponse>> response) {
                Log.i(TAG, "ОТВЕТ СЕРВЕРА SIMILAR: " + response.toString());
                List<EventResponse> eventsResponse = response.body();

                similarEventList = new ArrayList<>();
                for (int i = 0; i < eventsResponse.size(); i++) {
                    EventResponse event = eventsResponse.get(i);

                    String similarEventTitle = event.getTitle();
                    String similarEventDate = event.getTime().getStart();
                    String similarEventDateShort = similarEventDate.substring(0, Math.min(similarEventDate.length(), 10));
                    int similarEventUserQua = event.getSubscribers().size();

                    List<Subscriber> subscribers = event.getSubscribers();
                    List<String> subsAvatars = new ArrayList<>();
                    for (int j = 0; j < subscribers.size(); j++) {
                        Subscriber subscriber = subscribers.get(j);
                        String subAvatar = subscriber.getUser().getAvatarMob();
                        subsAvatars.add(subAvatar);
                    }

                    String mainPic = event.getCover().getLocationMob();

                    similarEventList.add(new SimilarEvent(
                            similarEventTitle,
                            similarEventDateShort,
                            "passsssss",
                            similarEventUserQua,
                            mainPic,
                            subsAvatars));
                }

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(EventActivity.this, similarEventList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(EventActivity.this, LinearLayoutManager.HORIZONTAL, false));
            }

            @Override
            public void onFailure(Call<List<EventResponse>> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.toString());
            }
        });
    }
}
