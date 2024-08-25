package com.example.cadNovo.Paciente;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity (name = "paciente")
@Table(name = "paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String sexo;
    private LocalDate dataNasc;
    private String nome;

    public Paciente(String cpf, String sexo, LocalDate dataNasc, String nome){
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
        this.nome = nome;
    }
}
