package com.example.cadNovo.Agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.cadNovo.Medico.Medico;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity (name = "agenda")
@Table(name = "agenda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomePaciente;
    private String email;
    private boolean status = true;
    private String clinica;
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
    private LocalDateTime dataHoraAgendamento;
    private LocalDate dataCadastro = LocalDate.now();


     public Agenda( String nomePaciente, String email, String clinica, Medico medico, LocalDateTime dataHoraAgendamento) {
        this.nomePaciente = nomePaciente;
        this.email = email;
        this.clinica = clinica;
        this.medico = medico;
        this.dataHoraAgendamento = dataHoraAgendamento;
    }


    
}
