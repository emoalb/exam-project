package com.exam.examproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostServiceModel {
    private String title;

    private String pictureUrl;

    private String description;

    private String creatorUsername;
}