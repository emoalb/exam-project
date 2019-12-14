package com.exam.examproject.repositories;

import com.exam.examproject.domain.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);



 @Query("SELECT u FROM User u INNER JOIN u.authorities a WHERE a.authority = :role ")
  List<User> getAdminList(@Param("role") String userRole);

    List<User> findAllByOrderByUsernameAsc();

}
