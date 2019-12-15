package com.exam.examproject.web.view.controllers;

import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.services.models.CommentServiceModel;
import com.exam.examproject.services.models.CreateCommentServiceModel;
import com.exam.examproject.services.models.LoginResponseModel;
import com.exam.examproject.services.services.CommentsService;
import com.exam.examproject.web.base.BaseController;
import com.exam.examproject.web.view.models.CreateCommentViewModel;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController extends BaseController {
    private final CommentsService commentsService;
    private final ModelMapper modelMapper;

    @ModelAttribute("createCommentViewModel")
    public CreateCommentViewModel createCommentViewModel() {
        return new CreateCommentViewModel();
    }

    @Autowired
    public CommentController(CommentsService commentsService, CommentsService commentsService1, ModelMapper modelMapper) {

        this.commentsService = commentsService1;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all/{id}")
    public ModelAndView getAllComments(@ModelAttribute("createCommentViewModel") CreateCommentViewModel createCommentViewModel, @PathVariable("id") String id) {
        return getModelAndView(id);
    }



    @PostMapping("/all/{id}")
    public ModelAndView postComment(@Valid @ModelAttribute("createCommentViewModel") CreateCommentViewModel createCommentViewModel, BindingResult bindingResult,
                                    @PathVariable("id") String id, HttpSession session) {
        if (session.getAttribute("user") == null) throw new UserNotFoundException("Invalid user!");
        if (bindingResult.hasErrors()) {
            return getModelAndView(id);
        }
        CreateCommentServiceModel createCommentServiceModel = this.modelMapper.map(createCommentViewModel, CreateCommentServiceModel.class);
        LoginResponseModel loginResponseModel = (LoginResponseModel) session.getAttribute("user");
        createCommentServiceModel.setCreatorId(loginResponseModel.getId());
        createCommentServiceModel.setPostId(id);
        this.commentsService.createComment(createCommentServiceModel);
        return super.redirect("/comments/all/" + id);
    }

    @GetMapping("/delete/{postId}/{commentId}")
    public ModelAndView getDeleteComment(@PathVariable("postId") String postId, @PathVariable("commentId") String commentId) {
        this.commentsService.deleteComment(commentId);
        return super.redirect("/comments/all/" + postId);
    }

    private ModelAndView getModelAndView(@PathVariable("id") String id) {
        List<CommentServiceModel> commentServiceModels = this.commentsService.getAllComments(id);
        String[] attrNames = {"allComments", "postId"};
        Object[] objects = {commentServiceModels, id};
        return super.render("comments/all", attrNames, objects);
    }
}
