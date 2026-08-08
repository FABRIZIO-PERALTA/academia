package com.fabri.academia;

import com.fabri.academia.domain.Curso;
import com.fabri.academia.domain.Recurso;
import com.fabri.academia.domain.Tema;
import com.fabri.academia.domain.enums.TipoRecurso;
import com.fabri.academia.service.CursoService;
import com.fabri.academia.service.RecursoService;
import com.fabri.academia.service.TemaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AcademiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademiaApplication.class, args);
	}

	@Bean
    CommandLineRunner probarTemaYRecurso(
			CursoService cursoService,
			TemaService temaService,
			RecursoService recursoService) {

		return args -> {

			// Crear curso
			Curso curso = new Curso();
			curso.setNombreCurso("Inglés Avanzado");

			Curso cursoGuardado = cursoService.crearCurso(curso);

			System.out.println(
					"Curso creado con id: " + cursoGuardado.getId()
			);

			// Crear tema
			Tema tema = new Tema();
			tema.setTitulo("Present Perfect");

			Tema temaGuardado =
					temaService.crearTema(
							tema,
							cursoGuardado.getId()
					);

			System.out.println(
					"Tema creado con id: " + temaGuardado.getId()
			);

			// Crear recurso
			Recurso recurso = new Recurso();
			recurso.setTitulo("Present Perfect Exercises");
			recurso.setUrl("https://www.liveworksheets.com");
			recurso.setTipo(TipoRecurso.ACTIVIDAD);

			Recurso recursoGuardado =
					recursoService.crearRecurso(
							recurso,
							temaGuardado.getId()
					);

			System.out.println(
					"Recurso creado con id: "
							+ recursoGuardado.getId()
			);

			System.out.println(
					"Tema del recurso: "
							+ recursoGuardado.getTema().getTitulo()
			);

			System.out.println(
					"Curso del tema: "
							+ recursoGuardado
							.getTema()
							.getCurso()
							.getNombreCurso()
			);
		};
	}
}
