package com.exam.examproject.services.services;

import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.services.models.ContactServiceModel;
import com.exam.examproject.services.models.CreateContactServiceModel;

import java.util.List;

public interface ContactsService {
    void createContact(CreateContactServiceModel contactServiceModel) throws UserNotFoundException;
    List<ContactServiceModel> getAllContactsByUserId(String userId) throws UserNotFoundException;
}
