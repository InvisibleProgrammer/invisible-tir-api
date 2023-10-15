package com.invisibleprogrammer.invisibletirapi.domain.service;

import com.invisibleprogrammer.invisibletirapi.domain.PasswordValidator;
import com.invisibleprogrammer.invisibletirapi.domain.User;
import com.invisibleprogrammer.invisibletirapi.domain.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final UserRepository userRepository;
    private final PasswordValidator passwordValidator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, PasswordValidator passwordValidator, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.passwordValidator = passwordValidator;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User signUp(String email, String password) throws UserAlreadyExistsException, InvalidPasswordException {
        if (!passwordValidator.isValid(password)) {
            throw new InvalidPasswordException();
        }

        if (userRepository.existsUserByEmailAddress(email)) {
            throw new UserAlreadyExistsException();
        }

        String apiKey = RandomStringUtils.randomAlphanumeric(20);
        String passwordHash = bCryptPasswordEncoder.encode(password);

        User newUser = new User(email, passwordHash, apiKey);
        newUser = userRepository.save(newUser);

        userRepository.flush();

        return newUser;
    }
}
