package com.fabri.academia.repository;


import com.fabri.academia.domain.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemaRepository extends JpaRepository<Tema, Long> {

    List<Tema> findByCursoId(Long cursoId);

}
