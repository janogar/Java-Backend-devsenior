package com.dev.security;

import java.io.IOException;
import java.nio.file.DirectoryStream.Filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dev.security.security.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final ProductController productController;
    private final AuthController authController;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService, AuthController authController, ProductController productController) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.authController = authController;
        this.productController = productController;
    }

    @Override 
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    )throws ServletException, IOException{
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;


        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        
        jwt = authHeader.substring(7);

        if(jwtService.isTokenValid(jwt)){
            username = jwtService.getUserNameFromToken(jwt);
            if(username != null && SecurityContextHolder.getContext().getAuthentication()== null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //buscamos los detalles y roles completos del usuario.
                UsernamePasswordAuthenticationToken authToken = new 
                UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities
                ());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
        
    }
}
