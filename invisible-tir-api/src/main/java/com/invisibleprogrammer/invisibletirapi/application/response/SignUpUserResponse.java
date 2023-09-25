package com.invisibleprogrammer.invisibletirapi.application.response;

public class SignUpUserResponse {

    private String email;
    private String apiKey;
    private String role;

    public SignUpUserResponse(String email, String role, String apiKey) {
        this.email = email;
        this.role = role;
        this.apiKey = apiKey;
    }

    public String getEmail() {
        return email;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getRole() {
        return role;
    }
}
