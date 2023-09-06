package com.invisibleprogrammer.invisibletirapi.application.response;

import lombok.Data;

@Data
public class SignUpUserResponse {

    private String email;
    private String role;

    public SignUpUserResponse(String email, String role) {
        this.email = email;
        this.role = role;
    }
}
