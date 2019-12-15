package com.exam.examproject.web.view.controllers;

import com.exam.examproject.common.Constants;
import com.exam.examproject.web.base.BaseController;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorsController extends BaseController implements ErrorController {
    @GetMapping("/error")
    public ModelAndView renderErrorPage() {

       return   super.renderError(Constants.NOT_FOUND_404);

    }
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
