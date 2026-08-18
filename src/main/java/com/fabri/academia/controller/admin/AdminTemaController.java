package com.fabri.academia.controller.admin;

import com.fabri.academia.domain.Tema;
import com.fabri.academia.domain.enums.Dificultad;
import com.fabri.academia.service.CursoService;
import com.fabri.academia.service.TemaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/temas")
public class AdminTemaController {

    private final TemaService temaService;
    private final CursoService cursoService;

    public AdminTemaController(
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

        return "admin/temas/nuevo";
    }

    @PostMapping("/nuevo")
    public String crearTema(
            Tema tema,
            @RequestParam Long cursoId) {

        temaService.crearTema(tema, cursoId);

        return "redirect:/admin/inicio";
    }
}
