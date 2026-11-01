package com.devsenior.JpaDemo.repository;

import com.devsenior.JpaDemo.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
;

//JPA Repository ya trae un CRUD completo sin escribir nada (ni codigo, java, ni codigo SQL)
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    List<Persona> findByNombre(String nombre);

    List<Persona> findByNombreContaining(String nombre);

    Persona findByEmail(String email);

    List<Persona> findByTelefonoStartingWith(String telefono);
}
