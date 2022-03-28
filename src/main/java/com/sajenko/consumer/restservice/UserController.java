package com.sajenko.consumer.restservice;

import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    int port = 8081;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
            RestTemplate rest = new RestTemplate();
            rest.setMessageConverters(Collections.singletonList(converter));
            ResponseEntity<User[]> exchange = rest.getForEntity(
                    "http://localhost:" + port + "/users",
                    User[].class);
            return Arrays.asList(exchange.getBody());
    }

    @GetMapping("/employers/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        RestTemplate rest = new RestTemplate();
        rest.setMessageConverters(Collections.singletonList(converter));
        return rest.getForEntity(
                "http://localhost:" + port + "/users/" + id,
                User.class);
    }
}
