package com.bullimore.usertestapp.connectors;

import com.bullimore.usertestapp.models.User;
import java.util.ArrayList;

public class UserConnectorImpl implements UserConnector {

    @Override
    public ArrayList<User> getAllUsers(){
        return new ArrayList<User>();
    }

    @Override
    public ArrayList<User> getLondonUsers(){
        return new ArrayList<User>();
    }
}
