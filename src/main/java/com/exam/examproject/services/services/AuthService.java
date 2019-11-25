package com.exam.examproject.services.services;

import com.exam.examproject.services.models.RegisterUserServiceModel;

public interface AuthService {
    void register(RegisterUserServiceModel registerUserServiceModel) throws Exception;

}
