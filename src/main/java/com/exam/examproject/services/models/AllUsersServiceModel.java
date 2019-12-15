package com.exam.examproject.services.models;

//import com.exam.examproject.domain.enums.UserRole;

import com.exam.examproject.domain.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllUsersServiceModel {
    private String id;

    private String username;

    private String email;

    private Date creationDate;

    private String authority;
}
