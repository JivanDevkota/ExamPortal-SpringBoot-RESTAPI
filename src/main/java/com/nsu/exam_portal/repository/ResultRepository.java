package com.nsu.exam_portal.repository;

import com.nsu.exam_portal.model.Result;
import com.nsu.exam_portal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByUser(User user);
}
