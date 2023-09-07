package com.invisibleprogrammer.invisibletirapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "tir_user")
@Data
public class User {

    @Id
    private int id;
    private String emailAddress;
    private String password;
    private String apiKey;


    public User(String emailAddress, String password, String apiKey) {
        this.id = 1;
        this.emailAddress = emailAddress;
        this.password = password;
        this.apiKey = apiKey;
    }

    public User() {

    }
}
