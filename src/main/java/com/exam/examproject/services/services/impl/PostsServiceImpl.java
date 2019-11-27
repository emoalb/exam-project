package com.exam.examproject.services.services.impl;

import com.exam.examproject.domain.entities.Post;
import com.exam.examproject.domain.entities.User;
import com.exam.examproject.repositories.PostRepository;
import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.models.CreatePostServiceModel;
import com.exam.examproject.services.models.PostServiceModel;
import com.exam.examproject.services.services.PostsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void createPost(CreatePostServiceModel createPostServiceModel) throws Exception {
        Optional<User> user = this.userRepository.findById(createPostServiceModel.getCreator_id());
        Post post = this.modelMapper.map(createPostServiceModel,Post.class);
        if(user.isEmpty()){
            throw  new Exception("Invalid user!");
        }
        post.setCreator(user.get());
        this.postRepository.save(post);

    }

    @Override
    public List<PostServiceModel> getAllPosts() {
        List<Post> allPosts = this.postRepository.findAll();
        List<PostServiceModel> posts = allPosts.stream().map(post -> this.modelMapper.map(post,PostServiceModel.class)).collect(Collectors.toList());
        return posts;
    }
}