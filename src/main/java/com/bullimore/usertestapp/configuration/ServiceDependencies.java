package com.bullimore.usertestapp.configuration;

import com.bullimore.usertestapp.connectors.UserConnector;
import com.bullimore.usertestapp.connectors.UserConnectorImpl;
import com.bullimore.usertestapp.services.ProximityService;
import com.bullimore.usertestapp.services.ProximityServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceDependencies {

    @Bean
    public UserConnector getUserConnector() {
        return new UserConnectorImpl();
    }

    @Bean
    public ProximityService getProximityService() {
        return new ProximityServiceImpl();
    }
}
