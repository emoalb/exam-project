package com.exam.examproject.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role
        extends BaseEntity
        implements GrantedAuthority {

    private String authority;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;
}
