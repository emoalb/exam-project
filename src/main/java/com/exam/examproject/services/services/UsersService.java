package com.exam.examproject.services.services;

import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.services.models.AllUsersServiceModel;
import com.exam.examproject.services.models.LoginResponseModel;
import com.exam.examproject.services.models.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsersService extends UserDetailsService {
  List<AllUsersServiceModel> allUsers();
  UserServiceModel findUserByName(String name);
  void deleteUserById(String id) throws UserNotFoundException;
  void upgradeToAdmin(String id) throws UserNotFoundException;
  UserServiceModel findUserById(String userId) throws UserNotFoundException;
}
