package com.devsenior.Jano.reoository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.devsenior.Jano.model.Cancion;

@Repository 
public class CancionRepository {



    public List<Cancion> obtenerCanciones() {
        return List.of(new Cancion("Bohemian Rhapsody"),
                new Cancion("Stairway to Heaven"),
                new Cancion("Hotel California"));
    }
}
