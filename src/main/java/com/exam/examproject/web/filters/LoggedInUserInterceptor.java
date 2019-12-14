package com.exam.examproject.web.filters;


import com.exam.examproject.services.models.LoginResponseModel;
import com.exam.examproject.services.services.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoggedInUserInterceptor implements AuthenticationSuccessHandler {
    private final AuthenticatedUserService authenticatedUserService;
    private final RedirectStrategy redirectStrategy;

    @Autowired
    public LoggedInUserInterceptor(AuthenticatedUserService authenticatedUserService, RedirectStrategy redirectStrategy) {
        super();
        this.authenticatedUserService = authenticatedUserService;

        this.redirectStrategy = redirectStrategy;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
        LoginResponseModel loginResponseModel = authenticatedUserService.loginResponseModel();
        if (loginResponseModel != null) {
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("user", loginResponseModel);
        }
        this.redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/");
    }
}
