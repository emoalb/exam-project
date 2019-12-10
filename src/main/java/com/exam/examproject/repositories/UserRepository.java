package com.exam.examproject.repositories;

import com.exam.examproject.domain.entities.User;
import com.exam.examproject.domain.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    List<User> findAllByRole(UserRole userRole);

    List<User> findAllByOrderByRoleAscUsernameAsc();

}
