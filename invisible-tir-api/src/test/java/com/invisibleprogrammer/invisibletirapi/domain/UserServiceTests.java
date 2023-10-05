package com.invisibleprogrammer.invisibletirapi.domain;

import com.invisibleprogrammer.invisibletirapi.domain.repository.ApiKeyRepository;
import com.invisibleprogrammer.invisibletirapi.domain.repository.UserRepository;
import com.invisibleprogrammer.invisibletirapi.domain.service.InvalidPasswordException;
import com.invisibleprogrammer.invisibletirapi.domain.service.UserAlreadyExistsException;
import com.invisibleprogrammer.invisibletirapi.domain.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    private static UserService userService;

    @Mock
    private static UserRepository userRepository;
    @Mock
    private static ApiKeyRepository apiKeyRepository;
    @Mock
    private static PasswordValidator passwordValidator;

    private final String email = "test@test.com";
    private final String password = "P@ssword123";

    @BeforeEach
    public void setup() {
        userService = new UserService(userRepository, apiKeyRepository, passwordValidator);
        when(passwordValidator.isValid(anyString())).thenReturn(true);
    }

    @Test
    public void signUp_success() throws UserAlreadyExistsException, InvalidPasswordException {
        var newUser = new User(email, password);
        newUser.setId(1);
        when(userRepository.save(any())).thenReturn(newUser);

        var createdUser = userService.signUp(email, password);

        assertEquals(newUser.getId(), createdUser.getId());
        assertNotNull(createdUser);

        verify(apiKeyRepository, times(1)).save(any());
        var apiKeys = createdUser.getApiKeys();
        assertEquals(1, apiKeys.size());
        assertNotNull(apiKeys.get(0));
    }

    @Test
    void signup_email_already_exists() {
        when(userRepository.existsUserByEmailAddress(anyString())).thenReturn(true);

        Assertions.assertThrows(UserAlreadyExistsException.class, () -> userService.signUp(email, password));
    }

    @Test
    void test_password_is_invalid() {
        when(passwordValidator.isValid(anyString())).thenReturn(false);

        Assertions.assertThrows(InvalidPasswordException.class, () -> userService.signUp(email, password));
    }
}
