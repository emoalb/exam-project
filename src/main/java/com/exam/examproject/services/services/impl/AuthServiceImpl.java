package com.exam.examproject.services.services.impl;

import com.exam.examproject.domain.entities.User;
import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.models.LoginResponseModel;
import com.exam.examproject.services.models.LoginUserServiceModel;
import com.exam.examproject.services.models.RegisterUserServiceModel;
import com.exam.examproject.services.services.AuthService;
import com.exam.examproject.services.services.AuthValidationService;
import com.exam.examproject.services.services.HashingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final AuthValidationService authValidationService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, HashingService hashingService, AuthValidationService authValidationService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.authValidationService = authValidationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(RegisterUserServiceModel registerUserServiceModel) throws Exception {
        registerUserServiceModel.setUsername(registerUserServiceModel.getUsername().toLowerCase());
        if (!authValidationService.isValid(registerUserServiceModel)) {
            throw new Exception("Invalid user data");
        }
        User user = this.modelMapper.map(registerUserServiceModel, User.class);
        user.setPassword(this.hashingService.hash(user.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public LoginResponseModel login(LoginUserServiceModel loginUserServiceModel) throws Exception {
       loginUserServiceModel.setUsername(loginUserServiceModel.getUsername().toLowerCase());
        Optional<User> userOptional = this.userRepository.findByUsernameAndPassword(loginUserServiceModel.getUsername(), this.hashingService.hash(loginUserServiceModel.getPassword()));
        if (userOptional.isEmpty()) {
            throw new Exception("Invalid user");
        }
        User user = userOptional.get();
        return new LoginResponseModel(user.getId(),user.getUsername());
    }
}
