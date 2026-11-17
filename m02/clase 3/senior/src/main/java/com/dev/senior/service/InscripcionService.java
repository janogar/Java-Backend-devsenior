package com.dev.senior.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.senior.model.dto.InscripcionRequestDTO;
import com.dev.senior.model.dto.InscripcionResponseDTO;
import com.dev.senior.model.entity.Curso;
import com.dev.senior.model.entity.Estudiante;
import com.dev.senior.model.entity.Inscripcion;
import com.dev.senior.repository.CursoRepository;
import com.dev.senior.repository.EstudianteRepository;
import com.dev.senior.repository.InscripcionRepository;

import jakarta.transaction.Transactional;

@Service
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;


    public InscripcionService(
            InscripcionRepository inscripcionRepository,
            EstudianteRepository estudianteRepository,
            CursoRepository cursoRepository) {
        this.inscripcionRepository = inscripcionRepository;
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional 
    public InscripcionResponseDTO crearInscripcion(InscripcionRequestDTO request){
        Estudiante estudiante = estudianteRepository.findById(request.getEstudianteId())
                                    .orElseThrow(()-> new IllegalArgumentException
                                    ("Estudiante no encontrado"));

        Curso curso = cursoRepository.findById(request.getCursoId())
                            .orElseThrow(()-> new IllegalArgumentException
                            ("Curso no encontrado"));
        if (inscripcionRepository.existsByEstudiante_IdAndCurso_Id(request.getEstudianteId(), request.getCursoId())) {
            throw new IllegalArgumentException("El estudiante ya esta matriculado a este curso");
        }
        
        Inscripcion inscripcion = new Inscripcion(estudiante,curso);

        Inscripcion creada =  inscripcionRepository.save(inscripcion);

        return convertirAResponseDTO(creada);
    }

    public List<InscripcionResponseDTO> listarInscripciones(){
        List<Inscripcion> inscripciones = inscripcionRepository.findAll();

        List<InscripcionResponseDTO> respuestas = new ArrayList<>();

        for(Inscripcion inscripcion: inscripciones){
            respuestas.add(convertirAResponseDTO(inscripcion));
        }

        return respuestas;
    }

    public List<InscripcionResponseDTO> buscarPorEstudianteId(Long estudianteId) {
        if (!estudianteRepository.existsById(estudianteId)) {
            throw new IllegalArgumentException("Estudiante no encontrado");
        }

        List<Inscripcion> inscripciones = inscripcionRepository.findByEstudiante_Id(estudianteId);

        List<InscripcionResponseDTO> respuestas = new ArrayList<>();

        for (Inscripcion inscripcion : inscripciones) {
            respuestas.add(convertirAResponseDTO(inscripcion));
        }

        return respuestas;
    }

    public List<InscripcionResponseDTO> buscarPorCursoId(Long cursoId){
        if (!cursoRepository.existsById(cursoId)) {
            throw new IllegalArgumentException("Curso no encontrado");
        }

        List<Inscripcion> inscripciones = inscripcionRepository.findByCurso_Id(cursoId);

        List<InscripcionResponseDTO> respuestas = new ArrayList<>();

        for(Inscripcion inscripcion: inscripciones){
            respuestas.add(convertirAResponseDTO(inscripcion));
        }

        return respuestas;
    }
    
    public void eliminarInscripcion(Long id){
        if(!inscripcionRepository.existsById(id)){
            throw new IllegalArgumentException("Inscripcion no encontrada");
        }

        inscripcionRepository.deleteById(id);
    }

    public InscripcionResponseDTO convertirAResponseDTO(Inscripcion inscripcion){
        return new InscripcionResponseDTO(inscripcion.getId(), inscripcion.getEstudiante().getId(), inscripcion.getEstudiante().getNombre(), inscripcion.getCurso().getId(), inscripcion.getCurso().getNombre(), inscripcion.getFechaInscripcion());
    }

}
