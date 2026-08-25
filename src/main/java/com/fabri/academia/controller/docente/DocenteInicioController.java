package com.fabri.academia.controller.docente;

import com.fabri.academia.domain.Docente;
import com.fabri.academia.service.DocenteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/docente")
public class DocenteInicioController {

    private final DocenteService docenteService;

    public DocenteInicioController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @GetMapping("/inicio")
    public String inicio(
            Authentication authentication,
            Model model) {

        Docente docente =
                docenteService.obtenerDocentePorUsername(
                        authentication.getName()
                );

        model.addAttribute(
                "nombre",
                docente.getNombre()
        );

        return "docente/inicio";
    }
}