package com.invisibleprogrammer.invisibletirapi.domain.service;

import com.invisibleprogrammer.invisibletirapi.domain.ApiKey;
import com.invisibleprogrammer.invisibletirapi.domain.PasswordValidator;
import com.invisibleprogrammer.invisibletirapi.domain.User;
import com.invisibleprogrammer.invisibletirapi.domain.repository.ApiKeyRepository;
import com.invisibleprogrammer.invisibletirapi.domain.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    private final UserRepository userRepository;
    private final ApiKeyRepository apiKeyRepository;
    private final PasswordValidator passwordValidator;

    public UserService(UserRepository userRepository, ApiKeyRepository apiKeyRepository, PasswordValidator passwordValidator) {
        this.userRepository = userRepository;
        this.apiKeyRepository = apiKeyRepository;
        this.passwordValidator = passwordValidator;
    }

    public User signUp(String email, String password) throws UserAlreadyExistsException, InvalidPasswordException {
        if (!passwordValidator.isValid(password)) {
            throw new InvalidPasswordException();
        }

        if (userRepository.existsUserByEmailAddress(email)) {
            throw new UserAlreadyExistsException();
        }

        String apiKey = RandomStringUtils.randomAlphanumeric(20);

        User newUser = new User(email, password);
        newUser = userRepository.save(newUser);

        ApiKey userApiKey = new ApiKey(apiKey, newUser);
        apiKeyRepository.save(userApiKey);

        newUser.setApiKeys(List.of(userApiKey));

        userRepository.flush();
        apiKeyRepository.flush();

        return newUser;
    }
}
