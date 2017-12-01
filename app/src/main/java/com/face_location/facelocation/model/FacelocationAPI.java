package com.face_location.facelocation.model;

import com.face_location.facelocation.model.Location.LocationBody;
import com.face_location.facelocation.model.Location.LocationResponse;
import com.face_location.facelocation.model.Registration.RegistrationBody;
import com.face_location.facelocation.model.Registration.RegistrationResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

/**
 * Created by admin on 26.11.17.
 */

public interface FacelocationAPI {

    //Registration
    @POST("api/auth/register")
    Call<RegistrationResponse> registerUser(
            @HeaderMap Map<String, String> headers,
            @Body RegistrationBody body
    );

    //Login
    @POST("api/auth/login")
    Call<RegistrationResponse> loginUser(
            @HeaderMap Map<String, String> headers,
            @Body RegistrationBody body
    );


    //New Location
    @POST("api/locations")
    Call<LocationResponse> addLocation(
            @HeaderMap Map<String, String> headers,
            @Body LocationBody body
    );
}
