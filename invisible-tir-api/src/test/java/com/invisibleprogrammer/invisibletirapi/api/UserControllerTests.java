package com.invisibleprogrammer.invisibletirapi.api;

import com.invisibleprogrammer.invisibletirapi.application.api.UserController;
import com.invisibleprogrammer.invisibletirapi.application.request.SignUpUserRequest;
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

import java.util.List;

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

        SignUpUserRequest request = new SignUpUserRequest();
        request.setEmail(email);
        request.setPassword(password);

        String payload = """
                {
                    "email": "test@codeyard.eu",
                    "password": "helloworld"
                }""";

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
        ).andExpect(status().isOk());

        // Todo: figure out the levels of testing. Do I want to check all the API call output?
//        SignUpUserResponse response = userController.signUp(request);
//
//        assertNotNull(response);
//        assertEquals(email, response.getEmail());
//        assertEquals("MEMBER", response.getRole());
//        getRoleassertEquals(apiKey, response.getApiKey());
    }
}
