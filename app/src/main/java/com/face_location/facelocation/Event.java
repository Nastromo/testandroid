package com.face_location.facelocation;

/**
 * Created by admin on 29.11.17.
 */

public class Event {
    String name, about, status;
    int userQuantity;
    String[] avatars;

    public Event(String name, String about, String status, int userQuantity, String[] avatars) {
        this.name = name;
        this.about = about;
        this.status = status;
        this.userQuantity = userQuantity;
        this.avatars = avatars;
    }

    public Event(String name, String about, String status, int userQuantity) {
        this.name = name;
        this.about = about;
        this.status = status;
        this.userQuantity = userQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserQuantity() {
        return userQuantity;
    }

    public void setUserQuantity(int userQuantity) {
        this.userQuantity = userQuantity;
    }

    public String[] getAvatars() {
        return avatars;
    }

    public void setAvatars(String[] avatars) {
        this.avatars = avatars;
    }
}
