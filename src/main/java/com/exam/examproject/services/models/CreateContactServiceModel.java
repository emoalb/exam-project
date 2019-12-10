package com.exam.examproject.services.models;

import com.exam.examproject.domain.enums.Town;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateContactServiceModel {
    private String phoneNumber;
    private String contactName;
    private Town town;
    private String userId;
}
