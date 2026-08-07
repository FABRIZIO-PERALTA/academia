package com.fabri.academia.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreCurso;
    @OneToMany(mappedBy = "curso")
    private List<Alumno> alumnos;
    @OneToMany(mappedBy = "curso")
    private List<Tema> temas;
    @ManyToMany(mappedBy = "cursos")
    private List<Docente> docentes;


}
