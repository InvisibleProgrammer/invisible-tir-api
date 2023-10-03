package com.invisibleprogrammer.invisibletirapi.application.api;

import com.invisibleprogrammer.invisibletirapi.application.request.SignUpUserRequest;
import com.invisibleprogrammer.invisibletirapi.application.response.SignUpUserResponse;
import com.invisibleprogrammer.invisibletirapi.domain.User;
import com.invisibleprogrammer.invisibletirapi.domain.service.UserAlreadyExistsException;
import com.invisibleprogrammer.invisibletirapi.domain.service.UserService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> signUp(@RequestBody SignUpUserRequest userRequest) {

        try {
            User newUser = usersService.signUp(userRequest.getEmail(), userRequest.getPassword());
            return ResponseEntity.ok(new SignUpUserResponse(userRequest.getEmail(), "MEMBER", newUser.getApiKeys().get(0).getApiKey()));

        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body("""
                    {
                      "code": 400,
                      "type": "BAD_REQUEST",
                      "message": "E-mail already exists"
                    }""");
        }

    }

}
