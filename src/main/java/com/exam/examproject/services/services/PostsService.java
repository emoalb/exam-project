package com.exam.examproject.services.services;

import com.exam.examproject.services.models.CreatePostServiceModel;
import com.exam.examproject.services.models.DetailsPostServiceModel;
import com.exam.examproject.services.models.EditPostServiceModel;
import com.exam.examproject.services.models.PostServiceModel;

import java.util.List;

public interface PostsService {
    void createPost(CreatePostServiceModel createPostServiceModel) throws Exception;

    List<PostServiceModel> getAllPosts();

    void updatePost(EditPostServiceModel editPostServiceModel) throws Exception;

    EditPostServiceModel findPostToEdit(String id) throws Exception;

    DetailsPostServiceModel findPostDetails(String id) throws Exception;

    void deletePostById(String id);
}
