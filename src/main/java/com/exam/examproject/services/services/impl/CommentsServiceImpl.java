package com.exam.examproject.services.services.impl;

import com.exam.examproject.domain.entities.Comment;
import com.exam.examproject.repositories.CommentRepository;
import com.exam.examproject.services.models.CommentServiceModel;
import com.exam.examproject.services.models.CreateCommentServiceModel;;
import com.exam.examproject.services.services.CommentsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    CommentsServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper){
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CommentServiceModel> getAllComments(String postId) {
      List<Comment> allComments =  this.commentRepository.findAllByPost_Id(postId);
        return allComments.stream().map(comment -> this.modelMapper.map(comment, CommentServiceModel.class)).collect(Collectors.toList());

    }

    @Override
    public void createComment(CreateCommentServiceModel createCommentServiceModel) {


    }
}
