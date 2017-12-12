package com.face_location.facelocation.model.Events;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Time {

    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("end")
    @Expose
    private String end;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

}
