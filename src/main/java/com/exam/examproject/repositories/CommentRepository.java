package com.exam.examproject.repositories;

import com.exam.examproject.domain.entities.Comment;
import com.exam.examproject.services.models.CreateCommentServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findAllByPost_Id(String id);

}
