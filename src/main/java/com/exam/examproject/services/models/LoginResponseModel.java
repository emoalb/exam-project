package com.exam.examproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class LoginResponseModel {
    private String username;

    public LoginResponseModel(String username) {
        this.username = username;
    }
}
