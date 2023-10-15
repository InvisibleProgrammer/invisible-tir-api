package com.invisibleprogrammer.invisibletirapi.domain;

import jakarta.persistence.*;

@Entity(name = "tir_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    private String emailAddress;
    private String passwordHash;
    private String apiKey;

    public User(String emailAddress, String password, String apiKey) {
        this.emailAddress = emailAddress;
        this.passwordHash = password;
        this.apiKey = apiKey;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = password;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
