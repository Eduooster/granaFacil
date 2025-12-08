package org.example.granafacil.infraestructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.granafacil.infraestructure.persistence.entites.UsuarioEntity;
import org.example.granafacil.infraestructure.persistence.repositories.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenServiceAdapter tokenServiceAdapter;
    private final UsuarioRepository usuarioRepository;


    public SecurityFilter(TokenServiceAdapter tokenServiceAdapter, UsuarioRepository usuarioRepository) {
        this.tokenServiceAdapter = tokenServiceAdapter;
        this.usuarioRepository = usuarioRepository;

    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        logger.info("entrando no doFilter");
        logger.info(request.getHeader("Authorization"));


        var tokenJWT = recuperarToken(request);
        logger.info("tokenJWT: " + tokenJWT);

        if(tokenJWT != null) {

            var subject = tokenServiceAdapter.getSubject(tokenJWT);
            logger.info("subject: " + subject);
            UsuarioEntity usuario = usuarioRepository.findById(Long.valueOf(subject))
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
            logger.info("usuario: " + usuario);

            var authentication = new UsernamePasswordAuthenticationToken(
                    usuario,
                    null,
                    usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }
}


