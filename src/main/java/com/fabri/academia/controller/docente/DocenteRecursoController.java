package com.fabri.academia.controller.docente;

import com.fabri.academia.domain.Recurso;
import com.fabri.academia.domain.enums.TipoRecurso;
import com.fabri.academia.service.CursoService;
import com.fabri.academia.service.RecursoService;
import com.fabri.academia.service.TemaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/docente/recursos")
public class DocenteRecursoController {

    private final CursoService cursoService;
    private final TemaService temaService;
    private final RecursoService recursoService;

    public DocenteRecursoController(
            CursoService cursoService,
            TemaService temaService,
            RecursoService recursoService) {

        this.cursoService = cursoService;
        this.temaService = temaService;
        this.recursoService = recursoService;
    }

    @GetMapping("/nuevo")
    public String nuevoRecurso(
            @RequestParam(required = false) Long cursoId,
            Model model) {

        model.addAttribute(
                "cursos",
                cursoService.obtenerCursos()
        );

        model.addAttribute(
                "recurso",
                new Recurso()
        );

        model.addAttribute(
                "tiposRecurso",
                TipoRecurso.values()
        );

        if (cursoId != null) {

            model.addAttribute(
                    "cursoSeleccionado",
                    cursoService.buscarPorId(cursoId)
                            .orElseThrow(() ->
                                    new IllegalArgumentException(
                                            "El curso no existe"
                                    )
                            )
            );

            model.addAttribute(
                    "temas",
                    temaService.obtenerTemasPorCurso(cursoId)
            );
        }

        return "docente/recursos/nuevo";
    }

    @PostMapping("/nuevo")
    public String crearRecurso(
            Recurso recurso,
            @RequestParam Long temaId) {

        recursoService.crearRecurso(recurso, temaId);

        return "redirect:/docente/recursos/creado";
    }

    @GetMapping("/creado")
    public String recursoCreado() {
        return "docente/recursos/creado";
    }
}
