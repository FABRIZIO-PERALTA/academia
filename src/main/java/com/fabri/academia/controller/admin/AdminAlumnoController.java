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


    // =========================
    // ELIMINAR ALUMNO
    // =========================

    @GetMapping("/eliminar")
    public String mostrarFormularioEliminar(Model model) {

        model.addAttribute(
                "cursos",
                cursoService.obtenerCursos()
        );

        return "admin/alumno/eliminar";
    }

    @PostMapping("/eliminar")
    public String seleccionarCurso(
            @RequestParam Long cursoId,
            Model model) {

        model.addAttribute(
                "cursos",
                cursoService.obtenerCursos()
        );

        model.addAttribute(
                "cursoSeleccionado",
                cursoId
        );

        model.addAttribute(
                "alumnos",
                alumnoService.obtenerAlumnosPorCurso(cursoId)
        );

        return "admin/alumno/eliminar";
    }

    @PostMapping("/eliminar/{alumnoId}")
    public String eliminarAlumno(
            @PathVariable Long alumnoId) {

        alumnoService.eliminarAlumno(alumnoId);

        return "redirect:/admin/alumnos/eliminado";
    }

    @GetMapping("/eliminado")
    public String alumnoEliminado() {
        return "admin/alumno/eliminado";
    }

    // =========================
// ELIMINAR ALUMNO 2
// =========================

    @GetMapping("/eliminar2")
    public String mostrarFormularioEliminar2(
            @RequestParam(required = false, defaultValue = "false") boolean eliminado,
            Model model) {

        model.addAttribute(
                "cursos",
                cursoService.obtenerCursos()
        );

        model.addAttribute(
                "eliminado",
                eliminado
        );

        return "admin/alumno/eliminar2";
    }


    @PostMapping("/eliminar2")
    public String seleccionarCurso2(
            @RequestParam Long cursoId,
            Model model) {

        model.addAttribute(
                "cursos",
                cursoService.obtenerCursos()
        );

        model.addAttribute(
                "cursoSeleccionado",
                cursoId
        );

        model.addAttribute(
                "alumnos",
                alumnoService.obtenerAlumnosPorCurso(cursoId)
        );

        return "admin/alumno/eliminar2";
    }


    @PostMapping("/eliminar2/seleccionar")
    public String seleccionarAlumno2(
            @RequestParam Long alumnoId,
            @RequestParam Long cursoId,
            Model model) {

        model.addAttribute(
                "cursos",
                cursoService.obtenerCursos()
        );

        model.addAttribute(
                "cursoSeleccionado",
                cursoId
        );

        model.addAttribute(
                "alumnos",
                alumnoService.obtenerAlumnosPorCurso(cursoId)
        );

        model.addAttribute(
                "alumnoSeleccionado",
                alumnoService.buscarPorId(alumnoId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "El alumno no existe"
                                ))
        );

        return "admin/alumno/eliminar2";
    }


    @PostMapping("/eliminar2/confirmar")
    public String confirmarEliminacion2(
            @RequestParam Long alumnoId) {

        alumnoService.eliminarAlumno(alumnoId);

        return "redirect:/admin/alumnos/eliminar2?eliminado=true";
    }
}