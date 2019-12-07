package com.exam.examproject.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor

public class Comment extends BaseEntity {

    @Column(name = "comment", nullable = false)
    private String comment;
    @Column(name="creation_date",nullable = false)
    private Date date;
    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "creator_id", referencedColumnName = "id", nullable = false)
    private User creator;

}
