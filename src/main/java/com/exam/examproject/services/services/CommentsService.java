package com.exam.examproject.services.services;

import com.exam.examproject.errors.PostNotFoundException;
import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.services.models.CommentServiceModel;
import com.exam.examproject.services.models.CreateCommentServiceModel;
import com.exam.examproject.services.models.MessageServiceModel;

import java.util.List;

public interface CommentsService {
    List<CommentServiceModel> getAllComments(String postId) throws  PostNotFoundException;
    void createComment(CreateCommentServiceModel createCommentServiceModel) throws PostNotFoundException, UserNotFoundException;
}
