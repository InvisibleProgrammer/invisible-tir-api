package com.invisibleprogrammer.invisibletirapi.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @PostMapping()
    public SignUpUserResponse signUp(@RequestBody SignUpUserRequest userRequest) {

        usersService.signUp(userRequest.getEmail(), userRequest.getPassword());

        return new SignUpUserResponse(userRequest.getEmail(), "MEMBER");
    }
}
