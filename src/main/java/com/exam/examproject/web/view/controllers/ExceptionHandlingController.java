package com.exam.examproject.web.view.controllers;

import com.exam.examproject.errors.CommentNotFoundException;
import com.exam.examproject.errors.PostNotFoundException;
import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.web.base.BaseController;
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

    @ExceptionHandler(CommentNotFoundException.class)
    public ModelAndView handleException(CommentNotFoundException exception) {
        return super.renderError(exception.getMessage());
    }
}
