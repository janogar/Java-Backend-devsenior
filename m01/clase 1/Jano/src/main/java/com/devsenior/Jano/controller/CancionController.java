package com.devsenior.Jano.controller;

import org.springframework.web.bind.annotation.RestController;

import com.devsenior.Jano.service.CancionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
public class CancionController {

    private final CancionService canciones;

    public CancionController(CancionService canciones) {
        this.canciones = canciones;
    }

    @GetMapping("/cancion")
    public String obtenerCancionDestacada() {
        return canciones.obtenerCancionDestacada().getTitulo();
    }
    
    
}
