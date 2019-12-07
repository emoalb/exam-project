package com.exam.examproject.web.view.controllers;

import com.exam.examproject.services.models.PostServiceModel;
import com.exam.examproject.services.services.PostsService;
import com.exam.examproject.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @ResponseBody
    public ModelAndView getHomePage() {
        //List<PostServiceModel> postServiceModels = this.postsService.getAllPosts();
        return super.render("homeapi");
    }
}
