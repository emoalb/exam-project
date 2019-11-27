package com.exam.examproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePostServiceModel {

    private String title;

    private String pictureUrl;

    private String description;

    private String creator_id;

    public CreatePostServiceModel(String title, String pictureUrl, String description, String creator_id) {
        this.title = title;
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.creator_id = creator_id;
    }
}
