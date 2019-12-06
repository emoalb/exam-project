package com.exam.examproject.web.controllers;

import com.exam.examproject.errors.PostNotFoundException;
import com.exam.examproject.errors.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlingController extends BaseController {
    @ExceptionHandler(PostNotFoundException.class)
    public ModelAndView handleException(PostNotFoundException exception) {
        return super.renderError(exception.getMessage());
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleException(UserNotFoundException exception) {
        return super.renderError(exception.getMessage());
    }
}
