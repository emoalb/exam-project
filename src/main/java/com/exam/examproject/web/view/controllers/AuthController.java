package com.exam.examproject.web.view.controllers;



import com.exam.examproject.services.models.RegisterUserServiceModel;
import com.exam.examproject.services.services.AuthService;
import com.exam.examproject.web.base.BaseController;
import com.exam.examproject.web.view.models.RegisterUserViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class AuthController extends BaseController {
    private final ModelMapper modelMapper;
    private final AuthService authService;

    @Autowired
    public AuthController(ModelMapper modelMapper, AuthService authService) {
        this.modelMapper = modelMapper;
        this.authService = authService;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterForm(@ModelAttribute("registerUserViewModel") RegisterUserViewModel registerUserViewModel, HttpSession session) {
        return super.render("register");
    }

    @GetMapping("/login")
    public ModelAndView getLoginForm(HttpSession session) {
        return super.render("login");
    }


    @PostMapping("/register")
    public ModelAndView postRegisterForm(@Valid @ModelAttribute("registerUserViewModel") RegisterUserViewModel registerUserViewModel, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return super.render("register");

        }
        RegisterUserServiceModel registerUserServiceModel = this.modelMapper.map(registerUserViewModel, RegisterUserServiceModel.class);
        try {
            this.authService.register(registerUserServiceModel);
        } catch (Exception e) {
            return super.render("register","isInvalid",true);
        }

        return super.redirect("/users/login");
    }

}
