package com.fabri.academia;

import com.fabri.academia.domain.Tema;
import com.fabri.academia.domain.enums.Dificultad;
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
    CommandLineRunner cargarTemasPrueba(
			TemaService temaService) {

		return args -> {

			Tema tema1 = new Tema();
			tema1.setTitulo("Colores");
			tema1.setDificultad(Dificultad.PRINCIPIANTE);

			temaService.crearTema(tema1, 7L);


			Tema tema2 = new Tema();
			tema2.setTitulo("La ropa");
			tema2.setDificultad(Dificultad.PRINCIPIANTE);

			temaService.crearTema(tema2, 7L);


			Tema tema3 = new Tema();
			tema3.setTitulo("Partes del cuerpo");
			tema3.setDificultad(Dificultad.PRINCIPIANTE);

			temaService.crearTema(tema3, 7L);
		};
	}

}
