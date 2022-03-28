package com.sajenko.consumer.restservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureStubRunner(ids = "com.sajenko:provider:+:stubs", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class UserControllerIntegrationTest {

    @StubRunnerPort("com.sajenko:provider")
    private int port;

    @Autowired
    private UserController userController;

    @BeforeEach
    void beforeAll() {
        this.userController.port = port;
    }

    @Test
    void shouldReturnAllUsers() {
        List<User> allUsers = userController.getAllUsers();

        assertEquals(2, allUsers.size());
        assertThat(allUsers.get(0).getFirstName(), is(not(emptyOrNullString())));

    }

    @Test
    void shouldReturnUser() {
        ResponseEntity<User> user = userController.getUser(5L);

        assertEquals(HttpStatus.OK, user.getStatusCode());
        assertEquals("Adam", user.getBody().getFirstName());
    }

    @Test
    void shouldReturnNoContent() {
        ResponseEntity<User> user = userController.getUser(6L);
        assertEquals(HttpStatus.NO_CONTENT, user.getStatusCode());
    }

}
