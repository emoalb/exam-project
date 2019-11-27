package com.exam.examproject.services.services;

import com.exam.examproject.services.models.CreatePostServiceModel;
import com.exam.examproject.services.models.PostServiceModel;

import java.util.List;

public interface PostsService {
 void createPost(CreatePostServiceModel createPostServiceModel) throws Exception;
 List<PostServiceModel> getAllPosts();
}
