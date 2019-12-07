package com.exam.examproject.web.view.controllers;


import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.services.models.CreateMessageServiceModel;
import com.exam.examproject.services.models.LoginResponseModel;
import com.exam.examproject.services.models.MessageServiceModel;
import com.exam.examproject.services.services.MessagesService;
import com.exam.examproject.web.base.BaseController;
import com.exam.examproject.web.view.models.CreateMessageViewModel;
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
    public ModelAndView getAllMessages(HttpSession session) {
        if(session.getAttribute("user")==null)throw new UserNotFoundException("Invalid user!");
        LoginResponseModel loginResponseModel = (LoginResponseModel) session.getAttribute("user");
        List<MessageServiceModel> messageServiceModels =  this.messagesService.getAllMessages(loginResponseModel.getId());

        return super.render("messages/all", "messages", messageServiceModels);
    }

    @GetMapping("/new")
    public ModelAndView getCreateNewMessage(@ModelAttribute("createMessageViewModel") CreateMessageViewModel createMessageViewModel, @RequestParam(value = "name", required = true) String name) {
        createMessageViewModel.setReceiver(name);
        return super.render("messages/create");
    }

    @PostMapping("/new")
    public ModelAndView postCreateNewMessage(@Valid @ModelAttribute("createMessageViewModel") CreateMessageViewModel createMessageViewModel, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return super.render("messages/create");
        }
        CreateMessageServiceModel createMessageServiceModel = this.modelMapper.map(createMessageViewModel, CreateMessageServiceModel.class);
        if(session.getAttribute("user")==null)throw new UserNotFoundException("Invalid user!");
        createMessageServiceModel.setSender(((LoginResponseModel) session.getAttribute("user")).getUsername());
        this.messagesService.sendMessage(createMessageServiceModel);

        return super.redirect("/");
    }

    @GetMapping("/delete")
    public ModelAndView getDeleteMessage(@RequestParam(value = "id", required = true) String id) {
        this.messagesService.deleteMessage(id);
        return super.redirect("/messages/all");

    }
}
