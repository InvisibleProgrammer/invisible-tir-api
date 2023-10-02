package com.invisibleprogrammer.invisibletirapi.application.response;

public class SignUpUserResponse {

    private String email;
    private String apiKey;
    private String role;

    public SignUpUserResponse() {
    }

    public SignUpUserResponse(String email, String role, String apiKey) {
        this.email = email;
        this.role = role;
        this.apiKey = apiKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
