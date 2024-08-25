package com.example.cadNovo.Controllers;

import com.example.cadNovo.User.User;
import com.example.cadNovo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public String listarUsuarios(Model model) {
        List<User> user = userRepository.findAll();
        model.addAttribute("user", user);
        return "usuarios";
    }
    @GetMapping("/novo")
    public String mostrarFormularioNovoUsuario(Model model) {
        model.addAttribute("user", new User());
        return "cadastro";
    }
    @PostMapping("/novo")
    public String cadastrarNovoUsuario(@ModelAttribute("usuario") User usuario) {
        userRepository.save(usuario);
        return "redirect:/usuarios";
    }
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarUsuario(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "editar";
    }
    @PostMapping("/{id}/editar")
    public String editarUsuario(@PathVariable Long id, @ModelAttribute("usuario") User usuario,
                                @RequestParam String login, @RequestParam String nome, @RequestParam String senha, @RequestParam LocalDate datanasc, @RequestParam(name = "ativo", required = false)  Boolean ativo) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(nome);
            existingUser.setLogin(login);
            existingUser.setSenha(senha);
            existingUser.setDatanasc(datanasc);
            existingUser.setAtivo(ativo);
            userRepository.save(existingUser);
        }
        return "redirect:/usuarios";
    }
    @GetMapping("/{id}/excluir")
    public String excluirUsuario(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/usuarios";
    }
}
