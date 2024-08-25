package com.example.cadNovo.Consulta;

import com.example.cadNovo.Medico.Medico;
import com.example.cadNovo.Paciente.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "consulta")
@Table(name = "consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String queixa;
    private String diagnostico;
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public Consulta(String queixa, String diagnostico, Medico medico) {
        this.queixa = queixa;
        this.diagnostico = diagnostico;
        this.medico = medico;
    }

}
