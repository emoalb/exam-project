package com.exam.examproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetailsPostServiceModel {
    private String id;

    private String title;

    private String pictureUrl;

    private String description;

    private String creatorUsername;
}
