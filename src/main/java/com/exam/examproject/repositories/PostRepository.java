package com.exam.examproject.repositories;

import com.exam.examproject.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,String> {
    Optional<Post> findById(String id);
}
