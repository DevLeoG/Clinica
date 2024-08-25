package com.example.cadNovo.Medico;

import com.example.cadNovo.Agenda.Agenda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity (name = "medico")
@Table(name = "medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Agenda> agendamentos;

    public Medico(String nome){
        this.nome= nome;
    }

}
