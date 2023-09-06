package com.invisibleprogrammer.invisibletirapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "tir_user")
public class User {

    @Id
    private int id;
    private String emailAddress;
    private String password;


    public User(String emailAddress, String password) {
        this.id = 1;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public User() {
        
    }
}
