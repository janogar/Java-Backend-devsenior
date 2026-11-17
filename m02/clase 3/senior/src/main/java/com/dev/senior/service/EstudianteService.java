package com.dev.senior.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.senior.model.dto.EstudianteRequestDTO;
import com.dev.senior.model.dto.EstudianteResponseDTO;
import com.dev.senior.model.entity.Estudiante;
import com.dev.senior.repository.EstudianteRepository;

@Service
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public List<EstudianteResponseDTO> listarEstudiante() {
        List<Estudiante> estudiantes = this.estudianteRepository.findAll();

        List<EstudianteResponseDTO> respuesta = new ArrayList<>();

        for (Estudiante estudiante : estudiantes) {
            respuesta.add(convertirAResponseDTO(estudiante));
        }

        return respuesta;
    }

    public EstudianteResponseDTO crearEstudiante(EstudianteRequestDTO request){
        Estudiante estudiante = new Estudiante(
            request.getNombre(),
            request.getCorreo(), null
        );

        Estudiante creado = estudianteRepository.save(estudiante);

        return convertirAResponseDTO(estudiante);
    }

    public EstudianteResponseDTO actualizarEstudiante(Long id, EstudianteRequestDTO request) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante con id: " + id + " no encontrado"));

        estudiante.setNombre(request.getNombre());
        estudiante.setCorreo(request.getCorreo());

        Estudiante estudianteActualizado = estudianteRepository.save(estudiante);
        return convertirAResponseDTO(estudianteActualizado);
    }

    public void eliminarEstudiante(Long id) {
        estudianteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante con id: " + id + " no encontrado"));

        estudianteRepository.deleteById(id);
    }

    private EstudianteResponseDTO convertirAResponseDTO (Estudiante estudiante){
        return new EstudianteResponseDTO(
            estudiante.getId(),
            estudiante.getNombre(),
            estudiante.getCorreo()
        );
    }

}
