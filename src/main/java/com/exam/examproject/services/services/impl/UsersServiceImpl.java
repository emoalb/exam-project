package com.exam.examproject.services.services.impl;

import com.exam.examproject.common.Constants;
import com.exam.examproject.domain.entities.Role;
import com.exam.examproject.domain.entities.User;
import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.models.AllUsersServiceModel;
import com.exam.examproject.services.models.CustomUser;
import com.exam.examproject.services.models.UserServiceModel;
import com.exam.examproject.services.services.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UsersServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AllUsersServiceModel> allUsers() {

        List<User> users = this.userRepository.findAllByOrderByUsernameAsc();

        List<AllUsersServiceModel> allUsersServiceModels = users.stream().map(user ->
                new AllUsersServiceModel(user.getId(), user.getUsername(), user.getEmail(), user.getCreationDate(), ((Role) user.getAuthorities().toArray()[0]).getAuthority())
        ).collect(Collectors.toList());
        return allUsersServiceModels;
    }


    @Override
    public void deleteUserById(String id) throws UserNotFoundException {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException(Constants.USER_NOT_FOUND_MESSAGE);
        this.userRepository.delete(user.get());
    }

    @Override
    public void upgradeToAdmin(String id) throws UserNotFoundException {
        this.changeUserStatus(id, "ROLE_ADMIN");

    }

    @Override
    public UserServiceModel findUserById(String userId) throws UserNotFoundException {
        Optional<User> userOptional = this.userRepository.findById(userId);
        if (userOptional.isEmpty()) throw new UserNotFoundException(Constants.USER_NOT_FOUND_MESSAGE);
        UserServiceModel userServiceModel = this.modelMapper.map(userOptional.get(), UserServiceModel.class);
        return userServiceModel;
    }

    @Override
    public void demoteUser(String id) {
        this.changeUserStatus(id, "ROLE_USER");
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).get();

        Set<GrantedAuthority> authorities = new HashSet<>(user.getAuthorities());

        return new CustomUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    private void changeUserStatus(String id, String roleStr) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(Constants.USER_NOT_FOUND_MESSAGE);
        }
        User user = userOptional.get();
        Role role = new Role();
        role.setAuthority(roleStr);
        user.setAuthorities(new HashSet<>(Collections.singletonList(role)));
        this.userRepository.save(user);
    }
}
