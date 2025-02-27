package com.example.cadNovo.Controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cadNovo.User.User;
import com.example.cadNovo.User.UserRepository;

@Controller
public class RecuperarSenhaController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/recupsenh")
    public String FormSenha(){
        return "recupsenh";
    }


    @PostMapping("/recupsenh")
    public String recuperarSenha(@RequestParam String login, @RequestParam LocalDate datanasc, Model model) {
        User user = userRepository.findByLoginAndDatanasc(login, datanasc);

        if (user != null) {
            return "redirect:/criasenh?login=" + login;
        } else {
            model.addAttribute("error", "Dados incorretos. Não foi possível recuperar a senha.");
            return "recupsenh" ;
        }
    }

    @PostMapping("/criasenh")
    public String criarNovaSenha(@RequestParam String senha, @RequestParam String senha1, @RequestParam String login, Model model) {
        // Verifica se as senhas são iguais
        if (!senha.equals(senha1)) {
            model.addAttribute("error", "As senhas não coincidem.");
            return "criasenh";
        }

        User existingUser = userRepository.findByLogin(login);
        if (existingUser != null) {

            existingUser.setSenha(senha);
            userRepository.save(existingUser);
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Usuário não encontrado.");
            return "criasenh";
        }
    }

    @GetMapping("/criasenh")
    public String FormCriaSenha(){
        return "criasenh";
    }
}
