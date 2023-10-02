package com.invisibleprogrammer.invisibletirapi.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invisibleprogrammer.invisibletirapi.application.api.UserController;
import com.invisibleprogrammer.invisibletirapi.application.response.SignUpUserResponse;
import com.invisibleprogrammer.invisibletirapi.domain.ApiKey;
import com.invisibleprogrammer.invisibletirapi.domain.User;
import com.invisibleprogrammer.invisibletirapi.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    public void createUser_returnsWith_OK() throws Exception {
        String email = "test@test.com";
        String password = "P@ssword123";
        String apiKey = "123abc";

        User newUser = new User(email, password);
        newUser.setId(1);
        newUser.setApiKeys(List.of(new ApiKey(apiKey, newUser)));

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
        SignUpUserResponse signUpUserResponse = objectMapper.readValue(response, SignUpUserResponse.class);

        assertNotNull(signUpUserResponse);
        assertEquals(email, signUpUserResponse.getEmail());
        assertEquals("MEMBER", signUpUserResponse.getRole());
        assertEquals(apiKey, signUpUserResponse.getApiKey());
    }
}
