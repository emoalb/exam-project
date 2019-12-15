package com.exam.examproject.web.view.models;

import com.exam.examproject.config.validations.StartWithCapital;
import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class EditPostViewModel {

    private String id;

    @StartWithCapital
    @Size(min = 3, max = 20, message = "Title must be between 3 and 20 characters")
    private String title;

    @URL(message = "This must be a valid url")
    @NotEmpty(message = "Url must not be empty")
    private String pictureUrl;
    @Size(min = 5, message = "Description must be at least 5 characters")
    private String description;


    private String creatorUsername;

}
