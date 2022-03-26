package com.example.authapi.config.security.token.filter;

import com.example.authapi.comum.exception.model.ValidacaoException;
import com.example.authapi.config.security.token.service.TokenService;
import com.example.authapi.usuario.model.Usuario;
import com.example.authapi.usuario.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.authapi.comum.exception.enums.EErrors.USUARIO_NAO_ENCONTRADO;

public class TokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public TokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var token = getToken(request);
        if (tokenService.isTokenValido(token)) {
            autenticar(token);
        }
        filterChain.doFilter(request, response);
    }

    private void autenticar(String token) {
        var usuario = findUsuarioById(tokenService.getUsuarioId(token));
        var auth = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private Usuario findUsuarioById(Integer usuarioId) {
        return usuarioRepository
                .findById(usuarioId)
                .orElseThrow(() -> new ValidacaoException(USUARIO_NAO_ENCONTRADO.getDescricao()));
    }

    private String getToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if (isTokenInvalido(token)) {
            return null;
        }
        return token.split(" ")[1];
    }

    private boolean isTokenInvalido(String token) {
        return ObjectUtils.isEmpty(token) || !token.startsWith("Bearer");
    }
}
