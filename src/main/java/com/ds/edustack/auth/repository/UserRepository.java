package com.ds.edustack.auth.repository;

import com.ds.edustack.auth.entity.User;
import com.ds.edustack.auth.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);

    User findByRole(UserRole userRole);
}
