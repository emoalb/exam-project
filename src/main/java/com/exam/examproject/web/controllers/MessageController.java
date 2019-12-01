package com.exam.examproject.web.controllers;


import com.exam.examproject.services.models.CreateMessageServiceModel;
import com.exam.examproject.services.models.LoginResponseModel;
import com.exam.examproject.services.models.MessageServiceModel;
import com.exam.examproject.services.services.MessagesService;
import com.exam.examproject.web.models.CreateMessageViewModel;
import com.exam.examproject.web.models.CreatePostViewModel;
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
@RequestMapping("/messages")
public class MessageController extends BaseController {
    private final ModelMapper modelMapper;
    private final MessagesService messagesService;

    @Autowired
    public MessageController(ModelMapper modelMapper, MessagesService messagesService) {
        this.modelMapper = modelMapper;
        this.messagesService = messagesService;
    }
    @ModelAttribute("createMessageViewModel")
    public CreateMessageViewModel createMessageViewModel() {
        return new CreateMessageViewModel();
    }

    @GetMapping("/all")
    public ModelAndView getAllMessages() {
        List<MessageServiceModel> messageServiceModels = this.messagesService.getAllMessages();
        return super.render("messages/all","messages",messageServiceModels);
    }
    @GetMapping("/new")
    public ModelAndView getCreateNewMessage(@ModelAttribute("createMessageViewModel") CreateMessageViewModel createMessageViewModel, @RequestParam(value = "name", required = true) String name) {

        return super.render("messages/create","receiver",name);
    }
    @PostMapping("/new")
    public ModelAndView postCreateNewMessage(@Valid @ModelAttribute("createMessageViewModel") CreateMessageViewModel createMessageViewModel, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return super.render("messages/create");
        }
        try {
            CreateMessageServiceModel createMessageServiceModel = this.modelMapper.map(createMessageViewModel,CreateMessageServiceModel.class);
            createMessageServiceModel.setSender(((LoginResponseModel)session.getAttribute("user")).getUsername());
            this.messagesService.sendMessage(createMessageServiceModel);
        } catch (Exception e) {
            return super.renderWithError("message/create","Error sending message!");
        }
        return super.redirect("/");
    }
}
