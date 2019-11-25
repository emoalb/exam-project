package com.exam.examproject.web.controllers;



import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

public ModelAndView render(String viewName){
ModelAndView modelAndView = new ModelAndView("_layouts/index");
modelAndView.addObject("view",viewName);
return modelAndView;
}

    public ModelAndView redirect(String url) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:" + url);

        return modelAndView;
    }
}
