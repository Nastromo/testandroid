package com.face_location.facelocation.model.NewPostEvent;

/**
 * Created by admin on 19.12.17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address_ {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("marker")
    @Expose
    private Marker_ marker;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Marker_ getMarker() {
        return marker;
    }

    public void setMarker(Marker_ marker) {
        this.marker = marker;
    }

}
