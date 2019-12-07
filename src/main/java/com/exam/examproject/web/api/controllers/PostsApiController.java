package com.exam.examproject.web.api.controllers;

import com.exam.examproject.services.models.PostServiceModel;
import com.exam.examproject.services.services.PostsService;
import com.exam.examproject.web.api.models.PostResponseModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PostsApiController {
    private final PostsService postsService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/api/posts")
    public ResponseEntity<List<PostResponseModel>> getPosts(HttpSession session) {
        List<PostServiceModel> postServiceModels = this.postsService.getAllPosts();
        List<PostResponseModel> postResponseModels = postServiceModels.stream()
                .map(post -> this.modelMapper.map(post, PostResponseModel.class)).collect(Collectors.toList());
        return new ResponseEntity<>(postResponseModels, HttpStatus.OK);
    }
}
