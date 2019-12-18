package com.exam.examproject.unit.services;


import com.exam.examproject.domain.entities.Comment;
import com.exam.examproject.domain.entities.Post;
import com.exam.examproject.errors.PostNotFoundException;
import com.exam.examproject.repositories.CommentRepository;
import com.exam.examproject.repositories.PostRepository;

import com.exam.examproject.services.models.CommentServiceModel;
import com.exam.examproject.services.services.impl.CommentsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class CommentsServiceTest {

    @InjectMocks
    CommentsServiceImpl commentsService;
    private Comment comment;
    private Post post;

    @Mock
    CommentRepository mockCommentRepository;

    @Mock
    PostRepository mockPostRepository;

    @Mock
    ModelMapper modelMapper;


    @Before
    public void setup() {
        comment = mock(Comment.class);
        post = mock(Post.class);
    }

    @Test
    public void getAllComments_shouldGetAllCommentsWithValidPostId() {
        String postId = "id";
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        comments.add(comment);
        Mockito.when(mockPostRepository.findById(postId)).thenReturn(Optional.of(post));
        Mockito.when(mockCommentRepository.findAllByPost_Id(postId)).thenReturn(comments);
        int result = commentsService.getAllComments(postId).size();
        System.out.println();
        assertEquals(comments.size(), result);

    }


    @Test(expected = PostNotFoundException.class)
    public void getAllComments_shouldThrowPostNotFoundException_whenPostIdIsInvalid() {
        String postId = "id";
        Mockito.when(mockPostRepository.findById(postId)).thenReturn(Optional.empty());
        commentsService.getAllComments(postId).size();


    }
}
