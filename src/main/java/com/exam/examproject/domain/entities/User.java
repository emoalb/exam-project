package com.exam.examproject.domain.entities;

import com.exam.examproject.domain.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role = UserRole.USER;

    @Column(name = "creation_date")
    private Date creationDate;

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
