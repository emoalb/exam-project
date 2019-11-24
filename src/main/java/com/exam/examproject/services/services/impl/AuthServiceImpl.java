package com.exam.examproject.services.services.impl;

import com.exam.examproject.domain.entities.User;
import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.models.RegisterUserServiceModel;
import com.exam.examproject.services.services.AuthService;
import com.exam.examproject.services.services.HashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final HashingService hashingService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, HashingService hashingService) {
        this.userRepository = userRepository;

        this.hashingService = hashingService;
    }

    @Override
    public void register(RegisterUserServiceModel registerUserServiceModel) {

this.userRepository.save((User)(new Object())) ;
    }
}
