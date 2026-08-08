package com.fabri.academia.service;

import com.fabri.academia.domain.Alumno;
import com.fabri.academia.domain.Curso;
import com.fabri.academia.repository.AlumnoRepository;
import com.fabri.academia.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final CursoRepository cursoRepository;

    public AlumnoService(
            AlumnoRepository alumnoRepository,
            CursoRepository cursoRepository) {

        this.alumnoRepository = alumnoRepository;
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
}
