package com.exam.examproject.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CreateCommentViewModel {
    @NotEmpty(message = "Comment cannot be empty")
    @Size(min = 4,message = "Comment must be at least 4 characters")
    public String comment;
}
