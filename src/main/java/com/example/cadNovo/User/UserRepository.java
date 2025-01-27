package com.example.cadNovo.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    User findByLoginAndDatanasc(String login, LocalDate datanasc);
}
