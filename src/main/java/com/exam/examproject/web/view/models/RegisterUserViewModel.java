package com.exam.examproject.web.view.models;

import com.exam.examproject.config.validations.PasswordValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class RegisterUserViewModel {

    @Size(min = 3, message = "User name must be at least 3 characters long")
    private String username;
    @Email(regexp = "[^@]+@[^\\.]+\\..+",message = "Invalid email")
    private String email;
    @PasswordValidation
    private String password;
    @NotEmpty
    private String repeatPassword;

}
