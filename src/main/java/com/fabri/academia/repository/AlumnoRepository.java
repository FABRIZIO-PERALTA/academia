package com.fabri.academia.repository;

import com.fabri.academia.domain.Alumno;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    Optional<Alumno> findByDni(String dni);
}
