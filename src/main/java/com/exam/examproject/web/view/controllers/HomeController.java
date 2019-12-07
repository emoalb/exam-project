package com.exam.examproject.web.view.controllers;

import com.exam.examproject.services.services.AuthService;
import com.exam.examproject.web.base.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class HomeController extends BaseController {

    private final AuthService authService;

    @Autowired
    public HomeController(AuthService authService) {

        this.authService = authService;
    }

    @GetMapping("/")
    @ResponseBody
    public ModelAndView getHomePage() {
       this.authService.SeedModerator();
        return super.render("home");
    }
}
