package com.invisibleprogrammer.invisibletirapi.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "tir_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    private String emailAddress;
    private String passwordHash;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ApiKey> apiKeys;

    public User(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.passwordHash = password;
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

    public List<ApiKey> getApiKeys() {
        return apiKeys;
    }

    public void setApiKeys(List<ApiKey> apiKey) {
        this.apiKeys = apiKey;
    }
}
