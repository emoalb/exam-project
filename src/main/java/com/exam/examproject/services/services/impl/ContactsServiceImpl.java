package com.exam.examproject.services.services.impl;

import com.exam.examproject.common.Constants;
import com.exam.examproject.domain.entities.Contact;
import com.exam.examproject.domain.entities.User;
import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.repositories.ContactRepository;
import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.models.ContactServiceModel;
import com.exam.examproject.services.models.CreateContactServiceModel;
import com.exam.examproject.services.services.ContactsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactsServiceImpl implements ContactsService {
    private final ModelMapper modelMapper;
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    @Autowired
    public ContactsServiceImpl(ModelMapper modelMapper, ContactRepository contactRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void createContact(CreateContactServiceModel contactServiceModel) throws UserNotFoundException {
        Optional<User> user = this.userRepository.findById(contactServiceModel.getUserId());
        if (user.isEmpty())
            throw new UserNotFoundException(Constants.USER_NOT_FOUND_MESSAGE);

        Contact contact = this.modelMapper.map(contactServiceModel, Contact.class);
        this.contactRepository.save(contact);

    }


    @Override
    public List<ContactServiceModel> getAllContactsByUserId(String userId) throws UserNotFoundException {
        if (this.userRepository.findById(userId).isEmpty())
            throw new UserNotFoundException(Constants.USER_NOT_FOUND_MESSAGE);
        List<Contact> contacts = this.contactRepository.findAllByUser_IdOrderByContactName(userId);
        List<ContactServiceModel> contactServiceModels = contacts.stream().map(contact -> this.modelMapper.map(contact, ContactServiceModel.class)).collect(Collectors.toList());
        return contactServiceModels;
    }


}
