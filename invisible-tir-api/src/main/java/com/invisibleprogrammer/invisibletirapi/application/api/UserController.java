package com.invisibleprogrammer.invisibletirapi.application.api;

import com.invisibleprogrammer.invisibletirapi.application.request.SignUpUserRequest;
import com.invisibleprogrammer.invisibletirapi.application.response.SignUpUserResponse;
import com.invisibleprogrammer.invisibletirapi.domain.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    final
    UserService usersService;

    public UserController(UserService usersService) {
        this.usersService = usersService;
    }

    @PostMapping()
    public SignUpUserResponse signUp(@RequestBody SignUpUserRequest userRequest) {

        usersService.signUp(userRequest.getEmail(), userRequest.getPassword());

        return new SignUpUserResponse(userRequest.getEmail(), "MEMBER");
    }

}
