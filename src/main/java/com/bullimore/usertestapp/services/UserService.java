package com.bullimore.usertestapp.services;

import com.bullimore.usertestapp.models.User;

import java.util.ArrayList;

public interface UserService {
    ArrayList<User> getUsers();
    ArrayList<User> filterUsers(ArrayList<User> users);
}
