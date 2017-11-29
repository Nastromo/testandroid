package com.face_location.facelocation.model;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

/**
 * Created by admin on 26.11.17.
 */

public interface FacelocationAPI {


    @POST("api/auth/register")
    Call<ResponseBody> registerUser(
            @HeaderMap Map<String, String> headers,
            @Body RegistrationBody body
    );
}
