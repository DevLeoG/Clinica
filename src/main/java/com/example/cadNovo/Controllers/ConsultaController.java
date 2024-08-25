package com.example.cadNovo.Controllers;

import com.example.cadNovo.Consulta.Consulta;
import com.example.cadNovo.Consulta.ConsultaRepository;
import com.example.cadNovo.Medico.Medico;
import com.example.cadNovo.Medico.MedicoRepository;
import com.example.cadNovo.Paciente.Paciente;
import com.example.cadNovo.Paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository conRepository;
    @Autowired
    private PacienteRepository pacRepository;
    @Autowired
    private MedicoRepository medRepository;

    @GetMapping
    public String listarUsuarios(Model model) {
        List<Consulta> user = conRepository.findAll();
        model.addAttribute("consulta", user);
        return "consultas";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovoUsuario(Model model) {

        List<Medico> medicos = medRepository.findAll();
        model.addAttribute("medicos", medicos);

        List<Paciente> pacientes = pacRepository.findAll();
        model.addAttribute("pacientes", pacientes);

        return "cadastroConsulta";
    }

    @PostMapping("/novo")
    public String cadastrarNovoPaciente(@ModelAttribute("consulta") Consulta con) {
        conRepository.save(con);
        return "redirect:/consultas";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarUsuario(@PathVariable Long id, Model model) {
        Consulta con = conRepository.findById(id).orElse(null);
        model.addAttribute("consulta", con);

        List<Medico> medicos = medRepository.findAll();
        model.addAttribute("medicos", medicos);

        List<Paciente> pacientes = pacRepository.findAll();
        model.addAttribute("pacientes", pacientes);

        return "editarConsulta";
    }

    @PostMapping("/{id}/editar")
    public String editarPaciente(@PathVariable Long id, @ModelAttribute("consulta") Consulta con,
                                 @RequestParam String queixa, @RequestParam String diagnostico, @RequestParam Long medico, @RequestParam Long paciente) {
        Consulta existingCon = conRepository.findById(id).orElse(null);
        Medico med1 = medRepository.getReferenceById(medico);
        Paciente pac1 = pacRepository.getReferenceById(paciente);
        if (existingCon != null) {
            existingCon.setDiagnostico(diagnostico);
            existingCon.setQueixa(queixa);
            existingCon.setPaciente(pac1);
            existingCon.setMedico(med1);
            conRepository.save(existingCon);
        }

        return "redirect:/consultas";
    }

    @GetMapping("/{id}/excluir")
    public String excluirUsuario(@PathVariable Long id) {
        conRepository.deleteById(id);
        return "redirect:/consultas";

    }
}
