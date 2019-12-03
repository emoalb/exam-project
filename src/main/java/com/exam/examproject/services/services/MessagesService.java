package com.exam.examproject.services.services;

import com.exam.examproject.domain.entities.Message;
import com.exam.examproject.services.models.CreateMessageServiceModel;
import com.exam.examproject.services.models.MessageServiceModel;

import java.util.List;


public interface MessagesService {

    void sendMessage(CreateMessageServiceModel createMessageServiceModel) throws Exception;
    List<MessageServiceModel>  getAllMessages(String username);
    void deleteMessage(String id);
}
