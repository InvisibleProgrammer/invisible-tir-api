package com.invisibleprogrammer.invisibletirapi.domain.service;

import com.invisibleprogrammer.invisibletirapi.domain.ApiKey;
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

    public UserService(UserRepository userRepository, ApiKeyRepository apiKeyRepository) {
        this.userRepository = userRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    public User signUp(String email, String password) throws UserAlreadyExistsException {
        if (userRepository.existsUserByEmailAddress(email)) {
            throw new UserAlreadyExistsException();
        }

        User newUser = new User(email, password);
        newUser = userRepository.save(newUser);

        String apiKey = RandomStringUtils.randomAlphanumeric(20);
        ApiKey userApiKey = new ApiKey(apiKey, newUser);
        apiKeyRepository.save(userApiKey);

        newUser.setApiKeys(List.of(userApiKey));

        return newUser;
    }
}

