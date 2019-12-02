package com.exam.examproject.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor

public class Comment  extends  BaseEntity{

    @Column(name = "comment",nullable = false)
    private String comment;

    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name="post_id",referencedColumnName = "id",nullable=false)
    private Post post;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="creator_id",referencedColumnName = "id",nullable=false)
    private User creator;

}
