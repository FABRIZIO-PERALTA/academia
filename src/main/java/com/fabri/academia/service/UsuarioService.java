package com.fabri.academia.service;

import com.fabri.academia.domain.Usuario;
import com.fabri.academia.domain.enums.Rol;
import com.fabri.academia.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario registrarAlumno(Usuario usuario) {

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

        usuario.setPassword(
                passwordEncoder.encode(usuario.getPassword())
        );

        usuario.setRol(Rol.ALUMNO);

        return usuarioRepository.save(usuario);
    }
}
