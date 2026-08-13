package com.fabri.academia.controller;

import com.fabri.academia.domain.Alumno;
import com.fabri.academia.domain.Curso;
import com.fabri.academia.domain.Recurso;
import com.fabri.academia.domain.Tema;
import com.fabri.academia.domain.enums.TipoRecurso;
import com.fabri.academia.service.AlumnoService;
import com.fabri.academia.service.RecursoService;
import com.fabri.academia.service.TemaService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/alumno")
public class AlumnoInicioController {

    private final AlumnoService alumnoService;
    private final RecursoService recursoService;
    private final TemaService temaService;

    public AlumnoInicioController(AlumnoService alumnoService, RecursoService recursoService, TemaService temaService) {
        this.alumnoService = alumnoService;
        this.recursoService = recursoService;
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

    @GetMapping("/temas/{temaId}")
    public String verTema(
            @PathVariable Long temaId,
            Model model) {

        Tema tema = temaService.buscarPorId(temaId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El tema no existe"
                        )
                );

        List<Recurso> recursos =
                recursoService.obtenerRecursosPorTema(temaId);

        List<Recurso> videos = recursos.stream()
                .filter(r -> r.getTipo() == TipoRecurso.VIDEO)
                .toList();

        List<Recurso> actividades = recursos.stream()
                .filter(r -> r.getTipo() == TipoRecurso.ACTIVIDAD)
                .toList();

        List<Recurso> juegos = recursos.stream()
                .filter(r -> r.getTipo() == TipoRecurso.JUEGO)
                .toList();

        model.addAttribute("tema", tema);
        model.addAttribute("videos", videos);
        model.addAttribute("actividades", actividades);
        model.addAttribute("juegos", juegos);

        return "alumno/tema";
    }
}
