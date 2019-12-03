package com.exam.examproject.web.controllers;

import com.exam.examproject.services.models.CommentServiceModel;
import com.exam.examproject.services.models.CreateCommentServiceModel;
import com.exam.examproject.services.models.CreateMessageServiceModel;
import com.exam.examproject.services.models.LoginResponseModel;
import com.exam.examproject.services.services.CommentsService;
import com.exam.examproject.web.models.CreateCommentViewModel;
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

    @ModelAttribute("createCommentViewModel")
    public CreateCommentViewModel createCommentViewModel(){
        return new CreateCommentViewModel();
    }
@Autowired
public CommentController(CommentsService commentsService, CommentsService commentsService1){

    this.commentsService = commentsService1;
}
    @GetMapping("/all")
    public ModelAndView getAllComments(@ModelAttribute("createCommentViewModel")CreateCommentViewModel createCommentViewModel, @RequestParam(value = "id", required = true) String id) {
//        LoginResponseModel loginResponseModel = (LoginResponseModel) session.getAttribute("user");
//        List<MessageServiceModel> messageServiceModels = null;
//
//        messageServiceModels = this.messagesService.getAllMessages(loginResponseModel.getId());

        List<CommentServiceModel> commentServiceModels = this.commentsService.getAllComments(id);

        return super.render("comments/all","allComments",commentServiceModels );
    }
    @PostMapping("/create")
    public ModelAndView postComment(@Valid @ModelAttribute("createCommentViewModel") CreateCommentViewModel createCommentViewModel, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return super.redirect("comments/all");
        }

      return super.redirect("/all") ;
    }
}
