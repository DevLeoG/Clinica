package com.example.cadNovo.User;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
