package com.fabri.academia.domain;

import com.fabri.academia.domain.enums.Dificultad;
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
@Table(name = "temas")
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @Enumerated(EnumType.STRING)
    private Dificultad dificultad;
    @OneToMany(mappedBy = "tema")
    private List<Recurso> recursos;


}
