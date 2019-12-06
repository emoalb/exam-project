package com.exam.examproject.web.controllers;

import com.exam.examproject.errors.PostNotFoundException;
import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.services.models.CreatePostServiceModel;
import com.exam.examproject.services.models.DetailsPostServiceModel;
import com.exam.examproject.services.models.EditPostServiceModel;
import com.exam.examproject.services.models.LoginResponseModel;
import com.exam.examproject.web.models.EditPostViewModel;
import com.exam.examproject.services.services.PostsService;
import com.exam.examproject.web.models.CreatePostViewModel;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/posts")
public class PostController extends BaseController {

    private final ModelMapper modelMapper;
    private final PostsService postService;
    private final Gson gson;


    @Autowired
    public PostController(ModelMapper modelMapper, PostsService postService, Gson gson) {
        this.modelMapper = modelMapper;
        this.postService = postService;
        this.gson = gson;
    }

    @ModelAttribute("createPostModel")
    public CreatePostViewModel createPostModel() {
        return new CreatePostViewModel();
    }

    @GetMapping("/new")
    public ModelAndView getCreatePost(@ModelAttribute("createPostModel") CreatePostViewModel createPostModel,HttpSession session) {
        if(session.getAttribute("user")==null)throw  new UserNotFoundException("Invalid user!");
        return super.render("posts/create");
    }

    @PostMapping("/new")
    public ModelAndView postCreatePost(@Valid @ModelAttribute("createPostModel") CreatePostViewModel createPostModel, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return super.render("posts/create");
        }
        CreatePostServiceModel createPostServiceModel = this.modelMapper.map(createPostModel, CreatePostServiceModel.class);


        LoginResponseModel loginResponseModel = (LoginResponseModel) session.getAttribute("user");
        createPostServiceModel.setCreator_id(loginResponseModel.getId());
        this.postService.createPost(createPostServiceModel);

        return super.redirect("/");
    }

    @GetMapping("/edit")
    public ModelAndView getEditPost(@RequestParam(value = "id", required = true) String id) {

        EditPostServiceModel editPostServiceModel = this.postService.findPostToEdit(id);
        return super.render("posts/edit", "post", editPostServiceModel);

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView putEditPost(@RequestBody String jsonResponse, HttpSession session) {
        System.out.println(jsonResponse);
        EditPostViewModel editViewModel = this.gson.fromJson(jsonResponse, EditPostViewModel.class);
        EditPostServiceModel editPostServiceModel = this.modelMapper.map(editViewModel, EditPostServiceModel.class);

        this.postService.updatePost(editPostServiceModel);
        return super.redirect("/");
    }

    @GetMapping("/details")
    public ModelAndView getDetailsPost(@RequestParam(value = "id", required = true) String id) {

        DetailsPostServiceModel detailsPostServiceModel = this.postService.findPostDetails(id);
        return super.render("posts/details", "post", detailsPostServiceModel);

    }

    @GetMapping("/delete")
    public ModelAndView getDeletePost(@RequestParam(value = "id", required = true) String id) {
        System.out.println(id);
            this.postService.deletePostById(id);


        return super.redirect("/");
    }


}
