package com.exam.examproject.web.controllers;

import com.exam.examproject.services.models.PostServiceModel;
import com.exam.examproject.services.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController extends BaseController {
private final PostsService postsService;
@Autowired
    public HomeController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/")
    public ModelAndView getHomePage() {
        List<PostServiceModel> postServiceModels =  this.postsService.getAllPosts();
        return super.render("home");

    }
}
