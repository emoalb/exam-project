package com.exam.examproject.services.services.impl;

import com.exam.examproject.domain.entities.Comment;
import com.exam.examproject.domain.entities.Post;
import com.exam.examproject.domain.entities.User;
import com.exam.examproject.repositories.CommentRepository;
import com.exam.examproject.repositories.PostRepository;
import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.models.CommentServiceModel;
import com.exam.examproject.services.models.CreateCommentServiceModel;;
import com.exam.examproject.services.services.CommentsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {
    private final CommentRepository commentRepository;

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    CommentsServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CommentServiceModel> getAllComments(String postId) {
        List<Comment> allComments = this.commentRepository.findAllByPost_Id(postId);
        return allComments.stream().map(comment -> this.modelMapper.map(comment, CommentServiceModel.class)).collect(Collectors.toList());

    }

    @Override
    public void createComment(CreateCommentServiceModel createCommentServiceModel) throws Exception {
        Comment comment = new Comment();
        comment.setComment(createCommentServiceModel.getComment());
        Optional<Post> post = this.postRepository.findById(createCommentServiceModel.getPostId());
        if (post.isEmpty()) throw new Exception("Invalid post id");
        comment.setPost(post.get());
        Optional<User> user = this.userRepository.findById(createCommentServiceModel.getCreatorId());
        if(user.isEmpty()) throw new Exception("Invalid user id");
        comment.setCreator(user.get());
        this.commentRepository.save(comment);
        System.out.println();
    }
}
