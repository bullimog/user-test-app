package com.bullimore.usertestapp.services;

import com.bullimore.usertestapp.connectors.UserConnector;
import com.bullimore.usertestapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    UserConnector userConnector;

    @Autowired
    ProximityService proximityService;

    @Value("${target.longitude}")
    private Double targetLongitude;
    @Value("${target.latitude}")
    private Double targetLatitude;
    @Value("${target.tolerance.miles}")
    private int targetTolerance;

    @Override
    public ArrayList<User> getUsers() {
        ArrayList<User> targetUsers = userConnector.getTargetUsers();
        ArrayList<User> allUsers = userConnector.getAllUsers();
        targetUsers.addAll(filterUsers(allUsers));
        return targetUsers;
    }

    @Override
    public ArrayList<User> filterUsers(ArrayList<User> users){
        ArrayList<User> keep = new ArrayList<User>();
        users.forEach(user -> {
            Float distance = proximityService.lonLatDifference(targetLatitude, targetLongitude,
                    user.getLatitude(), user.getLongitude());
            if(distance<targetTolerance){
                keep.add(user);
            }
        });

        return keep;
    }
}
