package com.exam.examproject.services.models;

import com.exam.examproject.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeedUserServiceModel {
    private String username;
    private  String password;
    private String email;
    private UserRole userRole;
}
