package com.itb.mif3an.pizzariaitaliana.config;

import com.itb.mif3an.pizzariaitaliana.exceptions.security.CustomAccessDeniedHandler;
import com.itb.mif3an.pizzariaitaliana.exceptions.security.CustomAuthenticationEntryPoint;
import com.itb.mif3an.pizzariaitaliana.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.itb.mif3an.pizzariaitaliana.model.entity.Permission.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL= {
            "/api/v1/index",
            "/imagens/**"
    };

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter,
                                 AuthenticationProvider authenticationProvider,
                                 LogoutHandler logoutHandler,
                                 CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
                                 CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
        this.logoutHandler = logoutHandler;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
               .csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(req ->
                       req
                         //  End-Points (Produto)
                       .requestMatchers(POST, "/api/v1/produto/**").hasAnyAuthority(ADMIN_CREATE.name(), FUNCIONARIO_CREATE.name())
                       .requestMatchers(PUT, "/api/v1/produto/**").hasAnyAuthority(ADMIN_UPDATE.name(), FUNCIONARIO_UPDATE.name())
                       .requestMatchers(DELETE, "/api/v1/produto/**").hasAnyAuthority(ADMIN_DELETE.name(), FUNCIONARIO_DELETE.name())
                       .requestMatchers(GET,"/api/v1/produto/**").permitAll()

                       //  End-Points (Categoria)


                       //  End-Points (Pedido)


                       .requestMatchers("/api/v1/auth/authenticate").permitAll()
                       .requestMatchers(WHITE_LIST_URL).permitAll()
                       .anyRequest()
                       .authenticated()
                    )
               .exceptionHandling( exception -> exception
                       .authenticationEntryPoint(customAuthenticationEntryPoint)
                       .accessDeniedHandler(customAccessDeniedHandler)
               )
               .sessionManagement( session -> session.sessionCreationPolicy(STATELESS))
               .authenticationProvider(authenticationProvider)
               .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
               .logout(logout ->
                           logout.logoutUrl("/api/v1/auth/logout")
                                   .addLogoutHandler(logoutHandler)
                                   .logoutSuccessHandler((request, response, authentication)-> SecurityContextHolder.clearContext())
                       );

        return http.build();
    }

}
