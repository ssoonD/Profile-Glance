package com.profileglance.db.repository;

import com.profileglance.db.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

    List<Interview> findAllByUser_UserEmail(String userEmail);

}