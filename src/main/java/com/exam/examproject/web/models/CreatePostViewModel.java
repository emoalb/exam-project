package com.exam.examproject.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePostViewModel {

    private String title;

    private String pictureUrl;

    private String description;
}
