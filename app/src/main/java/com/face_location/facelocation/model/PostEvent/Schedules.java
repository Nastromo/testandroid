package com.face_location.facelocation.model.PostEvent;

import java.util.List;

/**
 * Created by admin on 19.12.17.
 */

public class Schedules {
    String day;
    List<Timetable> timetable;

    public Schedules(String day, List<Timetable> timetable) {
        this.day = day;
        this.timetable = timetable;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Timetable> getTimetable() {
        return timetable;
    }

    public void setTimetable(List<Timetable> timetable) {
        this.timetable = timetable;
    }
}
