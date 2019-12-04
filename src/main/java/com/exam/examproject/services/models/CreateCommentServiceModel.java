package com.exam.examproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCommentServiceModel {
    private String comment;
    private String postId;
    private String creatorId;

}
