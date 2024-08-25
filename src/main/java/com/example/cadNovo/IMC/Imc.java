package com.example.cadNovo.IMC;


import com.example.cadNovo.Paciente.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Entity (name = "imc")
@Table(name = "imc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Imc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double peso;
    private double altura;
    private double resultado;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public void calcularResultado() {
        if (peso > 0 && altura > 0) {
            double imc = peso / (altura * altura);
            this.resultado = Math.round(imc * 100.0) / 100.0;
        }
    }

    public String getImcStatus() {
        if (resultado < 18.5) {
            return "Você está abaixo do peso.";
        } else if (resultado >= 18.5 && resultado < 24.9) {
            return "Você está com o peso normal.";
        } else if (resultado >= 25 && resultado < 29.9) {
            return "Você está com sobrepeso.";
        } else if (resultado >= 30 && resultado < 34.9) {
            return "Você está com obesidade grau I.";
        } else if (resultado >= 35 && resultado < 39.9) {
            return "Você está com obesidade grau II (severa).";
        } else {
            return "Você está com obesidade grau III (mórbida).";
        }
    }


}
