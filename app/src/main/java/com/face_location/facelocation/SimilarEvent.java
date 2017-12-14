package com.face_location.facelocation;

/**
 * Created by admin on 14.12.17.
 */

public class SimilarEvent {

    private String eventTitle;
    private String eventDate;
    private String passType;
    private String userQuantity;

    private String mainPicURL;
    private String userPicURL;
    private String userPicSecondURL;
    private String userPicThirdURL;

    public SimilarEvent(String eventTitle, String eventDate) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
    }

    public SimilarEvent(String eventTitle, String eventDate, String passType, String userQuantity, String mainPicURL, String userPicURL, String userPicSecondURL, String userPicThirdURL) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.passType = passType;
        this.userQuantity = userQuantity;
        this.mainPicURL = mainPicURL;
        this.userPicURL = userPicURL;
        this.userPicSecondURL = userPicSecondURL;
        this.userPicThirdURL = userPicThirdURL;
    }

    public SimilarEvent(String eventTitle, String eventDate, String passType, String userQuantity, String mainPicURL, String userPicURL, String userPicSecondURL) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.passType = passType;
        this.userQuantity = userQuantity;
        this.mainPicURL = mainPicURL;
        this.userPicURL = userPicURL;
        this.userPicSecondURL = userPicSecondURL;
    }

    public SimilarEvent(String eventTitle, String eventDate, String passType, String userQuantity, String mainPicURL, String userPicURL) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.passType = passType;
        this.userQuantity = userQuantity;
        this.mainPicURL = mainPicURL;
        this.userPicURL = userPicURL;
    }

    public SimilarEvent(String eventTitle, String eventDate, String passType, String userQuantity, String mainPicURL) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.passType = passType;
        this.userQuantity = userQuantity;
        this.mainPicURL = mainPicURL;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getPassType() {
        return passType;
    }

    public void setPassType(String passType) {
        this.passType = passType;
    }

    public String getUserQuantity() {
        return userQuantity;
    }

    public void setUserQuantity(String userQuantity) {
        this.userQuantity = userQuantity;
    }

    public String getMainPicURL() {
        return mainPicURL;
    }

    public void setMainPicURL(String mainPicURL) {
        this.mainPicURL = mainPicURL;
    }

    public String getUserPicURL() {
        return userPicURL;
    }

    public void setUserPicURL(String userPicURL) {
        this.userPicURL = userPicURL;
    }

    public String getUserPicSecondURL() {
        return userPicSecondURL;
    }

    public void setUserPicSecondURL(String userPicSecondURL) {
        this.userPicSecondURL = userPicSecondURL;
    }

    public String getUserPicThirdURL() {
        return userPicThirdURL;
    }

    public void setUserPicThirdURL(String userPicThirdURL) {
        this.userPicThirdURL = userPicThirdURL;
    }
}
