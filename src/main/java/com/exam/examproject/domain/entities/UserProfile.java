package com.exam.examproject.domain.entities;

import com.exam.examproject.domain.enums.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserProfile extends BaseEntity {
    @Column(name = "picture_url", nullable = false)
    private String pictureUrl;
    @Column(name="birth_date",nullable = false)
    private Date birthDate;
    @Column(name = "sex",nullable = false)
    private Sex sex;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
