package com.exam.examproject.web.filters;

import com.exam.examproject.common.Constants;
import com.exam.examproject.services.services.DatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class LoggingInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private DatesService datesService;


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String url = request.getRequestURL().toString();
        String ipAddress = request.getRemoteAddr();
        String method = request.getMethod();
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        String currentDate = formatter.format(datesService.getCurrentDate());
        //time
        //URL
        //User ip
        // HTTP METHOD

        System.out.println("Request url: " + url + " Remote ip " + ipAddress + " Method: " + method + " Date: " + currentDate);
    }
}
