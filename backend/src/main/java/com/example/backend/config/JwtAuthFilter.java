package com.example.backend.config;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthenticationProvider userAuthenticationProvider;

    @Override
    protected void doFilterInternal(//metoda care proceseaza cererile http
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {
        //se citește valoarea antetului Authorization din cererea HTTP
        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        //Verificarea prezentei antetului
        if (header != null) {
            String[] authElements = header.split(" ");
            //Validarea formatului antetului
            if (authElements.length == 2
                    && "Bearer".equals(authElements[0])) {
                try {//Validarea tokenului JWT
                    SecurityContextHolder.getContext().setAuthentication(
                            userAuthenticationProvider.validateToken(authElements[1]));
                } catch (RuntimeException e) {
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
