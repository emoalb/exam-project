package com.exam.examproject.web.controllers;


import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    public ModelAndView render(String viewName) {
        ModelAndView modelAndView = new ModelAndView("_layouts/index");
        modelAndView.addObject("view", viewName);
        return modelAndView;
    }
    public ModelAndView renderWithError(String viewName, String message){
        ModelAndView modelAndView = new ModelAndView("_layouts/index");
        modelAndView.addObject("view", viewName);
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    public ModelAndView render(String viewName, String attrName, Object object) {
        ModelAndView modelAndView = new ModelAndView("_layouts/index");
        modelAndView.addObject(attrName, object);
        modelAndView.addObject("view", viewName);
        return modelAndView;
    }
    public ModelAndView redirect(String url) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:" + url);

        return modelAndView;
    }


}
