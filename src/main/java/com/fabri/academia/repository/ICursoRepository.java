package com.fabri.academia.repository;


import com.fabri.academia.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICursoRepository extends JpaRepository<Curso, Long> {
}
