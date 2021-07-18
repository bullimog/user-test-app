package com.bullimore.usertestapp.configuration;

import com.bullimore.usertestapp.services.UserService;
import com.bullimore.usertestapp.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerDependencies {

    @Bean
    public UserService getUserService() {
        return new UserServiceImpl();
    }

}
