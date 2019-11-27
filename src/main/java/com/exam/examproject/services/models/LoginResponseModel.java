package com.exam.examproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class LoginResponseModel {
    private String id;
    private String username;

    public LoginResponseModel(String id,String username) {
        this.id = id;
        this.username = username;
    }
}
