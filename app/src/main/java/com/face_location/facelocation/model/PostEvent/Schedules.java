package com.face_location.facelocation.model.PostEvent;

import java.util.List;

/**
 * Created by admin on 19.12.17.
 */

public class Schedules {
    String day;
    List<ScheduleTimetable> scheduleTimetable;

    public Schedules(String day, List<ScheduleTimetable> scheduleTimetable) {
        this.day = day;
        this.scheduleTimetable = scheduleTimetable;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<ScheduleTimetable> getScheduleTimetable() {
        return scheduleTimetable;
    }

    public void setScheduleTimetable(List<ScheduleTimetable> scheduleTimetable) {
        this.scheduleTimetable = scheduleTimetable;
    }
}
