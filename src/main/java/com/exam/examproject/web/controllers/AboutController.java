package com.exam.examproject.web.controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutController extends BaseController {
    @GetMapping("/about")
    public ModelAndView getHomePage() {
        return super.render("about");
    }
}
