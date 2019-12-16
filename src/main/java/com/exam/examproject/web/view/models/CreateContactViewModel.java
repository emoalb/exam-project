package com.exam.examproject.web.view.models;

import com.exam.examproject.config.validations.StartWithCapital;
import com.exam.examproject.domain.enums.Town;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CreateContactViewModel {
    @NotEmpty
    @Size(min = 3)
    private String phoneNumber;
    @NotEmpty
    @Size(min = 3)
    @StartWithCapital
    private String contactName;
    private Town town;
}
