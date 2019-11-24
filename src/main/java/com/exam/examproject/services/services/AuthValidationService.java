package com.exam.examproject.services.services;


import com.exam.examproject.services.models.RegisterUserServiceModel;

public interface AuthValidationService {
    boolean isValid(RegisterUserServiceModel userServiceModel);

}
