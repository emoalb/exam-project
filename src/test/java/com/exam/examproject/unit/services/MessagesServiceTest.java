package com.exam.examproject.unit.services;

import com.exam.examproject.common.Constants;
import com.exam.examproject.domain.entities.Message;
import com.exam.examproject.domain.entities.User;
import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.repositories.MessageRepository;
import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.models.CreateMessageServiceModel;
import com.exam.examproject.services.services.HashingService;
import com.exam.examproject.services.services.MessagesService;

import com.exam.examproject.services.services.impl.MessagesServiceImpl;
import org.junit.Before;
import org.junit.Test;


import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)

public class MessagesServiceTest {
    @InjectMocks
    MessagesServiceImpl messagesService;

    @Mock
    MessageRepository mockMessageRepository;

    @Mock
    UserRepository mockUserRepository;

    private User user;
    private Message message;

    @Before
    public void setup() {
        user = mock(User.class);
        message = mock(Message.class);

    }


    @Test(expected = UserNotFoundException.class)
    public void getAllMessages_whenUserIdIsInvalid_shouldThrowUserNotFoundException() {
        String userId = "1233456";
        Mockito.when(mockUserRepository.findById(userId)).thenReturn(Optional.empty());
        messagesService.getAllMessages(userId);
    }

    @Test(expected = UserNotFoundException.class)
    public void sendMessage_whenSenderIsInvalid_shouldThrowUserNotFoundException() {

        String senderName = "sender";
        String receiverName = "receiver";
        Mockito.when(mockUserRepository.findByUsername(senderName)).thenReturn(Optional.empty());
        Mockito.when(mockUserRepository.findByUsername(receiverName)).thenReturn(Optional.of(user));
        //Mockito.when(mockMessageRepository.save(message)).thenThrow(new UserNotFoundException(Constants.USER_NOT_FOUND_MESSAGE));
        CreateMessageServiceModel createMessageServiceModel = new CreateMessageServiceModel();
        createMessageServiceModel.setSender(senderName);
        createMessageServiceModel.setReceiver(receiverName);
        createMessageServiceModel.setMessage("some msg");
        messagesService.sendMessage(createMessageServiceModel);
    }
}
