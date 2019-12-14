package com.exam.examproject.services.models;

import com.exam.examproject.domain.entities.Role;
import com.exam.examproject.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeedUserServiceModel {
    private String username;
    private  String password;
    private String email;
    private Set<Role> authorities;
}
