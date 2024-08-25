package com.example.cadNovo.Controllers;

import com.example.cadNovo.User.User;
import com.example.cadNovo.User.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "loginForm";
    }
    @PostMapping("/login")
    public String fazerLogin(@RequestParam String login, @RequestParam String senha, Model model, HttpSession session) {
        User user = userRepository.findByLogin(login);
        if (user != null && user.getSenha().equals(senha)) {
            // Logado com sucesso
            session.setAttribute("user", user);
            return "redirect:/home" ;
        } else {
            model.addAttribute("error", "Login ou Senha incorreta");
            return "loginForm";
        }
    }

    @GetMapping("/home")
    public String mostrarPaginaHome(Model model, HttpSession session) {
        User user =(User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "ad";
        } else {
            return "redirect:/login"; // Redireciona para a página de login se o usuário não estiver logado
        }
    }
}

