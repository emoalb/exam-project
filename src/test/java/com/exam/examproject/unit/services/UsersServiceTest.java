package com.exam.examproject.unit.services;

import com.exam.examproject.domain.entities.User;
import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.repositories.UserRepository;


import com.exam.examproject.services.models.AllUsersServiceModel;
import com.exam.examproject.services.models.UserServiceModel;
import com.exam.examproject.services.services.UsersService;
import com.exam.examproject.services.services.impl.UsersServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UsersServiceTest {
    @InjectMocks
    UsersServiceImpl usersService;

    @Mock
    private UserRepository mockedUserRepository;
    @Mock
    private ModelMapper mockedModelMapper;


    private User user;

    @Before
    public void init() {
        user = Mockito.mock(User.class);
    }


    @Test(expected = UserNotFoundException.class)
    public void deleteUserById_whenIdIsInvalid_shouldThrowUserNotFountException() {
        String userId = "id";
        Mockito.when(mockedUserRepository.findById(userId)).thenReturn(Optional.empty());
        usersService.deleteUserById(userId);
    }

    @Test
    public void getAllUsers_shouldReturnAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user);
        Mockito.when(mockedUserRepository.findAllByOrderByUsernameAsc()).thenReturn(users);
     List<AllUsersServiceModel> allUsersServiceModels = usersService.allUsers();
        System.out.println();
      assertEquals(users.size(), allUsersServiceModels.size());
    }

}
