package com.fabri.academia;

import com.fabri.academia.domain.Usuario;
import com.fabri.academia.domain.enums.Rol;
import com.fabri.academia.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class AcademiaApplication {

	public static void main(String[] args) {

		SpringApplication.run(AcademiaApplication.class, args);
	}

	@Bean
    CommandLineRunner cargarAdministradores(
			UsuarioRepository usuarioRepository,
			PasswordEncoder passwordEncoder) {

		return args -> {

			if (!usuarioRepository.existsByUsername("admin1")) {

				Usuario admin1 = new Usuario();
				admin1.setUsername("admin1");
				admin1.setEmail("admin1@academia.com");
				admin1.setPassword(
						passwordEncoder.encode("claveAdmin1")
				);
				admin1.setRol(Rol.ADMIN);

				usuarioRepository.save(admin1);
			}

			if (!usuarioRepository.existsByUsername("admin2")) {

				Usuario admin2 = new Usuario();
				admin2.setUsername("admin2");
				admin2.setEmail("admin2@academia.com");
				admin2.setPassword(
						passwordEncoder.encode("claveAdmin2")
				);
				admin2.setRol(Rol.ADMIN);

				usuarioRepository.save(admin2);
			}
		};
	}

}
