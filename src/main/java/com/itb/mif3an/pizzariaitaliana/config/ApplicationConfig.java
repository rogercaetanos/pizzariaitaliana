package com.itb.mif3an.pizzariaitaliana.config;

import com.itb.mif3an.pizzariaitaliana.auditing.ApplicationAuditAware;
import com.itb.mif3an.pizzariaitaliana.model.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    private final UsuarioRepository usuarioRepository;

    public ApplicationConfig(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> usuarioRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Contém o objeto (usuario) autenticado : recebe o usuário do banco e um algoritmo para comparação da senha

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Método que será utilizado no login de usuário (autenticação) - trabalha com o email e a senha

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuditorAware<Long> auditorAware() {
        return new ApplicationAuditAware();
    }

}
