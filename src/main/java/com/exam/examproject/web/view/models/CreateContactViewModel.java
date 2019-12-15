package com.exam.examproject.web.view.models;

import com.exam.examproject.domain.enums.Town;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class CreateContactViewModel {
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String contactName;
    private Town town;
}
