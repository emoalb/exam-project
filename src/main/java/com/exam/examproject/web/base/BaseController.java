package com.exam.examproject.web.base;


import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    protected ModelAndView render(String viewName) {
        ModelAndView modelAndView = new ModelAndView("_layouts/index");
        modelAndView.addObject("view", viewName);
        return modelAndView;
    }


    protected ModelAndView render(String viewName, String attrName, Object object) {
        ModelAndView modelAndView = new ModelAndView("_layouts/index");
        modelAndView.addObject(attrName, object);
        modelAndView.addObject("view", viewName);
        return modelAndView;
    }

    protected ModelAndView render(String viewName, String[] attrNames, Object[] objects) {
        ModelAndView modelAndView = new ModelAndView("_layouts/index");
        for (int i = 0; i < attrNames.length; i++) {
            modelAndView.addObject(attrNames[i], objects[i]);
        }
        modelAndView.addObject("view", viewName);
        return modelAndView;
    }

    protected ModelAndView renderError(String message) {
        ModelAndView modelAndView = new ModelAndView("_layouts/index");
        modelAndView.addObject("view", "errors/error");
        modelAndView.addObject("errorMessage", message);
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    protected ModelAndView redirect(String url) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:" + url);

        return modelAndView;
    }


}
