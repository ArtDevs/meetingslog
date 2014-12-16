package org.artdevs.meetingslog.model;

import java.util.Date;

/**
 * Created by Slava on 10.12.2014.
 */
public class User {
    private Integer id;
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String email;
    private String comment;
    private Date tmLastLogin;
    private Date tmRegistered;

    public User(){}

    public User(Integer id,
                String login,
                String password,
                String firstName,
                String secondName,
                String email,
                String comment,
                Date tmLastLogin,
                Date tmRegistered) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.comment = comment;
        this.tmLastLogin = tmLastLogin;
        this.tmRegistered = tmRegistered;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTmLastLogin() {
        return tmLastLogin;
    }

    public void setTmLastLogin(Date tmLastLogin) {
        this.tmLastLogin = tmLastLogin;
    }

    public Date getTmRegistered() {
        return tmRegistered;
    }

    public void setTmRegistered(Date tmRegistered) {
        this.tmRegistered = tmRegistered;
    }

    @Override
    public String toString() {
        return login + " (" + firstName + " " + secondName +")";
    }
}
