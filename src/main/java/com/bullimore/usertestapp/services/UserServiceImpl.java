package com.bullimore.usertestapp.services;

import com.bullimore.usertestapp.connectors.UserConnector;
import com.bullimore.usertestapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class UserServiceImpl implements UserService{

    @Autowired
    UserConnector userDataConnector;

    @Autowired
    ProximityService proximityService;

    @Override
    public ArrayList<User> getUsers() {
        return new ArrayList<User>();
    }

    @Override
    public ArrayList<User> filterUsers(ArrayList<User> users){
        return users;
    }


}
