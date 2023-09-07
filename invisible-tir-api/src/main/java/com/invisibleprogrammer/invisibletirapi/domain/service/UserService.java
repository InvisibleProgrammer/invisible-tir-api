package com.invisibleprogrammer.invisibletirapi.domain.service;

import com.invisibleprogrammer.invisibletirapi.domain.User;
import com.invisibleprogrammer.invisibletirapi.domain.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String email, String password) {

        String apiKey = RandomStringUtils.randomAlphanumeric(20);

        User newUser = new User(email, password, apiKey);

        userRepository.save(newUser);
    }
}

