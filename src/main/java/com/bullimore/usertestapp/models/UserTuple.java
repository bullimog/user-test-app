package com.bullimore.usertestapp.models;

public class UserTuple {
    User[] targetUsers;
    User[] allUsers;

    public UserTuple(User[] targetUsers, User[] allUsers) {
        this.targetUsers = targetUsers;
        this.allUsers = allUsers;
    }

    public User[] getTargetUsers() {
        return targetUsers;
    }

    public User[] getAllUsers() {
        return allUsers;
    }
}
