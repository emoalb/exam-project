package com.exam.examproject.services.services.impl;

import com.exam.examproject.domain.entities.Message;
import com.exam.examproject.domain.entities.User;
import com.exam.examproject.repositories.MessageRepository;
import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.models.CreateMessageServiceModel;
import com.exam.examproject.services.models.MessageServiceModel;
import com.exam.examproject.services.services.MessagesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessagesServiceImpl implements MessagesService {
private final UserRepository userRepository;
private final MessageRepository messageRepository;
private final ModelMapper modelMapper;

@Autowired
    public MessagesServiceImpl(UserRepository userRepository, MessageRepository messageRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void sendMessage(CreateMessageServiceModel createMessageServiceModel) throws Exception {
        if(createMessageServiceModel.getMessage().length()==0){
            throw new Exception("Message is empty!");
        }
       Optional<User> sender = this.userRepository.findByUsername(createMessageServiceModel.getSender());
        if(sender.isEmpty()){
            throw new Exception("Invalid user sender");

        }
        Optional<User> receiver = this.userRepository.findByUsername(createMessageServiceModel.getReceiver());
        if(receiver.isEmpty()){
            throw new Exception("Invalid user sender");

        }
        Message message = new Message();
        message.setMessage(createMessageServiceModel.getMessage());
        message.setSendUser(sender.get());
        message.setReceiveUser(receiver.get());
        this.messageRepository.save(message);
    }

    @Override
    public List<MessageServiceModel> getAllMessages(String id) {

    List<Message> allMessages = this.messageRepository.getAllByReceiveUser_Id(id);
        return allMessages.stream().map(message -> this.modelMapper.map(message, MessageServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteMessage(String id) {
        this.messageRepository.deleteById(id);
    }

}
