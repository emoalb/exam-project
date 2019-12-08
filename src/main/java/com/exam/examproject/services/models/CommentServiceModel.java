package com.exam.examproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

public class CommentServiceModel {
    private String id;
    private String comment;
    private String creatorUsername;
    private Date date;

}
