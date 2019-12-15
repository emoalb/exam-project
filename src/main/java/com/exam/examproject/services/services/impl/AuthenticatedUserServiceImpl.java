package com.exam.examproject.services.services.impl;

import com.exam.examproject.services.models.LoginResponseModel;
import com.exam.examproject.services.services.AuthenticatedUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class AuthenticatedUserServiceImpl implements AuthenticatedUserService {
    private final ModelMapper modelMapper;

    @Autowired
    public AuthenticatedUserServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public LoginResponseModel loginResponseModel() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) {
            return null;
        }
        LoginResponseModel loginResponseModel = this.modelMapper.map(authentication.getPrincipal(), LoginResponseModel.class);
        return loginResponseModel;
    }
}
