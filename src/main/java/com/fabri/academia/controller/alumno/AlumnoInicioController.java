package com.fabri.academia.controller.alumno;

import com.fabri.academia.domain.Alumno;
import com.fabri.academia.domain.Curso;
import com.fabri.academia.domain.Recurso;
import com.fabri.academia.domain.Tema;
import com.fabri.academia.domain.enums.TipoRecurso;
import com.fabri.academia.service.AlumnoService;
import com.fabri.academia.service.RecursoService;
import com.fabri.academia.service.TemaService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            Authentication authentication,
            Model model) {

        Alumno alumno =
                alumnoService.obtenerAlumnoPorUsername(
                        authentication.getName()
                );

        Tema tema = temaService.buscarPorId(temaId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El tema no existe"
                        )
                );

        if (!tema.getCurso().getId()
                .equals(alumno.getCurso().getId())) {

            throw new AccessDeniedException(
                    "No tenés permiso para acceder a este tema"
            );
        }

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

        Map<Long, String> videosEmbed = videos.stream()
                .collect(Collectors.toMap(
                        Recurso::getId,
                        video -> convertirYoutubeEmbed(video.getUrl())
                ));

        model.addAttribute("tema", tema);
        model.addAttribute("videos", videos);
        model.addAttribute("actividades", actividades);
        model.addAttribute("juegos", juegos);
        model.addAttribute("videos", videos);
        model.addAttribute("videosEmbed", videosEmbed);

        return "alumno/tema";
    }

    private String convertirYoutubeEmbed(String url) {

        if (url == null || url.isBlank()) {
            return null;
        }

        // https://www.youtube.com/watch?v=VIDEO_ID
        if (url.contains("youtube.com/watch?v=")) {

            String id = url.substring(
                    url.indexOf("v=") + 2
            );

            // Elimina parámetros adicionales:
            // &t=20s, &list=..., etc.
            if (id.contains("&")) {
                id = id.substring(0, id.indexOf("&"));
            }

            return "https://www.youtube.com/embed/" + id;
        }

        // https://youtu.be/VIDEO_ID
        if (url.contains("youtu.be/")) {

            String id = url.substring(
                    url.indexOf("youtu.be/") + 9
            );

            if (id.contains("?")) {
                id = id.substring(0, id.indexOf("?"));
            }

            return "https://www.youtube.com/embed/" + id;
        }

        // Por si ya ingresaron una URL embed
        if (url.contains("youtube.com/embed/")) {
            return url;
        }

        return null;
    }
}
