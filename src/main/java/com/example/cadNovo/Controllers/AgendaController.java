package com.example.cadNovo.Controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.cadNovo.Agenda.Agenda;
import com.example.cadNovo.Agenda.AgendaRepository;
import com.example.cadNovo.Medico.Medico;
import com.example.cadNovo.Medico.MedicoRepository;

@Controller
@RequestMapping("agendamento")
public class AgendaController {

        @Autowired
        private AgendaRepository agendaRepository;
        @Autowired
        private MedicoRepository medicoRepository;

    @GetMapping("/home")
    public String paginaInicial(){
        return "pagagend";
    }


    @GetMapping("/lista")
    public String listarAgendamentos(Model model) {
        List<Agenda> agenda = agendaRepository.findAll();
        model.addAttribute("agendamento", agenda);
        return "meusagend";
    }

    @GetMapping("/novo")
    public String novoAgendamentoForm(  Model model) {

        List<Medico> medicos = medicoRepository.findAll();
        model.addAttribute("medicos", medicos);

        return "cadastroAgenda";
    }

    @PostMapping("/novo")
    public String salvarAgendamento(@RequestParam String nomePaciente, @RequestParam String email, @RequestParam LocalDateTime dataHoraAgendamento,
                                    @RequestParam Long medico , @RequestParam String clinica, Model model, RedirectAttributes redirectAttributes) {
        List<Agenda> agenda = agendaRepository.findAll();
        boolean igual = false;
        Medico medico1 = medicoRepository.findById(medico).orElse(null);

        for(int i = 0; i< agenda.size();i++){

            if(agenda.get(i).getClinica().equals(clinica) && agenda.get(i).getDataHoraAgendamento().equals(dataHoraAgendamento) && agenda.get(i).getMedico().equals(medico1) ) {
                igual = true;
                redirectAttributes.addFlashAttribute("error", "Ja possui agendamento marcado para essa clinica, data e medico ");
                return "redirect:/agendamento/novo";
            }
        }

        if(igual == false){
            Agenda newAgenda = new Agenda(nomePaciente, email, clinica, medico1,dataHoraAgendamento);
            agendaRepository.save(newAgenda);
            return "redirect:/agendamento/lista";

        }
        return "redirect:/agendamento/novo";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarAgendamento(@PathVariable Long id, Model model) {
        Agenda agenda = agendaRepository.findById(id).orElse(null);
        model.addAttribute("agendamento", agenda);

        List<Medico> medicos = medicoRepository.findAll();
        model.addAttribute("medicos", medicos);

        return "editarAgenda";
    }

    @PostMapping("/{id}/editar")
    public String editarAgendamento(@PathVariable Long id, @ModelAttribute("agendamento") Agenda agendamento,
                                    @RequestParam String nomePaciente, @RequestParam String email,
                                    @RequestParam LocalDateTime dataHoraAgendamento,
                                    @RequestParam Long medico, @RequestParam String clinica,
                                    @RequestParam Boolean status) {
        Agenda existingAgenda = agendaRepository.findById(id).orElse(null);
        Medico medico1 = medicoRepository.findById(medico).orElse(null);
        if (existingAgenda != null) {
            existingAgenda.setNomePaciente(nomePaciente);
            existingAgenda.setEmail(email);
            existingAgenda.setDataHoraAgendamento(dataHoraAgendamento);
            existingAgenda.setMedico(medico1);
            existingAgenda.setClinica(clinica);
            existingAgenda.setStatus(status);
            agendaRepository.save(existingAgenda);
        }
        return "redirect:/agendamento/lista";
    }

    @GetMapping("/{id}/excluir")
    public String excluirAgendamento(@PathVariable("id") Long id) {
        agendaRepository.deleteById(id);
        return "redirect:/agendamento/lista";
    }
    }

