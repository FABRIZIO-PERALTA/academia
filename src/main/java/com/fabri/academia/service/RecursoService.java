package com.fabri.academia.service;

import com.fabri.academia.domain.Recurso;
import com.fabri.academia.domain.Tema;
import com.fabri.academia.repository.RecursoRepository;
import com.fabri.academia.repository.TemaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoService {

    private final RecursoRepository recursoRepository;
    private final TemaRepository temaRepository;

    public RecursoService(
            RecursoRepository recursoRepository,
            TemaRepository temaRepository) {

        this.recursoRepository = recursoRepository;
        this.temaRepository = temaRepository;
    }

    @Transactional
    public Recurso crearRecurso(Recurso recurso, Long temaId) {

        Tema tema = temaRepository.findById(temaId)
                .orElseThrow(() ->
                        new IllegalArgumentException("El tema no existe"));

        recurso.setTema(tema);

        return recursoRepository.save(recurso);
    }

    public List<Recurso> obtenerRecursos() {
        return recursoRepository.findAll();
    }

    public Optional<Recurso> buscarPorId(Long id) {
        return recursoRepository.findById(id);
    }
}