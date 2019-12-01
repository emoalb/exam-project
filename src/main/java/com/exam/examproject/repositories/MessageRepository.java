package com.exam.examproject.repositories;

import com.exam.examproject.domain.entities.Message;
import com.exam.examproject.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,String> {

List<Message> getAllByReceiveUser_Id(String id);
}
