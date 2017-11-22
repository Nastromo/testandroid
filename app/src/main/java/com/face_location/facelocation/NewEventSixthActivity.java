package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class NewEventSixthActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonChoseSchedule;

    //Delete after tests
    TextView textView2;
    ImageView imageView2;
    //Chane these variables to proper names
    String title, latitude, longitude, about, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_sixth);

        buttonChoseSchedule = (Button) findViewById(R.id.buttonChoseSchedule);
        buttonChoseSchedule.setOnClickListener(this);

        //Delete after tests
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        textView2 = (TextView) findViewById(R.id.textView2);

        //Converts bytes to Bitmap
//        byte[] bytesArray = readBytesFromFile(NewEventThirdActivity.imgFilePath);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(bytesArray, 0, bytesArray.length);

        //Display the Bitmap as an ImageView
//        imageView2.setImageBitmap(decodedByte);
//        imageView2.setVisibility(View.VISIBLE);

        //Test data which has to be sent on server
//        textView2.setText(title + "\n" +
//                latitude + "\n" +
//                longitude + "\n" +
//                about + "\n" +
//                contact);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.buttonChoseSchedule:
                Intent newEventSeventhActivity = new Intent(this, NewEventSeventhActivity.class);
                startActivity(newEventSeventhActivity);
                break;
        }
    }

    // delete after tests
    private static byte[] readBytesFromFile(String filePath) {

        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;

        try {

            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];

            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return bytesArray;

    }
}
