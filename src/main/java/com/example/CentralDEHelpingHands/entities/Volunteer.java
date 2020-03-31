package com.example.CentralDEHelpingHands.entities;

import com.example.CentralDEHelpingHands.validators.EmailValidator;
import com.example.CentralDEHelpingHands.validators.PasswordUtils;
import com.example.CentralDEHelpingHands.validators.PasswordValidator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
public class Volunteer {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String email;
    private String password;
    //private String salt;
    private String link;

    public Volunteer() {
    }

    public Volunteer(Long id, String firstName, String lastName, String phoneNum, String email, String password, String link) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.password = password;
        //this.salt = salt;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if( EmailValidator.validateEmail(email)){
                this.email = email;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(PasswordValidator.validatePassword(password)) {
            String salt = PasswordUtils.getSalt(30);
            this.password = salt + ":" + PasswordUtils.generateSecurePassword(password, salt);
        }
    }

//    public String getSalt() {
//        return salt;
//    }
//
//    public void setSalt(String salt) {
//        this.salt = salt;
//    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
