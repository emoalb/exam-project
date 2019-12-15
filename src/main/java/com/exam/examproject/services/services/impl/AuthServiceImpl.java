package com.exam.examproject.services.services.impl;

import com.exam.examproject.common.Constants;
import com.exam.examproject.domain.entities.Role;
import com.exam.examproject.domain.entities.User;
import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.models.RegisterUserServiceModel;
import com.exam.examproject.services.models.SeedUserServiceModel;
import com.exam.examproject.services.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final SeedUserService seedUserService;
    private final AuthValidationService authValidationService;
    private final DatesService datesService;
    private final ModelMapper modelMapper;


    @Autowired
    public AuthServiceImpl(UserRepository userRepository, HashingService hashingService, SeedUserService seedUserService, AuthValidationService authValidationService, DatesService datesService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.seedUserService = seedUserService;
        this.authValidationService = authValidationService;
        this.datesService = datesService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(RegisterUserServiceModel registerUserServiceModel) throws Exception {
        registerUserServiceModel.setUsername(registerUserServiceModel.getUsername().toLowerCase());
        if (!authValidationService.isValid(registerUserServiceModel)) {
            throw new Exception(Constants.USER_NOT_FOUND_MESSAGE);
        }
        User user = this.modelMapper.map(registerUserServiceModel, User.class);
        user.setPassword(this.hashingService.hash(user.getPassword()));
        user.setCreationDate(this.datesService.getCurrentDate());
        Role role = new Role();
        role.setAuthority("ROLE_USER");
        user.setAuthorities(new HashSet<>(Collections.singletonList(role)));
        this.userRepository.save(user);
    }


    @Override
    public void SeedModerator() {

        List<User> users = this.userRepository.getAdminList("ROLE_OWNER");
        if (!users.isEmpty()) {
            System.out.println("Moderator already exist");
            return;
        }
        String username = "moderator";
        String email = "some@email.com";
        String password = this.hashingService.hash("12345");
        Role role = new Role();
        role.setAuthority("ROLE_OWNER");
        SeedUserServiceModel seedUserServiceModel =
                new SeedUserServiceModel(username, password, email, new HashSet<>(Collections.singletonList(role)));
        this.seedUserService.SeedUser(seedUserServiceModel);

    }
}
