package com.exam.examproject.services.services.impl;

import com.exam.examproject.domain.entities.User;
import com.exam.examproject.domain.enums.UserRole;
import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.models.LoginResponseModel;
import com.exam.examproject.services.models.LoginUserServiceModel;
import com.exam.examproject.services.models.RegisterUserServiceModel;
import com.exam.examproject.services.models.SeedUserServiceModel;
import com.exam.examproject.services.services.AuthService;
import com.exam.examproject.services.services.AuthValidationService;
import com.exam.examproject.services.services.HashingService;
import com.exam.examproject.services.services.SeedUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final SeedUserService seedUserService;
    private final AuthValidationService authValidationService;
    private final ModelMapper modelMapper;


    @Autowired
    public AuthServiceImpl(UserRepository userRepository, HashingService hashingService, SeedUserService seedUserService, AuthValidationService authValidationService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.seedUserService = seedUserService;
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
        Optional<User> userOptional = this.userRepository.findByUsernameAndPassword(loginUserServiceModel.getUsername(),
                this.hashingService.hash(loginUserServiceModel.getPassword()));
        if (userOptional.isEmpty()) {
            throw new Exception("Invalid user");
        }
        User user = userOptional.get();
        return new LoginResponseModel(user.getId(), user.getUsername(),user.getRole().toString());
    }

    @Override
    public void SeedModerator() {
        List<User> users = this.userRepository.findAllByRole(UserRole.MODERATOR);
        if (!users.isEmpty()) {
            System.out.println("Moderator already exist");
            return;
        }
        String username = "moderator";
        String email = "some@email.com";
        String password = this.hashingService.hash("12345");

        SeedUserServiceModel seedUserServiceModel =
                new SeedUserServiceModel(username, password, email, UserRole.MODERATOR);
        this.seedUserService.SeedUser(seedUserServiceModel);

    }
}
