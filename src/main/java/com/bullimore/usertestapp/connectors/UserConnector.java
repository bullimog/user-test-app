package com.bullimore.usertestapp.connectors;

import com.bullimore.usertestapp.models.User;
import reactor.core.publisher.Mono;

public interface UserConnector {
    Mono<User[]> getAllUsers();
    Mono<User[]> getTargetUsers();
}
