package com.exam.examproject.web.view.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor

public class CreateMessageViewModel {

    @NotEmpty(message = "Message cannot be empty field!")
    private String message;
    private String receiver;

}
