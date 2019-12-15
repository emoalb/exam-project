package com.exam.examproject.services.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostServiceModel {


    private String title;

    private String pictureUrl;

    private String description;

    private String creator_id;

}
