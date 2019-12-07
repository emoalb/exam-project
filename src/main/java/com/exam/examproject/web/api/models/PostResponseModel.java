package com.exam.examproject.web.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseModel {

    private String id;

    private String title;

    private String pictureUrl;

    private String description;

    private String creatorUsername;
}
