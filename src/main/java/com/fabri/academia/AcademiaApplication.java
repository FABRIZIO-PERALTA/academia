package com.fabri.academia;

import com.fabri.academia.domain.Alumno;
import com.fabri.academia.domain.Curso;
import com.fabri.academia.repository.IAlumnoRepository;
import com.fabri.academia.repository.ICursoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AcademiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademiaApplication.class, args);
	}

	@Bean
	CommandLineRunner pruebaPersistencia(
			ICursoRepository cursoRepository,
			IAlumnoRepository alumnoRepository) {

		return args -> {

			Curso curso = new Curso();
			curso.setNombreCurso("Inglés inicial");

			cursoRepository.save(curso);

			Alumno alumno = new Alumno();
			alumno.setNombre("Juan");
			alumno.setApellido("Pérez");
			alumno.setCurso(curso);

			alumnoRepository.save(alumno);

			System.out.println("Curso guardado con id: " + curso.getId());
			System.out.println("Alumno guardado con id: " + alumno.getId());
		};
	}
}
