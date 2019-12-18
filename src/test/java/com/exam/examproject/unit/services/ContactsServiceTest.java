package com.exam.examproject.unit.services;

import com.exam.examproject.domain.entities.Contact;
import com.exam.examproject.domain.entities.User;
import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.repositories.ContactRepository;
import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.models.CreateContactServiceModel;
import com.exam.examproject.services.services.impl.ContactsServiceImpl;
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

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ContactsServiceTest {

    @InjectMocks
    ContactsServiceImpl contactsService;

    @Mock
    ContactRepository mockContactRepository;
    @Mock
    ModelMapper modelMapper;

    @Mock
    UserRepository mockUserRepository;

    private User user;
    private Contact contact;
    private CreateContactServiceModel createContactServiceModel;

    @Before
    public void setup() {
        user = Mockito.mock(User.class);
        contact = Mockito.mock(Contact.class);
        createContactServiceModel = Mockito.mock(CreateContactServiceModel.class);

    }

    @Test(expected = UserNotFoundException.class)
    public void createContact_shouldThrowUserNotFoundException_whenUserIdIsInvalid() {
        String userId = "id";
        Mockito.when(mockUserRepository.findById(userId)).thenReturn(Optional.empty());
        createContactServiceModel.setUserId(userId);
        contactsService.createContact(createContactServiceModel);

    }

}
