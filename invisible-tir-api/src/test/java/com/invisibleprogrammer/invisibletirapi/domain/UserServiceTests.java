package com.invisibleprogrammer.invisibletirapi.domain;

import com.invisibleprogrammer.invisibletirapi.domain.repository.ApiKeyRepository;
import com.invisibleprogrammer.invisibletirapi.domain.repository.UserRepository;
import com.invisibleprogrammer.invisibletirapi.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private ApiKeyRepository apiKeyRepository;

    @Test
    public void signUp_success() {
        String email = "test@test.com";
        String password = "P@ssword123";

        userService = new UserService(userRepository, apiKeyRepository);
        var newUser = new User(email, password);
        newUser.setId(1);

        when(userRepository.save(any())).thenReturn(newUser);

        var createdUser = userService.signUp(email, password);

        assertEquals(newUser.getId(), createdUser.getId());
        assertNotNull(createdUser);
        var apiKeys = createdUser.getApiKeys();
        assertEquals(1, apiKeys.size());
        assertNotNull(apiKeys.get(0));
    }
}
