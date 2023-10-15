package com.invisibleprogrammer.invisibletirapi.application.response;

public class UserResponse {

    private String email;
    private String fullName;
    private String bio;
    private String apiKey;
    private String role;

    public UserResponse() {
    }

    public UserResponse(String email, String role, String apiKey) {
        this.email = email;
        this.role = role;
        this.apiKey = apiKey;
    }

    public UserResponse(String email, String fullName, String bio, String apiKey, String role) {
        this.email = email;
        this.fullName = fullName;
        this.bio = bio;
        this.apiKey = apiKey;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
