package com.face_location.facelocation.model;

import com.face_location.facelocation.model.Events.MyEventResponse;
import com.face_location.facelocation.model.Location.LocationBody;
import com.face_location.facelocation.model.Location.LocationGetResponse;
import com.face_location.facelocation.model.Location.LocationResponse;
import com.face_location.facelocation.model.MyProfile.ProfileBody;
import com.face_location.facelocation.model.MyProfile.ProfileResponse;
import com.face_location.facelocation.model.Registration.RegistrationBody;
import com.face_location.facelocation.model.Registration.RegistrationResponse;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

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


    //New LocationForAdapter
    @POST("api/locations")
    Call<LocationResponse> addLocation(
            @HeaderMap Map<String, String> headers,
            @Body LocationBody body
    );

    //Search LocationForAdapter
    @GET("api/locations")
    Call <List<LocationGetResponse>> searchLocation(
            @HeaderMap Map<String, String> headers
    );

    //Upload User Avatar
    @Multipart
    @POST("api/profile/avatar")
    Call <ResponseBody> uploadAvatar(
            @HeaderMap Map<String, String> header,
            @Part MultipartBody.Part file
            );

    //Update My Profile
    @POST("api/profile")
    Call<ProfileResponse> updateMyProfile(
            @HeaderMap Map<String, String> headers,
            @Body ProfileBody body
    );

    //My Profile
    @GET("api/profile")
    Call <ProfileResponse> getMyProfile(
            @HeaderMap Map<String, String> headers
    );

    //My Events
    @GET("api/events")
    Call <List<MyEventResponse>> getMyEvents(
            @HeaderMap Map<String, String> headers,
            @Query("user") String userID
    );

    //My Visited Events
    @GET("api/events")
    Call <List<MyEventResponse>> getMyVisitedEvents(
            @HeaderMap Map<String, String> headers,
            @Query("subscriber") String userID
    );

    //My Locations
    @GET("api/locations")
    Call <List<LocationGetResponse>> getMyLocations(
            @HeaderMap Map<String, String> headers,
            @Query("user") String userID
    );
}
