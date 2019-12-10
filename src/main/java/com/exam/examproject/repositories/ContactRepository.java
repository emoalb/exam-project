package com.exam.examproject.repositories;

import com.exam.examproject.domain.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,String> {
    List<Contact> findAllByUser_IdOrderByContactName(String id);

}
