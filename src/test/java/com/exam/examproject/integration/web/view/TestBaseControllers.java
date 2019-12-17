package com.exam.examproject.integration.web.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class TestBaseControllers {
    @BeforeEach
    private void setupTest() {
        MockitoAnnotations.initMocks(this);
        this.beforeEach();
    }

    protected void beforeEach() {
    }
    @Autowired
    protected MockMvc mockMvc;
}
