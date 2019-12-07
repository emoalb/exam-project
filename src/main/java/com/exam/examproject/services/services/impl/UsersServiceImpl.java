package com.exam.examproject.services.services.impl;

import com.exam.examproject.domain.entities.User;
import com.exam.examproject.domain.enums.UserRole;
import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.models.AllUsersServiceModel;
import com.exam.examproject.services.services.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl  implements UsersService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UsersServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AllUsersServiceModel> allUsers() {
        List<User> users = this.userRepository.findAllByOrderByRoleAscUsernameAsc();
        List<AllUsersServiceModel> allUsersServiceModels = users.stream().map(user ->
                this.modelMapper.map(user, AllUsersServiceModel.class)).collect(Collectors.toList());
        return allUsersServiceModels;
    }

    @Override
    public void deleteUserById(String id) throws UserNotFoundException {
        Optional<User> user = this.userRepository.findById(id);
        if(user.isEmpty())throw new UserNotFoundException("Invalid user!");
        this.userRepository.delete(user.get());
    }

    @Override
    public void upgradeToAdmin(String id) throws UserNotFoundException {
        Optional<User> userOptional = this.userRepository.findById(id);
        if(userOptional.isEmpty()){
            System.out.println("Exception");
            throw new UserNotFoundException("Invalid user!");
        }
        User user = userOptional.get();
        user.setRole(UserRole.ADMIN);
        this.userRepository.save(user);
    }
}
