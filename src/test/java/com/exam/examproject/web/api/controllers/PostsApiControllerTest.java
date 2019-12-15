package com.exam.examproject.web.api.controllers;

import com.exam.examproject.domain.entities.Post;
import com.exam.examproject.domain.entities.User;
import com.exam.examproject.repositories.PostRepository;
import com.exam.examproject.services.models.PostServiceModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PostsApiControllerTest {
    @BeforeEach
    private void setupTest() {
        MockitoAnnotations.initMocks(this);
    }

    protected int port = 8000;
@MockBean
PostRepository postRepository;
    protected String getFullUrl(String route) {
        return "http://localhost:" + port + route;
    }

    protected TestRestTemplate getRestTemplate() {
        return new TestRestTemplate();
    }
    @Test
    void getALlPosts_withPost_shouldReturnPost(){
        Post post = new Post();
        post.setId("dhfuihdsfu");
        post.setTitle("Titlee");
        post.setPictureUrl("someUrl");
        post.setDescription("dordfhdhfudih");
        post.setCreator(new User());
        Mockito.when(postRepository.findAll()).thenReturn(List.of(post));
        PostServiceModel[] result =
                getRestTemplate()
                        .getForObject(
                                getFullUrl("/api/posts"),
                                PostServiceModel[].class);

        assertEquals(1, result.length);
        assertEquals(post.getId(), result[0].getId());
        assertEquals(post.getTitle(), result[0].getTitle());
    }

}

