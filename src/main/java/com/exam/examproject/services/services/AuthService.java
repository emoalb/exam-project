package com.exam.examproject.services.services;

import com.exam.examproject.services.models.LoginResponseModel;
import com.exam.examproject.services.models.LoginUserServiceModel;
import com.exam.examproject.services.models.RegisterUserServiceModel;

public interface AuthService {
    void register(RegisterUserServiceModel registerUserServiceModel) throws Exception;

    LoginResponseModel login(LoginUserServiceModel loginUserServiceModel) throws Exception;

    void SeedModerator();
}
