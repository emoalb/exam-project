package com.exam.examproject.services;

import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.repositories.UserRepository;


import com.exam.examproject.services.services.UsersService;
import com.exam.examproject.services.services.impl.UsersServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsersServiceTest {
    private UserRepository mockedUserRepository;
    private ModelMapper mockedModelMapper;

    @Before
    public void init() {
        this.mockedUserRepository = Mockito.mock(UserRepository.class);
        this.mockedModelMapper = Mockito.mock(ModelMapper.class);
    }

    @Test
    public void deleteUserById_whenIdIsInvalid_shouldThrowUserNotFountException() {
        String userId = "123ABC";
        UsersService usersService = new UsersServiceImpl(this.mockedUserRepository,this.mockedModelMapper );
        Mockito.when(mockedUserRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> usersService.deleteUserById(userId));
    }

}
