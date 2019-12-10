package com.exam.examproject.web.view.controllers;

import com.exam.examproject.common.Constants;
import com.exam.examproject.errors.UserNotFoundException;
import com.exam.examproject.services.models.ContactServiceModel;
import com.exam.examproject.services.models.LoginResponseModel;
import com.exam.examproject.services.models.UserServiceModel;
import com.exam.examproject.services.services.ContactsService;
import com.exam.examproject.services.services.UsersService;
import com.exam.examproject.web.base.BaseController;
import com.exam.examproject.web.view.models.CreateContactViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ContactController extends BaseController {
    private final ModelMapper modelMapper;
    private final UsersService usersService;
    private final ContactsService contactsService;

    @Autowired
    public ContactController(ModelMapper modelMapper, UsersService usersService, ContactsService contactsService) {
        this.modelMapper = modelMapper;
        this.usersService = usersService;
        this.contactsService = contactsService;
    }

    @GetMapping("/contacts/{id}")
    public ModelAndView getContacts(@ModelAttribute("createContactViewModel") CreateContactViewModel createContactViewModel,
                                    @PathVariable String id, HttpSession session) {
        if (!id.equals(((LoginResponseModel) session.getAttribute("user")).getId()))
            throw new UserNotFoundException(Constants.USER_NOT_FOUND_MESSAGE);
        List<ContactServiceModel> contactServiceModels = this.contactsService.getAllContactsByUserId(id);
        UserServiceModel userServiceModel = this.usersService.findUserById(id);
        String[] attrNames = {"user", "contacts"};
        Object[] objects = {userServiceModel, contactServiceModels};
        return super.render("contacts/all", attrNames, objects);
    }

}
