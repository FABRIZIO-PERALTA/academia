package com.fabri.academia.service;

import com.fabri.academia.domain.Curso;
import com.fabri.academia.domain.Docente;
import com.fabri.academia.repository.CursoRepository;
import com.fabri.academia.repository.DocenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {

    private final DocenteRepository docenteRepository;
    private final CursoRepository cursoRepository;

    public DocenteService(
            DocenteRepository docenteRepository,
            CursoRepository cursoRepository) {

        this.docenteRepository = docenteRepository;
        this.cursoRepository = cursoRepository;
    }

    public Docente crearDocente(Docente docente) {

        if (docente.getCursos() == null) {
            docente.setCursos(new ArrayList<>());
        }

        return docenteRepository.save(docente);
    }

    public List<Docente> obtenerDocentes() {
        return docenteRepository.findAll();
    }

    public Optional<Docente> buscarPorId(Long id) {
        return docenteRepository.findById(id);
    }

    @Transactional
    public Docente asignarCurso(Long docenteId, Long cursoId) {

        Docente docente = docenteRepository.findById(docenteId)
                .orElseThrow(() ->
                        new IllegalArgumentException("El docente no existe"));

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() ->
                        new IllegalArgumentException("El curso no existe"));

        if (!docente.getCursos().contains(curso)) {
            docente.getCursos().add(curso);
        }

        return docente;
    }

    public Docente obtenerDocentePorUsername(String username) {

        return docenteRepository
                .findByUsuarioUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No existe un docente asociado a este usuario"
                        )
                );
    }

}
