package com.sajenko.consumer.restservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(ids = {"com.sajenko:provider:+:stubs"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class UserControllerIntegrationTest {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @Disabled
    void shouldReturnAllUsers() {
        List<User> allUsers = userController.getAllUsers();

        assertEquals(2, allUsers.size());
        assertThat(allUsers.get(0).getName(), is(not(emptyOrNullString())));

    }

    @Test
    void mockMvc() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/users/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        User user = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), User.class);

        assertEquals("Adam", user.getName());

    }

}
