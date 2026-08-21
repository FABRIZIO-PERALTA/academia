package com.fabri.academia.controller.auth;

import com.fabri.academia.domain.Usuario;
import com.fabri.academia.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroController {

    private final UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {

        model.addAttribute(
                "usuario",
                new Usuario()
        );

        return "registro";
    }

    @PostMapping("/registro")
    public String registrarAlumno(
            Usuario usuario,
            @RequestParam String dni,
            Model model) {

        try {

            usuarioService.registrarAlumno(usuario, dni);

            return "redirect:/login?registroExitoso";

        } catch (IllegalArgumentException e) {

            model.addAttribute("usuario", usuario);
            model.addAttribute("dni", dni);
            model.addAttribute("error", e.getMessage());

            return "registro";
        }
    }
}
