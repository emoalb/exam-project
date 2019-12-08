package com.exam.examproject.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message extends BaseEntity {

    @Column(name = "message", nullable = false)
    private String message;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
    private User sendUser;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "receiver_id", referencedColumnName = "id", nullable = false)
    private User receiveUser;

}

