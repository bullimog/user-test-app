package com.bullimore.usertestapp.connectors;

import com.bullimore.usertestapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class UserConnectorImpl implements UserConnector {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${allusers.url}")
    private String allUsersUrl;

    @Value("${cityusers.url}")
    private String cityUsersUrl;

    @Override
    public ArrayList<User> getAllUsers(){
        User[] userArray = webClientBuilder.build()
                .get()
                .uri(allUsersUrl)
                .retrieve()
                .bodyToMono(User[].class)
                .block();

        ArrayList<User> users = new ArrayList<>();
        if(userArray!= null) {
            Collections.addAll(users, userArray);
        }
        return  users;
    }

    @Override
    public ArrayList<User> getTargetUsers(){
        User[] userArray = webClientBuilder.build()
                .get()
                .uri(cityUsersUrl)
                .retrieve()
                .bodyToMono(User[].class)
                .block();

        ArrayList<User> users = new ArrayList<>();
        if(userArray!= null) {
            Collections.addAll(users, userArray);
        }
        return  users;

    }
}
