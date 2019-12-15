package com.exam.examproject.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
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

    public int hashCode() {
        return Objects.hash(this.authority);
    }

    public boolean equals(Object o) {
        if (o == this) { return true; }
        if (o == null || !(o instanceof Role) ) { return false; }
        return Objects.equals(this.authority, ((Role) o).authority);
    }
    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;


}
