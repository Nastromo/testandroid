package com.face_location.facelocation.model;

/**
 * Created by admin on 26.11.17.
 */

public class RegistrationBody {
    public String email;
    public String password;

    public RegistrationBody(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
