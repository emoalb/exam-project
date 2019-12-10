package com.exam.examproject.web.view.models;

import com.exam.examproject.domain.enums.Town;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateContactViewModel {
    private String phoneNumber;
    private String contactName;
    private Town town;
}
