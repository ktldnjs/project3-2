package com.sparta.week04_prac5.repository;

import com.sparta.week04_prac5.model.MemoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MemoUser, Long> {
    Optional<MemoUser> findByUsername(String username);
}
