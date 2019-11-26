package com.exam.examproject.domain.entities;

import com.exam.examproject.domain.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Enumerated(EnumType.STRING
    )
    @Column(name = "role")
    private UserRole role = UserRole.USER;

    @OneToMany(mappedBy = "creator")
    private List<Post> posts;

}
