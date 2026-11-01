package com.dev.senior.service;

import org.springframework.stereotype.Service;
import com.dev.senior.repository.ReservaRepository;
import com.dev.senior.model.Reserva;

import java.util.List;

@Service
public class ReservaService {

    //Atributos principal del service
    private final ReservaRepository reservaRepository;

    //Constructor del service
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> findAll() {
        //aqui podriamos implementar reglas: verificar autenticacion, rol del cliente, etc.
        return reservaRepository.findAll();
    }

    public Reserva findById(Long id) {
        return reservaRepository.findById(id);
    }

    public boolean save(Reserva reserva) {
        boolean salaOcupada = reservaRepository.existBySalaAndHora(reserva.getSala(), reserva.getHora());

        //logica de negocio.
        if (salaOcupada) {
            return false;
        }
        
        reservaRepository.save(reserva);
        return true;
    }

    public boolean existsReserva(Reserva reserva) {
        return reservaRepository.existsReserva(reserva);
    }

    public boolean update(Reserva actualizacionReserva) {
        return reservaRepository.update(actualizacionReserva);
    }
    
    public boolean delete(Long id) {
        return reservaRepository.delete(id);
    }




}
