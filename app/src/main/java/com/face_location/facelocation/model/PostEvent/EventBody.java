package com.face_location.facelocation.model.PostEvent;

import java.util.List;

/**
 * Created by admin on 19.12.17.
 */

public class EventBody {
    String title, text, start, end, type;
    boolean isprivate;
    int seats, frequency;
    List<Schedules> schedules;
    String[] locations;

    public EventBody(String title, String text, String start, String end, String type, boolean isprivate, int seats, int frequency, List<Schedules> schedules, String[] locations) {
        this.title = title;
        this.text = text;
        this.start = start;
        this.end = end;
        this.type = type;
        this.isprivate = isprivate;
        this.seats = seats;
        this.frequency = frequency;
        this.schedules = schedules;
        this.locations = locations;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIsprivate() {
        return isprivate;
    }

    public void setIsprivate(boolean isprivate) {
        this.isprivate = isprivate;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public List<Schedules> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedules> schedules) {
        this.schedules = schedules;
    }

    public String[] getLocations() {
        return locations;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }
}
