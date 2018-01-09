package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.face_location.facelocation.model.DataBase.DataBaseHelper;
import com.face_location.facelocation.model.GetEvent.User;

import java.util.ArrayList;

public class LocalizedActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    String TAG = "LocalizedActivity";
    DataBaseHelper applicationDB;
    String[] userArrayData;
    String eventID, eventNameFromIntent, usersQuantityFromIntent;
    ArrayList<User> parcelables;
    TextView eventName, usersQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localized);

        eventID = getIntent().getStringExtra("id");
        eventNameFromIntent = getIntent().getStringExtra("event_name");
        usersQuantityFromIntent = getIntent().getStringExtra("users_quantity");

        eventName = (TextView) findViewById(R.id.eventName);
        eventName.setText(eventNameFromIntent);

        usersQuantity = (TextView) findViewById(R.id.usersQuantity);
        usersQuantity.setText(usersQuantityFromIntent);

        parcelables = getIntent().getParcelableArrayListExtra("data");
        Log.i(TAG, "РАЗМЕР ЛИСТА ДЛЯ ПАРСА НА СЛЕД ЄКРАНЕ: " + parcelables.size());
        Log.i(TAG, "АВАТР НА СТАРНИЦЕ ЛОКАЛАЙЗЕД АКТИВИТИ: " + parcelables.get(0).getAvatarMob());

        TextView backTextView = (TextView) findViewById(R.id.backTextView);
        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.containerEvent);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsLocalized);

        applicationDB = DataBaseHelper.getInstance(this);
        userArrayData = applicationDB.retrieveFirstLoginValues();

        ImageView avatar = (ImageView) findViewById(R.id.avatar);

        String userAvatar = userArrayData[10];

        if (userAvatar != null){
            avatar.setBackground(null);
            Glide
                    .with(LocalizedActivity.this)
                    .load(userAvatar)
                    .thumbnail(0.1f) //shows mini image which weight 0.1 from real image while real image is downloading
                    .apply(RequestOptions
                            .circleCropTransform())
                    //      .placeholder(R.drawable.oval)) //shows drawable while real/mini image is downloading
                    .into(avatar);
        }



        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myProfile = new Intent(LocalizedActivity.this, MyProfileActivity.class);
                startActivity(myProfile);
            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    //Makes tabs spin
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    LocalsFragment locals = new LocalsFragment();
                    return locals;

                case 1:
                    ChatFragment chatFragment = new ChatFragment();
                    return chatFragment;

                case 2:
                    AttentionFragment attentionFragment = new AttentionFragment();
                    Bundle bundleAtt = new Bundle();
                    bundleAtt.putString("eventID", eventID);
                    attentionFragment.setArguments(bundleAtt);
                    return attentionFragment;

                case 3:
                    FilesFragment filesFragment = new FilesFragment();
                    Bundle bundleFl = new Bundle();
                    bundleFl.putString("eventID", eventID);
                    filesFragment.setArguments(bundleFl);
                    return filesFragment;

                default:
                    return null;
                }
            }

            @Override
            public int getCount () {
                return 4;
            }
    }


}
