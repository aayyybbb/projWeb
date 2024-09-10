package com.jojoldu.book.springbootdeveloper.repository;

import com.jojoldu.book.springbootdeveloper.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Long> {
    public Optional<Email> findByEmail(String email);
}
