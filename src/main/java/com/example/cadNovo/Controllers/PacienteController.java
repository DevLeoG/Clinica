package com.example.cadNovo.Controllers;

import com.example.cadNovo.Paciente.Paciente;
import com.example.cadNovo.Paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacRepository;


    @GetMapping
    public String listarUsuarios(Model model) {
        List<Paciente> user = pacRepository.findAll();
        model.addAttribute("paciente", user);
        return "pacientes";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovoUsuario(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "cadastropaciente";
    }

    @PostMapping("/novo")
    public String cadastrarNovoPaciente(@ModelAttribute("paciente") Paciente pac) {
        pacRepository.save(pac);
        return "redirect:/pacientes";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarUsuario(@PathVariable Long id, Model model) {
        Paciente pac = pacRepository.findById(id).orElse(null);
        model.addAttribute("paciente", pac);
        return "editarPacientes";
    }

    @PostMapping("/{id}/editar")
    public String editarPaciente(@PathVariable Long id, @ModelAttribute("paciente") Paciente pac,
                                 @RequestParam String nome, @RequestParam String cpf, @RequestParam LocalDate dataNasc, @RequestParam String sexo) {
        Paciente existingUser = pacRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setCpf(cpf);
            existingUser.setSexo(sexo);
            existingUser.setNome(nome);
            existingUser.setDataNasc(dataNasc);
            pacRepository.save(existingUser);
        }


        return "redirect:/pacientes";
    }

    @GetMapping("/{id}/excluir")
    public String excluirUsuario(@PathVariable Long id) {
        pacRepository.deleteById(id);
        return "redirect:/pacientes";

    }

}