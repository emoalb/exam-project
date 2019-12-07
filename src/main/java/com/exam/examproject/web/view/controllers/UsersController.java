package com.exam.examproject.web.view.controllers;

import com.exam.examproject.services.models.AllUsersServiceModel;
import com.exam.examproject.services.services.UsersService;
import com.exam.examproject.web.base.BaseController;
import com.exam.examproject.web.view.models.AllUsersViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UsersController extends BaseController {
    private final UsersService usersService;
    private final ModelMapper modelMapper;

    @Autowired
    public UsersController(UsersService usersService, ModelMapper modelMapper) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public ModelAndView getAllUsers() {
        List<AllUsersServiceModel> allUsersServiceModels = this.usersService.allUsers();
        List<AllUsersViewModel> allUsers = allUsersServiceModels.stream().map(user -> this.modelMapper.map(user, AllUsersViewModel.class)).collect(Collectors.toList());
        return super.render("all-users", "allUsers", allUsers);
    }
    @GetMapping("/delete/{id}")
    public ModelAndView getDeleteUser(@PathVariable("id") String id) {
       this.usersService.deleteUserById(id);
        return super.redirect("/users/all");
    }
    @GetMapping("/admin/{id}")
    public ModelAndView getUpgradeToAdminUser(@PathVariable("id") String id) {
        this.usersService.upgradeToAdmin(id);
        return super.redirect("/users/all");
    }
}
