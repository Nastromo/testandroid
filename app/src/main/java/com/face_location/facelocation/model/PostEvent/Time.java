package com.face_location.facelocation.model.PostEvent;

/**
 * Created by admin on 20.12.17.
 */

public class Time {
    String start, end;

    public Time(String start, String end) {
        this.start = start;
        this.end = end;
    }

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
