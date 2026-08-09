package com.fabri.academia.controller;

import com.fabri.academia.domain.Docente;
import com.fabri.academia.service.CursoService;
import com.fabri.academia.service.DocenteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/docentes")
public class DocenteController {

    private final DocenteService docenteService;
    private final CursoService cursoService;

    public DocenteController(
            DocenteService docenteService,
            CursoService cursoService) {

        this.docenteService = docenteService;
        this.cursoService = cursoService;
    }

    @GetMapping
    public String listarDocentes(Model model) {

        model.addAttribute(
                "docentes",
                docenteService.obtenerDocentes()
        );

        return "docentes/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {

        model.addAttribute(
                "docente",
                new Docente()
        );

        return "docentes/formulario";
    }

    @PostMapping
    public String crearDocente(Docente docente) {

        docenteService.crearDocente(docente);

        return "redirect:/docentes";
    }

    @GetMapping("/asignar-curso")
    public String mostrarAsignacionCurso(Model model) {

        model.addAttribute(
                "docentes",
                docenteService.obtenerDocentes()
        );

        model.addAttribute(
                "cursos",
                cursoService.obtenerCursos()
        );

        return "docentes/asignar-curso";
    }

    @PostMapping("/asignar-curso")
    public String asignarCurso(
            @RequestParam Long docenteId,
            @RequestParam Long cursoId) {

        docenteService.asignarCurso(docenteId, cursoId);

        return "redirect:/docentes";
    }
}
