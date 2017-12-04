package com.face_location.facelocation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class NewEventFourthActivity extends AppCompatActivity implements View.OnClickListener{

    Spinner spinnerEventPeriod;
    TextView buttonBackView, forwardButtonTextView;
    EditText placesQuantity;
    public static final String TAG = "newEvent";
    public static final String EVENT_PLACES = "seats";
    public static final String EVENT_PERIOD = "frequency";
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

        placesQuantity = (EditText) findViewById(R.id.placesQuantity);

        //Show saved images from byte array
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
                String placesEvent = placesQuantity.getText().toString();
                Log.i(TAG, "Количество мест: " + placesEvent);

                String periodEvent = spinnerEventPeriod.getSelectedItem().toString();
                Log.i(TAG, "Периодичность: " + periodEvent);

                //TODO спросить Диму, какие значения соответствуют словам
                switch (periodEvent){
                    case "Одноразово":
                        periodEvent = "0";
                        Log.i(TAG, "Периодичность: " + periodEvent);
                        break;

                    case "Щотижнево":
                        periodEvent = "1";
                        Log.i(TAG, "Периодичность: " + periodEvent);
                        break;

                    case "Без кінця":
                        periodEvent = "2";
                        Log.i(TAG, "Периодичность: " + periodEvent);
                        break;
                }

                //Save Event place quantity to shared preferences file
                SharedPreferences sharedPref = getSharedPreferences(NewEventFirstActivity.FILE_EVENT_DETAILS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(EVENT_PLACES, placesEvent);
                editor.putString(EVENT_PERIOD, periodEvent);
                editor.commit();

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
//            Files file = new Files(filePath);
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

