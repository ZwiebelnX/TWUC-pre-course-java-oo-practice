package com.twu.model;

import com.twu.model.type.UserType;

public class User {
    private String name;

    private String password;

    private UserType userType;

    private int voteTicket = 10;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public int getVoteTicket() {
        return voteTicket;
    }

    public void setVoteTicket(int voteTicket) {
        this.voteTicket = voteTicket;
    }
}
