package com.invisibleprogrammer.invisibletirapi.users;

import lombok.Data;

@Data
public class SignUpUserRequest {
    public String email;
    public String password;
}
