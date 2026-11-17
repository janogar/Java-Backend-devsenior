package com.dev.senior.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.senior.model.dto.EstudianteRequestDTO;
import com.dev.senior.model.dto.EstudianteResponseDTO;
import com.dev.senior.service.EstudianteService;

@RestController 
@RequestMapping("/estudiantes")
public class EstudianteController {
    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }
    
    @GetMapping 
    public List<EstudianteResponseDTO> listarEstudiantes(){
        return this.service.listarEstudiante();
    }

    @PostMapping 
    public EstudianteResponseDTO crearEstuidante(
        @RequestBody EstudianteRequestDTO request
    ){
        return this.service.crearEstudiante(request);
    }

    @PutMapping("/{id}")
    public EstudianteResponseDTO actualizarEstudiante(
        @PathVariable Long id,
        @RequestBody EstudianteRequestDTO request
    ){
        return this.service.actualizarEstudiante(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminarEstudiante(@PathVariable Long id){
        this.service.eliminarEstudiante(id);
    }
}
