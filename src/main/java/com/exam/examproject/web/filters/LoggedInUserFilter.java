package com.exam.examproject.web.filters;

import com.exam.examproject.services.models.LoginResponseModel;
import com.exam.examproject.services.services.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoggedInUserFilter implements Filter {
    private final AuthenticatedUserService authenticatedUserService;

    @Autowired
    public LoggedInUserFilter(AuthenticatedUserService authenticatedUserService) {
        this.authenticatedUserService = authenticatedUserService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        LoginResponseModel loginResponseModel = authenticatedUserService.loginResponseModel();

        if (loginResponseModel == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        session.setAttribute("user", loginResponseModel);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

