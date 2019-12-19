package com.exam.examproject.unit.services;

import com.exam.examproject.common.Constants;
import com.exam.examproject.domain.entities.Post;
import com.exam.examproject.errors.PostNotFoundException;
import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.repositories.PostRepository;
import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.models.DetailsPostServiceModel;
import com.exam.examproject.services.models.EditPostServiceModel;
import com.exam.examproject.services.models.PostServiceModel;
import com.exam.examproject.services.services.impl.PostsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.class)

public class PostsServiceTest {
    @InjectMocks
    PostsServiceImpl postsService;

    @Mock
    PostRepository mockPostRepository;

    @Mock
    UserRepository mockUserRepository;


    @Mock
    ModelMapper modelMapper;

    private Post post;

    private DetailsPostServiceModel detailsPostServiceModel;



    @Before
    public void setup() {
        post = mock(Post.class);
        detailsPostServiceModel = mock(DetailsPostServiceModel.class);

    }

    @Test
    public void findPostDetails_whenPostIdExists_ShouldWork() {
        Mockito.when(mockPostRepository.findById("id")).thenReturn(Optional.of(post));
        Mockito.when(modelMapper.map(post, DetailsPostServiceModel.class))
                .thenReturn(detailsPostServiceModel);

        DetailsPostServiceModel result = postsService.findPostDetails("id");
        Mockito.verify(mockPostRepository).findById("id");
        Mockito.verify(modelMapper).map(post, DetailsPostServiceModel.class);
        assertEquals(detailsPostServiceModel, result);

    }

    @Test(expected = PostNotFoundException.class)

    public void findPostDetails_whenPostIdIsInvalid_ShouldThrowPostNotFoundException() {
        String postId = "233209";
        Mockito.when(mockPostRepository.findById(postId)).thenReturn(Optional.empty());
        postsService.findPostDetails(postId);
    }

    @Test
    public void getAllPosts_shouldReturnAllPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        posts.add(post);
        Mockito.when(mockPostRepository.findAll()).thenReturn(posts);
        int result = postsService.getAllPosts().size();
        assertEquals(posts.size(), result);
    }

    @Test(expected = UserNotFoundException.class)
    public void updatePost_shouldThrowUserNotFoundException_whenUserNameIsInvalid() {
        String userName = "somename";
        Mockito.when(mockUserRepository.findByUsername(userName)).thenReturn(Optional.empty());
        EditPostServiceModel editPostServiceModel = new EditPostServiceModel();
        editPostServiceModel.setCreatorUsername(userName);
        editPostServiceModel.setId("someId");
        editPostServiceModel.setTitle("sometitle");
        editPostServiceModel.setDescription("descr");
        postsService.updatePost(editPostServiceModel);
    }
}
