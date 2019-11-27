package com.exam.examproject.web.controllers;

import com.exam.examproject.services.models.CreatePostServiceModel;
import com.exam.examproject.services.models.LoginResponseModel;
import com.exam.examproject.services.services.PostsService;
import com.exam.examproject.web.models.CreatePostViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/posts")
public class PostController extends BaseController {

    private final ModelMapper modelMapper;
    private final PostsService postService;

    @Autowired
    public PostController(ModelMapper modelMapper, PostsService postService) {
        this.modelMapper = modelMapper;
        this.postService = postService;
    }

    @GetMapping("/new")
    public ModelAndView getCreatePost(){
        return super.render("posts/create");
    }
    @PostMapping("/new")
    public  ModelAndView postCreatePost(@ModelAttribute CreatePostViewModel createPostViewModel, HttpSession session){
        CreatePostServiceModel createPostServiceModel = this.modelMapper.map(createPostViewModel,CreatePostServiceModel.class);
        LoginResponseModel loginResponseModel  =(LoginResponseModel)session.getAttribute("user");
        createPostServiceModel.setCreator_id(loginResponseModel.getId());
        try {
            this.postService.createPost(createPostServiceModel);
        } catch (Exception e) {
            return super.render("/","message",e.getMessage());
        }
        return super.redirect("/");
    }
}
