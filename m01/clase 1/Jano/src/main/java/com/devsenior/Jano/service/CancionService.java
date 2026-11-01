 package com.devsenior.Jano.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsenior.Jano.model.Cancion;
import com.devsenior.Jano.reoository.CancionRepository;

@Service 
public class CancionService {

    private final CancionRepository canciones;

    public CancionService(CancionRepository canciones) {
        this.canciones = canciones;
    }

    public Cancion obtenerCancionDestacada() {
        return canciones.obtenerCanciones().get(0);
    }
    
}
