package com.face_location.facelocation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NewEventThirdActivity extends AppCompatActivity implements View.OnClickListener{

    Spinner spinnerEventPublicity;
    TextView buttonBackView, forwardButtonTextView;
    public static final int PICK_IMAGE = 1;
    static String imgFileName;
    static String imgFilePath;
    static byte[] imgBytes;
    Bitmap eventImageBitmap;
    Button buttonChosePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_third);

        buttonBackView = (TextView) findViewById(R.id.buttonBackView);
        buttonBackView.setOnClickListener(this);

        forwardButtonTextView = (TextView) findViewById(R.id.forwardButtonTextView);
        forwardButtonTextView.setOnClickListener(this);

        buttonChosePhoto = (Button) findViewById(R.id.buttonChosePhoto);
        buttonChosePhoto.setOnClickListener(this);

        spinnerEventPublicity = (Spinner) findViewById(R.id.spinnerEventPublicity);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                getResources().getStringArray(R.array.event_publicity));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEventPublicity.setAdapter(myAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.buttonBackView:
                onBackPressed();
                break;

            case R.id.forwardButtonTextView:
                Intent newEventFourthActivity = new Intent(this, NewEventFourthActivity.class);
                startActivity(newEventFourthActivity);
                break;

            case R.id.buttonChosePhoto:
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");
                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                startActivityForResult(chooserIntent, PICK_IMAGE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null){
            Uri returnUri = data.getData();
            Cursor returnCursor =
                    getContentResolver().query(returnUri, null, null, null, null);

            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            returnCursor.moveToFirst();

            imgFileName = returnCursor.getString(nameIndex);

            String upToNCharacters = imgFileName.substring(0, Math.min(imgFileName.length(), 20));
            buttonChosePhoto.setText(upToNCharacters + "...");

            //Get Bitmap from gallery's chosen photo
            try {
                eventImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), returnUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            saveImgToInternalStorage();
            getImageFilePath();
            returnCursor.close();
        }
    }

    //Convert image to String
//    private String imgToString (Bitmap bitmap){
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100, baos);
//
//        imgByteszz = baos.toByteArray();
//        encodedImage = Base64.encodeToString(imgByteszz, Base64.DEFAULT);
//        return encodedImage;
//    }

    private void saveImgToInternalStorage(){
        FileOutputStream fos = null;

        //Get byte array from photo
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        eventImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        imgBytes = baos.toByteArray();

        //Write to Internal Storage
        try {
            fos = openFileOutput(imgFileName, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            fos.write(imgBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getImageFilePath(){
        return imgFilePath = this.getFilesDir() + "/" + imgFileName;
    }
}
