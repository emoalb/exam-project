package com.exam.examproject.services.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
