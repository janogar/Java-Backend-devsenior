package com.dev.senior.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dev.senior.model.Reserva;

@Repository  
public class ReservaRepository {

    //Atributos
    private List<Reserva> reservas = new ArrayList<>();

    //Metodos
    public List<Reserva> findAll(){
        return reservas;
    }
 
    public Reserva findById(Long id) {
           
        for (Reserva reserva: reservas ){
             if(reserva.getId().equals(id)) {
                return reserva;
             }
        }
 
        return null;
    }

    public Reserva save(Reserva reserva){
        reservas.add(reserva);
        return reserva;
    }

    public boolean existsReserva(Reserva reserva) {
        return reservas.contains(reserva);
    }

    public boolean update(Reserva actualizacionReserva) {
        for(int i=0;i <  reservas.size(); i++){
            if (reservas.get(i).getId().equals(actualizacionReserva.getId())){
                reservas.set(i, actualizacionReserva); 
                return true;
            }
        }
        return false;
    }

    public boolean delete(Long id) {
        for (int i = 0; i < reservas.size(); i++){
            if (reservas.get(i).getId().equals(id)) {

                reservas.remove(i);
                return true;
            } 
        }
        return false;
    }

    public boolean existBySalaAndHora(String sala, String hora){
        for(Reserva reserva: reservas){
            if (reserva.getSala().equals(sala) && reserva.getHora().equals(hora)) {
                return true; 
            }
        }
        return false;
    }
}
