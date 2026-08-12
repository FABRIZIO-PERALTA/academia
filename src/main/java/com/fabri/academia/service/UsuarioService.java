package com.fabri.academia.service;

import com.fabri.academia.domain.Alumno;
import com.fabri.academia.domain.Usuario;
import com.fabri.academia.domain.enums.Rol;
import com.fabri.academia.repository.AlumnoRepository;
import com.fabri.academia.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AlumnoRepository alumnoRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            AlumnoRepository alumnoRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.alumnoRepository = alumnoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario registrarAlumno(Usuario usuario, String dni) {

        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new IllegalArgumentException(
                    "El nombre de usuario ya está registrado"
            );
        }

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException(
                    "El email ya está registrado"
            );
        }

        Alumno alumno = alumnoRepository.findByDni(dni).orElseThrow(() ->
                        new IllegalArgumentException(
                                "No existe un alumno registrado con ese DNI"
                        )
                );

        if (alumno.getUsuario() != null) {
            throw new IllegalArgumentException(
                    "Este alumno ya tiene una cuenta registrada"
            );
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        usuario.setRol(Rol.ALUMNO);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        alumno.setUsuario(usuarioGuardado);

        return usuarioGuardado;
    }
}
