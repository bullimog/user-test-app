package com.bullimore.usertestapp.controllers;

import com.bullimore.usertestapp.models.User;
import com.bullimore.usertestapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ArrayList<User> getAllTargetUsers()
    {
        return userService.getUsers();
    }
}
