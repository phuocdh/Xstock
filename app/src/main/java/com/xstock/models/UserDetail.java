package com.xstock.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserDetail extends RealmObject {

    @PrimaryKey
    String email;
    String password;
    String username;
    String firstname;
    String lastname;
    String sexid;
    int is_active;
    int groupID;

    public UserDetail(String username, String firstname, String lastname, String sexid, int is_active, int groupID) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.sexid = sexid;
        this.is_active = is_active;
        this.groupID = groupID;
    }

    public UserDetail() {

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSexid() {
        return sexid;
    }

    public void setSexid(String sexid) {
        this.sexid = sexid;
    }

    public int getIsActive() {
        return is_active;
    }

    public void setIsActive(int is_active) {
        this.is_active = is_active;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
}
