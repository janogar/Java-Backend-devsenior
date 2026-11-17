package com.dev.senior.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.senior.model.entity.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion,Long> {
    List<Inscripcion> findByEstudiante_Id(Long estudianteId);

    List<Inscripcion> findByCurso_Id(Long cursoId);

    boolean existsByEstudiante_IdAndCurso_Id(Long estudianteId, Long cursoId);
}
