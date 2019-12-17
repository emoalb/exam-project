package com.exam.examproject.integration.web.view.controllers;

import com.exam.examproject.integration.web.view.TestBaseControllers;
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


public class AuthControllerTest extends TestBaseControllers {


    @Test
    void getLogin_shouldReturnLoginViewWithResponse200() throws Exception {
        mockMvc.perform(get("/users/login")).andExpect(status().isOk()).andExpect(view().name("_layouts/index"))
                .andExpect(model().attribute("view","login"));
    }


    @Test
    void getRegister_shouldReturnRegisterViewWithResponse200() throws Exception {
        mockMvc.perform(get("/users/register")).andExpect(status().isOk()).andExpect(view().name("_layouts/index"))
                .andExpect(model().attribute("view","register"));
    }

}
