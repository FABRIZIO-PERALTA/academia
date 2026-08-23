package com.fabri.academia.controller.admin;

import com.fabri.academia.domain.Alumno;
import com.fabri.academia.service.AlumnoService;
import com.fabri.academia.service.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/alumnos")
public class AdminAlumnoController {

    private final AlumnoService alumnoService;
    private final CursoService cursoService;

    public AdminAlumnoController(
            AlumnoService alumnoService,
            CursoService cursoService) {

        this.alumnoService = alumnoService;
        this.cursoService = cursoService;
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

        return "admin/alumno/nuevo";
    }

    @PostMapping("/nuevo")
    public String crearAlumno(
            @ModelAttribute("alumno") Alumno alumno,
            @RequestParam Long cursoId,
            Model model) {

        try {

            alumnoService.crearAlumno(alumno, cursoId);

            return "redirect:/admin/alumnos/creado";

        } catch (IllegalArgumentException e) {

            model.addAttribute("error", e.getMessage());

            model.addAttribute(
                    "cursos",
                    cursoService.obtenerCursos()
            );

            return "admin/alumno/nuevo";
        }
    }

    @GetMapping("/creado")
    public String alumnoCreado() {
        return "admin/alumno/creado";
    }

}
