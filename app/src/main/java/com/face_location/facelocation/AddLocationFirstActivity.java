package com.face_location.facelocation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddLocationFirstActivity extends AppCompatActivity implements View.OnClickListener {

    TextView backButtonTextView, forwardButtonTextView;
    public static final String LOCATION_TITLE = "title";
    public static final String FILE_LOCATION_DETAILS = "Location details";
    EditText newLocationTitle;
    String locationID;
    public static final String TAG = "AddLocationFirst";

    //TODO проверить чтобы все ключи в файле FILE_EVENT_DETAILS и FILE_LOCATION_DETAILS были уникальными

    //Uncomment these variables if photo choose needed
//    public static final int PICK_IMAGE = 1;
//    static String imgFileName;
//    static String imgFilePath;
//    static byte[] imgBytes;
//    Bitmap locationImageBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location_first);

        locationID = getIntent().getStringExtra("id");
        Log.i(TAG, "ID ВЫБРАННОЙ ЛОКАЦИИ: " + locationID);

        backButtonTextView = (TextView) findViewById(R.id.buttonBackView);
        backButtonTextView.setOnClickListener(this);

        forwardButtonTextView = (TextView) findViewById(R.id.forwardButtonTextView);
        forwardButtonTextView.setOnClickListener(this);

        //Uncomment these code if photo choose needed
//        buttonChosePhoto= (Button) findViewById(R.id.buttonChosePhoto);
//        buttonChosePhoto.setOnClickListener(this);

        newLocationTitle = (EditText) findViewById(R.id.newLocationTitle);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.buttonBackView:
                onBackPressed();
                break;

            case R.id.forwardButtonTextView:
                String locationTitle = newLocationTitle.getText().toString();

                //Save LocationForAdapter name to shared preferences file
                SharedPreferences sharedPref = getSharedPreferences(FILE_LOCATION_DETAILS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(LOCATION_TITLE, locationTitle);
                editor.commit();

                Intent secondStep = new Intent(this, AddLocationSecondActivity.class);
                secondStep.putExtra("id", locationID);
                startActivity(secondStep);
                break;

            //Uncomment these code if photo choose needed
//            case R.id.buttonChosePhoto:
//                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                getIntent.setType("image/*");
//                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
//                startActivityForResult(chooserIntent, PICK_IMAGE);
        }
    }

    //Uncomment these code if photo choose needed
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null){
//            Uri returnUri = data.getData();
//            Cursor returnCursor =
//                    getContentResolver().query(returnUri, null, null, null, null);
//
//            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
//            returnCursor.moveToFirst();
//
//            imgFileName = returnCursor.getString(nameIndex);
//
//            String upToNCharacters = imgFileName.substring(0, Math.min(imgFileName.length(), 20));
//            buttonChosePhoto.setText(upToNCharacters + "...");
//
//            //Get Bitmap from gallery's chosen photo
//            try {
//                locationImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), returnUri);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            saveImgToInternalStorage();
//            getImageFilePath();
//            returnCursor.close();
//        }
//    }

      //Convert image to String (these code don't require to choose photo)
//    private String imgToString (Bitmap bitmap){
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100, baos);
//
//        imgByteszz = baos.toByteArray();
//        encodedImage = Base64.encodeToString(imgByteszz, Base64.DEFAULT);
//        return encodedImage;
//    }


    //Uncomment these code if photo choose needed
//    private void saveImgToInternalStorage(){
//        FileOutputStream fos = null;
//
//        //Get byte array from photo
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        locationImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        imgBytes = baos.toByteArray();
//
//        //Write to Internal Storage
//        try {
//            fos = openFileOutput(imgFileName, Context.MODE_PRIVATE);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            fos.write(imgBytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    private String getImageFilePath(){
//        return imgFilePath = this.getFilesDir() + "/" + imgFileName;
//    }
}
