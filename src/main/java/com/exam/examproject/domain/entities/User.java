package com.exam.examproject.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "creation_date")
    private Date creationDate;
    @Transient
    @Column
    private boolean isAccountNonExpired;
    @Transient
    @Column
    private boolean isAccountNonLocked;
    @Transient
    @Column
    private boolean isCredentialsNonExpired;

    @Transient
    @Column
    private boolean isEnabled;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities;


    @OneToMany(targetEntity = Contact.class, mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contact> contacts;

    @OneToMany(targetEntity = Post.class, mappedBy = "creator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(targetEntity = Message.class, mappedBy = "sendUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> sentMessages;

    @OneToMany(targetEntity = Message.class, mappedBy = "receiveUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> receivedMessages;

    @OneToMany(targetEntity = Comment.class, mappedBy = "creator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;
}
