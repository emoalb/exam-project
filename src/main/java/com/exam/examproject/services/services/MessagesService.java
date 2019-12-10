package com.exam.examproject.services.services;


import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.services.models.CreateMessageServiceModel;
import com.exam.examproject.services.models.MessageServiceModel;

import java.util.List;


public interface MessagesService {

    void sendMessage(CreateMessageServiceModel createMessageServiceModel) throws UserNotFoundException;
    List<MessageServiceModel>  getAllMessages(String userId);
    void deleteMessage(String id);
}
