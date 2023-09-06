package com.invisibleprogrammer.invisibletirapi.application.request;

import lombok.Data;

@Data
public class SignUpUserRequest {
    public String email;
    public String password;
}
