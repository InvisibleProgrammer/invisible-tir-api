package com.invisibleprogrammer.invisibletirapi.application.response;

public class SignUpUserResponse {

    private String email;
    private String role;

    public SignUpUserResponse(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
