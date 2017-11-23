package com.face_location.facelocation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;

public class SupportActivity extends AppCompatActivity implements View.OnClickListener{

    EditText newLocationTitle, problemTextEdit;
    TextView buttonBackView;
    Button sendIssue;
    String url, title, problem;
    private static final String ISSUE = "/api/issue";
    private static final String ISSUE_TITLE = "title";
    private static final String ISSUE_PROBLEM = "problem";
    private static final String TAG = "newIssue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        buttonBackView = (TextView) findViewById(R.id.buttonBackView);
        buttonBackView.setOnClickListener(this);

        newLocationTitle = (EditText) findViewById(R.id.newLocationTitle);
        newLocationTitle.setOnClickListener(this);

        problemTextEdit = (EditText) findViewById(R.id.problemTextEdit);
        problemTextEdit.setOnClickListener(this);

        sendIssue = (Button) findViewById(R.id.sendIssue);
        sendIssue.setOnClickListener(this);

        url = getResources().getString(R.string.base_url);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonBackView:
                onBackPressed();
                break;

            case R.id.sendIssue:
                sendIssue();
                break;
        }
    }

    public void sendIssue(){
        title = newLocationTitle.getText().toString().trim();
        problem = problemTextEdit.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url + ISSUE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Все прошло хорошо!", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, error.toString());
            }
        }){
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();

                Log.i(TAG, "Тема вопроса: " + title);
                Log.i(TAG, "Текст проблемы: " + problem);

                params.put(ISSUE_TITLE, title);
                params.put(ISSUE_PROBLEM, problem);

                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}

