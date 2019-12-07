package com.exam.examproject.web.view.controllers;


import com.exam.examproject.services.models.LoginResponseModel;
import com.exam.examproject.services.models.LoginUserServiceModel;
import com.exam.examproject.services.models.RegisterUserServiceModel;
import com.exam.examproject.services.services.AuthService;
import com.exam.examproject.web.base.BaseController;
import com.exam.examproject.web.view.models.LoginUserViewModel;
import com.exam.examproject.web.view.models.RegisterUserViewModel;
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
    public ModelAndView getRegisterForm(HttpSession session) {
        return super.render("register");
    }

    @GetMapping("/login")
    public ModelAndView getLoginForm(HttpSession session) {
        return super.render("login");
    }

    @GetMapping("/logout")
    public ModelAndView getLogout(HttpSession session) {
        session.setAttribute("user", null);
        return super.redirect("/");
    }

    @PostMapping("/register")
    public ModelAndView postRegisterForm(@ModelAttribute RegisterUserViewModel registerUserViewModel, HttpSession session) {
        RegisterUserServiceModel registerUserServiceModel = this.modelMapper.map(registerUserViewModel, RegisterUserServiceModel.class);
        try {
            this.authService.register(registerUserServiceModel);
        } catch (Exception e) {
            return super.renderError(e.getMessage());
        }

        return super.redirect("/users/login");
    }

    @PostMapping("/login")
    public ModelAndView postRegisterForm(@ModelAttribute LoginUserViewModel loginUserViewModel, HttpSession session) {
        LoginUserServiceModel loginUserServiceModel = this.modelMapper.map(loginUserViewModel, LoginUserServiceModel.class);
        try {
            LoginResponseModel loginResponseModel = this.authService.login(loginUserServiceModel);
            session.setAttribute("user", loginResponseModel);
        } catch (Exception e) {
            return super.renderError(e.getMessage());
        }
        return super.redirect("/");
    }
}