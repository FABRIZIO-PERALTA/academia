package com.fabri.academia.controller.docente;

import com.fabri.academia.domain.Tema;
import com.fabri.academia.domain.enums.Dificultad;
import com.fabri.academia.service.CursoService;
import com.fabri.academia.service.TemaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/docente/temas")
public class DocenteTemaController {

    private final TemaService temaService;
    private final CursoService cursoService;

    public DocenteTemaController(
            TemaService temaService,
            CursoService cursoService) {

        this.temaService = temaService;
        this.cursoService = cursoService;
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

        model.addAttribute(
                "dificultades",
                Dificultad.values()
        );

        return "docente/temas/nuevo";
    }

    @PostMapping("/nuevo")
    public String crearTema(
            Tema tema,
            @RequestParam Long cursoId) {

        temaService.crearTema(tema, cursoId);

        return "redirect:/docente/inicio";
    }
}
