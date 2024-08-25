package com.example.cadNovo.Agenda;

import com.example.cadNovo.Medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    boolean existsByClinicaAndDataHoraAgendamentoAndMedico(String clinica, LocalDateTime dataHoraAgendamento, Medico medico);
}
