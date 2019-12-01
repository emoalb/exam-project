package com.exam.examproject.services.services.impl;


import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.models.RegisterUserServiceModel;
import com.exam.examproject.services.services.AuthValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthValidationServiceImpl implements AuthValidationService {

    private UserRepository userRepository;


    @Autowired
    public AuthValidationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(RegisterUserServiceModel userServiceModel) {
        return this.isEmailValid(userServiceModel.getEmail())
                && this.arePasswordsValid(userServiceModel.getPassword(), userServiceModel.getRepeatPassword())
                && this.isUsernameValid(userServiceModel.getUsername());
    }

    private boolean arePasswordsValid(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isUsernameValid(String username) {
        return username.length()!=0 && this.userRepository.findByUsername(username).isEmpty();
    }

    private boolean isEmailValid(String email)
    {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
