package com.nsu.exam_portal.repository;

import com.nsu.exam_portal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.roleName = :roleName")
    Optional<User> findByRoleName(@Param("roleName") String roleName);

}
