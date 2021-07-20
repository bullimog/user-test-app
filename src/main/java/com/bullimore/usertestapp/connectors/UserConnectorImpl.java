package com.bullimore.usertestapp.connectors;

import com.bullimore.usertestapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserConnectorImpl implements UserConnector {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${allusers.url}")
    private String allUsersUrl;

    @Value("${cityusers.url}")
    private String cityUsersUrl;

    @Override
    public Mono<User[]> getAllUsers(){
        return webClientBuilder.build()
                .get()
                .uri(allUsersUrl)
                .retrieve()
                .bodyToMono(User[].class);
    }

    @Override
    public Mono<User[]> getTargetUsers(){
        return webClientBuilder.build()
                .get()
                .uri(cityUsersUrl)
                .retrieve()
                .bodyToMono(User[].class);
    }
}
