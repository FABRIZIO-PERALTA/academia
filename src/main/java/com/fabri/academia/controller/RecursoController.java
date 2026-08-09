package com.fabri.academia.controller;

import com.fabri.academia.domain.Recurso;
import com.fabri.academia.domain.enums.TipoRecurso;
import com.fabri.academia.service.RecursoService;
import com.fabri.academia.service.TemaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/recursos")
public class RecursoController {

    private final RecursoService recursoService;
    private final TemaService temaService;

    public RecursoController(
            RecursoService recursoService,
            TemaService temaService) {

        this.recursoService = recursoService;
        this.temaService = temaService;
    }

    @GetMapping
    public String listarRecursos(Model model) {

        model.addAttribute(
                "recursos",
                recursoService.obtenerRecursos()
        );

        return "recursos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {

        model.addAttribute(
                "recurso",
                new Recurso()
        );

        model.addAttribute(
                "temas",
                temaService.obtenerTemas()
        );

        model.addAttribute(
                "tiposRecurso",
                TipoRecurso.values()
        );

        return "recursos/formulario";
    }

    @PostMapping
    public String crearRecurso(
            Recurso recurso,
            @RequestParam Long temaId) {

        recursoService.crearRecurso(recurso, temaId);

        return "redirect:/recursos";
    }
}
