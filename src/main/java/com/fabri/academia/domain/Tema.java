package com.fabri.academia.domain;

import com.fabri.academia.domain.enums.Dificultad;

import java.util.List;

public class Tema {

    private Long id;
    private String titulo;
    private Curso curso;
    private Dificultad dificultad;
    private List<Recurso> recursos;
    private List<Docente> docentes;
}
