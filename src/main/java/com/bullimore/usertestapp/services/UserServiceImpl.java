package com.bullimore.usertestapp.services;

import com.bullimore.usertestapp.connectors.UserConnector;
import com.bullimore.usertestapp.models.User;
import com.bullimore.usertestapp.models.UserTuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

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

    private static final long TIMEOUT_SECONDS = 10;

    @Override
    public ArrayList<User> getUsers() {
        Mono<User[]> targetUsersMono = userConnector.getTargetUsers().subscribeOn(Schedulers.boundedElastic());
        Mono<User[]> allUsersMono = userConnector.getAllUsers().subscribeOn(Schedulers.boundedElastic());
        Mono<UserTuple> monoUserTuple = Mono.zip(targetUsersMono, allUsersMono, UserTuple::new);

        UserTuple userTuple = monoUserTuple.block(Duration.ofSeconds(TIMEOUT_SECONDS));
        ArrayList<User> targetUsers = new ArrayList<>();
        ArrayList<User> allUsers = new ArrayList<>();
        if (userTuple != null) {
            Collections.addAll(targetUsers, userTuple.getTargetUsers());
            Collections.addAll(allUsers, userTuple.getAllUsers());
        }
        targetUsers.addAll(filterUsers(allUsers));
        return targetUsers;
    }

    @Override
    public ArrayList<User> filterUsers(ArrayList<User> users){
        Predicate<User> filter = user -> (
                proximityService.lonLatDifference(targetLatitude, targetLongitude,
                user.getLatitude(), user.getLongitude()) >= targetTolerance
                );
        users.removeIf(filter);
        return users;
    }
}
