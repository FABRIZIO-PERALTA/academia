package com.fabri.academia.repository;

import com.fabri.academia.domain.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecursoRepository
        extends JpaRepository<Recurso, Long> {

    List<Recurso> findByTemaId(Long temaId);
}
