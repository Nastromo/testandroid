package com.face_location.facelocation.model.MyProfile;

/**
 * Created by admin on 04.12.17.
 */

public class ProfileBody {

    String userEmail, userName, userSoname, userNumber, userJob;

    public ProfileBody(String userEmail, String userName, String userSoname, String userNumber, String userJob) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userSoname = userSoname;
        this.userNumber = userNumber;
        this.userJob = userJob;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSoname() {
        return userSoname;
    }

    public void setUserSoname(String userSoname) {
        this.userSoname = userSoname;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserJob() {
        return userJob;
    }

    public void setUserJob(String userJob) {
        this.userJob = userJob;
    }
}
