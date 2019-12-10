package com.exam.examproject.services.services;

import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.services.models.AllUsersServiceModel;
import com.exam.examproject.services.models.UserServiceModel;

import java.util.List;

public interface UsersService {
  List<AllUsersServiceModel> allUsers();
  void deleteUserById(String id) throws UserNotFoundException;
  void upgradeToAdmin(String id) throws UserNotFoundException;
  UserServiceModel findUserById(String userId) throws UserNotFoundException;
}
