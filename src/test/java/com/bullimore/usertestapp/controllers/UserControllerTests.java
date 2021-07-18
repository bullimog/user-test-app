package com.bullimore.usertestapp.controllers;

import com.bullimore.usertestapp.models.User;
import com.bullimore.usertestapp.services.UserService;
import org.hamcrest.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

        @Test
        public void getUsersTest() throws Exception {
        User user1 = new User.Builder().
                id(1).
                first_name("dave").
                last_name("smith").
                email("this@there.com").
                ip_address("192.168.1.1").
                latitude(1.0d).
                longitude(1.0d).
                build();
        User user2 = new User.Builder().
                id(2).
                first_name("bob").
                last_name("brown").
                email("another@here.com").
                ip_address("192.168.1.2").
                latitude(1.1d).
                longitude(1.1d).build();
        ArrayList<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        Mockito.when(userService.getUsers()).thenReturn(users);


        mockMvc.perform(get("/users/london"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].last_name", Matchers.equalTo("smith")));
    }
}
