package com.fabri.academia.service;

import com.fabri.academia.domain.Curso;
import com.fabri.academia.domain.Tema;
import com.fabri.academia.repository.CursoRepository;
import com.fabri.academia.repository.TemaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TemaService {

    private final TemaRepository temaRepository;
    private final CursoRepository cursoRepository;

    public TemaService(
            TemaRepository temaRepository,
            CursoRepository cursoRepository) {

        this.temaRepository = temaRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public Tema crearTema(Tema tema, Long cursoId) {

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() ->
                        new IllegalArgumentException("El curso no existe"));

        tema.setCurso(curso);

        return temaRepository.save(tema);
    }

    public List<Tema> obtenerTemas() {
        return temaRepository.findAll();
    }

    public Optional<Tema> buscarPorId(Long id) {
        return temaRepository.findById(id);
    }
}
