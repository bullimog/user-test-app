package com.bullimore.usertestapp.services;

import com.bullimore.usertestapp.connectors.UserConnector;
import com.bullimore.usertestapp.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTests {

@InjectMocks
UserService userService = new UserServiceImpl();

@Mock
ProximityService proximityService;

@Mock
UserConnector userConnector;

@BeforeEach
public void initialiseConfig(){
    ReflectionTestUtils.setField(userService,"targetTolerance",50);
    ReflectionTestUtils.setField(userService,"targetLatitude",51.51271919);
    ReflectionTestUtils.setField(userService,"targetLongitude",-0.09075364);
}


    User londonUser1 = new User.Builder().
            id(1).first_name("dave").last_name("smith").
            email("this@there.com").ip_address("192.168.1.1").
            latitude(1.0d).longitude(1.0d).build();
    User londonUser2 = new User.Builder().
            id(1).first_name("janet").last_name("brown").
            email("this@there.co.uk").ip_address("192.168.1.2").
            latitude(1.0d).longitude(1.0d).build();
    User londonUser3 = new User.Builder().
            id(1).first_name("sandra").last_name("green").
            email("this@home.co.uk").ip_address("192.168.1.3").
            latitude(1.0d).longitude(1.0d).build();
    User longbentonLatLonUser = new User.Builder().
            id(1).first_name("brian").last_name("jones").
            email("another@there.com").ip_address("192.168.1.4").
            latitude(55.010025d).longitude(-1.588431d).build();
    User newcastleLatLonUser = new User.Builder().
            id(1).first_name("george").last_name("white").
            email("george@there.com").ip_address("192.168.1.5").
            latitude(54.988757d).longitude(-1.628316d).build();
    User northamptonLatLonUser = new User.Builder().
            id(1).first_name("simon").last_name("south").
            email("simon@google.com").ip_address("192.168.1.6").
            latitude(52.237508d).longitude(-0.918168d).build();
    User wembleyLatLonUser = new User.Builder().
            id(1).first_name("barry").last_name("barker").
            email("barry@google.com").ip_address("192.168.1.7").
            latitude(51.555398d).longitude(-0.278651d).build();
    User justOver50MilesLatLonUser = new User.Builder().
            id(1).first_name("greg").last_name("cambridge").
            email("greg@google.com").ip_address("192.168.1.8").
            latitude(52.24d).longitude(-0.090753d).build();
    User justUnder50MilesLatLonUser = new User.Builder().
            id(1).first_name("bob").last_name("hall").
            email("bob@google.com").ip_address("192.168.1.9").
            latitude(52.225d).longitude(-0.090753d).build();

    @Test
    public void getOnlyLondonAndCloseToLondonUsersTest() {
        User[] londonUsers = {londonUser1, londonUser2, londonUser3};
        Mono<User[]> mono1 = Mono.just(londonUsers);
        Mockito.when(userConnector.getTargetUsers()).thenReturn(mono1);

        User[] allUsers = {longbentonLatLonUser, newcastleLatLonUser, northamptonLatLonUser,
                           wembleyLatLonUser,justOver50MilesLatLonUser,justUnder50MilesLatLonUser};
        Mono<User[]> mono2 = Mono.just(allUsers);
        Mockito.when(userConnector.getAllUsers()).thenReturn(mono2);

        when(proximityService.lonLatDifference(any(),any(),eq(55.010025d), any())).thenReturn(270.0f);
        when(proximityService.lonLatDifference(any(),any(),eq(54.988757d), any())).thenReturn(275.0f);
        when(proximityService.lonLatDifference(any(),any(),eq(52.237508d), any())).thenReturn(100.0f);
        when(proximityService.lonLatDifference(any(),any(),eq(51.555398d), any())).thenReturn(7.0f);
        when(proximityService.lonLatDifference(any(),any(),eq(52.24d), any())).thenReturn(51.0f);
        when(proximityService.lonLatDifference(any(),any(),eq(52.225d), any())).thenReturn(49.0f);


        ArrayList<User> filteredUsers = userService.getUsers();
        Assertions.assertEquals(filteredUsers.size(), 5);
        Assertions.assertTrue(filteredUsers.contains(londonUser1));
        Assertions.assertTrue(filteredUsers.contains(londonUser2));
        Assertions.assertTrue(filteredUsers.contains(londonUser3));
        Assertions.assertTrue(filteredUsers.contains(wembleyLatLonUser));
        Assertions.assertTrue(filteredUsers.contains(justUnder50MilesLatLonUser));
    }

    @Test
    public void filterUsersBasedOnLatLonTest() {
        when(proximityService.lonLatDifference(any(),any(),eq(52.24d), any())).thenReturn(51.0f);
        when(proximityService.lonLatDifference(any(),any(),eq(52.225d),any())).thenReturn(49.0f);
        ArrayList<User> users = new ArrayList<User>();
        users.add(justOver50MilesLatLonUser);
        users.add(justUnder50MilesLatLonUser);
        ArrayList<User> filteredUsers = userService.filterUsers(users);
        Assertions.assertEquals(filteredUsers.size(), 1);
        Assertions.assertTrue(filteredUsers.contains(justUnder50MilesLatLonUser));
    }
}
