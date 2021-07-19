package com.bullimore.usertestapp.connectors;

import com.bullimore.usertestapp.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.ArrayList;

@SpringBootTest
public class UserConnectorIntegrationTests {

@Autowired
UserConnector userConnector;

    @Test
    public void testIntegrationWithAllUserEndpoint() {
        String testUrl = "https://bpdts-test-app.herokuapp.com/users";
        ReflectionTestUtils.setField(userConnector,"allUsersUrl",testUrl);
        ArrayList<User> allUsers = userConnector.getAllUsers();
        Assertions.assertFalse(allUsers.isEmpty());
        Assertions.assertTrue(allUsers.get(0).getId() > 0);
        Assertions.assertTrue(allUsers.get(0).getLast_name().length() > 0);
    }

    @Test
    public void testIntegrationWithLondonUsersEndpoint() {
        String testUrl = "https://bpdts-test-app.herokuapp.com/city/London/users";
        ReflectionTestUtils.setField(userConnector,"allUsersUrl",testUrl);
        ArrayList<User> allUsers = userConnector.getAllUsers();
        Assertions.assertFalse(allUsers.isEmpty());
        Assertions.assertTrue(allUsers.get(0).getId() > 0);
    }
}