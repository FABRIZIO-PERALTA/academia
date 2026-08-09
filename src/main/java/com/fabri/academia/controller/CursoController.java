package com.fabri.academia.controller;

import com.fabri.academia.domain.Curso;
import com.fabri.academia.service.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public String listarCursos(Model model) {

        model.addAttribute(
                "cursos",
                cursoService.obtenerCursos()
        );

        return "cursos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {

        model.addAttribute(
                "curso",
                new Curso()
        );

        return "cursos/formulario";
    }

    @PostMapping
    public String crearCurso(Curso curso) {

        cursoService.crearCurso(curso);

        return "redirect:/cursos";
    }
}
