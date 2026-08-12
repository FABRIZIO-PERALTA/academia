package com.fabri.academia.controller;

import com.fabri.academia.domain.Alumno;
import com.fabri.academia.domain.Curso;
import com.fabri.academia.domain.Tema;
import com.fabri.academia.service.AlumnoService;
import com.fabri.academia.service.TemaService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/alumno")
public class AlumnoInicioController {

    private final AlumnoService alumnoService;
    private final TemaService temaService;

    public AlumnoInicioController(
            AlumnoService alumnoService,
            TemaService temaService) {

        this.alumnoService = alumnoService;
        this.temaService = temaService;
    }

    @GetMapping("/inicio")
    public String inicio(
            Authentication authentication,
            Model model) {

        String username = authentication.getName();

        Alumno alumno =
                alumnoService.obtenerAlumnoPorUsername(username);

        Curso curso = alumno.getCurso();

        List<Tema> temas =
                temaService.obtenerTemasPorCurso(curso.getId());

        model.addAttribute("alumno", alumno);
        model.addAttribute("curso", curso);
        model.addAttribute("temas", temas);

        return "alumno/inicio";
    }
}
