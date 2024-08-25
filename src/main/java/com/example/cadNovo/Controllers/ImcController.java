package com.example.cadNovo.Controllers;

import com.example.cadNovo.IMC.Imc;
import com.example.cadNovo.IMC.ImcRepository;
import com.example.cadNovo.Paciente.Paciente;
import com.example.cadNovo.Paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("imc")
public class ImcController {

    @Autowired
    private ImcRepository imcRepository;
    @Autowired
    private PacienteRepository pacRepository;

    @GetMapping
    public String listarUsuarios(Model model) {
        List<Imc> imc = imcRepository.findAll();
        model.addAttribute("imc", imc);
        return "imc";
    }

    @GetMapping("/novo")
    public String mostrarFormularioIMC(Model model) {
        List<Paciente> pacientes = pacRepository.findAll();
        model.addAttribute("pacientes", pacientes);
        return "cadastroImc";
    }

    @PostMapping("/novo")
    public String novoImc(@RequestParam double peso, @RequestParam double altura, @RequestParam Long paciente) {
        Paciente pac = pacRepository.getReferenceById(paciente);
        Imc imc = new Imc();
        imc.setPeso(peso);
        imc.setAltura(altura);
        imc.setPaciente(pac);
        imc.calcularResultado();
        imcRepository.save(imc);
        return "redirect:/imc";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarMedico(@PathVariable Long id, Model model) {
        Imc imc = imcRepository.findById(id).orElse(null);
        model.addAttribute("imc", imc);

        List<Paciente> pacientes = pacRepository.findAll();
        model.addAttribute("pacientes", pacientes);
        return "editarImc";
    }

    @PostMapping("/{id}/editar")
    public String editarMedico(@PathVariable Long id, @ModelAttribute("medico") Imc imc, @RequestParam double peso, @RequestParam double altura,
                               @RequestParam Long paciente) {
        Imc existingImc = imcRepository.findById(id).orElse(null);
        Paciente pac = pacRepository.getReferenceById(paciente);
        if (existingImc != null) {
            existingImc.setAltura(altura);
            existingImc.setPeso(peso);
            existingImc.calcularResultado();
            existingImc.setPaciente(pac);
            imcRepository.save(existingImc);
        }
        return "redirect:/imc";
    }

    @GetMapping("/{id}/excluir")
    public String excluirMedico(@PathVariable Long id) {
        imcRepository.deleteById(id);
        return "redirect:/imc";
    }





}
