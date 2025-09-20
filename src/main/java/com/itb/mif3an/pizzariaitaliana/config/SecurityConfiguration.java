package com.itb.mif3an.pizzariaitaliana.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL= {
            "/api/v1/index",
            "/imagens/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
               .csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(req ->
                       req.requestMatchers("/api/v1/produto/**").permitAll()

                       );


        return http.build();
    }


}
