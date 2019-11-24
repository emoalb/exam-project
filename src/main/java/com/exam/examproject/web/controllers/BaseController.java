package com.exam.examproject.web.controllers;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

public ModelAndView render(String viewName){
ModelAndView modelAndView = new ModelAndView("_layouts/index");
modelAndView.addObject("view",viewName);
return modelAndView;
}
}
