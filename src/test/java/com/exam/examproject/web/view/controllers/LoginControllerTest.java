package com.exam.examproject.web.view.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class LoginControllerTest {
    @BeforeEach
    private void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void getLogin_shouldReturnLoginViewWithResponse200() throws Exception {
        mockMvc.perform(get("/users/login")).andExpect(status().isOk()).andExpect(view().name("_layouts/index"))
                .andExpect(model().attribute("view","login"));
    }
}
