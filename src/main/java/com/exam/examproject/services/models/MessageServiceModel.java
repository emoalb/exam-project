package com.exam.examproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageServiceModel {
    private String id;
    private String message;
    private String sendUserUsername;
    private String receiveUserUsername;
}
