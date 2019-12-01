package com.exam.examproject.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post extends BaseEntity {
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name="picture_url", nullable = false)
    private String pictureUrl;
    @Column(name="description")
    private String description;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="creator_id",referencedColumnName = "id",nullable=false)
    private User creator;

}
