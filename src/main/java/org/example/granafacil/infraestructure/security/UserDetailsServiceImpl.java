package org.example.granafacil.infraestructure.security;

import org.example.granafacil.infraestructure.persistence.repositories.UsuarioRepositoryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioRepositoryImpl usuarioRepositoryImpl;

    public UserDetailsServiceImpl(UsuarioRepositoryImpl usuarioRepositoryImpl) {
        this.usuarioRepositoryImpl = usuarioRepositoryImpl;

    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepositoryImpl.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
