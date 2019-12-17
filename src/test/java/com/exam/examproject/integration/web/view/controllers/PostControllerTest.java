package com.exam.examproject.integration.web.view.controllers;

import com.exam.examproject.domain.entities.Post;
import com.exam.examproject.domain.entities.User;
import com.exam.examproject.errors.PostNotFoundException;
import com.exam.examproject.integration.web.view.TestBaseControllers;
import com.exam.examproject.repositories.PostRepository;
import com.exam.examproject.services.models.LoginResponseModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostControllerTest extends TestBaseControllers {

    @MockBean
    PostRepository postRepository;


    @WithMockUser()
    @Test
    void getDetailsPost_whenPostIdValid_ShouldWork() throws Exception {
        MockHttpSession session = new MockHttpSession();
        String postId = "someId";

        Post post = new Post();
        post.setId(postId);
        post.setCreator(new User());
        post.setDescription("some description");
        post.setTitle("Title");
        post.setPictureUrl("someurl");


        LoginResponseModel loginResponseModel = new LoginResponseModel();
        loginResponseModel.setId("id");
        loginResponseModel.setUsername("name");


        session.setAttribute("user",loginResponseModel);


        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/posts/details/" + postId).session(session);
        mockMvc.perform(builder).andExpect(status().isOk()).andExpect(view().name("_layouts/index"))
                .andExpect(model().attribute("view","posts/details"));

    }

}
