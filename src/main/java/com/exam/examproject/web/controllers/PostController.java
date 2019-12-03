package com.exam.examproject.web.controllers;

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
    public ModelAndView getCreatePost(@ModelAttribute("createPostModel") CreatePostViewModel createPostModel) {
        return super.render("posts/create");
    }

    @PostMapping("/new")
    public ModelAndView postCreatePost(@Valid @ModelAttribute("createPostModel") CreatePostViewModel createPostModel, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return super.render("posts/create");
        }
        CreatePostServiceModel createPostServiceModel = this.modelMapper.map(createPostModel, CreatePostServiceModel.class);

        try {
            LoginResponseModel loginResponseModel = (LoginResponseModel) session.getAttribute("user");
            createPostServiceModel.setCreator_id(loginResponseModel.getId());
            this.postService.createPost(createPostServiceModel);
        } catch (Exception e) {
            return super.renderWithError("posts/create", "Error creating post");
        }
        return super.redirect("/");
    }

    @GetMapping("/edit")
    public ModelAndView getEditPost(@RequestParam(value = "id", required = true) String id) {
        try {
            EditPostServiceModel editPostServiceModel = this.postService.findPostToEdit(id);
            return super.render("posts/edit", "post", editPostServiceModel);
        } catch (Exception e) {
            return super.renderWithError("home", e.getMessage());

        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView putEditPost(@RequestBody String jsonResponse, HttpSession session) {
        System.out.println(jsonResponse);
        EditPostViewModel editViewModel = this.gson.fromJson(jsonResponse, EditPostViewModel.class);
        EditPostServiceModel editPostServiceModel = this.modelMapper.map(editViewModel, EditPostServiceModel.class);
        try {
            this.postService.updatePost(editPostServiceModel);
        } catch (Exception e) {

            return super.renderWithError("posts/edit", e.getMessage());
        }
        return super.redirect("/");
    }

    @GetMapping("/details")
    public ModelAndView getDetailsPost(@RequestParam(value = "id", required = true) String id) {
        try {
            DetailsPostServiceModel detailsPostServiceModel = this.postService.findPostDetails(id);
            return super.render("posts/details", "post", detailsPostServiceModel);
        } catch (Exception e) {
            return super.renderWithError("home", e.getMessage());

        }
    }

    @GetMapping("/delete")
    public ModelAndView getDeletePost(@RequestParam(value = "id", required = true) String id) {
        System.out.println(id);
        try {
            this.postService.deletePostById(id);

        } catch (Exception e) {
            return super.renderWithError("home", e.getMessage());
        }
        return super.redirect("/");
    }


}
