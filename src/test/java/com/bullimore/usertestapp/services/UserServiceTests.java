package com.bullimore.usertestapp.services;

import com.bullimore.usertestapp.connectors.UserConnector;
import com.bullimore.usertestapp.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTests {

@MockBean
private UserConnector userConnector;

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
            id(1).first_name("simon").last_name("south").
            email("simon@google.com").ip_address("192.168.1.7").
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
    public void getOnlyLondonAndCloseToLondonUsersTest() throws Exception {
        UserService userService = new UserServiceImpl();
        ArrayList<User> londonUsers = new ArrayList<User>();
        londonUsers.add(londonUser1);
        londonUsers.add(londonUser2);
        londonUsers.add(londonUser3);
        when(userConnector.getLondonUsers()).thenReturn(londonUsers);

        ArrayList<User> allUsers = new ArrayList<User>();
        londonUsers.add(longbentonLatLonUser);
        londonUsers.add(newcastleLatLonUser);
        londonUsers.add(northamptonLatLonUser);
        londonUsers.add(wembleyLatLonUser);
        londonUsers.add(justOver50MilesLatLonUser);
        londonUsers.add(justUnder50MilesLatLonUser);
        when(userConnector.getAllUsers()).thenReturn(allUsers);

        ArrayList<User> filteredUsers = userService.getUsers();
        Assertions.assertEquals(filteredUsers.size(), 5);
        Assertions.assertTrue(filteredUsers.contains(londonUser1));
        Assertions.assertTrue(filteredUsers.contains(londonUser2));
        Assertions.assertTrue(filteredUsers.contains(londonUser3));
        Assertions.assertTrue(filteredUsers.contains(wembleyLatLonUser));
        Assertions.assertTrue(filteredUsers.contains(justUnder50MilesLatLonUser));
    }

    @Test
    public void filterUsersBasedOnLatLonTest() throws Exception {
        UserService userService = new UserServiceImpl();
        ArrayList<User> users = new ArrayList<User>();
        users.add(justOver50MilesLatLonUser);
        users.add(justUnder50MilesLatLonUser);
        when(userConnector.getAllUsers()).thenReturn(users);
        ArrayList<User> filteredUsers = userService.getUsers();
        Assertions.assertEquals(filteredUsers.size(), 1);
        Assertions.assertTrue(filteredUsers.contains(justUnder50MilesLatLonUser));
    }
}
