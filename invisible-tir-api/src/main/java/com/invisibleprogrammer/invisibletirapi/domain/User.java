package com.invisibleprogrammer.invisibletirapi.domain;

import jakarta.persistence.*;

@Entity(name = "tir_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    private String emailAddress;
    private String fullName;
    private String bio;
    private String passwordHash;
    private String apiKey;

    public User(String emailAddress, String password, String apiKey) {
        this.emailAddress = emailAddress;
        this.passwordHash = password;
        this.apiKey = apiKey;
    }

    public User(int id, String emailAddress, String fullName, String bio, String passwordHash, String apiKey) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.fullName = fullName;
        this.bio = bio;
        this.passwordHash = passwordHash;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
