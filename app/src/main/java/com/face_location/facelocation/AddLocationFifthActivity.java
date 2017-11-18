package com.face_location.facelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddLocationFifthActivity extends AppCompatActivity implements View.OnClickListener{

    EditText eventName;
    Button cancelEventCreation, successEventCreation;
//    final CharSequence[] items = {"Red", "Green", "Blue"}; //For AlertDialog
    Spinner spinnerEventType, spinnerEventTypezzz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location_fifth);

        eventName = (EditText) findViewById(R.id.eventName);

        cancelEventCreation = (Button) findViewById(R.id.cancelEventCreation);
        cancelEventCreation.setOnClickListener(this);

        successEventCreation = (Button) findViewById(R.id.successEventCreation);
        successEventCreation.setOnClickListener(this);

        spinnerEventType = (Spinner) findViewById(R.id.spinnerEventType);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                getResources().getStringArray(R.array.event_types));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEventType.setAdapter(myAdapter);

    }

    @Override
    public void onClick(View view) {
      switch(view.getId()){
          case R.id.cancelEventCreation:
              Intent mainActivity = new Intent(this, MainActivity.class);
              startActivity(mainActivity);
              break;
          case R.id.successEventCreation:
              //TODO implement second step event creation
              Intent secondStepEventCreation = new Intent(this, MainActivity.class);
              startActivity(secondStepEventCreation);
              break;

                //AlertDialog
//              AlertDialog.Builder builder = new AlertDialog.Builder(this);
//              builder.setTitle("Pick a color");
//              builder.setItems(items, new DialogInterface.OnClickListener() {
//                  public void onClick(DialogInterface dialog, int item) {
//                      Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
//                  }
//              });
//              AlertDialog alert = builder.create();
//              alert.show();
      }
    }
}
