package com.exam.examproject.services.services;

import com.exam.examproject.services.models.CommentServiceModel;
import com.exam.examproject.services.models.CreateCommentServiceModel;
import com.exam.examproject.services.models.MessageServiceModel;

import java.util.List;

public interface CommentsService {
    List<CommentServiceModel> getAllComments(String postId);
    void createComment(CreateCommentServiceModel createCommentServiceModel) throws Exception;
}
