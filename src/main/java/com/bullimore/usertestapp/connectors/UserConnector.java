package com.bullimore.usertestapp.connectors;

import com.bullimore.usertestapp.models.User;
import java.util.ArrayList;

public interface UserConnector {
    ArrayList<User> getAllUsers();
    ArrayList<User> getTargetUsers();
}
