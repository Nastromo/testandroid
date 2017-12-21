package com.face_location.facelocation.model.PostEvent;

/**
 * Created by admin on 20.12.17.
 */

public class Timetable {
    String title;
    Time time;

    public Timetable(String title, Time time) {
        this.title = title;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
