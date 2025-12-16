package org.example.granafacil.infraestructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.granafacil.infraestructure.persistence.entites.UsuarioEntity;
import org.example.granafacil.infraestructure.persistence.repositories.UsuarioRepositoryImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenServiceAdapter tokenServiceAdapter;
    private final UsuarioRepositoryImpl usuarioRepositoryImpl;


    public SecurityFilter(TokenServiceAdapter tokenServiceAdapter, UsuarioRepositoryImpl usuarioRepositoryImpl) {
        this.tokenServiceAdapter = tokenServiceAdapter;
        this.usuarioRepositoryImpl = usuarioRepositoryImpl;

    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        logger.info("entrando no doFilter");
        logger.info(request.getHeader("Authorization"));


        var tokenJWT = recuperarToken(request);
        logger.info("tokenJWT: " + tokenJWT);

        String path = request.getServletPath();

        logger.info("path: " + path);

        if (path.equals("/auth/refresh")) {
            filterChain.doFilter(request, response);
            return;
        }

        if(tokenJWT != null) {



            var subject = tokenServiceAdapter.getSubject(tokenJWT);
            logger.info("subject: " + subject);
            UsuarioEntity usuario = usuarioRepositoryImpl.findById(Long.valueOf(subject))
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


