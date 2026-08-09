package com.fabri.academia.controller;

import com.fabri.academia.domain.Alumno;
import com.fabri.academia.service.AlumnoService;
import com.fabri.academia.service.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;
    private final CursoService cursoService;

    public AlumnoController(
            AlumnoService alumnoService,
            CursoService cursoService) {

        this.alumnoService = alumnoService;
        this.cursoService = cursoService;
    }

    @GetMapping
    public String listarAlumnos(Model model) {

        model.addAttribute(
                "alumnos",
                alumnoService.obtenerAlumnos()
        );

        return "alumnos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {

        model.addAttribute(
                "alumno",
                new Alumno()
        );

        model.addAttribute(
                "cursos",
                cursoService.obtenerCursos()
        );

        return "alumnos/formulario";
    }

    @PostMapping
    public String crearAlumno(
            Alumno alumno,
            @RequestParam Long cursoId) {

        alumnoService.crearAlumno(alumno, cursoId);

        return "redirect:/alumnos";
    }
}
