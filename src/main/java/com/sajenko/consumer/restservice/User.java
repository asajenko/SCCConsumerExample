package com.sajenko.consumer.restservice;

public class User {

    private Long id;
    private String firstName;

    public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.firstName = name;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
}
