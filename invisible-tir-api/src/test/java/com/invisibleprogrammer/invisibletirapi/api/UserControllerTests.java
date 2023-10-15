package com.invisibleprogrammer.invisibletirapi.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invisibleprogrammer.invisibletirapi.application.api.UserController;
import com.invisibleprogrammer.invisibletirapi.application.response.UserResponse;
import com.invisibleprogrammer.invisibletirapi.domain.User;
import com.invisibleprogrammer.invisibletirapi.domain.service.InvalidPasswordException;
import com.invisibleprogrammer.invisibletirapi.domain.service.UserAlreadyExistsException;
import com.invisibleprogrammer.invisibletirapi.domain.service.UserService;
import com.invisibleprogrammer.invisibletirapi.domain.service.WrongCredentialsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    public void createUser_success_Ok() throws Exception {
        String email = "test@test.com";
        String password = "P@ssword123";
        String apiKey = "123abc";

        User newUser = new User(email, password, apiKey);
        newUser.setId(1);
        newUser.setApiKey(apiKey);

        Mockito.when(userService.signUp(anyString(), anyString())).thenReturn(newUser);

        String payload = """
                {
                    "email": "test@test.com",
                    "password": "P@ssword123"
                }""";

        MvcResult mvcResult = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                ).andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        UserResponse userResponse = objectMapper.readValue(response, UserResponse.class);

        assertNotNull(userResponse);
        assertEquals(email, userResponse.getEmail());
        assertEquals("MEMBER", userResponse.getRole());
        assertEquals(apiKey, userResponse.getApiKey());
    }

    @Test
    public void createUser_alreadyExists_returnsWith_BadRequest() throws Exception {
        Mockito.when(userService.signUp(anyString(), anyString())).thenThrow(UserAlreadyExistsException.class);

        String payload = """
                {
                    "email": "test@test.com",
                    "password": "P@ssword123"
                }""";

        String expectedResponse = """
                {
                  "code": 400,
                  "type": "BAD_REQUEST",
                  "message": "E-mail already exists"
                }""";

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                ).andExpect(status().isBadRequest())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void createUser_invalidPassword_returnsWith_UnprocessableEntity() throws Exception {
        Mockito.when(userService.signUp(anyString(), anyString())).thenThrow(InvalidPasswordException.class);

        String payload = """
                {
                    "email": "test@test.com",
                    "password": "password"
                }""";

        String expectedResponse = """
                {
                  "code": 422,
                  "type": "UNPROCESSABLE_ENTITY",
                  "message": "The password must be at least 10 characters, contains numeric characters, minimum 1 uppercase letter [A-Z] and minimum 1 special character"
                }""";

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                ).andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void signIn_success_Ok() throws Exception {
        String email = "test@codeyard.eu";
        String fullName = "Imre Ujlaki";
        String bio = "Lorem ipsum dolor sit amet";
        String password = "P@ssword123";
        String apiKey = "123abc";

        User user = new User(1, email, fullName, bio, password, apiKey);

        Mockito.when(userService.signIn(anyString(), anyString())).thenReturn(user);

        String payload = """
                {
                    "email": "test@codeyard.eu",
                    "password": "P@ssword123"
                }""";

        MvcResult mvcResult = mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                ).andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        UserResponse userResponse = objectMapper.readValue(response, UserResponse.class);

        assertNotNull(userResponse);
        assertEquals(email, userResponse.getEmail());
        assertEquals(fullName, userResponse.getFullName());
        assertEquals(bio, userResponse.getBio());
        assertEquals("MEMBER", userResponse.getRole());
        assertEquals(apiKey, userResponse.getApiKey());
    }

    @Test
    public void singInUser_wrongCredentials_UnprocessableEntity() throws Exception {
        Mockito.when(userService.signIn(anyString(), anyString())).thenThrow(WrongCredentialsException.class);

        String payload = """
                {
                    "email": "test@test.com",
                    "password": "password"
                }""";

        String expectedResponse = """
                {
                  "code": 422,
                  "type": "UNPROCESSABLE_ENTITY",
                  "message": "Invalid e-mail or password"
                }""";

        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                ).andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedResponse));
    }

}
