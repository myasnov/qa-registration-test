package com.automationpractice.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"gender", "firstname", "lastname", "email", "password", "dateOfBirth", "addresses", "homephone", "mobilephone"})

public class User {

    private Gender gender;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Date dateOfBirth;
    private Address addresses;
    private String homephone;
    private String mobilephone;
    public enum Gender {
        FEMALE,
        MALE
    }
}
