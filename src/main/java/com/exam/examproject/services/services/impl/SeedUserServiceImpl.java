package com.exam.examproject.services.services.impl;

import com.exam.examproject.domain.entities.User;
import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.models.SeedUserServiceModel;
import com.exam.examproject.services.services.SeedUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SeedUserServiceImpl implements SeedUserService {
private final UserRepository userRepository;
private final ModelMapper modelMapper;
    public SeedUserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void SeedUser(SeedUserServiceModel seedUserServiceModel) {
       User user = this.modelMapper.map(seedUserServiceModel, User.class);
        this.userRepository.save(user);

    }
}
