package com.exam.examproject.web.view.models;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class EditPostViewModel implements Serializable {

    @Expose
    private String id;

    @Expose
    private String title;

    @Expose
    private String pictureUrl;

    @Expose
    private String description;

    @Expose
    private String creatorUsername;

}
