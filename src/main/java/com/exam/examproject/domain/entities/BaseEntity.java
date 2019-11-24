package com.exam.examproject.domain.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity {
@Id
@GenericGenerator(
        name = "uuid-string",
        strategy = "org.hibernate.id.UUIDGenerator"
)
@Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;
}

