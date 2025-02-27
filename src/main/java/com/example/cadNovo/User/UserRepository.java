package com.example.cadNovo.User;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    User findByLoginAndDatanasc(String login, LocalDate datanasc);
}
