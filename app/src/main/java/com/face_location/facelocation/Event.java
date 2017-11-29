package com.face_location.facelocation;

/**
 * Created by admin on 29.11.17.
 */

public class Event {
    String name, abour, status, userQuantity;

    public Event(String name, String abour, String status, String userQuantity) {
        this.name = name;
        this.abour = abour;
        this.status = status;
        this.userQuantity = userQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbour() {
        return abour;
    }

    public void setAbour(String abour) {
        this.abour = abour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserQuantity() {
        return userQuantity;
    }

    public void setUserQuantity(String userQuantity) {
        this.userQuantity = userQuantity;
    }
}
