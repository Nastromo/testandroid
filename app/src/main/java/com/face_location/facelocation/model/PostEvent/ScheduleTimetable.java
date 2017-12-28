package com.face_location.facelocation.model.PostEvent;

/**
 * Created by admin on 20.12.17.
 */

public class ScheduleTimetable {
    String title;
    ScheduleTime scheduleTime;

    public ScheduleTimetable(String title, ScheduleTime scheduleTime) {
        this.title = title;
        this.scheduleTime = scheduleTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ScheduleTime getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(ScheduleTime scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    @Override
    public String toString() {
        return "ScheduleTimetable{" +
                "title='" + title + '\'' +
                ", scheduleTime=" + scheduleTime +
                '}';
    }
}
