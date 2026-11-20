package com.dev.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration 
public class SecurityConfig {


    private final JwtAuthenticationFilter jwtAuthFilter;
    
    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http
             .csrf(csrf -> csrf.disable())
             //definimos las rutas que requieren autentificacion
             .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/login").permitAll()
                    .requestMatchers(HttpMethod.GET,"/api/products/**").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.POST,"api/products/public").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/products/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT,"/api/products/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE,"/api/products/**").hasRole("ADMIN")
                    .anyRequest().permitAll()
             )
             // Le decimos a Spring que ejecuta este filtro antes del uso Password authentificacions
             .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

             return http.build();
    }

    @Bean 
    public UserDetailsService userDetailsService(){
        UserDetails admin = User.builder()
                            .username("admin")
                            .password("{noop}1234")
                            .roles("ADMIN")
                            .build();

        UserDetails usuario = User.builder()
                                .username("usuario")
                                .password("{noop}1234")
                                .roles("USER")
                                .build();

        return new InMemoryUserDetailsManager(admin, usuario);
    }
}
