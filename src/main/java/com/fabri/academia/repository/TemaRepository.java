package com.fabri.academia.repository;


import com.fabri.academia.domain.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long> {
}
