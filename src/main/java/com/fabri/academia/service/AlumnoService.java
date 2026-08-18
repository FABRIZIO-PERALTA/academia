package com.fabri.academia.service;

import com.fabri.academia.domain.Alumno;
import com.fabri.academia.domain.Curso;
import com.fabri.academia.domain.Usuario;
import com.fabri.academia.repository.AlumnoRepository;
import com.fabri.academia.repository.CursoRepository;
import com.fabri.academia.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.alumnoRepository = alumnoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public Alumno crearAlumno(Alumno alumno, Long cursoId) {

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() ->
                        new IllegalArgumentException("El curso no existe"));

        alumno.setCurso(curso);

        return alumnoRepository.save(alumno);
    }

    public List<Alumno> obtenerAlumnos() {
        return alumnoRepository.findAll();
    }

    public Optional<Alumno> buscarPorId(Long id) {
        return alumnoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Alumno obtenerAlumnoPorUsername(String username) {

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuario no encontrado"));

        Alumno alumno = usuario.getAlumno();

        if (alumno == null) {
            throw new IllegalStateException(
                    "El usuario no tiene un alumno asociado"
            );
        }

        return alumno;
    }

    @Transactional(readOnly = true)
    public List<Alumno> obtenerAlumnosPorCurso(Long cursoId) {
        return alumnoRepository.findByCursoId(cursoId);
    }

}
