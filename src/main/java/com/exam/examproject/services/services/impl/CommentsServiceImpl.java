package com.exam.examproject.services.services.impl;

import com.exam.examproject.common.Constants;
import com.exam.examproject.domain.entities.Comment;
import com.exam.examproject.domain.entities.Post;
import com.exam.examproject.domain.entities.User;
import com.exam.examproject.errors.CommentNotFoundException;
import com.exam.examproject.errors.PostNotFoundException;
import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.repositories.CommentRepository;
import com.exam.examproject.repositories.PostRepository;
import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.models.CommentServiceModel;
import com.exam.examproject.services.models.CreateCommentServiceModel;;
import com.exam.examproject.services.services.CommentsService;
import com.exam.examproject.services.services.DatesService;
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
    private final DatesService datesService;

    @Autowired
    CommentsServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository, ModelMapper modelMapper, DatesService datesService) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.datesService = datesService;
    }

    @Override
    public List<CommentServiceModel> getAllComments(String postId) throws PostNotFoundException {
        Optional<Post> post = this.postRepository.findById(postId);
        if (post.isEmpty()) throw new PostNotFoundException(Constants.POST_NOT_FOUND_MESSAGE);
        List<Comment> allComments = this.commentRepository.findAllByPost_Id(postId);
        return allComments.stream().map(comment -> this.modelMapper.map(comment, CommentServiceModel.class)).collect(Collectors.toList());

    }

    @Override
    public void createComment(CreateCommentServiceModel createCommentServiceModel) throws PostNotFoundException, UserNotFoundException {
        Comment comment = new Comment();
        comment.setComment(createCommentServiceModel.getComment());
        Optional<Post> post = this.postRepository.findById(createCommentServiceModel.getPostId());
        if (post.isEmpty()) throw new PostNotFoundException(Constants.POST_NOT_FOUND_MESSAGE);
        comment.setPost(post.get());
        Optional<User> user = this.userRepository.findById(createCommentServiceModel.getCreatorId());
        if (user.isEmpty()) throw new UserNotFoundException(Constants.USER_NOT_FOUND_MESSAGE);
        comment.setCreator(user.get());
        comment.setDate(this.datesService.getCurrentDate());
        this.commentRepository.save(comment);

    }

    @Override
    public void deleteComment(String id) throws CommentNotFoundException {
        Optional<Comment> comment = this.commentRepository.findById(id);
        if (comment.isEmpty()) throw new CommentNotFoundException(Constants.COMMENT_NOT_FOUND_MESSAGE);
        this.commentRepository.delete(comment.get());
    }
}
