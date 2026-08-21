package com.fabri.academia.controller.docente;

import com.fabri.academia.domain.Curso;
import com.fabri.academia.service.AlumnoService;
import com.fabri.academia.service.CursoService;
import com.fabri.academia.service.TemaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/docente/cursos")
public class DocenteCursoController {

    private final CursoService cursoService;
    private final AlumnoService alumnoService;
    private final TemaService temaService;

    public DocenteCursoController(
            CursoService cursoService,
            AlumnoService alumnoService,
            TemaService temaService) {

        this.cursoService = cursoService;
        this.alumnoService = alumnoService;
        this.temaService = temaService;
    }

    @GetMapping
    public String listarCursos(Model model) {

        model.addAttribute(
                "cursos",
                cursoService.obtenerCursos()
        );

        return "docente/cursos/lista";
    }

    @GetMapping("/{cursoId}")
    public String verCurso(
            @PathVariable Long cursoId,
            Model model) {

        Curso curso = cursoService.buscarPorId(cursoId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El curso no existe"
                        )
                );

        model.addAttribute(
                "curso",
                curso
        );

        model.addAttribute(
                "alumnos",
                alumnoService.obtenerAlumnosPorCurso(cursoId)
        );

        model.addAttribute(
                "temas",
                temaService.obtenerTemasPorCurso(cursoId)
        );

        return "docente/cursos/detalle";
    }
}
