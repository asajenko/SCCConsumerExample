package com.sajenko.consumer.restservice;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public List<User> getAllUsers() {
//        ClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
            RestTemplate rest = new RestTemplate();
            rest.setMessageConverters(Collections.singletonList(converter));
            ResponseEntity<User[]> exchange = rest.getForEntity(
                    "http://localhost:8081/users",
                    User[].class);
            return Arrays.asList(exchange.getBody());
    }
}
