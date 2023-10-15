package com.invisibleprogrammer.invisibletirapi.domain;

import com.invisibleprogrammer.invisibletirapi.domain.repository.UserRepository;
import com.invisibleprogrammer.invisibletirapi.domain.service.UserService;
import com.invisibleprogrammer.invisibletirapi.domain.service.WrongCredentialsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceSignInTests {
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
    }

    @Test
    void signIn_success() throws WrongCredentialsException {
        String apiKey = "123abc";
        var user = new User(email, password, apiKey);
        user.setId(1);
        when(userRepository.findUserByEmailAddress(anyString())).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches(anyString(), anyString())).thenReturn(true);

        var loggedInUser = userService.signIn(email, password);

        assertNotNull(loggedInUser);
        assertEquals(user.getId(), loggedInUser.getId());
        assertEquals(apiKey, loggedInUser.getApiKey());

        verify(bCryptPasswordEncoder, times(1)).matches(eq(password), anyString());
    }

    @Test
    void signIn_wrongEmail() {
        when(userRepository.findUserByEmailAddress(anyString())).thenReturn(Optional.empty());

        assertThrows(WrongCredentialsException.class, () -> userService.signIn(email, password));
    }

    @Test
    void signIn_wrongPassword() {
        String apiKey = "123abc";
        var user = new User(email, password, apiKey);
        user.setId(1);
        when(userRepository.findUserByEmailAddress(anyString())).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches(anyString(), anyString())).thenReturn(false);

        assertThrows(WrongCredentialsException.class, () -> userService.signIn(email, password));
    }
}
