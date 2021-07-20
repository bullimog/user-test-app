package com.bullimore.usertestapp.connectors;

import com.bullimore.usertestapp.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Mono;
import java.time.Duration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
public class UserConnectorIntegrationTests {

@Autowired
UserConnector userConnector;

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
    User[] londonUsers = {londonUser1, londonUser2, londonUser3};

    User longbentonLatLonUser = new User.Builder().
            id(1).first_name("brian").last_name("jones").
            email("another@there.com").ip_address("192.168.1.4").
            latitude(55.010025d).longitude(-1.588431d).build();
    User newcastleLatLonUser = new User.Builder().
            id(7).first_name("george").last_name("white").
            email("george@there.com").ip_address("192.168.1.5").
            latitude(54.988757d).longitude(-1.628316d).build();
    User[] allUsers = {longbentonLatLonUser, newcastleLatLonUser};

    WireMockServer wireMockServer = new WireMockServer();
    @BeforeEach
    public void startMock(){
        wireMockServer.start();
    }

    @AfterEach
    public void stopMock(){
        wireMockServer.stop();
    }

    @Test
    public void testIntegrationWithAllUserTestEndpoint() {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(allUsers);
        }catch(JsonProcessingException ex){
            System.out.println("Json parse error!");
        }
        configureFor("localhost", 8080);
        stubFor(get(urlEqualTo("/users")).willReturn(aResponse().
                withFixedDelay(1000).
                withStatus(200).
                withHeader("Content-Type", "application/json").
                withBody(json)));

        String testUrl = "http://localhost:8080/users";
        ReflectionTestUtils.setField(userConnector,"allUsersUrl",testUrl);
        Mono<User[]> allUsersMono = userConnector.getAllUsers();
        User[] allUsers =  allUsersMono.block(Duration.ofSeconds(10));
        Assertions.assertEquals(allUsers.length, 2);
        Assertions.assertEquals((int) allUsers[1].getId(), 7);
        Assertions.assertEquals(allUsers[0].getLast_name(), "jones");
    }

    @Test
    public void testIntegrationWithLondonUsersTestEndpoint() {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(londonUsers);
        }catch(JsonProcessingException ex){
            System.out.println("Json parse error!");
        }
        configureFor("localhost", 8080);
        stubFor(get(urlEqualTo("/city/London/users")).willReturn(aResponse().
                withFixedDelay(1000).
                withStatus(200).
                withHeader("Content-Type", "application/json").
                withBody(json)));

        String testUrl = "http://localhost:8080/city/London/users";
        ReflectionTestUtils.setField(userConnector,"cityUsersUrl",testUrl);
        Mono<User[]> londonUsersMono = userConnector.getTargetUsers();
        User[] londonUsers = londonUsersMono.block(Duration.ofSeconds(10));
        Assertions.assertEquals(londonUsers.length, 3);
        Assertions.assertEquals((int) londonUsers[0].getId(), 1);
        Assertions.assertEquals(londonUsers[1].getFirst_name(), "janet");
    }
}