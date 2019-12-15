package com.exam.examproject.web.view.controllers;

import com.exam.examproject.web.base.BaseController;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorsController extends BaseController implements ErrorController {
    @GetMapping("/error")
    public ModelAndView renderErrorPage() {

       return   super.renderError("404 not found!");

    }
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
