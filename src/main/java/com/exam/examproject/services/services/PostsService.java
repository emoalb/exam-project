package com.exam.examproject.services.services;

import com.exam.examproject.errors.PostNotFoundException;
import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.services.models.CreatePostServiceModel;
import com.exam.examproject.services.models.DetailsPostServiceModel;
import com.exam.examproject.services.models.EditPostServiceModel;
import com.exam.examproject.services.models.PostServiceModel;

import java.util.List;

public interface PostsService {
    void createPost(CreatePostServiceModel createPostServiceModel) throws UserNotFoundException;

    List<PostServiceModel> getAllPosts();

    void updatePost(EditPostServiceModel editPostServiceModel) throws UserNotFoundException;

    EditPostServiceModel findPostToEdit(String id) throws PostNotFoundException;

    DetailsPostServiceModel findPostDetails(String id) throws PostNotFoundException;

    void deletePostById(String id)throws  PostNotFoundException;
}
