package com.exam.examproject.integration.web.api.controllers;

import com.exam.examproject.domain.entities.Post;
import com.exam.examproject.domain.entities.User;
import com.exam.examproject.repositories.PostRepository;
import com.exam.examproject.services.models.PostServiceModel;
import com.exam.examproject.services.services.HashingService;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class PostsApiControllerTest {
    @BeforeEach
    private void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @LocalServerPort
    int port;

    @MockBean
    PostRepository postRepository;
    @MockBean
    HashingService hashingService;

    String getFullUrl(String route) {
        return "http://localhost:" + port + route;
    }

    TestRestTemplate getRestTemplate() {
        return new TestRestTemplate();
    }
    protected TestRestTemplate getRestTemplate(String username, String password) {
        if(username != null && password !=null) {
            return new TestRestTemplate(username, password);
        }

        return getRestTemplate();
    }
    @WithMockUser()
    @Test
    void getALlPosts_withPost_shouldReturnPost() {
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

