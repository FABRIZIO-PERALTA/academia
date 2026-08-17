package com.fabri.academia.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminInicioController {

    @GetMapping("/inicio")
    public String inicio(
            Authentication authentication,
            Model model) {

        String username = authentication.getName();

        String nombreAdministrador;

        if (username.equals("admin1")) {
            nombreAdministrador = "NombreAdmin1";
        } else if (username.equals("admin2")) {
            nombreAdministrador = "NombreAdmin2";
        } else {
            nombreAdministrador = username;
        }

        model.addAttribute(
                "nombreAdministrador",
                nombreAdministrador
        );

        return "admin/inicio";
    }
}
