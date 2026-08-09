package com.fabri.academia.controller;

import com.fabri.academia.domain.Tema;
import com.fabri.academia.service.CursoService;
import com.fabri.academia.service.TemaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/temas")
public class TemaController {

    private final TemaService temaService;
    private final CursoService cursoService;

    public TemaController(
            TemaService temaService,
            CursoService cursoService) {

        this.temaService = temaService;
        this.cursoService = cursoService;
    }

    @GetMapping
    public String listarTemas(Model model) {

        model.addAttribute(
                "temas",
                temaService.obtenerTemas()
        );

        return "temas/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {

        model.addAttribute(
                "tema",
                new Tema()
        );

        model.addAttribute(
                "cursos",
                cursoService.obtenerCursos()
        );

        return "temas/formulario";
    }

    @PostMapping
    public String crearTema(
            Tema tema,
            @RequestParam Long cursoId) {

        temaService.crearTema(tema, cursoId);

        return "redirect:/temas";
    }
}
