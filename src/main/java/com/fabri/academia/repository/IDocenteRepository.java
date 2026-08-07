package com.fabri.academia.repository;


import com.fabri.academia.domain.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDocenteRepository extends JpaRepository<Docente, Long> {
}
