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

        Optional<Alumno> alumnoExistente =
                alumnoRepository.findByDni(alumno.getDni());

        if (alumnoExistente.isPresent()) {

            Alumno existente = alumnoExistente.get();

            throw new IllegalArgumentException(
                    "Ja existeix l'alumne "
                            + existente.getNombre()
                            + " "
                            + existente.getApellido()
                            + " amb aquest DNI. Pertany al curs "
                            + existente.getCurso().getNivel()
                            + "."
            );
        }

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El curs seleccionat no existeix"
                        )
                );

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

    @Transactional
    public void eliminarAlumno(Long id) {

        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("El alumno no existe"));

        Usuario usuario = alumno.getUsuario();

        alumnoRepository.delete(alumno);

        if (usuario != null) {
            usuarioRepository.delete(usuario);
        }
    }

}
