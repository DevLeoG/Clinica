package com.example.cadNovo.Controllers;

import com.example.cadNovo.User.User;
import com.example.cadNovo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class CadastroController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/cadastro")
    public String mostrarFormularioCad() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(@RequestParam String login, @RequestParam String nome, @RequestParam String senha, @RequestParam LocalDate datanasc, Model model) {
        List<User> user = userRepository.findAll();
        boolean igual = false;

        for(int i = 0; i< user.size();i++){

            if(user.get(i).getLogin().equals(login)){
                igual = true;
            }

        }
        if(igual == false){
            User newUser = new User(login, nome,senha, datanasc, LocalDate.now());
            userRepository.save(newUser);

        }else{
            model.addAttribute("error", "Email já cadastrado");
        }

        return "redirect:/login";
    }

}
