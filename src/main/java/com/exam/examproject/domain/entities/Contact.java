package com.exam.examproject.domain.entities;

import com.exam.examproject.domain.enums.Town;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor
public class Contact extends BaseEntity {

    @Column(name = "phone_number", nullable = false)
    private Integer entity;

    @Column(name = "contact_name",nullable = false)
    private String contactName;

    @Column(name = "town",nullable = false)
    @Enumerated(EnumType.STRING)
    private Town town;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;


}
