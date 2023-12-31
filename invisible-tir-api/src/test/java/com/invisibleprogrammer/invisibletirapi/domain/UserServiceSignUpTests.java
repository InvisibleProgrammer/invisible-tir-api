package com.invisibleprogrammer.invisibletirapi.domain;

import com.invisibleprogrammer.invisibletirapi.domain.repository.UserRepository;
import com.invisibleprogrammer.invisibletirapi.domain.service.InvalidPasswordException;
import com.invisibleprogrammer.invisibletirapi.domain.service.UserAlreadyExistsException;
import com.invisibleprogrammer.invisibletirapi.domain.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceSignUpTests {

    private static UserService userService;

    @Mock
    private static UserRepository userRepository;
    @Mock
    private static PasswordValidator passwordValidator;
    @Spy
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final String email = "test@test.com";
    private final String password = "P@ssword123";

    @BeforeEach
    public void setup() {
        userService = new UserService(userRepository, passwordValidator, bCryptPasswordEncoder);
        when(passwordValidator.isValid(anyString())).thenReturn(true);
    }

    @Test
    public void signUp_success() throws UserAlreadyExistsException, InvalidPasswordException {
        String apiKey = "123abc";
        var newUser = new User(email, password, apiKey);
        newUser.setId(1);
        when(userRepository.save(any())).thenReturn(newUser);

        var createdUser = userService.signUp(email, password);

        assertNotNull(createdUser);
        assertEquals(newUser.getId(), createdUser.getId());
        assertEquals(apiKey, createdUser.getApiKey());

        verify(bCryptPasswordEncoder, times(1)).encode(anyString());
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
