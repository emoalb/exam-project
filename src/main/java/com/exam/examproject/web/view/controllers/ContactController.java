package com.exam.examproject.web.view.controllers;

import com.exam.examproject.services.models.ContactServiceModel;
import com.exam.examproject.services.services.ContactsService;
import com.exam.examproject.web.base.BaseController;
import com.exam.examproject.web.view.models.CreateContactViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ContactController extends BaseController {
    private final ModelMapper modelMapper;

    private final ContactsService contactsService;

    @Autowired
    public ContactController(ModelMapper modelMapper, ContactsService contactsService) {
        this.modelMapper = modelMapper;
        this.contactsService = contactsService;
    }

    @GetMapping("/contacts/{id}")
    public ModelAndView getContacts(@ModelAttribute("createContactViewModel") CreateContactViewModel createContactViewModel,
                                    @PathVariable String id){
        List<ContactServiceModel> contactServiceModels = this.contactsService.getAllContactsByUserId(id);

    return super.render("contacts/all");
}

}
