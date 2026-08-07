package com.fabri.academia.domain;

import com.fabri.academia.domain.enums.TipoRecurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recursos")
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tema_id")
    private Tema tema;
    private String titulo;
    private String url;
    @Enumerated(EnumType.STRING)
    private TipoRecurso tipo;


}
