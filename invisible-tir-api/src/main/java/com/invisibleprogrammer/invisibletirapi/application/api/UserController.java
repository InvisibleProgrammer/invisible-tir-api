package com.invisibleprogrammer.invisibletirapi.application.api;

import com.invisibleprogrammer.invisibletirapi.application.request.SignUpUserRequest;
import com.invisibleprogrammer.invisibletirapi.application.response.SignUpUserResponse;
import com.invisibleprogrammer.invisibletirapi.domain.User;
import com.invisibleprogrammer.invisibletirapi.domain.service.InvalidPasswordException;
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
        } catch (InvalidPasswordException e) {
            return ResponseEntity.unprocessableEntity().body("""
                    {
                      "code": 422,
                      "type": "UNPROCESSABLE_ENTITY",
                      "message": "The password must be at least 10 characters, contains numeric characters, minimum 1 uppercase letter [A-Z] and minimum 1 special character"
                    }""");
        }

    }

}
