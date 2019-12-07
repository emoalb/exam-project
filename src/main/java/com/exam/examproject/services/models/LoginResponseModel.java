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
public class LoginResponseModel {

    private String id;
    private String username;
    private String userRole;


}
