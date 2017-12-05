package com.face_location.facelocation.model.Location;

public class LocationBody {

    String title, longitude, latitude, text, contact;
    boolean publised;

    public LocationBody(String title, String longitude, String latitude, String text, String contact, boolean publised) {
        this.title = title;
        this.longitude = longitude;
        this.latitude = latitude;
        this.text = text;
        this.contact = contact;
        this.publised = publised;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean isPublised() {
        return publised;
    }

    public void setPublised(boolean publised) {
        this.publised = publised;
    }
}
