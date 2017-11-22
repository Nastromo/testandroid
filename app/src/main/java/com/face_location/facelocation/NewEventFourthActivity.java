package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class NewEventFourthActivity extends AppCompatActivity implements View.OnClickListener{

    Spinner spinnerEventPeriod;
    TextView buttonBackView, forwardButtonTextView;
//    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_fourth);

        buttonBackView = (TextView) findViewById(R.id.buttonBackView);
        buttonBackView.setOnClickListener(this);

        forwardButtonTextView = (TextView) findViewById(R.id.forwardButtonTextView);
        forwardButtonTextView.setOnClickListener(this);

        spinnerEventPeriod = (Spinner) findViewById(R.id.spinnerEventPeriod);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                getResources().getStringArray(R.array.event_period));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEventPeriod.setAdapter(myAdapter);

//        imageView2 = (ImageView) findViewById(R.id.imageView2);
//
//        //Converts bytes to Bitmap
//                byte[] bytesArray = readBytesFromFile(NewEventThirdActivity.imgFilePath);
//                Bitmap decodedByte = BitmapFactory.decodeByteArray(bytesArray, 0, bytesArray.length);
//
//                //Display the Bitmap as an ImageView
//                imageView2.setImageBitmap(decodedByte);
//                imageView2.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonBackView:
                onBackPressed();
                break;
            case R.id.forwardButtonTextView:
                Intent newEventFifthActivity = new Intent(this, NewEventFifthActivity.class);
                startActivity(newEventFifthActivity);
        }
    }

    // delete after tests
//    private static byte[] readBytesFromFile(String filePath) {
//
//        FileInputStream fileInputStream = null;
//        byte[] bytesArray = null;
//
//        try {
//
//            File file = new File(filePath);
//            bytesArray = new byte[(int) file.length()];
//
//            //read file into bytes[]
//            fileInputStream = new FileInputStream(file);
//            fileInputStream.read(bytesArray);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fileInputStream != null) {
//                try {
//                    fileInputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return bytesArray;
//
//    }
}

