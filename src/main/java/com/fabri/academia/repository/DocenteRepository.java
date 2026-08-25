package com.fabri.academia.repository;


import com.fabri.academia.domain.Docente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<Docente, Long> {


    Optional<Docente> findByUsuarioUsername(String username);
}
