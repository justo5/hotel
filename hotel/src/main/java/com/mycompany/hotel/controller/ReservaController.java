
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.controller;

import com.mycompany.hotel.dto.ReservaDTO;
import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.model.Reserva;
import com.mycompany.hotel.model.Pasajero;
import com.mycompany.hotel.model.Habitacion;
import com.mycompany.hotel.service.ReservaService;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservaController implements Icrud<ReservaDTO> {

    private ReservaService reservaService;

    public ReservaController() {
        this.reservaService = new ReservaService();
    }

   
    
    

    @Override
    public void crear(ReservaDTO dato) throws SQLException {
        reservaService.crearReserva(dato);
    }

    @Override
    public void actualizar(ReservaDTO dato, int id) throws SQLException {
        reservaService.actualizarReserva(dato, id);
    }

    @Override
    public void borrar(ReservaDTO dato) throws SQLException {
        reservaService.borrarReserva(dato);
    }

    @Override
    public void borrar(int id) throws SQLException {
       reservaService.borrarPorId(id);
    }

    @Override
    public ReservaDTO recuperarPorId(int id) throws SQLException {
        ReservaDTO reservaDTO;
        reservaDTO = reservaService.recuperarPorId(id);
        return reservaDTO;
    }

    @Override
    public List<ReservaDTO> recuperarTodos() throws SQLException {
        List<ReservaDTO> reservasDTO;
        reservasDTO = reservaService.recuperarTodos();
        return reservasDTO;
    }
    
   
}

