package com.example.cadNovo.Controllers;

import com.example.cadNovo.Medico.Medico;
import com.example.cadNovo.Medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository userRepository;
    @GetMapping
    public String listarUsuarios(Model model) {
        List<Medico> medico = userRepository.findAll();
        model.addAttribute("medico", medico);
        return "medicos";
    }
    @GetMapping("/novo")
    public String mostrarFormularioNovoMedico() {return "cadastroMedico";}
    @PostMapping("/novo")
    public String cadastrarMedico(@RequestParam String nome) {
        Medico newMedico = new Medico(nome);
        userRepository.save(newMedico);
        return "redirect:/medico";
    }
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarMedico(@PathVariable Long id, Model model) {
        Medico medico = userRepository.findById(id).orElse(null);
        model.addAttribute("medico", medico);
        return "editarMedico";
    }
    @PostMapping("/{id}/editar")
    public String editarMedico(@PathVariable Long id, @ModelAttribute("medico") Medico medico, @RequestParam String nome) {
        Medico existingMedico = userRepository.findById(id).orElse(null);
        if (existingMedico != null) {
            existingMedico.setNome(nome);
            userRepository.save(existingMedico);
        }
        return "redirect:/medico";
    }
    @GetMapping("/{id}/excluir")
    public String excluirMedico(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/medico";
    }

}
