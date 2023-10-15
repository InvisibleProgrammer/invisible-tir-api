package com.invisibleprogrammer.invisibletirapi.application.api;

import com.invisibleprogrammer.invisibletirapi.application.request.UserRequest;
import com.invisibleprogrammer.invisibletirapi.application.response.UserResponse;
import com.invisibleprogrammer.invisibletirapi.domain.User;
import com.invisibleprogrammer.invisibletirapi.domain.service.InvalidPasswordException;
import com.invisibleprogrammer.invisibletirapi.domain.service.UserAlreadyExistsException;
import com.invisibleprogrammer.invisibletirapi.domain.service.UserService;
import com.invisibleprogrammer.invisibletirapi.domain.service.WrongCredentialsException;
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
    public ResponseEntity<?> signUp(@RequestBody UserRequest userRequest) {

        try {
            User newUser = usersService.signUp(userRequest.getEmail(), userRequest.getPassword());
            return ResponseEntity.ok(new UserResponse(userRequest.getEmail(), "MEMBER", newUser.getApiKey()));

        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body("""
                    {
                      "code": 400,
                      "type": "BAD_REQUEST",
                      "message": "E-mail already exists"
                    }""");
        } catch (InvalidPasswordException e) {
            return ResponseEntity.unprocessableEntity().body("""
                    {
                      "code": 422,
                      "type": "UNPROCESSABLE_ENTITY",
                      "message": "The password must be at least 10 characters, contains numeric characters, minimum 1 uppercase letter [A-Z] and minimum 1 special character"
                    }""");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody UserRequest userRequest) {

        try {
            User user = usersService.signIn(userRequest.getEmail(), userRequest.getPassword());
            return ResponseEntity.ok(new UserResponse(userRequest.getEmail(), user.getFullName(), user.getBio(), user.getApiKey(), "MEMBER"));

        } catch (WrongCredentialsException e) {
            return ResponseEntity.unprocessableEntity().body("""
                    {
                      "code": 422,
                      "type": "UNPROCESSABLE_ENTITY",
                      "message": "Invalid e-mail or password"
                    }""");
        }

    }
}
