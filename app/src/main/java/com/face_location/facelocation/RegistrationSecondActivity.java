package com.face_location.facelocation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.face_location.facelocation.model.FacelocationAPI;
import com.face_location.facelocation.model.RegistrationBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationSecondActivity extends AppCompatActivity implements View.OnClickListener{

    Button backLoginButton, signupButton;
    EditText userPassForLogining;
    String userPass;
    TextView enterPassTextView;

    private static final String REG = "api/auth/register";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String TAG = "Registration";
    String url;
//    HurlStack hurlStack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_second);

        backLoginButton = (Button) findViewById(R.id.backLoginButton);
        backLoginButton.setOnClickListener(this);

        signupButton = (Button) findViewById(R.id.signupButton);
        signupButton.setOnClickListener(this);

        userPassForLogining = (EditText) findViewById(R.id.userPassForLogining);

        enterPassTextView = (TextView) findViewById(R.id.enterPassTextView);

        url = getResources().getString(R.string.base_url);

//        hurlStack = new HurlStack() {
//            @Override
//            protected HttpURLConnection createConnection(URL url) throws IOException {
//                Log.i(TAG, "Сработал метод");
//                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) super.createConnection(url);
//                try {
//                    httpsURLConnection.setSSLSocketFactory(getSSLSocketFactory());
//                    httpsURLConnection.setHostnameVerifier(getHostnameVerifier());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return httpsURLConnection;
//            }
//        };


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backLoginButton:
                onBackPressed();
                break;

            case R.id.signupButton:
                getUserPass();
                if(TextUtils.isEmpty(userPass)){
                    showPassRequires();
                    break;
                } else {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();


                    FacelocationAPI api = retrofit.create(FacelocationAPI.class);

                    RegistrationBody reg = new RegistrationBody("somenetttttttwemail@gmail.com", "somepass");


                    HashMap<String, String> headerMap = new HashMap<String, String>();
                    headerMap.put("Content-Type", "application/json");

                    Call<ResponseBody> call = api.registerUser(headerMap, reg);

                    Log.i(TAG, "Сработало до сих пор");

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Toast.makeText(RegistrationSecondActivity.this, "Все прошло хорошо",Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onResponse: " + response.toString());
                            Log.i(TAG, "onResponse: " + call.toString());

                            try{
                                String json = response.body().toString();
                                Log.d(TAG, "onResponse: json: " + json);
                                JSONObject data = null;
                                data = new JSONObject(json);
                            Log.d(TAG, "onResponse: data: " + data.optString("json"));

                            }catch (JSONException e){
                                Log.e(TAG, "onResponse: JSONException: " + e.getMessage() );
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });

//                    userRegistration();
                }
                break;
        }
    }

    public void showPassRequires(){
        enterPassTextView.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    private String getUserPass(){
        return userPass = userPassForLogining.getText().toString();
    }



//    private void userRegistration(){
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url + REG, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(), "Все прошло хорошо!", Toast.LENGTH_SHORT).show();
//
////                Intent mainActivity = new Intent(this, MainActivity.class);
////                startActivity(mainActivity);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
//                String errorDisplay = error.toString();
//                Log.i(TAG, "Ошибка: " + errorDisplay);
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put(EMAIL, RegistrationFirstActivity.userEmail);
//                params.put(PASSWORD, userPass);
//                return params;
//            }
//        };
//        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
//        final RequestQueue requestQueue = Volley.newRequestQueue(this, hurlStack);
//        requestQueue.add(stringRequest);
//    }


//    private HostnameVerifier getHostnameVerifier() {
//        return new HostnameVerifier() {
//            @Override
//            public boolean verify(String hostname, SSLSession session) {
//                //return true; // verify always returns true, which could cause insecure network traffic due to trusting TLS/SSL server certificates for wrong hostnames
//                HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
//                return hv.verify("face-location.com", session);
//            }
//        };
//    }

//    private TrustManager[] getWrappedTrustManagers(TrustManager[] trustManagers) {
//        final X509TrustManager originalTrustManager = (X509TrustManager) trustManagers[0];
//        return new TrustManager[]{
//                new X509TrustManager() {
//                    public X509Certificate[] getAcceptedIssuers() {
//                        return originalTrustManager.getAcceptedIssuers();
//                    }
//
//                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
//                        try {
//                            if (certs != null && certs.length > 0) {
//                                certs[0].checkValidity();
//                            } else {
//                                originalTrustManager.checkClientTrusted(certs, authType);
//                            }
//                        } catch (CertificateException e) {
//                            Log.w("checkClientTrusted", e.toString());
//                        }
//                    }
//
//                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
//                        try {
//                            if (certs != null && certs.length > 0) {
//                                certs[0].checkValidity();
//                            } else {
//                                originalTrustManager.checkServerTrusted(certs, authType);
//                            }
//                        } catch (CertificateException e) {
//                            Log.w("checkServerTrusted", e.toString());
//                        }
//                    }
//                }
//        };
//    }

//    private SSLSocketFactory getSSLSocketFactory()
//            throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, KeyManagementException {
//        CertificateFactory cf = CertificateFactory.getInstance("X.509");
//        InputStream caInput = getResources().openRawResource(R.raw.my_server_cert); // this cert file stored in \app\src\main\res\raw folder path
//
//        Certificate ca = cf.generateCertificate(caInput);
//        caInput.close();
//
//        KeyStore keyStore = KeyStore.getInstance("BKS");
//        keyStore.load(null, null);
//        keyStore.setCertificateEntry("ca", ca);
//
//        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
//        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
//        tmf.init(keyStore);
//
//        TrustManager[] wrappedTrustManagers = getWrappedTrustManagers(tmf.getTrustManagers());
//
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(null, wrappedTrustManagers, null);
//
//        return sslContext.getSocketFactory();
//    }
}
