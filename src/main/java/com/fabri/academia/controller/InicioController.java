package com.fabri.academia.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/inicio")
    public String redirigirInicio(Authentication authentication) {

        if (authentication == null ||
                !authentication.isAuthenticated()) {

            return "redirect:/login";
        }

        boolean esAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        boolean esDocente = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_DOCENTE"));

        boolean esAlumno = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ALUMNO"));

        if (esAdmin) {
            return "redirect:/admin/inicio";
        }

        if (esDocente) {
            return "redirect:/docente/inicio";
        }

        if (esAlumno) {
            return "redirect:/alumno/inicio";
        }

        return "redirect:/login";
    }
}
