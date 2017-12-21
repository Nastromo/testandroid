package com.face_location.facelocation.model.NewPostEvent;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventBody {

    private String title;

    private String text;

    private Time time;

    private Integer seats;

    private Boolean published;

    private Boolean _private;

    private List<Schedule> schedules = null;

    private List<Location_> locations = null;

    public EventBody(String title, String text, Time time, Integer seats, Boolean published, Boolean _private, List<Schedule> schedules, List<Location_> locations) {
        this.title = title;
        this.text = text;
        this.time = time;
        this.seats = seats;
        this.published = published;
        this._private = _private;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean get_private() {
        return _private;
    }

    public void set_private(Boolean _private) {
        this._private = _private;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<Location_> getLocations() {
        return locations;
    }

    public void setLocations(List<Location_> locations) {
        this.locations = locations;
    }
}
