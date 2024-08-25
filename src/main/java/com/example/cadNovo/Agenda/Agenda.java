package com.example.cadNovo.Agenda;

import com.example.cadNovo.Medico.Medico;
import com.example.cadNovo.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;

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
