package com.exam.examproject.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.MappedSuperclass;

@MappedSuperclass


@Getter
@Setter
@NoArgsConstructor
public class BaseEntity {

    void Hi(){

    }
}

