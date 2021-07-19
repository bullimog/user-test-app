package com.bullimore.usertestapp.controllers;

import com.bullimore.usertestapp.models.User;
import com.bullimore.usertestapp.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@Api(value = "users",produces="An Array of Users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ApiOperation(value = "GETs user data from bpdts API", notes = "Calls the API at https://bpdts-test-app.herokuapp.com/, and returns people who are listed as either living in London, or whose current coordinates are within 50 miles of London.", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error - dependent API unavailable", response = String.class),
            @ApiResponse(code = 200, message = "Successful/OK", response = User.class)}
    )
    public ArrayList<User> getAllTargetUsers()
    {
        return userService.getUsers();
    }
}
