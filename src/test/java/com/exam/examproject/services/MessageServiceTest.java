package com.exam.examproject.services;

import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.repositories.MessageRepository;
import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.services.MessagesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MessageServiceTest {
    @BeforeEach
    private void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @MockBean
    UserRepository userRepository;

    @Autowired
    MessagesService messagesService;

    @Test
    void getAllMessages_whenUserIdIsInvalid_shouldThrowUserNotFoundException(){
        String userId = "1233456";
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,()->messagesService.getAllMessages(userId));
    }
}
