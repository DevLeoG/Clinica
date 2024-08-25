package com.example.cadNovo.User;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity (name = "user")
@Table (name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String name;
    private LocalDate datanasc;
    private Boolean ativo;
    private String senha;
    private LocalDate datacad = LocalDate.now();


    public User( String login, String nome, String senha, LocalDate dataNascimento, LocalDate datacad){
        this.login = login;
        this.name = nome;
        this.senha = senha;
        this.datanasc = dataNascimento;
        this.datacad = datacad;
    }

}
