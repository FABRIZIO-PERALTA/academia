package com.fabri.academia.repository;

import com.fabri.academia.domain.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecursoRepository extends JpaRepository<Recurso, Long> {
}
