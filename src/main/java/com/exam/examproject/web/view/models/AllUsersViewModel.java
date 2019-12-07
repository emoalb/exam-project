package com.exam.examproject.web.view.models;

import com.exam.examproject.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllUsersViewModel {

    private String id;

    private String username;

    private String email;

    private String role;
}
