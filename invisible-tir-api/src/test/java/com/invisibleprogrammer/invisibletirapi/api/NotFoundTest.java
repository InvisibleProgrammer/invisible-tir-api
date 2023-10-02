package com.invisibleprogrammer.invisibletirapi.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NotFoundTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void unknown_route_returnsWith_404() throws Exception {
        mockMvc.perform(get("/not-existing-route")
                ).andExpect(status().isNotFound())
                .andExpect(content().string("""
                        {
                          "code": 404,
                          "type": "NOT_FOUND",
                          "message": "Service not found"
                        }"""));

    }
}
