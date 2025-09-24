package com.itb.mif3an.pizzariaitaliana.jwt;


import com.itb.mif3an.pizzariaitaliana.model.entity.Usuario;
import com.itb.mif3an.pizzariaitaliana.token.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService, TokenRepository tokenRepository) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.tokenRepository = tokenRepository;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull  FilterChain filterChain) throws ServletException, IOException {

           final String authHeader = request.getHeader("Authorization");
           final String token;
           final String userEmail;

           if(authHeader == null || !authHeader.startsWith("Bearer ")) {
               filterChain.doFilter(request, response);
               return;
           }
           token = authHeader.substring(7);
           userEmail = jwtService.extractUsername(token);
           if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
               Usuario userDetails = (Usuario) this.userDetailsService.loadUserByUsername(userEmail);
               var isTokenValid = tokenRepository.findByToken(token)
                       .map(t -> !t.isExpired() && !t.isRevoked())
                       .orElse(false);
               if( jwtService.isTokenValid(token, userDetails) && isTokenValid) {
                   UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                           userDetails, null, userDetails.getAuthorities()
                   );
                   authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                   );
                   SecurityContextHolder.getContext().setAuthentication(authToken);
               }
           }
           filterChain.doFilter(request, response);
    }
}
