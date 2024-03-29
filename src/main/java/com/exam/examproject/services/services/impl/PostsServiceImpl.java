package com.exam.examproject.services.services.impl;

import com.exam.examproject.common.Constants;
import com.exam.examproject.domain.entities.Post;
import com.exam.examproject.domain.entities.User;
import com.exam.examproject.errors.PostNotFoundException;
import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.repositories.PostRepository;
import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.models.CreatePostServiceModel;
import com.exam.examproject.services.models.DetailsPostServiceModel;
import com.exam.examproject.services.models.EditPostServiceModel;
import com.exam.examproject.services.models.PostServiceModel;
import com.exam.examproject.services.services.PostsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostsServiceImpl implements PostsService {

    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostsServiceImpl(ModelMapper modelMapper, PostRepository postRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createPost(CreatePostServiceModel createPostServiceModel) throws UserNotFoundException {
        Optional<User> user = this.userRepository.findById(createPostServiceModel.getCreator_id());
        Post post = this.modelMapper.map(createPostServiceModel, Post.class);
        if (user.isEmpty()) {
            throw new UserNotFoundException(Constants.USER_NOT_FOUND_MESSAGE);
        }
        post.setCreator(user.get());
        this.postRepository.save(post);

    }

    @Override
    public List<PostServiceModel> getAllPosts() {
        List<Post> allPosts = this.postRepository.findAll();
        return allPosts.stream().map(post -> this.modelMapper.map(post, PostServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public void updatePost(EditPostServiceModel editPostServiceModel) throws UserNotFoundException {
        Post post = this.modelMapper.map(editPostServiceModel, Post.class);
        Optional<User> user = this.userRepository.findByUsername(editPostServiceModel.getCreatorUsername());
        if (user.isEmpty()) {
            throw new UserNotFoundException(Constants.USER_NOT_FOUND_MESSAGE);
        }
        post.setCreator(user.get());
        this.postRepository.save(post);
    }

    @Override
    public EditPostServiceModel findPostToEdit(String id) throws PostNotFoundException {

        return this.modelMapper.map(findPostById(id), EditPostServiceModel.class);
    }

    @Override
    public DetailsPostServiceModel findPostDetails(String id) throws PostNotFoundException {

        return this.modelMapper.map(findPostById(id), DetailsPostServiceModel.class);
    }

    private Post findPostById(String id) throws PostNotFoundException {

        Optional<Post> post = this.postRepository.findById(id);
        if (post.isEmpty()) {
            throw new PostNotFoundException(Constants.POST_NOT_FOUND_MESSAGE);
        }

        return post.get();
    }

    @Override
    @Transactional

    public void deletePostById(String id) throws PostNotFoundException {
        Post post = this.findPostById(id);
        this.postRepository.delete(post);
    }
}
